package cn.cyan.view;

import cn.cyan.util.DB;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Cyan
 * @Date: 2019/5/20 8:07
 * 界面跳转思路 http://tieba.baidu.com/p/6115593929
 * 主要是进入登陆后的界面 可以同一个框架jframe 添加不同的jpanel了
 */
public class LoginPage extends JFrame{

    private String accountNumber;
    private String password;
    private String userType;
    private String user;
    private JFrame jFrameLogin;
    private JLabel jLabelAccountNumber;
    private JLabel jLabelPassword;
    private JLabel jLabelUserType;
    private JTextField jTextFieldAccountNumber;
    private JTextField jTextFieldPassword;
    private JRadioButton jRadioButtonStudent;
    private JRadioButton jRadioButtonTeacher;
    private JRadioButton jRadioButtonAdmin;
    private ButtonGroup buttonGroup;//必须要把单选框放入按钮组作用域中才能实现当选！！
    private JButton jButtonChangePassWord;
    private JButton jButtonLogin;
    private JButton jButtonExit;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private JPanel jPanelLogin;


    public LoginPage() {
//        jFrameLogin = new JFrame();
        jLabelAccountNumber = new JLabel();
        jLabelPassword = new JLabel();
        jLabelUserType = new JLabel();
        jTextFieldAccountNumber = new JTextField();
        jTextFieldPassword = new JTextField();
        jRadioButtonStudent = new JRadioButton();
        jRadioButtonTeacher = new JRadioButton();
        jRadioButtonAdmin = new JRadioButton();
        buttonGroup = new ButtonGroup();
        jButtonChangePassWord = new JButton();
        jButtonLogin = new JButton();
        jButtonExit = new JButton();
        gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();

        init();

    }

    public void init() {

        this.setTitle("登录");
        //设置窗体最顶层显示
//        jFrameLogin.setAlwaysOnTop(true);

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

        /**
         * 重载jpanel的paintComponent(Graphics g)方法
         * 通过重载该方法，在JPanel的绘制阶段将指定图片绘制上去即可。
         * 由于背景是绘制出来的，因此不会对布局有任何影响。
         * https://www.jb51.net/article/128171.htm
         */
        jPanelLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

//                ImageIcon imageIcon = new ImageIcon("viewingImg/LoginPageBackground.png");
                try {
                    /**
                     * image如何创建对象
                     * https://blog.csdn.net/u012723673/article/details/53326257
                     */
//                    Image image = ImageIO.read(new File("viewingImg/LoginPageBackground.png"));
//                imageIcon.paintIcon(this, g, 0, 0);
                    URL imgURL = this.getClass().getResource("/viewingImg/LoginPageBackground.png");
                    ImageIcon imageIcon = new ImageIcon(imgURL);
                    Image image = imageIcon.getImage();
                    /**
                     * 获取屏幕的高度与宽度（两种方式）
                     * https://blog.csdn.net/jobfyz/article/details/78466171
                     */
//                    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//                    Rectangle rectangle = graphicsEnvironment.getMaximumWindowBounds();
//                    int width = rectangle.width;
//                    int height = rectangle.height;
                    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = dimension.width;  //1707px
                    int height = dimension.height;  //960px
//                    System.out.println(width + " " + height);
                    g.drawImage(image, 0, 0, width, height, this);
                } catch (Exception e) {
                    e.getStackTrace();
                }

            }
        };

        jLabelAccountNumber.setText("账   号： ");
        jLabelPassword.setText("密   码： ");
        jLabelUserType.setText("用户类型：");
        jLabelAccountNumber.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jLabelPassword.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jLabelUserType.setFont(new Font("微软雅黑", Font.BOLD, 16));

        //设置文本域长度
        jTextFieldAccountNumber.setColumns(20);
        jTextFieldPassword.setColumns(20);

        jRadioButtonStudent.setText("学生");
        jRadioButtonTeacher.setText("教师");
        jRadioButtonAdmin.setText("管理员");
        jRadioButtonStudent.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jRadioButtonTeacher.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jRadioButtonAdmin.setFont(new Font("微软雅黑", Font.BOLD, 16));


        jButtonLogin.setText("");
        jButtonChangePassWord.setText("");
        jButtonExit.setText("");
        jButtonLogin.setFont(new Font("微软雅黑", Font.BOLD, 12));
        jButtonChangePassWord.setFont(new Font("微软雅黑", Font.BOLD, 12));
        jButtonExit.setFont(new Font("微软雅黑", Font.BOLD, 12));
        //设置按钮透明度和无边框
        //https://blog.csdn.net/u011393661/article/details/39587391
        jButtonLogin.setMargin(new Insets(0, 0, 0, 0));
        jButtonLogin.setContentAreaFilled(false);  //不绘制按钮底色
        jButtonLogin.setBorderPainted(false); //不绘制边框
        jButtonLogin.setFocusPainted(false);//不绘制焦点框 https://www.cnblogs.com/xinluwutu/p/5958659.html
        URL imgURL1 = this.getClass().getResource("/viewingImg/LoginOrigin.png");
