package cn.cyan.facedao;

import cn.cyan.util.FileUtil;
import com.baidu.aip.util.Base64Util;

/**
 * @Author: Cyan
 * @Date: 2019/5/30 22:45
 * 用来注册上传人脸到百度平台人脸数据库
 */
public class FacebaiduRegister {

    public void uploadingFace(String filepath, String face_student_id, String face_student_name) {
        try {
            System.out.println("filename is " + filepath);
            System.out.println(face_student_id);
            System.out.println(face_student_name);

            byte[] imgData;
            imgData = FileUtil.readFileByBytes(filepath);
            String imgStr = Base64Util.encode(imgData);
            FacebaiduMain facedeal = new FacebaiduMain();

            facedeal.Faceinfomain("add", imgStr, face_student_id, face_student_name);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
