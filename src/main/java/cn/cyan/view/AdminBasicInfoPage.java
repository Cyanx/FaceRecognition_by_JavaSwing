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

/**
 * @Author: Cyan
 * @Date: 2019/5/31 9:01
 */
public class AdminBasicInfoPage extends JFrame {
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

    public AdminBasicInfoPage() {

        this.setTitle("考勤系统管理员界面");
        this.setLayout(new GridLayout(1, 1));
        this.setBounds(600, 200, 720, 320);
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
        jTablePersonalInfo = new JTable(6, 2);
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
    public void receTheAccountInfo(final AdminBasicInfoPage adminBasicInfoPage) {
        final String account = adminBasicInfoPage.getUserAccount();
        final String pwd = adminBasicInfoPage.getUserPassword();

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

            rs = db.executeQuery("select * from admin where admin_id = '" + account + "'");
            //
            System.out.println("select * from admin where admin_id = '" + account + "'");

            if (rs.wasNull()) {
                System.out.println("获取数据失败为空！");
            } else {
                /**
                 * 接下来获取数据展示在表格中
                 */
                String attribute_id = null;
                String attribute_name = null;
                String attribute_sex = null;
                String attribute_position = null;
                String attribute_department = null;
                String loginpassword = null;

                rs.next();

                attribute_id = rs.getString("admin_id");
                attribute_name = rs.getString("admin_name");
                attribute_sex = rs.getString("admin_sex");
                attribute_position = rs.getString("admin_position");
                attribute_department = rs.getString("admin_department");
                loginpassword = rs.getString("admin_pwd");

                System.out.println(attribute_id);
                System.out.println(attribute_name);
                System.out.println(attribute_sex);
                System.out.println(attribute_position);
                System.out.println(attribute_department);
                System.out.println(loginpassword);

                jTablePersonalInfo.setValueAt("职工号", 0, 0);
                jTablePersonalInfo.setValueAt("姓名", 1, 0);
                jTablePersonalInfo.setValueAt("性别", 2, 0);
                jTablePersonalInfo.setValueAt("职位", 3, 0);
                jTablePersonalInfo.setValueAt("部门", 4, 0);
                jTablePersonalInfo.setValueAt("密码", 5, 0);


                jTablePersonalInfo.setValueAt(attribute_id, 0, 1);
                jTablePersonalInfo.setValueAt(attribute_name, 1, 1);
                jTablePersonalInfo.setValueAt(attribute_sex, 2, 1);
                jTablePersonalInfo.setValueAt(attribute_position, 3, 1);
                jTablePersonalInfo.setValueAt(attribute_department, 4, 1);
                jTablePersonalInfo.setValueAt(loginpassword, 5, 1);

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

                db.executeUpdate("delete from admin where admin_id = '" + account + "'");


                db.executeUpdate("insert into admin values(" + "'" + value[0] + "'" + "," +  "'" + value[1]+ "'"  + "," +
                        "'" + value[2] + "'" + "," +  "'" + value[3] + "'" + "," +  "'" +value[4] + "'" + "," + "'" +
                        value[5] + "'" + ")");
                //测试SQL语句
                System.out.println("insert into admin values(" + "'" + value[0] + "'" + "," +  "'" + value[1]+ "'"  + "," +
                        "'" + value[2] + "'" + "," +  "'" + value[3] + "'" + "," +  "'" +value[4] + "'" + "," + "'" +
                        value[5] + "'" + ")");


                System.out.println("操作已完成");
            }
        });


    }


    //单元测试
    public static void main(String[] args) {
        AdminBasicInfoPage adminBasicInfoPage = new AdminBasicInfoPage();
    }
}

