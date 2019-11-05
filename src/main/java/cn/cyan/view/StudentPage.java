package cn.cyan.view;

import cn.cyan.util.DB;
import cn.cyan.util.OpertareDBSets.OperateDB2;
import cn.cyan.util.OpertareDBSets.OperateDB3;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @Author: Cyan
 * @Date: 2019/5/20 8:10
 * 最初想法两个表格  一个基本信息表格 展示并且可以修改
 * 一个选课信息结果展示表格  只可查看不可修改
 * （学号不可修改 姓名一般也不可修改 性别 年龄 学院 专业 登录密码可以修改）
 */
public class StudentPage extends JFrame {

    private JPanel jPanelStudent;
    private static String userType = "学生";
    private String userAccount;
    private String userPassword;
    private DefaultTableModel defaultTableModel;
    private JTable jTablePersonalInfo;
    private JTable jTableClasses;
    private JScrollPane jScrollPaneBasicInfo;
    private JScrollPane jScrollPaneClassesInfo;
    private JButton jButtonSaveTheChange;
    private JButton jButtonFaceRegister;
    private JButton jButtonCheckTheClasses;
    private JLabel jLabelBasicInfo;
    private JLabel jLabelClassesInfo;
    private JPanel jPanelBasicInfo;
    private JPanel jPanelClassesInfo;

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

