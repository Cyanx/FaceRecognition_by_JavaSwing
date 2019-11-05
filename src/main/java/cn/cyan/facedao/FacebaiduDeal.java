package cn.cyan.facedao;

import cn.cyan.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 更改人脸库 组 需要修改
 * 添加人脸库时 修改add()方法（以及detect()方法）中参数         (应该是管理员界面会调用此接口)
 * 搜索人脸时 修改search()方法中参数           （考勤会调用此接口）
 * 修改与删除人脸                                （也是管理员才会调用的接口）
 */

public class FacebaiduDeal {

    /**
     * 百度云AK
     */
    private static final String API_KEY = "mBBpwKa7HOwGTwzlXim5dcUn";
    /**
     * 百度云SK
     */
    private static final String SECRET_KEY = "4ETRBMSq37nVLhbyyIFDAg2Sskhn96hF";
    /**
     * 获取access_token的接口地址
     */
    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token?";
    /**
     * 进行人脸检测的接口地址
     */
    private static final String DETECT_HOST = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
    /**
     * 进行人脸入人脸库的接口地址
     */
    private static final String FACEADD_HOST = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
    /**
     * 进行人脸搜索的接口地址
     */
    private static final String FACRENZ_HOST = "https://aip.baidubce.com/rest/2.0/face/v3/search";
    /**
     * 进行人脸更新的接口地址
     */
    private static final String FACERGX_HOST = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
    /**
     * 进行人脸删除的接口地址
     */
    private static final String FACEDELE_HOST = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
    /**
     * 进行人脸信息查询的接口地址
     */
    private static final String FACESELECT_HOST = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";

    /**
     * Gruop_id
     */
    private static final String groupid = "student_face_database";
    
	/**
     * 人脸探测 json解析搞定
     * @return 返回人脸数
	 * @throws Exception 
     */
    public String detect(String imgParam) throws Exception {
        JSONObject jsonObject = null;

        String param = "max_face_num=" + 1
                + "&face_field="
                + "age,beauty,gender,glasses,race,quality"
                + "&image=" + imgParam
                + "&image_type=BASE64";

        String accessToken = getAuth();
        String result = HttpUtil.post(DETECT_HOST, accessToken,"application/json", param);
        System.out.println(result);


        jsonObject = JSONObject.fromObject(result);
        String error_code = jsonObject.optString("error_code");

        /**
         * 划重点！！！！！！！！！
         * 写上博客
         * 去表头 也就是只取result对象 前面的都不要
         * V3返回JSON result是对象 不是数组Array
         * 所以V2文档得解析出错
         */
        if (error_code.equals("0")) {
            JSONObject jsonObjectResult = JSONObject.fromObject(jsonObject.optJSONObject("result"));

            //取result对象中的face_num对象
            int face_num;
            face_num = jsonObjectResult.optInt("face_num");
            System.out.println("face_num: " + face_num);

            //取result对象中的 face_list对象数组
            JSONArray jsonArrayFaceList = JSONArray.fromObject(jsonObjectResult.optJSONArray("face_list"));

            //取对象数组的face_token对象
            String face_token = jsonArrayFaceList.optJSONObject(0).optString("face_token");
            System.out.println("face_token: " + face_token);

            //取对象数组的 location 对象
            JSONObject jsonObjectLocation = jsonArrayFaceList.optJSONObject(0).optJSONObject("location");
            //然后解析location中的各个键值对
            double left = jsonObjectLocation.optDouble("left");
            double top = jsonObjectLocation.optDouble("top");
            double width = jsonObjectLocation.optDouble("width");
            double height = jsonObjectLocation.optDouble("height");
            int rotation = jsonObjectLocation.optInt("rotation");
            System.out.println("left: " + left);
            System.out.println("top: " + top);
            System.out.println("width: " + width);
            System.out.println("height: " + height);
            System.out.println("rotation: " + rotation);

            //取face_probability
            Double face_probability = jsonArrayFaceList.optJSONObject(0).optDouble("face_probability");

            //取angle
            JSONObject jsonObjectAngle = jsonArrayFaceList.optJSONObject(0).optJSONObject("angle");
            double yaw = jsonObjectAngle.optDouble("yaw");
            double pitch = jsonObjectAngle.optDouble("pitch");
            double roll = jsonObjectAngle.optDouble("roll");
            System.out.println("yaw: " + yaw);
            System.out.println("pitch: " + pitch);
            System.out.println("roll: " + roll);

            //取age
            double age = jsonArrayFaceList.optJSONObject(0).optDouble("age");
            System.out.println("age: " + age);
            //取beauty
            double beauty = jsonArrayFaceList.optJSONObject(0).optDouble("beauty");
            System.out.println("beauty: " + beauty);

            //取gender
            JSONObject jsonObjectGender = jsonArrayFaceList.optJSONObject(0).optJSONObject("gender");
            String type = jsonObjectGender.optString("type");
            double probability = jsonObjectGender.optDouble("probability");
            System.out.println("gender type: " + type);
            System.out.println("gender probability: " + probability);

            //取glasses
            JSONObject jsonObjectGlasses = jsonArrayFaceList.optJSONObject(0).optJSONObject("glasses");
            String glasses_type = jsonObjectGender.optString("type");
            double glasses_probability = jsonObjectGender.optDouble("probability");
            System.out.println("glasses type: " + glasses_type);
            System.out.println("glasses probability: " + glasses_probability);

            //取race
            JSONObject jsonObjectRace = jsonArrayFaceList.optJSONObject(0).optJSONObject("race");
            String race_type = jsonObjectGender.optString("type");
            double race_probability = jsonObjectGender.optDouble("probability");
            System.out.println("race type: " + race_type);
            System.out.println("race probability: " + race_probability);

            //取quality
            JSONObject jsonObjectQuality = jsonArrayFaceList.optJSONObject(0).optJSONObject("quality");
            JSONObject jsonObjectOcclusion = jsonObjectQuality.optJSONObject("occlusion");
            double left_eye = jsonObjectOcclusion.optDouble("left_eye");
            double right_eye = jsonObjectOcclusion.optDouble("right_eye");
            double nose = jsonObjectOcclusion.optDouble("nose");
            double mouth = jsonObjectOcclusion.optDouble("mouth");
            double left_cheek = jsonObjectOcclusion.optDouble("left_cheek");
            double right_cheek = jsonObjectOcclusion.optDouble("right_cheek");
            double chin_contour = jsonObjectOcclusion.optDouble("chin_contour");
            System.out.println("left_eye: " + left_eye);
            System.out.println("right_eye：" + right_eye);

            //取blur
            double blur = jsonArrayFaceList.optJSONObject(0).optDouble("blur");
            System.out.println("blur: " + blur);
            //取illumination
            double illumination = jsonArrayFaceList.optJSONObject(0).optDouble("illumination");
            //取completeness
            int completeness = jsonArrayFaceList.optJSONObject(0).optInt("competeness");



            /**
             * 测试成功的 Bean方法 以后有机会学
             */
//        try {
////            FJFaceListUtil fjFaceListUtil = JSON.parseObject(result, FJFaceListUtil.class);
////            System.out.println(JSON.toJSONString(fjFaceListUtil));
//
//        } catch (Exception e) {
//            System.out.println("解析异常");
//            e.printStackTrace();
//        }
        }

        return error_code;

    }
    
