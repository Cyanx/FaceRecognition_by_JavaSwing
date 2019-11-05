package cn.cyan.view;

import cn.cyan.facedao.FacebaiduRegister;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * @Author: Cyan
 * @Date: 2019/5/30 22:41
 * 调用facedao里面的
 */
public class FaceUploadingPage extends JFrame {

    //
    private FacebaiduRegister facebaiduRegister;
    private JPanel jPanel;
    private JLabel jLabelStudentID;
    private JLabel jLabelStudentName;
    private JLabel jLabelFacePath;
    private JTextField jTextFieldStudentID;
    private JTextField jTextFieldStudentName;
    private JTextField jTextFieldFacePath;
    private JLabel jLabelSubmit;
    private JButton jButtonSubmit;

    public FaceUploadingPage() {
        //修改左上角Java图标
        URL imgURL = this.getClass().getResource("/viewingImg/titleicon.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);
        Image image = imageIcon.getImage();
        this.setIconImage(image);

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch(Exception e) {
            //TODO exception
            e.printStackTrace();
        }
        facebaiduRegister = new FacebaiduRegister();
        jPanel = new JPanel(new GridLayout(4,2));

        jLabelStudentID = new JLabel("学生学号");
        jLabelStudentName = new JLabel("学生姓名");
        jLabelFacePath = new JLabel("人脸图片路径");
        jTextFieldStudentID = new JTextField();
        jTextFieldStudentName = new JTextField();
        jTextFieldFacePath = new JTextField();
        jLabelSubmit = new JLabel("确认信息无误后上传");
        jButtonSubmit = new JButton("提交并上传");

        jPanel.add(jLabelStudentID);
        jPanel.add(jTextFieldStudentID);
        jPanel.add(jLabelStudentName);
        jPanel.add(jTextFieldStudentName);
        jPanel.add(jLabelFacePath);
        jPanel.add(jTextFieldFacePath);
        jPanel.add(jLabelSubmit);
        jPanel.add(jButtonSubmit);


        this.setTitle("人脸信息录入界面");
        this.setBounds(600, 200, 720, 320);
        this.setLocationRelativeTo(null);
        this.add(jPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Event();

    }

    public void Event() {
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String student_id = jTextFieldStudentID.getText();
                String student_name = jTextFieldStudentName.getText();
                String filePath = jTextFieldFacePath.getText();

                //测试数据获取
                System.out.println(student_id);
                System.out.println(student_name);
                System.out.println(filePath);

//                if (student_id!=null&&student_name!=null&&filePath!=null) {
//                    facebaiduRegister.uploadingFace(filePath, student_id, student_name);
//                }

            }
        });
    }

    public static void main(String[] args) {
        FaceUploadingPage faceUploadingPage = new FaceUploadingPage();
    }
}