//        ImageIcon imageIcon1 = new ImageIcon(imgURL1);
        System.out.println(this.getClass().getResource("/viewingImg/LoginOrigin.png"));
        jButtonLogin.setIcon(new ImageIcon(imgURL1));//设置默认图片
        URL imgURL2 = this.getClass().getResource("/viewingImg/LoginRoll.png");
        System.out.println(this.getClass().getResource("/viewingImg/LoginRoll.png"));
        jButtonLogin.setRolloverIcon(new ImageIcon(imgURL2));//设置鼠标经过图片
        URL imgURL3 = this.getClass().getResource("/viewingImg/LoginPressed.png");
        System.out.println(this.getClass().getResource("/viewingImg/LoginPressed.png"));
        jButtonLogin.setPressedIcon(new ImageIcon(imgURL3));//设置鼠标按下图片

        jButtonExit.setMargin(new Insets(0, 0, 0, 0));
        jButtonExit.setContentAreaFilled(false);  //不绘制按钮底色
        jButtonExit.setBorderPainted(false); //不绘制边框
        jButtonExit.setFocusPainted(false);//不绘制焦点框 https://www.cnblogs.com/xinluwutu/p/5958659.html
        URL imgURL4 = this.getClass().getResource("/viewingImg/QuitOrigin.png");
        jButtonExit.setIcon(new ImageIcon(imgURL4));//设置默认图片
        URL imgURL5 = this.getClass().getResource("/viewingImg/QuitRoll.png");
        jButtonExit.setRolloverIcon(new ImageIcon(imgURL5));//设置鼠标经过图片
        URL imgURL6 = this.getClass().getResource("/viewingImg/QuitPressed.png");
        jButtonExit.setPressedIcon(new ImageIcon(imgURL6));//设置鼠标按下图片

        jButtonChangePassWord.setMargin(new Insets(0, 0, 0, 0));
        jButtonChangePassWord.setContentAreaFilled(false);  //不绘制按钮底色
        jButtonChangePassWord.setBorderPainted(false); //不绘制边框
        jButtonChangePassWord.setFocusPainted(false);//不绘制焦点框 https://www.cnblogs.com/xinluwutu/p/5958659.html
        URL imgURL7 = this.getClass().getResource("/viewingImg/ChangePwdOrigin.png");
        jButtonChangePassWord.setIcon(new ImageIcon(imgURL7));//设置默认图片
        URL imgURL8 = this.getClass().getResource("/viewingImg/ChangePwdRoll.png");
        jButtonChangePassWord.setRolloverIcon(new ImageIcon(imgURL8));//设置鼠标经过图片
        URL imgURL9 = this.getClass().getResource("/viewingImg/ChangePwdPressed.png");
        jButtonChangePassWord.setPressedIcon(new ImageIcon(imgURL9));//设置鼠标按下图片



        //设置jframe为全屏
//        this.setUndecorated(false);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

