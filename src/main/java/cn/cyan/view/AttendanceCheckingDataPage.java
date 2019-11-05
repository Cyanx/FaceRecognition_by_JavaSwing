package cn.cyan.view;

import cn.cyan.util.DB;
import cn.cyan.util.OpertareDBSets.OperateDB4;
import jdk.nashorn.internal.ir.CatchNode;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @Author: Cyan
 * @Date: 2019/5/29 22:31
 */
public class AttendanceCheckingDataPage extends JFrame {

    private String userAccount;
    private String userPassword;
    private DefaultTableModel defaultTableModel;
    private JTable jTableAttendanceData;
    private JScrollPane jScrollPaneData;
    private JButton jButtonSaveTheChange;
    private JButton jButtonCheckAmount;
    private JPanel jPanelBasicInfo;
    private JComboBox jComboBoxselectcheckingtable;
    private JPanel jPaneljcombox;
    private JPanel jPanelTable;

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

    public AttendanceCheckingDataPage() {

        this.setTitle("考勤后台界面");
        this.setLayout(new BorderLayout());
        this.setBounds(600, 200, 800, 320);
        this.setLocationRelativeTo(null);
        //修改左上角Java图标
        URL imgURL = this.getClass().getResource("/viewingImg/titleicon.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);
        Image image = imageIcon.getImage();
        this.setIconImage(image);

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch(Exception e) {
            //TODO exception
            e.printStackTrace();
        }

        jPanelBasicInfo = new JPanel();


//        jPanelBasicInfo.setPreferredSize(new Dimension(720, 280));

        /**
         * 考勤数据表格
         */
//        //取得数据库的考勤表的各行数据
//        Vector rowData = OperateDB4.getRows();
//        //取得数据库的考勤表的表头数据
//        Vector columnNames = OperateDB4.getHead();
//
//        //新建表格
//        defaultTableModel = new DefaultTableModel(rowData, columnNames);
//        jTableAttendanceData = new JTable(defaultTableModel);
//        jScrollPaneData = new JScrollPane(jTableAttendanceData);
        jPaneljcombox = new JPanel();
        jPanelTable = new JPanel();

        jButtonSaveTheChange = new JButton("添加备注");
        jButtonCheckAmount = new JButton("查看未考勤人数");
        jButtonCheckAmount.setBorder(null);
        jButtonCheckAmount.setContentAreaFilled(true);
        jButtonCheckAmount.setFocusPainted(false);
        jPaneljcombox.add(jButtonCheckAmount);
        jButtonSaveTheChange.setContentAreaFilled(true);
        jButtonSaveTheChange.setBorder(null);

        //将面板和表格分别添加到窗体中
//        this.add(jScrollPaneData, BorderLayout.CENTER);
        this.add(jButtonSaveTheChange, BorderLayout.EAST);
        this.add(jPaneljcombox, BorderLayout.WEST);
        this.add(jPanelTable, BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    //接收教师功能选择页面账户
    //接下来实现表的展示
    public void receTheAccountInfo(final AttendanceCheckingDataPage attendanceCheckingDataPage) {
        final String account = attendanceCheckingDataPage.getUserAccount();
        final String pwd = attendanceCheckingDataPage.getUserPassword();

        //测试传入参数
        System.out.println(account);
        System.out.println(pwd);


        /**
         * 通过数据库视图查登录教师所授课课程
         */
        try {
            DB db = new DB();
            ResultSet rs = null;
            int class_account = 0;
            ArrayList labels = new ArrayList();

            rs = db.executeQuery("select class_id from view_selectcheckingclass where class_teacher_id = '" + account + "'");
            if (rs.wasNull()) {
                System.out.println("获取数据失败为空！");
            } else {
                while (rs.next()) {
                    class_account++;
                    String reslut = rs.getString("class_id");
                    labels.add(reslut);
                }
            }
            String[] strLabel = (String[]) labels.toArray(new String[0]);
            jComboBoxselectcheckingtable = new JComboBox(strLabel);
            jComboBoxselectcheckingtable.setEditable(false);
            jComboBoxselectcheckingtable.setSelectedIndex(0);
            jPaneljcombox.add(jComboBoxselectcheckingtable);

            /**
             * 设置默认的选项对应的考勤table
             */
            final String defaultItem = jComboBoxselectcheckingtable.getSelectedItem().toString();
            try {
                DB db2 = new DB();
                ResultSet rs2 = null;

                System.out.println("here");
                rs2 = db.executeQuery("select s_id, s_name, s_flag, s_lastcheck, s_remark from view_attendancechecking where s_classid = '" + defaultItem + "'");
//                    System.out.println("here");
                if (rs2.wasNull()) {
                    System.out.println("获取数据失败为空！");
                } else {
                    Vector<String> columnName = new Vector<String>();//字段名
                    Vector<Vector<Object>> dataVector = new
                            Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
                    columnName.add("学生学号");
                    columnName.add("学生姓名");
                    columnName.add("签到签退标志");
                    columnName.add("最近一次考勤");
                    columnName.add("备注");
//                        System.out.println("here2");

                    try {
                        while (rs2.next()) {
                            Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
                            for (int i = 1; i <= 5; i++) {
                                vec.add(rs2.getObject(i));
                                String test = rs2.getObject(i).toString();
                                System.out.println(test);
                            }
                            dataVector.add(vec);
                        }
                        defaultTableModel = new DefaultTableModel();//建立默认的JTable模型
                        defaultTableModel.setDataVector(dataVector, columnName);//设定模型数据和字段
                        jTableAttendanceData = new JTable(defaultTableModel);
                        jScrollPaneData = new JScrollPane(jTableAttendanceData);

                        jPanelTable.invalidate();
                        jPanelTable.removeAll();
                        jPanelTable.revalidate();
                        jPanelTable.add(jScrollPaneData);
                        attendanceCheckingDataPage.add(jPanelTable, BorderLayout.CENTER);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * 下拉框事件处理
         * 根据课程调用视图查询该课程的选课学生生成考勤表
         * 根据不同课程重绘表格
         * 放borderlayout的center
         */
        final Object[] objects = new Object[1];
        jComboBoxselectcheckingtable.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                //解决关于JComboBox触发事件总是执行两次的问题
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String class_id = jComboBoxselectcheckingtable.getSelectedItem().toString();
                    objects[0] = class_id;
                    //测试下拉框的选项内容
                    System.out.println("选择的是" + class_id);
                    System.out.println(objects[0].toString());

                    try {
                        DB db = new DB();
                        ResultSet rs = null;

                        System.out.println("here");
                        rs = db.executeQuery("select s_id, s_name, s_flag, s_lastcheck, s_remark from view_attendancechecking where s_classid = '" + class_id + "'");
//                    System.out.println("here");
                        if (rs.wasNull()) {
                            System.out.println("获取数据失败为空！");
                        } else {
                            Vector<String> columnName = new Vector<String>();//字段名
                            Vector<Vector<Object>> dataVector = new
                                    Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
                            columnName.add("学生学号");
                            columnName.add("学生姓名");
                            columnName.add("签到签退标志");
                            columnName.add("最近一次考勤");
                            columnName.add("备注");
//                        System.out.println("here2");

                            try {
                                while (rs.next()) {
                                    Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
                                    for (int i = 1; i <= 5; i++) {
                                        vec.add(rs.getObject(i));
                                        String test = rs.getObject(i).toString();
                                        System.out.println(test);
                                    }
                                    dataVector.add(vec);
                                }
                                defaultTableModel = new DefaultTableModel();//建立默认的JTable模型
                                defaultTableModel.setDataVector(dataVector, columnName);//设定模型数据和字段
                                jTableAttendanceData = new JTable(defaultTableModel);
                                jScrollPaneData = new JScrollPane(jTableAttendanceData);

                                jPanelTable.invalidate();
                                jPanelTable.removeAll();
                                jPanelTable.revalidate();
                                jPanelTable.add(jScrollPaneData);
                                attendanceCheckingDataPage.add(jPanelTable, BorderLayout.CENTER);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            }

        });

        /**
         * 添加备注也就是修改
         */
        jButtonSaveTheChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableAttendanceData.getColumnCount();
                int row = jTableAttendanceData.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableAttendanceData.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

//                db.executeUpdate("delete from view_attendancechecking where s_classid =" + objects[0].toString());

                for (int i = 0; i < row; i++) {
                    db.executeUpdate("update student set " +
                            "student_id = '" + value[i][0] + "'," +
                            "student_name='" + value[i][1] + "'," +
                            "student_flag='" + value[i][2] + "'," +
                            "student_lastchecktime='" + value[i][3] + "'," +
                            "student_remark='" + value[i][4] + "'" + " " +
                            "where student_id='" + value[i][0] + "'");
//                    System.out.println("update student set " +
//                            "student_id = '" + value[i][0] + "',"+
//                            "student_name='"+ value[i][1] + "',"+
//                            "student_flag='" + value[i][2]+ "',"+
//                            "student_lastchecktime='"+ value[i][3]+ "',"+
//                            "student_remark='" + value[i][4]+ "'"+ " " +
//                            "where student_id='" + value[i][0] + "'");
                }

                System.out.println("操作已完成");
            }
        });

        /**
         * 显示有多少人未签到
         */
        jButtonCheckAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DB db = new DB();
                    ResultSet rs = null;

                    String class_id = jComboBoxselectcheckingtable.getSelectedItem().toString();
                    System.out.println("查看为考勤人数");
                    rs = db.executeQuery("select count(s_id) from view_attendancechecking where s_classid = '" + class_id + "' and s_flag = 0");
                    if (rs.wasNull()) {
                        System.out.println("获取数据失败为空！");
                    } else {
                        rs.next();
                        int student_count = rs.getInt("count(s_id)");
                        System.out.println("未考勤人数；" + student_count);
                        JOptionPane.showMessageDialog(null, "未考勤人数 " + student_count);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    //单元测试
    public static void main(String[] args) {
        new AttendanceCheckingDataPage();
    }
}

