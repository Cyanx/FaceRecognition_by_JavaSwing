package cn.cyan.facedao;

import cn.cyan.util.FileUtil;
import com.baidu.aip.util.Base64Util;

public class FacebaiduTest {
	
	public static void main(String[] args) {


        try {
        	byte[] imgData;
			imgData = FileUtil.readFileByBytes("D:\\Java大作业\\FaceRecoDemo\\src\\main\\java\\cn\\cyan\\facedao\\images\\lisa01.jpg");
			String imgStr = Base64Util.encode(imgData);
            FacebaiduMain facedeal = new FacebaiduMain();
			/**
			 * 如果要往人脸库中加入人脸
			 * faceflag为add
			 * uid 自己设置 最好按序增加
			 * userinfo 必须填写为学生姓名
			 */
			facedeal.Faceinfomain("add", imgStr, "001", "辛璐玥");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