//        设置jframe为透明 且使用gridbaglayout布局
        jPanelLogin.setOpaque(false);
        jPanelLogin.setLayout(gridBagLayout);

        //账号标签显示
        gridBagConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 10, 0 ),0, 0);
        gridBagLayout.setConstraints(jLabelAccountNumber, gridBagConstraints);
        jPanelLogin.add(jLabelAccountNumber);

        //账号文本框显示
        gridBagConstraints = new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        gridBagLayout.setConstraints(jTextFieldAccountNumber, gridBagConstraints);
        jPanelLogin.add(jTextFieldAccountNumber);

        //密码标签显示
        gridBagConstraints = new GridBagConstraints(0,1,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridBagLayout.setConstraints(jLabelPassword, gridBagConstraints);
        jPanelLogin.add(jLabelPassword);

        //密码文本框显示
        gridBagConstraints = new GridBagConstraints(1,1,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        gridBagLayout.setConstraints(jTextFieldPassword, gridBagConstraints);
        jPanelLogin.add(jTextFieldPassword);

        //用户类型（权限）标签显示

        gridBagConstraints = new GridBagConstraints(0,2,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridBagLayout.setConstraints(jLabelUserType, gridBagConstraints);
        jPanelLogin.add(jLabelUserType);

        //用户类型单选框显示
        gridBagConstraints = new GridBagConstraints(1,2,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        final JPanel jPanelUserType = new JPanel();
        //单选框按钮组透明（本身透明 外带jpanel透明）
        //https://bbs.csdn.net/topics/390790530
        jRadioButtonStudent.setOpaque(false);
        jRadioButtonTeacher.setOpaque(false);
        jRadioButtonAdmin.setOpaque(false);
        jRadioButtonStudent.setFocusPainted(false);
        jRadioButtonTeacher.setFocusPainted(false);
        jRadioButtonAdmin.setFocusPainted(false);
        buttonGroup.add(jRadioButtonStudent);
        buttonGroup.add(jRadioButtonTeacher);
        buttonGroup.add(jRadioButtonAdmin);
        jPanelUserType.add(jRadioButtonStudent);
        jPanelUserType.add(jRadioButtonTeacher);
        jPanelUserType.add(jRadioButtonAdmin);
        //单选框承载的组件jpanel背景透明
        jPanelUserType.setOpaque(false);
        gridBagLayout.setConstraints(jPanelUserType, gridBagConstraints);
        jPanelLogin.add(jPanelUserType);

        //修改密码显示
        gridBagConstraints = new GridBagConstraints(0,3,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridBagLayout.setConstraints(jButtonChangePassWord, gridBagConstraints);
        jPanelLogin.add(jButtonChangePassWord);
        jButtonChangePassWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountNumber = jTextFieldAccountNumber.getText();

                //获取单选框信息
                for (Component btn : jPanelUserType.getComponents()) {
                    if (btn instanceof JRadioButton) {
                        if (((JRadioButton)btn).isSelected()) {
                            userType = ((JRadioButton) btn).getText();
                            System.out.println("测试： 用户选择的角色权限为： "+ userType);
                        }
                    }
                }

                password = jTextFieldPassword.getText();


                /**
                 * 测试用户类型是否选择
                 */
                Boolean flag = true;
                while (flag == true) {
                    if (userType == null) {
                        Object[] usertypes = {"学生", "教师", "管理员"};
                        int op = (int)JOptionPane.showOptionDialog(null, "选择用户类型",
                                "提示输入", JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE, null, usertypes, usertypes[0]);
                        System.out.println("下标为" + op);

                        //出口检测
                        if (op >= 0) {
                            flag = false;
                            userType = (String)usertypes[op];
                            System.out.println("usertype is " + userType);
                        } else {
                            flag = true;
                        }
                    } else { //若不为空
                        break;
                    }
                }

                //测试
                System.out.println(accountNumber);
                System.out.println(userType);
                System.out.println(password);

                    PasswordChangingPage passwordChangingPage = new PasswordChangingPage();
                    passwordChangingPage.setAcoountNumber(accountNumber);
                    passwordChangingPage.setUserType(userType);
                    passwordChangingPage.setPassword(password);
                    passwordChangingPage.receDataFromLoginPage(passwordChangingPage);

            }
        });

        //登录按钮显示
        gridBagConstraints = new GridBagConstraints(1,3,1,1,0,0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridBagLayout.setConstraints(jButtonLogin, gridBagConstraints);
        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取账号文本域的数据
                accountNumber = jTextFieldAccountNumber.getText().toString();
                System.out.println("测试： 用户输入的账号为： " + accountNumber);
                //获取密码文本域的数据
                password = jTextFieldPassword.getText();
                System.out.println("测试： 用户输入的密码为： " + password);
                //获取单选框的数据信息
                for (Component btn : jPanelUserType.getComponents()) {
                    if (btn instanceof JRadioButton) {
                        if (((JRadioButton)btn).isSelected()) {
                            userType = ((JRadioButton) btn).getText();
                            System.out.println("测试： 用户选择的角色权限为： "+ userType);
                        }
                    }
                }

                /**
                 * 根据usertype修改user
                 */
                if (userType.equals("学生")) {
                    user = "student";
                } else if (userType.equals("教师")) {
                    user = "teacher";
                } else if (userType.equals("管理员")) {
                    user = "admin";
                }

                /**
                 * 连接数据库  JDBC
                 * 查询 是否存在此用户 和密码 角色编号 是否正确（数据库需要权限角色表）
                 */
                try {
                    DB db = new DB();
                    ResultSet rs = null;
                    rs = db.executeQuery("select * from " + user  + " where " + user + "_id = '" + accountNumber + "'");
                    //测试语句
                    System.out.println("select * from " + user  + " where " + user + "_id = '" + accountNumber + "'");

                    //判断数据可查询返回结果是否为空 空即不存在此用户权限
                    if (rs.wasNull()) {
                        JOptionPane.showMessageDialog(null, "此用户权限不存在，请重新选择正确用户权限！");
                    } else {

                        /**
                         * 接下来判断密码是否正确
                         */
                        rs.next();
                        String loginpwd = rs.getString(user +"_pwd");

                        if (loginpwd.equals(password)) {

                            /**
                             * 假设存在此用户 及 密码 角色数据对的上号
                             * 学生界面创建StudentPage对象
                             * 管理员同样创建AdminPage对象
                             * 而教师进入教师TeacherPage页面后再打开三种界面  教师基本信息 考勤界面 考勤后台数据查看界面
                             */
                            if (userType.equals("学生")) {
//                                jFrameLogin.dispose();
                                StudentPage studentPage = new StudentPage();
                                studentPage.setUserAccount(accountNumber);
                                studentPage.setUserPassword(password);
                                studentPage.showTheData(studentPage);
                            } else if (userType.equals("教师")) {
//                                jFrameLogin.dispose();
                                TeacherPage teacherPage = new TeacherPage();
                                teacherPage.setUserAccount(accountNumber);
                                teacherPage.setUserPassword(password);
                                teacherPage.showFrameTeacher();
                            } else if (userType.equals("管理员")) {
//                                jFrameLogin.dispose();
                                AdminPage adminPage = new AdminPage();
                                adminPage.setUserAccount(accountNumber);
                                adminPage.setUserPassword(password);
                                adminPage.showFrameAdmin();
                            } else {
                                JOptionPane.showMessageDialog(null, "账户不存在，或者密码错误，或者权限不匹配，请重新输入并再尝试登录");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "密码或账号错误，请确保您的输入无误再尝试登录");
                        }
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "账户不存在，或者密码错误，或者权限不匹配，请重新输入并再尝试登录");
                    e1.printStackTrace();
                }




            }
        });
        jPanelLogin.add(jButtonLogin);

        //退出按钮显示
        gridBagConstraints = getGridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);
        gridBagLayout.setConstraints(jButtonExit, gridBagConstraints);
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jPanelLogin.add(jButtonExit);


        //添加面板到框架
        this.add(jPanelLogin);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private  static GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth,
                                                             int gridheight, double weightx, double weighty,
                                                             int anchor, int fill, Insets insets, int ipadx,
                                                             int ipady) {
        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor,
                fill, insets, ipadx, ipady);
    }

    public void showFrameLogin() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
//        loginPage.showFrameLogin();

    }
}
