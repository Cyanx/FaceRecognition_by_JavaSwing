package cn.cyan.view;

/**
 * @Author: Cyan
 * @Date: 2019/5/19 22:39
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * 初始化页面 展示系统图片 logo 等信息
 * 用户选择 继续 或者 退出本系统
 * 总结null布局  和 setComponentZOrder()的使用方法
 *
 * 关于IntelliJ IDEA如何生成exe程序
 * https://blog.csdn.net/qq_29496057/article/details/53333419
 *
 * 生成jar包 生成可执行文件的图片无法显示问题
 * https://phl.iteye.com/blog/1990525
 * help me a lot
 */
public class InitializingPage extends JFrame {

    private static final long serialVersionUID = 1L;
    Point pressedPoint;

    public InitializingPage() {
        //设置窗口背景色
        this.getContentPane().setBackground(new Color(222, 221, 226));
        //取消窗体修饰效果
        this.setUndecorated(true);
        //设置窗体透明度
        this.setOpacity(0.99f);

        //窗体使用绝对布局
        this.getContentPane().setLayout(null);
        //在后面用了 这里用 setBounds后没有效果 要在setBounds后面使用才有效果
//        //窗体居中
//        this.setLocationRelativeTo(null);
        //使窗体最顶层显示
//        this.setAlwaysOnTop(true);
        //修改左上角Java图标
        URL imgURLIcon = this.getClass().getResource("/viewingImg/titleicon.png");
        ImageIcon imageIconIcon = new ImageIcon(imgURLIcon);
        Image image = imageIconIcon.getImage();
        this.setIconImage(image);
        this.setTitle("人脸识别考勤系统");



        JButton buttonExit = new JButton("退出");
        JButton buttonContinue = new JButton("继续");

        /**
         * 给退出button添加监听事件
         */
        buttonExit.addActionListener(new ActionListener() {
//            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * 给继续button添加监听事件
         */
        buttonContinue.addActionListener(new ActionListener() {
//            @Override
            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
                //销毁或者设置窗体不可见
                dispose();
                new LoginPage();

            }
        });

        buttonExit.setBounds(1080, 700, 70, 30);
        buttonContinue.setBounds(990, 700, 70, 30);
        buttonExit.setFont(new Font("微软雅黑", Font.BOLD, 15));
        buttonContinue.setFont(new Font("微软雅黑", Font.BOLD, 15));
        buttonExit.setForeground(Color.white);
        buttonContinue.setForeground(Color.white);
        /**如何设置按钮透明https://blog.csdn.net/xietansheng/article/details/74363221**/
        buttonExit.setContentAreaFilled(false);
        buttonContinue.setContentAreaFilled(false);
        buttonExit.setFocusPainted(false);
        buttonContinue.setFocusPainted(false);




        this.add(buttonExit);
        this.add(buttonContinue);
        this.setComponentZOrder(buttonExit, 1);
        this.setComponentZOrder(buttonContinue, 2);

        JTextArea textArea = new JTextArea();
        textArea.setBackground(null);
        //设置文本背景透明
        textArea.setOpaque(false);
        //移除焦点
        textArea.setEditable(false);

        textArea.setText("作者：\n" + "Cyan\n" + "日期：\n" + "2019年6月");
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textArea.setBounds(50, 600, 160, 100);
        textArea.setForeground(Color.white);

        this.add(textArea);
//        this.getContentPane().add(textArea);
        this.setComponentZOrder(textArea, 3);

        /**人脸识别考勤系统**/
        JTextField jTextField = new JTextField();
        jTextField.setBackground(null);
        jTextField.setBorder(new EmptyBorder(0, 0, 0, 0));  //设置无边框   setBorder(null);这种可以,但是如果updateUI后会重新出新边框。 可以用setBorder(new EmptyBorder(0,0,0,0);来设置
        jTextField.setOpaque(false);
        jTextField.setEditable(false);
        jTextField.setForeground(Color.white);
//        jTextField.setEnabled(false);  //设置以后字体颜色默认 不能设置自己颜色
        jTextField.setText("人脸识别考勤系统");
        jTextField.setFont(new Font("微软雅黑", Font.BOLD, 88));
        jTextField.setBounds(50, 36, 800, 200);


        this.add(jTextField);
        this.setComponentZOrder(jTextField, 4);


//使用标签存放logo
        JLabel jLabelLogo = new JLabel();
        //关于路径问题参考 https://blog.csdn.net/sinat_31719925/article/details/51604650
        //width 1159   height 745
        //viewingImg viewingImg/index6.png
//        ImageIcon imageIcon = new ImageIcon("src/viewingImg/index6.png");//cn/cyan/view/viewingImg/lisa01.jpg src/viewingImg/index6.png
        URL imgURL = this.getClass().getResource("/viewingImg/index6.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);
        System.out.println(imgURL);
        System.out.println(this.getClass().getResource("/viewingImg/index6.png"));
        jLabelLogo.setIcon(imageIcon);
        jLabelLogo.setBounds(0, 0, 1159, 740);
        jLabelLogo.setOpaque(false);

        //加载进 窗体
//        this.getContentPane().add(jLabelLogo);
        this.add(jLabelLogo);
        this.setComponentZOrder(jLabelLogo, 5);


        /**
         * 窗体鼠标移动事件
         */
        this.addMouseListener(new MouseAdapter() {
            @Override
            //鼠标按下事件
            public void mousePressed(MouseEvent e) {
                //记录鼠标坐标
                pressedPoint = e.getPoint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            //鼠标拖拽事件
            public void mouseDragged(MouseEvent e) {
                //获取当前坐标
                Point point = e.getPoint();
                //获取窗体坐标
                Point locationpoint = getLocation();

                int x = locationpoint.x + point.x - pressedPoint.x;
                int y = locationpoint.y + point.y - pressedPoint.y;
                //改变窗体位置
                setLocation(x, y);
            }
        });


        this.setTitle("");
        this.setBounds(240, 80, 1159, 740);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new InitializingPage();
    }

}
