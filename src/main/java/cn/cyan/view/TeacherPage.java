package cn.cyan.view;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @Author: Cyan
 * @Date: 2019/5/20 8:10
 * 考勤入口 和基本信息修改入口 和考勤数据查看 与 特殊情况 如请假信息添加
 */
public class TeacherPage extends JFrame {

//    private JFrame jFrameTeacher;
    private JPanel jPanelTeacher;
//    private JPanel jPanelBasicInfo;
//    private JPanel jPanelAttendanceChecking;
//    private JPanel jPanelAttendanceData;
    private JLabel jLabelBasicInfo;
    private JLabel jLabelBasicInfoText;
    private JLabel jLabelAttendanceChecking;
    private JLabel jLabelAttendanceCheckingText;
    private JLabel jLabelAttendanceData;
    private JLabel jLabelAttendanceDataText;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private String userAccount;
    private String userPassword;
    private static String userType = "教师";


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public TeacherPage() {
//        jFrameTeacher = new JFrame();
        /**总面板*/
        jPanelTeacher = new JPanel();
//        /**基本信息面板**/
//        jPanelBasicInfo = new JPanel();
//        /**考勤面板**/
//        jPanelAttendanceChecking = new JPanel();
//        /**考勤数据后台**/
//        jPanelAttendanceData = new JPanel();
        /**基本信息图标标签**/
        jLabelBasicInfo = new JLabel();
        jLabelBasicInfoText = new JLabel("教师信息");
        /**考勤图标标签**/
        jLabelAttendanceChecking = new JLabel();
        jLabelAttendanceCheckingText = new JLabel("学生考勤");
        /**考勤后台数据图标标签**/
        jLabelAttendanceData = new JLabel();
        jLabelAttendanceDataText = new JLabel("考勤数据");

        /**网格包布局**/
        gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();

        //修改左上角Java图标
        URL imgURL = this.getClass().getResource("/viewingImg/titleicon.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);
        Image image = imageIcon.getImage();
        this.setIconImage(image);

        init();

    }

    public void init() {
        this.setTitle("考勤系统教师界面");
//        jFrameTeacher.setLayout(null);
//        jFrameTeacher.setBounds(300, 200, 1200, 800);
//        jFrameTeacher.setLocationRelativeTo(null);
//        this.setAlwaysOnTop(true);
//        this.setUndecorated(false);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch(Exception e) {
            //TODO exception
            e.printStackTrace();
        }

        jPanelTeacher = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                try {
                    /**
                     * image如何创建对象
                     * https://blog.csdn.net/u012723673/article/details/53326257
                     */
//                    Image image = ImageIO.read(new File("viewingImg/TeacherPageBackground.png"));
                    URL imgURL = this.getClass().getResource("/viewingImg/TeacherPageBackground.png");
                    ImageIcon imageIcon = new ImageIcon(imgURL);
                    Image image = imageIcon.getImage();
                    /**
                     * 获取屏幕的高度与宽度（两种方式）
                     * https://blog.csdn.net/jobfyz/article/details/78466171
                     */
                    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = dimension.width;  //1707px
                    int height = dimension.height;  //960px
                    g.drawImage(image, 0, 0, width, height, this);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        };

        //基本信息入口图标标签
        int iconwidth1 = 128;
        int iconheight1 = 128;
        ImageIcon imageIcon1;
        URL imgURL1 = this.getClass().getResource("/viewingImg/basicinfo.png");
        System.out.println(this.getClass().getResource("/viewingImg/basicinfo.png"));
        imageIcon1 = new ImageIcon(imgURL1);
        Image image1 = imageIcon1.getImage();
        image1 = image1.getScaledInstance(iconwidth1, iconheight1, Image.SCALE_DEFAULT);
        imageIcon1.setImage(image1);
        jLabelBasicInfo.setIcon(imageIcon1);
//        jPanelBasicInfo.add(jLabelBasicInfo);
//        jPanelBasicInfo.setBounds(70, 200, iconwidth1, iconheight1);


        //考勤入口图标标签
        int iconwidth2 = 160;
        int iconheight2 = 160;
        ImageIcon imageIcon2;
        URL imgURL4 = this.getClass().getResource("/viewingImg/attendanceicon.png");
        imageIcon2 = new ImageIcon(imgURL4);
        Image image2 = imageIcon1.getImage();
        image2 = image2.getScaledInstance(iconwidth1, iconheight1, Image.SCALE_DEFAULT);
        imageIcon1.setImage(image2);
        jLabelAttendanceChecking.setIcon(imageIcon2);
//        jPanelAttendanceChecking.add(jLabelAttendanceChecking);
//        jPanelAttendanceChecking.setBounds(400, 200, iconwidth2, iconheight2);

        //考勤后台数据入口便签
        int iconwidth3 = 160;
        int iconheight3 = 160;
        ImageIcon imageIcon3;
        URL imgURL7 = this.getClass().getResource("/viewingImg/data.png");
        imageIcon3 = new ImageIcon(imgURL7);
        Image image3 = imageIcon1.getImage();
        image3 = image3.getScaledInstance(iconwidth1, iconheight1, Image.SCALE_DEFAULT);
        imageIcon1.setImage(image3);
        jLabelAttendanceData.setIcon(imageIcon3);
//        jPanelAttendanceData.add(jLabelAttendanceData);
//        jPanelAttendanceData.setBounds(800, 200, iconwidth3, iconheight3);


        /**
         * 文字标签
         */
        Color color = new Color(191, 173, 111);
        jLabelBasicInfoText.setFont(new Font("微软雅黑", Font.BOLD, 24));
        jLabelBasicInfoText.setForeground(color);
        jLabelAttendanceCheckingText.setFont(new Font("微软雅黑", Font.BOLD, 24));
        jLabelAttendanceCheckingText.setForeground(color);
        jLabelAttendanceDataText.setFont(new Font("微软雅黑", Font.BOLD, 24));
        jLabelAttendanceDataText.setForeground(color);



        /**
         * 如何javaSwing关闭子窗口不关闭父窗口
         * http://www.cnblogs.com/hualidezhuanshen/p/5433572.html
         * 给图标标签添加鼠标监听事件
         * https://blog.csdn.net/xuqimm/article/details/71170300
         */
        jLabelAttendanceChecking.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

//                jFrameTeacher.setVisible(false);
                /**
                 *
                 * 控制关闭考勤页面的同时设置被不可见的页面为可见
                 */
                AttendanceCheckingPage attendanceCheckingPage = new AttendanceCheckingPage();
            }
            /**
             * 图标变换
             * 鼠标点击
             * 鼠标进入
             * 鼠标离开
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });


        /**
         * 给图标标签添加监听事件
         *
         */
        jLabelAttendanceCheckingText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                new AttendanceCheckingPage();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });


        /**
         * 教师基本信息图标标签和文字标签添加鼠标监听事件
         */
        jLabelBasicInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                TeacherBasicInfoPage teacherBasicInfoPage = new TeacherBasicInfoPage();
                String account = getUserAccount();
                String pwd = getUserPassword();

                teacherBasicInfoPage.setUserAccount(account);
                teacherBasicInfoPage.setUserPassword(pwd);

                teacherBasicInfoPage.receTheAccountInfo(teacherBasicInfoPage);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        jLabelBasicInfoText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                TeacherBasicInfoPage teacherBasicInfoPage = new TeacherBasicInfoPage();
                String account = getUserAccount();
                String pwd = getUserPassword();

                teacherBasicInfoPage.setUserAccount(account);
                teacherBasicInfoPage.setUserPassword(pwd);

                teacherBasicInfoPage.receTheAccountInfo(teacherBasicInfoPage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        /**
         * 考勤后台数据图标标签和文字标签添加鼠标监听事件
         */
        jLabelAttendanceData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AttendanceCheckingDataPage attendanceCheckingDataPage = new AttendanceCheckingDataPage();
                String account = getUserAccount();
                String pwd = getUserPassword();

                attendanceCheckingDataPage.setUserAccount(account);
                attendanceCheckingDataPage.setUserPassword(pwd);

                attendanceCheckingDataPage.receTheAccountInfo(attendanceCheckingDataPage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        jLabelAttendanceDataText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AttendanceCheckingDataPage attendanceCheckingDataPage = new AttendanceCheckingDataPage();
                String account = getUserAccount();
                String pwd = getUserPassword();

                attendanceCheckingDataPage.setUserAccount(account);
                attendanceCheckingDataPage.setUserPassword(pwd);

                attendanceCheckingDataPage.receTheAccountInfo(attendanceCheckingDataPage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });


        /**
         * 设置JPanelTeacher
         * 为网格包布局
         */
        jPanelTeacher.setOpaque(false);
        jPanelTeacher.setLayout(gridBagLayout);

        /**
         * 接下来设置各组件以适应网格包布局
         */
        //基本信息入口标签显示
        gridBagConstraints = new GridBagConstraints(0, 0, 6, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 100, 0, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelBasicInfo, gridBagConstraints);
        jPanelTeacher.add(jLabelBasicInfo);

        //考勤入口标签显示
        gridBagConstraints = new GridBagConstraints(12, 0, 6, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 0, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelAttendanceChecking, gridBagConstraints);
        jPanelTeacher.add(jLabelAttendanceChecking);

        //考勤后台数据标签显示
        gridBagConstraints = new GridBagConstraints(24, 0, 6, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 0, 100 ),0, 0);
        gridBagLayout.setConstraints(jLabelAttendanceData, gridBagConstraints);
        jPanelTeacher.add(jLabelAttendanceData);

        //基本信息文字内容标签内容
        gridBagConstraints = new GridBagConstraints(0, 6, 6, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 100, 120, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelBasicInfoText, gridBagConstraints);
        jPanelTeacher.add(jLabelBasicInfoText);

        //考勤入口文字内容标签内容
        gridBagConstraints = new GridBagConstraints(12, 6, 6, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 120, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelAttendanceCheckingText, gridBagConstraints);
        jPanelTeacher.add(jLabelAttendanceCheckingText);

        //考勤数据文字内容标签内容
        gridBagConstraints = new GridBagConstraints(24, 6, 6, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 120, 100 ),0, 0);
        gridBagLayout.setConstraints(jLabelAttendanceDataText, gridBagConstraints);
        jPanelTeacher.add(jLabelAttendanceDataText);


        this.add(jPanelTeacher);
    }




    public void showFrameTeacher() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private  static GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth,
                                                             int gridheight, double weightx, double weighty,
                                                             int anchor, int fill, Insets insets, int ipadx,
                                                             int ipady) {
        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor,
                fill, insets, ipadx, ipady);
    }


    public static void main(String[] args) {
        TeacherPage teacherPage = new TeacherPage();
        teacherPage.showFrameTeacher();

    }
}