    public StudentPage() {

        this.setTitle("考勤系统学生界面");
        this.setLayout(new GridLayout(2, 1));
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

        jLabelBasicInfo = new JLabel("基本信息");
        jLabelClassesInfo = new JLabel("选课信息");
        jPanelBasicInfo = new JPanel();
        jPanelClassesInfo = new JPanel();


        jPanelBasicInfo.setPreferredSize(new Dimension(800, 300));
        jPanelClassesInfo.setPreferredSize(new Dimension(800, 300));

        /**
         * 基本信息表格
         */
        jTablePersonalInfo = new JTable(8, 2);
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
//        jScrollPaneBasicInfo.setPreferredSize(new Dimension(500,360));



        jButtonSaveTheChange = new JButton("保存修改");
        jButtonSaveTheChange.setContentAreaFilled(true);
        jButtonSaveTheChange.setBorder(null);
        jButtonFaceRegister = new JButton("人脸注册");
        jButtonFaceRegister.setContentAreaFilled(true);
        jButtonFaceRegister.setBorder(null);


        jPanelBasicInfo.add(jLabelBasicInfo);
        jPanelBasicInfo.add(jScrollPaneBasicInfo);
        jPanelBasicInfo.add(jButtonSaveTheChange);
        jPanelBasicInfo.add(jButtonFaceRegister);

        this.add(jPanelBasicInfo);



        //事件处理
//        showTheData();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void showTheData(final StudentPage studentPage) {

        final String account = studentPage.getUserAccount();
        //测试传值是否成功
        System.out.println("传入的账号为：" + account);


        /**
         * 个人基本信息
         */
        try {
            //获取数据库此账号数据
            DB db = new DB();
            ResultSet rs = null;

            rs = db.executeQuery("select * from student where student_id = '" + account + "'");
            //
            System.out.println("select * from student where student_id = '" + account + "'");

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
                String attribute_major = null;
                String attribute_email = null;
                String attribute_phonenumber = null;
                String loginpassword = null;

                rs.next();

                attribute_id = rs.getString("student_id");
                attribute_name = rs.getString("student_name");
                attribute_sex = rs.getString("student_sex");
                attribute_age = rs.getString("student_age");
                attribute_school = rs.getString("student_school");
                attribute_major = rs.getString("student_major");
                attribute_email = rs.getString("student_email");
                attribute_phonenumber = rs.getString("student_phone");
//                loginpassword = rs.getString("登录密码");

                System.out.println(attribute_id);
                System.out.println(attribute_name);
                System.out.println(attribute_sex);
                System.out.println(attribute_age);
                System.out.println(attribute_school);
                System.out.println(attribute_major);
                System.out.println(attribute_email);
                System.out.println(attribute_phonenumber);
                System.out.println(loginpassword);

                jTablePersonalInfo.setValueAt("学号", 0, 0);
                jTablePersonalInfo.setValueAt("姓名", 1, 0);
                jTablePersonalInfo.setValueAt("性别", 2, 0);
                jTablePersonalInfo.setValueAt("年龄", 3, 0);
                jTablePersonalInfo.setValueAt("学院", 4, 0);
                jTablePersonalInfo.setValueAt("专业", 5, 0);
                jTablePersonalInfo.setValueAt("邮箱", 6, 0);
                jTablePersonalInfo.setValueAt("手机号", 7, 0);
//                jTablePersonalInfo.setValueAt("密码", 8, 0);


                jTablePersonalInfo.setValueAt(attribute_id, 0, 1);
                jTablePersonalInfo.setValueAt(attribute_name, 1, 1);
                jTablePersonalInfo.setValueAt(attribute_sex, 2, 1);
                jTablePersonalInfo.setValueAt(attribute_age, 3, 1);
                jTablePersonalInfo.setValueAt(attribute_school, 4, 1);
                jTablePersonalInfo.setValueAt(attribute_major, 5, 1);
                jTablePersonalInfo.setValueAt(attribute_email, 6, 1);
                jTablePersonalInfo.setValueAt(attribute_phonenumber, 7, 1);
//                jTablePersonalInfo.setValueAt(loginpassword, 8, 1);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }


        /**
         * 个人选课信息查看
         * 封装好的OperateDB要更改数据才行
         */
        try {
            //获取数据库此账号数据
            DB db = new DB();
            ResultSet rs = null;

            rs = db.executeQuery("select * from classselecting where select_stu_id = '" + account + "'");
            //
            System.out.println("select * from classselecting where select_stu_id = '" + account + "'");

            if (rs.wasNull()) {
                System.out.println("获取数据失败为空！");
            } else {
                /**
                 * 接下来获取数据展示在表格中
                 */
//                Vector rowData = OperateDB2.getRows();
//                Vector columnNames = OperateDB2.getHead();

                /**
                 * 接下来获取数据展示在表格中
                 */
                String select_id = null;
                String select_class_id = null;
                String select_stu_id = null;

                //要将指针放到结果集最后一行才能读出总结果集行数
                rs.last();
                int rowcount = rs.getRow();
                System.out.println(rowcount);
                jTableClasses = new JTable(rowcount, 3);

                jTableClasses.setValueAt("选课id", 0, 0);
                jTableClasses.setValueAt("课程id", 0, 1);
                jTableClasses.setValueAt("选课学生学号", 0, 2);

                //将指针移到第一行 也就是还包括表头 继续遍历结果
                rs.first();
                int i = 1;
                while (rs.next() && i < rowcount) {
                    select_id = rs.getString("select_id");
                    select_class_id = rs.getString("select_class_id");
                    select_stu_id = rs.getString("select_stu_id");


                    System.out.println(select_id);
                    System.out.println(select_class_id);
                    System.out.println(select_stu_id);

                    jTableClasses.setValueAt(select_id, i, 0);
                    jTableClasses.setValueAt(select_class_id, i, 1);
                    jTableClasses.setValueAt(select_stu_id, i, 2);

                    i++;
                }

//                jTablePersonalInfo.setValueAt(loginpassword, 8, 1);

//                defaultTableModel = new DefaultTableModel(rowData, columnNames);
//                jTableClasses = new JTable(defaultTableModel);
//
//                jTablePersonalInfo.setPreferredScrollableViewportSize(new Dimension(500, 200));
//                jTablePersonalInfo.setFillsViewportHeight(true);
//                jTableClasses.setPreferredScrollableViewportSize(new Dimension(500, 200));
//                jTableClasses.setFillsViewportHeight(true);

                jScrollPaneClassesInfo = new JScrollPane(jTableClasses);
//        jScrollPaneClassesInfo.setPreferredSize(new Dimension(500, 360));

                jButtonCheckTheClasses = new JButton("查看详细");
                jButtonCheckTheClasses.setBackground(Color.pink);
                jButtonCheckTheClasses.setOpaque(false);
                jButtonCheckTheClasses.setBorder(null);

                jPanelClassesInfo.add(jLabelClassesInfo);
                jPanelClassesInfo.add(jScrollPaneClassesInfo);
                jPanelClassesInfo.add(jButtonCheckTheClasses);

                this.add(jPanelClassesInfo);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }


        jButtonSaveTheChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTablePersonalInfo.getColumnCount();
                int row = jTablePersonalInfo.getRowCount();

                System.out.println(row);

                //value数组存放表格中第二列的所有数据
                String[] value = new String[row];
                String str = jTablePersonalInfo.getValueAt(3, 1).toString();
                System.out.println(str);

                for (int i = 0; i < row; i++) {

                    value[i] = jTablePersonalInfo.getValueAt(i, 1).toString();
                    System.out.println(value[i]);

                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from student where student_id = '" + account + "'");


                db.executeUpdate("insert into student(student_id,student_name,student_sex,student_age,student_school,student_major,student_email,student_phone) " +
                        "values(" + "'" + value[0] + "'" + "," +  "'" + value[1]+ "'"  + "," +
                         "'" + value[2] + "'" + "," +  "'" + value[3] + "'" + "," +  "'" +value[4] + "'" + "," + "'" +
                        value[5] + "'" + "," + "'" + value[6] + "'" + "," + "'" + value[7] + "'" + ")");
//                        "," +  "'" +value[8] +  "'" +")");
                //测试SQL语句
                System.out.println("insert into student(student_id,student_name,student_sex,student_age,student_school,student_major,student_email,student_phone) " +
                        "values(" + "'" + value[0] + "'" + "," +  "'" + value[1]+ "'"  + "," +
                        "'" + value[2] + "'" + "," +  "'" + value[3] + "'" + "," +  "'" +value[4] + "'" + "," + "'" +
                        value[5] + "'" + "," + "'" + value[6] + "'" + "," + "'" + value[7] + "'" + ")");


                System.out.println("操作已完成");
            }
        });

        jButtonCheckTheClasses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrameCheckingClasses = new JFrame();
                DefaultTableModel tableModel;         //默认显示的表格
                JTable jTable;                        //表格
                JPanel jPanel;   //增加信息的面板

                jFrameCheckingClasses.setBounds(500, 600, 600, 450);
                jFrameCheckingClasses.setTitle("课程表");

                //取得数据库的课程表的各行数据
                Vector rowData = OperateDB3.getRows();
                //取得数据库的课程表的表头数据
                Vector columnNames = OperateDB3.getHead();

                //新建表格
                tableModel = new DefaultTableModel(rowData, columnNames);
                jTable = new JTable(tableModel);

                JScrollPane jScrollPane = new JScrollPane(jTable);

                jFrameCheckingClasses.add(jScrollPane);
                jFrameCheckingClasses.setVisible(true);
            }
        });

        jButtonFaceRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentRegister studentRegister = new StudentRegister();
            }
        });


    }






    //此方法最好不用 是拿来测试展示界面的
    public void showFrameStudent() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        StudentPage studentPage = new StudentPage();

    }
}
