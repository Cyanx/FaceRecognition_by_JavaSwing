package cn.cyan.facedao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @author chenxianj
 * 20180107
 *调用百度提供的人脸识别接口进行人脸信息的
 *入库、检测、识别、库中人脸修改、库中人脸
 *删除以及相关查询
 */
public class FacebaiduMain {
	

	/**
	 * chenxianj 20170117
	 *人脸信息处理的主方法：向外部提供该方法，
	 *通过传过来的标志进行相关方法的调用
	 *imgStr:人脸信息，经base64转码后的
	 *faceflag:人脸信息处理标志，如下
	 *detect:人脸信息检测
	 *add:向人脸库中增加人脸信息
	 *recognition：人脸识别，判断指定人脸是否在人脸库中
	 *update：修改库中已存在的人脸信息
	 *delete：删除库中指定人脸信息
	 *select：查询库中指定人脸信息
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	public void Faceinfomain(String faceflag,String imgStr,String uid,String userinfo) throws Exception{
		/**
		 * face flag 选择人脸处理方法detect（被包含）、add、recognition、update、delete、select（被包含）
		 * imgStr是base64编码
		 * uid 是user_id
		 * userinfo 即用户信息 默认未user_id
		 */
		try {
			//人脸处理类
			FacebaiduDeal facedeal = new FacebaiduDeal();

			/**
			 * imgStr为请求参数
			 * 人脸检测的请求参数有
			 * 必选 image （string）即图片base64编码
			 * 必选 image_type即图片类型BASE64、URL、FACE_TOKEN
			 * 非必选 face_field (string) 包括age、beauty expression face_shape gender glasses landmark
			 *landmark150 race quality eye_status emotion face_type
			 * 非必选max_face_num int32 最多处理人脸数目 默认值为1 仅检测图片中面积最大的人脸 最大值10 检测图片中面积最大的10张人脸
			 * 非必选 face_type string 人脸的类型 LIVE IDCARD WATERMARK CERT 默认live生活照
			 * 非必选 liveness string 活体控制 检测中不符合要求的人脸会被过滤 NONE 不进行控制 LOW较低的活体要求 MORMAL HIGH 默认NONE
			 */
			String imgParam = null;
			
			if(faceflag.equals("detect")||faceflag.equals("add")||faceflag.equals("search")||faceflag.equals("update")){
				
				if(imgStr==null){
					
					throw new Exception("人脸信息不能为空！");
				}
			}
			imgParam = URLEncoder.encode(imgStr, "UTF-8");
			
			//人脸检测,imgParam
			if(faceflag.equals("detect")){
				
				facedeal.detect(imgParam);
				
			}


			//人脸信息入人脸库
			if(faceflag.equals("add")){
				
				//入库之前先检测下人脸信息是否合规
				facedeal.detect(imgParam);
				
				JSONObject jsonObject = facedeal.faceadd(imgParam, uid, userinfo);
				
				if(jsonObject==null){
					
					throw new Exception("脸部信息入库失败！");
				}
				
				String errorcode = jsonObject.optString("error_code");
				
				if(errorcode.equals("0")){
					JOptionPane.showMessageDialog(null, "人脸注册成功" );

				} else {
					String errormsg = jsonObject.optString("error_msg");

					System.out.println("errormsg: "+errormsg);

					throw new Exception("脸部信息入库失败！报错信息为："+errormsg);
				}
			}

			
			//人脸信息搜索，判断指定人脸是否在人脸库中
			if(faceflag.equals("search")){
				
				JSONObject jsonjt = facedeal.facesearch(imgParam);
				String error_msg = jsonjt.optString("error_msg");
				String error_code = jsonjt.optString("error_code");

				System.out.println("errormsg:"+error_msg);

				
				if(error_code.equals("0")){
					
					JSONObject jsonObjectResult = JSONObject.fromObject(jsonjt.optJSONObject("result"));

					String face_token = jsonObjectResult.optString("face_token");

					JSONArray jsonArrayUserList = jsonObjectResult.optJSONArray("user_list");
					String group_id = jsonArrayUserList.optJSONObject(0).optString("group_id");
					String user_id = jsonArrayUserList.optJSONObject(0).optString("user_id");
					String user_info = jsonArrayUserList.optJSONObject(0).optString("user_info");
					double score = jsonArrayUserList.optJSONObject(0).optDouble("score");


					if(score<80){
						
						throw new Exception("该人脸信息在人脸库中不存在！");
					}
					System.out.println("face_token: " + face_token);
					System.out.println("group_id: "+group_id);
					System.out.println("user_info: "+user_info);
					System.out.println("scores: "+score);

					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,"以下是人脸识别返回结果信息：\n"
							+ "group_id: "+group_id + "\n" +"user_info: "+user_info + "\n" + "匹配得分: "+score );
				}else {
					System.out.println("因为某些原因查找失败");
					JOptionPane.showMessageDialog(null, "因为某些原因查找失败");

				}
			}


			//人脸信息更新
			if(faceflag.equals("update")){
				
				//入库之前先检测下人脸信息是否合规
				facedeal.detect(imgParam);
				
				JSONObject jsonObject = facedeal.faceupdate(imgParam, userinfo,uid);
				
				String errorcode = jsonObject.optString("error_code");
				
				if(errorcode == "0"){
					
					String errormsg = jsonObject.optString("error_msg");
					
					System.out.println("errormsg:"+errormsg);
				}
				
				jsonObject.optInt("log_id");
				
			}


			//人脸信息删除
			if(faceflag.equals("delete")){

				//删除之前想看是否存在这个人的信息需要获取face_token信息来删除
				JSONObject jsonObjectTest = facedeal.facesearch(imgParam);
				JSONObject jsonObjectResult = JSONObject.fromObject(jsonObjectTest.optJSONObject("result"));

				String error_code = jsonObjectTest.optString("error_code");

				if (error_code.equals("0")) {
					String face_token = jsonObjectResult.optString("face_token");

					System.out.println(face_token);


					JSONObject jsonObject = facedeal.facedelete(uid, face_token);

					String errorcode = jsonObject.optString("error_code");

					if(!errorcode.equals("0")){

						String errormsg = jsonObject.optString("error_msg");

						System.out.println("errormsg:"+errormsg);
					}

					jsonObject.optDouble("log_id");
				} else {
					System.out.println("删除失败 不存在这样的人脸");
				}


				
			}
			//人脸信息查询
			if(faceflag.equals("select")){
				JSONObject sjson = facedeal.faceselect(uid);
				
				if(sjson.optJSONObject("result")!=null){
					
					JSONObject jsonObjectd = JSONObject.fromObject(sjson.optJSONObject("result"));

					String group_id = jsonObjectd.optJSONArray("user_list").optJSONObject(0).optString("gruop_id");
					String user_info = jsonObjectd.optJSONArray("user_list").optJSONObject(0).optString("user_info");
					
					System.out.println("group_id: "+group_id);
					System.out.println("user_info: "+user_info);


					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException event) {
						event.printStackTrace();
					}

					JOptionPane.showMessageDialog(null,"以下是人脸信息查询返回结果信息：\n" + "group_id: "+group_id + "\n" +"user_info: "+user_info  );
					
				}else{
					
					String errorcode = sjson.optString("error_msg");
					
					System.out.println("errorcode:"+errorcode);
				}
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(ExceptionUtils.getStackTrace(e));
		}
	}
}
