package cn.cyan.view;

import cn.cyan.facedao.FacebaiduRegister;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

/**
 * @Author: Cyan
 * @Date: 2019/6/18 19:33
 * @notice: 如果学生已经注册人脸 那就是更新人脸 或者不再能更改人脸
 */
public class StudentRegister extends JFrame {
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
    private JPanel jPanelbuttons;


    public StudentRegister() {
        this.setTitle("学生人脸注册界面");
        this.setBounds(600, 200, 800, 600);
        this.setLocationRelativeTo(null);

        //修改左上角Java图标
        URL imgURL = this.getClass().getResource("/viewingImg/titleicon.png");
        System.out.println(this.getClass().getResource("/viewingImg/titleicon.png"));
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

        final Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        /**
         * panel是webcam封装好的
         */
        final WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        facebaiduRegister = new FacebaiduRegister();
        jPanel = new JPanel(new GridLayout(4,2, 10, 60));

        jLabelStudentID = new JLabel("学生学号");
        jLabelStudentName = new JLabel("学生姓名");
//        jLabelFacePath = new JLabel("人脸图片路径");
        jTextFieldStudentID = new JTextField();
        jTextFieldStudentName = new JTextField();
        jTextFieldStudentName.setSize(100, 10);
//        jTextFieldFacePath = new JTextField();
        jLabelSubmit = new JLabel("确认信息无误后上传");
        jButtonSubmit = new JButton("提交并上传");

        jPanel.add(jLabelStudentID);
        jPanel.add(jTextFieldStudentID);
        jPanel.add(jLabelStudentName);
        jPanel.add(jTextFieldStudentName);
//        jPanel.add(jLabelFacePath);
//        jPanel.add(jTextFieldFacePath);
        jPanel.add(jLabelSubmit);
        jPanel.add(jButtonSubmit);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(jPanel,BorderLayout.EAST);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);


        /**
         * 按钮触发事件
         */
        //南方panel上拍照按钮取图注册按钮人脸库注册
        final JButton button = new JButton("拍照");

        //添加 中间panel(核心panel视频流) 拍照btn 东方panel
        this.add(panel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);

        /**
         * 对象数组法 通过一个final的Object对象数组,存放需要的值
         * 传出匿名内部类的变量值
         * 以便利用图片地址
         * 资料来自 感谢！
         * https://blog.csdn.net/u010746364/article/details/50607236
         */
        final Object[] objects = new Object[1];
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {

                //实现拍照保存-------start
                String fileName = "D://Demoimg//" + System.currentTimeMillis();
                objects[0] = fileName + ".jpg";
                System.out.println(objects[0]);
                //保存路径即图片名称（不用加后缀）
                WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_JPG);

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run()
                    {
                        JOptionPane.showMessageDialog(null, "获取图片成功");
                        button.setEnabled(true);    //设置按钮可点击

                        return;
                    }
                });
                //实现拍照保存-------end

            }
        });



        //实现人脸注册
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String filename = objects[0].toString();
                    System.out.println("filename is " + filename);

                    String student_id = jTextFieldStudentID.getText();
                    String student_name = jTextFieldStudentName.getText();

                    //测试数据获取
                    System.out.println(student_id);
                    System.out.println(student_name);

                    if (student_id!=null&&student_name!=null&&filename!=null) {
                        facebaiduRegister.uploadingFace(filename, student_id, student_name);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                webcam.close();
            }
        });
    }

    public static void main(String args[]) {
        StudentRegister studentRegister = new StudentRegister();
    }

}