    /**
     * 人脸信息入人脸库
     * @return
     * imgParam:人脸base64串
     * uid：人脸id（例如身份证等）
     * @throws Exception 
     */
    public JSONObject faceadd(String imgParam, String uid, String userinfo) throws Exception {
        JSONObject jsonObject = null;

    	if(userinfo==null){
    		userinfo = uid;
    	}
        /**
         * 参数修改处
         */
        String param = "user_id=" + uid
                + "&group_id=" + groupid
                + "&image=" + imgParam
                + "&image_type=BASE64"
                + "&user_info=" + userinfo
                + "&action_type=REPLACE"
                + "&liveness_control=LOW";
        
        String accessToken = getAuth();
        String result = HttpUtil.post(FACEADD_HOST, accessToken, "application/json", param);
        
        jsonObject = JSONObject.fromObject(result);
            
        return jsonObject;
    }
    
    
    /**
     * 人脸信息识别，判断指定人脸是否存在库中
     * @param imgParam:人脸信息，base64转码后的串
     * @return
     */
    public JSONObject facesearch(String imgParam) {
        JSONObject jsonObject = null;
        
        try {

            /**
             * 参数修改处
             */
            String param = "&group_id_list=" + groupid//暂时 下一周改接口 方便后期查询
                    + "&image=" + imgParam
                    + "&image_type=BASE64"
                    + "&quality_control=LOW"
                    + "&liveness_control=LOW"
                    + "&max_user_num=1";
            
            String accessToken = getAuth();
            String result = HttpUtil.post(FACRENZ_HOST, accessToken, "application/json",param);
            jsonObject = JSONObject.fromObject(result);
            return jsonObject;
            
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 人脸信息库存更新
     * @param imgParam
     * @param userinfo
     * @return
     */
    public JSONObject faceupdate(String imgParam, String userinfo, String uid) {
        JSONObject jsonObject = null;
        
        try {
        	
            String param = "&user_id="+uid
            		+ "&image=" + imgParam
                    + "&image_type=BASE64"
            		+ "&user_info=" + userinfo 
            		+ "&group_id=" + groupid
                    + "&action_type=REPLACE"
                    + "&quality_control=LOW"
                    + "&liveness_control=LOW";
            
            String accessToken = getAuth();
            String result = HttpUtil.post(FACERGX_HOST, accessToken, "application/json", param);
            jsonObject = JSONObject.fromObject(result);
            return jsonObject;
            
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 指定人脸信息删除
     * @param uid
     * @return
     */
    public JSONObject facedelete(String uid, String face_token) {
    	
        JSONObject jsonObject = null;

        try {
            String param = "&user_id=" + uid
            		+ "&group_id=" + groupid
                    + "&face_token=" + face_token;

            System.out.println(param);
            
            String accessToken = getAuth();
            String result = HttpUtil.post(FACEDELE_HOST, accessToken, "application/json",param);
            jsonObject = JSONObject.fromObject(result);
            return jsonObject;
            
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据人脸查询用户信息
     * @param uid
     * @return
     */
    public JSONObject faceselect(String uid) {
    	
        JSONObject jsonObject = null;
        
        try {
            String param = "&user_id="+uid
            		+"&group_id=" + groupid;
            
            String accessToken = getAuth();
            String result = HttpUtil.post(FACESELECT_HOST, accessToken, "application/json", param);
            jsonObject = JSONObject.fromObject(result);
            
            return jsonObject;
            
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取权限token
     * @return
     */
    public  String getAuth(){
        // 获取access_token地址
        String getAccessTokenUrl = AUTH_HOST
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + API_KEY
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + SECRET_KEY;
        JSONObject jsonObject = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            jsonObject = JSONObject.fromObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
