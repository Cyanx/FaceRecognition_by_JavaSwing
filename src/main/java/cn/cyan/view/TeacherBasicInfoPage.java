package cn.cyan.view;

import cn.cyan.util.DB;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * @Author: Cyan
 * @Date: 2019/5/29 19:41
 */
public class TeacherBasicInfoPage extends JFrame {
    private String userAccount;
    private String userPassword;
    private DefaultTableModel defaultTableModel;
    private JTable jTablePersonalInfo;
    private JScrollPane jScrollPaneBasicInfo;
    private JButton jButtonSaveTheChange;
    private JLabel jLabelBasicInfo;
    private JPanel jPanelBasicInfo;

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

    public TeacherBasicInfoPage() {

        this.setTitle("考勤系统教师界面");
        this.setLayout(new GridLayout(1, 1));
        this.setBounds(600, 200, 720, 520);
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

        jLabelBasicInfo = new JLabel("基本信息");
        jPanelBasicInfo = new JPanel();


//        jPanelBasicInfo.setPreferredSize(new Dimension(720, 280));

        /**
         * 基本信息表格
         */
        jTablePersonalInfo = new JTable(9, 2);
        //设置行宽
        jTablePersonalInfo.setRowHeight(30);
        //设置列宽
        jTablePersonalInfo.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTablePersonalInfo.getColumnModel().getColumn(1).setPreferredWidth(400);
        //设置表头不可见
        jTablePersonalInfo.getTableHeader().setVisible(false);
        /**
         * 选课信息表格 调整到方法内
         */


        jScrollPaneBasicInfo = new JScrollPane(jTablePersonalInfo);
        jScrollPaneBasicInfo.setPreferredSize(new Dimension(500,250));



        jButtonSaveTheChange = new JButton("保存修改");
        jButtonSaveTheChange.setBackground(Color.CYAN);
        jButtonSaveTheChange.setContentAreaFilled(true);
        jButtonSaveTheChange.setBorder(null);

        jPanelBasicInfo.add(jLabelBasicInfo);
        jPanelBasicInfo.add(jScrollPaneBasicInfo);
        jPanelBasicInfo.add(jButtonSaveTheChange);

        this.add(jPanelBasicInfo);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    //接收教师功能选择页面账户
    //接下来实现表的展示
    public void receTheAccountInfo(final TeacherBasicInfoPage teacherBasicInfoPage) {
        final String account = teacherBasicInfoPage.getUserAccount();
        final String pwd = teacherBasicInfoPage.getUserPassword();

        //测试传入参数
        System.out.println(account);
        System.out.println(pwd);

        /**
         * 个人基本信息
         */
        try {
            //获取数据库此账号数据
            DB db = new DB();
            ResultSet rs = null;

            rs = db.executeQuery("select * from teacher where teacher_id = '" + account + "'");
            //
            System.out.println("select * from teacher where teacher_id = '" + account + "'");

            if (rs.wasNull()) {
                System.out.println("获取数据失败为空！");
            } else {
                /**
                 * 接下来获取数据展示在表格中
                 */
                String attribute_id = null;
                String attribute_name = null;
                String attribute_sex = null;
                String attribute_age = null;
                String attribute_school = null;
                String attribute_title = null;
                String attribute_email = null;
                String attribute_phonenumber = null;
                String loginpassword = null;

                rs.next();

                attribute_id = rs.getString("teacher_id");
                attribute_name = rs.getString("teacher_name");
                attribute_sex = rs.getString("teacher_sex");
                attribute_age = rs.getString("teacher_age");
                attribute_school = rs.getString("teacher_school");
                attribute_title = rs.getString("teacher_position");
                attribute_email = rs.getString("teacher_email");
                attribute_phonenumber = rs.getString("teacher_phone");
                loginpassword = rs.getString("teacher_pwd");

                System.out.println(attribute_id);
                System.out.println(attribute_name);
                System.out.println(attribute_sex);
                System.out.println(attribute_age);
                System.out.println(attribute_school);
                System.out.println(attribute_title);
                System.out.println(attribute_email);
                System.out.println(attribute_phonenumber);
                System.out.println(loginpassword);

                jTablePersonalInfo.setValueAt("教师号", 0, 0);
                jTablePersonalInfo.setValueAt("姓名", 1, 0);
                jTablePersonalInfo.setValueAt("性别", 2, 0);
                jTablePersonalInfo.setValueAt("年龄", 3, 0);
                jTablePersonalInfo.setValueAt("学院", 4, 0);
                jTablePersonalInfo.setValueAt("职称", 5, 0);
                jTablePersonalInfo.setValueAt("邮箱", 6, 0);
                jTablePersonalInfo.setValueAt("手机号", 7, 0);
                jTablePersonalInfo.setValueAt("密码", 8, 0);


                jTablePersonalInfo.setValueAt(attribute_id, 0, 1);
                jTablePersonalInfo.setValueAt(attribute_name, 1, 1);
                jTablePersonalInfo.setValueAt(attribute_sex, 2, 1);
                jTablePersonalInfo.setValueAt(attribute_age, 3, 1);
                jTablePersonalInfo.setValueAt(attribute_school, 4, 1);
                jTablePersonalInfo.setValueAt(attribute_title, 5, 1);
                jTablePersonalInfo.setValueAt(attribute_email, 6, 1);
                jTablePersonalInfo.setValueAt(attribute_phonenumber, 7, 1);
                jTablePersonalInfo.setValueAt(loginpassword, 8, 1);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        jButtonSaveTheChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTablePersonalInfo.getColumnCount();
                int row = jTablePersonalInfo.getRowCount();

                //测试
                System.out.println(row);

                //value数组存放表格中第二列的所有数据
                String[] value = new String[row];
//                //测试
//                String str = jTablePersonalInfo.getValueAt(3, 1).toString();
//                System.out.println(str);

                for (int i = 0; i < row; i++) {

                    value[i] = jTablePersonalInfo.getValueAt(i, 1).toString();
                    System.out.println(value[i]);

                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("insert into teacher values(" + "'" + value[0] + "'" + "," +  "'" + value[1]+ "'"  + "," +
                        "'" + value[2] + "'" + "," +  "'" + value[3] + "'" + "," +  "'" +value[4] + "'" + "," + "'" +
                        value[5] + "'" + "," + "'" + value[6] + "'" + "," + "'" + value[7] + "'" +
                        "," +  "'" +value[8] +  "'" +")");
                //测试SQL语句
                System.out.println("insert into teacher values(" + "'" + value[0] + "'" + "," +  "'" + value[1]+ "'"  + "," +
                        "'" + value[2] + "'" + "," +  "'" + value[3] + "'" + "," +  "'" +value[4] + "'" + "," + "'" +
                        value[5] + "'" + "," + "'" + value[6] + "'" + "," + "'" + value[7] + "'" +
                        "," +  "'" +value[8] +  "'" +")");


                System.out.println("操作已完成");
            }
        });


    }


    //单元测试
    public static void main(String[] args) {
        new TeacherBasicInfoPage();
    }
}
