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
 * @Date: 2019/6/1 8:49
 */
public class PasswordChangingPage extends JFrame {

    private JPanel jPanel;
    private JPanel jPanelNorth;
    private JPanel jPanelSouth;
    private JPanel jPanelWest;
    private JPanel jPanelEast;
    private JPanel jPanelCenter;
    private JLabel jLabelOldPwd;
    private JLabel jLabelNewPwd;
    private JLabel jLabelEnsurePwd;
    private JTextField jTextFieldOldPwd;
    private JTextField jTextFieldNewPwd;
    private JTextField jTextFieldEnsurePwd;
    private String acoountNumber;
    private String userType;
    private String password;
    private JButton jButtonMakeSure;
    private JButton jButtonSubmitChange;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public PasswordChangingPage() {

        this.setTitle("修改密码");
        this.setBounds(600, 200, 420, 360);
        this.setLocationRelativeTo(null);
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

        jPanel = new JPanel(new BorderLayout());
        jPanelNorth = new JPanel();
        jPanelSouth = new JPanel();
        jPanelWest = new JPanel(new GridLayout(3, 1, 0, 80));
        jPanelEast = new JPanel();
        jPanelCenter = new JPanel(new GridLayout(3, 1, 0, 80));
        jLabelOldPwd = new JLabel("旧密码");
        jLabelNewPwd = new JLabel("新密码");
        jLabelEnsurePwd = new JLabel("确认密码");
        jTextFieldOldPwd = new JTextField();
        jTextFieldNewPwd = new JTextField();
        jTextFieldEnsurePwd = new JTextField();
        jButtonMakeSure = new JButton("确认");
        jButtonSubmitChange = new JButton("提交");

        jPanelCenter.add(jTextFieldOldPwd);
        jPanelCenter.add(jTextFieldNewPwd);
        jPanelCenter.add(jTextFieldEnsurePwd);

        jPanelWest.add(jLabelOldPwd);
        jPanelWest.add(jLabelNewPwd);
        jPanelWest.add(jLabelEnsurePwd);

        jPanelSouth.add(jButtonMakeSure);
        jPanelSouth.add(jButtonSubmitChange);

        jPanel.add(jPanelCenter, BorderLayout.CENTER);
        jPanel.add(jPanelWest, BorderLayout.WEST);
        jPanel.add(jPanelSouth, BorderLayout.SOUTH);


        this.add(jPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public String getAcoountNumber() {
        return acoountNumber;
    }

    public void setAcoountNumber(String acoountNumber) {
        this.acoountNumber = acoountNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void receDataFromLoginPage(PasswordChangingPage passwordChangingPage) {

        final String accountNumber = passwordChangingPage.getAcoountNumber();
        final String password = passwordChangingPage.getPassword();
        final String userType = passwordChangingPage.getUserType();
        //测试
        System.out.println(accountNumber);
        System.out.println(password);
        System.out.println(userType);


        /**
         * 根据usertype修改user
         */
        String user = null;
        if (userType.equals("学生")) {
            user = "student";
        } else if (userType.equals("教师")) {
            user = "teacher";
        } else if (userType.equals("管理员")) {
            user = "admin";
        }
        final String usertype = user;

       jButtonMakeSure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DB db = new DB();
                    ResultSet rs = null;
                    rs = db.executeQuery("select * from " + usertype  + " where " + usertype + "_id = '" + accountNumber + "'");
                    //测试语句
                    System.out.println("select * from " + usertype  + " where " + usertype + "_id = '" + accountNumber + "'");

                    //判断数据可查询返回结果是否为空 空即不存在此用户权限
                    if (rs.wasNull()) {
                        JOptionPane.showMessageDialog(null, "此用户权限不存在，请重新选择正确用户权限！");
                    } else {

                        /**
                         * 接下来判断密码是否正确
                         */
                        rs.next();
                        String loginpwd = rs.getString(usertype + "_pwd");

                        if (loginpwd.equals(password)) {
                            System.out.println("账户密码对应正确");

                            /**
                             * 假设存在此用户 及 密码 角色数据对的上号
                             *
                             */
                            if (jTextFieldEnsurePwd.getText().equals(jTextFieldNewPwd.getText())) {
                                System.out.println("两次输入新密码匹配");
                                JOptionPane.showMessageDialog(null,"两次输入的新密码匹配！");

                                if (userType.equals("学生")) {
                                    jButtonSubmitChange.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            DB db = new DB();
                                            db.executeUpdate("update student set student_pwd = '" + jTextFieldNewPwd.getText() + "' where student_id = '" + accountNumber + "'");
                                            //测试语句
                                            System.out.println("update student set student_pwd = '" + jTextFieldNewPwd.getText() + "' where studnet_id = '" + accountNumber + "'");
                                        }
                                    });

                                } else if (userType.equals("教师")) {

                                    jButtonSubmitChange.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            DB db = new DB();
                                            db.executeUpdate("update teacher set teacher_pwd = '" + jTextFieldNewPwd.getText() + "' where teacher_id = '" + accountNumber + "'");
                                            //测试语句
                                            System.out.println("update teacher set teacher_pwd = '" + jTextFieldNewPwd.getText() + "' where teacher_id = '" + accountNumber + "'");
                                        }
                                    });
                                } else if (userType.equals("管理员")) {

                                    jButtonSubmitChange.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            DB db = new DB();
                                            db.executeUpdate("update admin set admin_pwd = '" + jTextFieldNewPwd.getText() + "' where admin_id = '" + accountNumber + "'");
                                            //测试语句
                                            System.out.println("update admin set admin_pwd = '" + jTextFieldNewPwd.getText() + "' where admin_id = '" + accountNumber + "'");
                                        }
                                    });
                                } else {
                                    JOptionPane.showMessageDialog(null, "账户不存在，或者密码错误，或者权限不匹配，请重新输入并再尝试");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "新密码有误！请再次确认输入的新密码！（两次不匹配）");
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



    }

    public static void main(String[] args) {
        PasswordChangingPage passwordChangingPage = new PasswordChangingPage();

    }
}
