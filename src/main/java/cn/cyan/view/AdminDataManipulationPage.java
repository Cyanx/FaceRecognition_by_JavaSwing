package cn.cyan.view;

import cn.cyan.util.DB;
import cn.cyan.util.OpertareDBSets.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.Vector;

/**
 * @Author: Cyan
 * @Date: 2019/5/31 9:34
 * 按照开发计划书 要实现的功能是
 * 实际上就是
 * 对 授课表 教师表 学生表 考勤表的操作
 * 一个界面显示多个表格
 * jtabbedpane 使用https://www.cnblogs.com/pianistedward/p/10138256.html
 */
public class AdminDataManipulationPage extends JFrame {

    private JPanel jPanelStudent;
    private JPanel jPanelTeacher;
    private JPanel jPanelAttendanceCheckingData;
    private JPanel jPanelClassesData;
    private JPanel jPanelClassesSet;
    private JComboBox jComboBoxTablesSelecting;
    private JTabbedPane jTabbedPane;
    private JButton jButtonSaveTheChange0;
    private JButton jButtonSaveTheChange1;
    private JButton jButtonSaveTheChange2;
    private JButton jButtonSaveTheChange30;
    private JButton jButtonSaveTheChange31;
    private JButton jButtonSaveTheChange32;
    private JButton jButtonAdd0;
    private JButton jButtonAdd1;
    private JButton jButtonAdd2;
    private JButton jButtonAdd30;
    private JButton jButtonAdd31;
    private JButton jButtonAdd32;
    private JButton jButtonDelete0;
    private JButton jButtonDelete1;
    private JButton jButtonDelete2;
    private JButton jButtonDelete30;
    private JButton jButtonDelete31;
    private JButton jButtonDelete32;
    private DefaultTableModel defaultTableModel0;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private DefaultTableModel defaultTableModel30;
    private DefaultTableModel defaultTableModel31;
    private DefaultTableModel defaultTableModel32;
    private JTable jTableStudent;
    private JTable jTableTeacher;
    private JTable jTableAttendaceChecking;
    private JTable jTableClassesData0;
    private JTable jTableClassesData1;
    private JTable jTableClassesData2;
    private JScrollPane jScrollPane0;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane30;
    private JScrollPane jScrollPane31;
    private JScrollPane jScrollPane32;

    public AdminDataManipulationPage() {
        this.setTitle("管理员系统数据管理");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(600, 200, 1200, 700);
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
            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
        } catch(Exception e) {
            //TODO exception
            e.printStackTrace();
        }

        jPanelStudent = new JPanel();
        jPanelTeacher = new JPanel();
        jPanelAttendanceCheckingData = new JPanel();
        jPanelClassesData = new JPanel();
        jComboBoxTablesSelecting = new JComboBox();
        jTabbedPane = new JTabbedPane();

        /**
         *   设置四个表格面板
         */

        //取得数据库的学生表的各行数据
        Vector rowDataStudent = OperateDB.getRows();
        //取得数据库的学生表的表头数据
        Vector columnNamesStudent = OperateDB.getHead();
        defaultTableModel0 = new DefaultTableModel(rowDataStudent, columnNamesStudent);
        jTableStudent = new JTable(defaultTableModel0);
        jTableStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int column_count = jTableStudent.getColumnCount();
        for (int i = 0; i < column_count; i++) {
            jTableStudent.getColumnModel().getColumn(i).setPreferredWidth(120);
        }
        jScrollPane0 = new JScrollPane(jTableStudent);
        jScrollPane0.setPreferredSize(new Dimension(800, 600));
        jButtonSaveTheChange0 = new JButton("提交并上传");
        jButtonAdd0 = new JButton("添加行");
        jButtonDelete0 = new JButton("删除指定行");
        jPanelStudent.add(jScrollPane0);
        jPanelStudent.add(jButtonSaveTheChange0);
        jPanelStudent.add(jButtonAdd0);
        jPanelStudent.add(jButtonDelete0);


        //取得数据库的教师表的各行数据
        Vector rowDataTeacher = OperateDB5.getRows();
        //取得数据库的教师表的表头数据
        Vector columnNamesTeacher = OperateDB5.getHead();
        defaultTableModel1 = new DefaultTableModel(rowDataTeacher, columnNamesTeacher);
        jTableTeacher = new JTable(defaultTableModel1);
        jScrollPane1 = new JScrollPane(jTableTeacher);
        jScrollPane1.setPreferredSize(new Dimension(800, 600));
        jButtonSaveTheChange1 = new JButton("提交并上传");
        jButtonAdd1 = new JButton("添加行");
        jButtonDelete1 = new JButton("删除指定行");
        jPanelTeacher.add(jScrollPane1);
        jPanelTeacher.add(jButtonSaveTheChange1);
        jPanelTeacher.add(jButtonAdd1);
        jPanelTeacher.add(jButtonDelete1);

        //取得数据库的考勤表的各行数据
        Vector rowAttendanceCheckingData = OperateDB4.getRows();
        //取得数据库的考勤表的表头数据
        Vector columnAttendanceCheckingData = OperateDB4.getHead();
        defaultTableModel2 = new DefaultTableModel(rowAttendanceCheckingData, columnAttendanceCheckingData);
        jTableAttendaceChecking = new JTable(defaultTableModel2);
        jScrollPane2 = new JScrollPane(jTableAttendaceChecking);
        jScrollPane2.setPreferredSize(new Dimension(800, 600));
        jButtonSaveTheChange2 = new JButton("提交并上传");
        jButtonAdd2 = new JButton("添加行");
        jButtonDelete2 = new JButton("删除指定行");
        jPanelAttendanceCheckingData.add(jScrollPane2);
        jPanelAttendanceCheckingData.add(jButtonSaveTheChange2);
        jPanelAttendanceCheckingData.add(jButtonAdd2);
        jPanelAttendanceCheckingData.add(jButtonDelete2);

        /**
         * 用下拉框实现三个表格切换
         */
        //取得数据库的课程相关的各行数据
        Vector rowClass0 = OperateDB2.getRows();//选课表
        //取得数据库的选课表的表头数据
        Vector columnClass0 = OperateDB2.getHead();//选课表
        defaultTableModel30 = new DefaultTableModel(rowClass0, columnClass0);
        jTableClassesData0 = new JTable(defaultTableModel30);
        jScrollPane30 = new JScrollPane(jTableClassesData0);
        jScrollPane30.setPreferredSize(new Dimension(800, 600));
        jButtonSaveTheChange30 = new JButton("提交并上传");
        jButtonAdd30 = new JButton("添加行");
        jButtonDelete30 = new JButton("删除指定行");

        //取得数据库的课程表的各行数据
        Vector rowClass1 = OperateDB3.getRows();//课程表
        //取得数据库的课程表的表头数据
        Vector columnClass1 = OperateDB3.getHead();//课程表
        defaultTableModel31 = new DefaultTableModel(rowClass1, columnClass1);
        jTableClassesData1 = new JTable(defaultTableModel31);
        jScrollPane31 = new JScrollPane(jTableClassesData1);
        jScrollPane31.setPreferredSize(new Dimension(800, 600));
        jButtonSaveTheChange31 = new JButton("提交并上传");
        jButtonAdd31 = new JButton("添加行");
        jButtonDelete31 = new JButton("删除指定行");


        //取得数据库的授课表相关的各行数据
        Vector rowClass2 = OperateDB6.getRows();//授课表
        //取得数据库的授课表的表头数据
        Vector columnClass2 = OperateDB6.getHead();//授课表
        defaultTableModel32 = new DefaultTableModel(rowClass2, columnClass2);
        jTableClassesData2 = new JTable(defaultTableModel32);
        jScrollPane32 = new JScrollPane(jTableClassesData2);
        jScrollPane32.setPreferredSize(new Dimension(800, 600));
        jButtonSaveTheChange32 = new JButton("提交并上传");
        jButtonAdd32 = new JButton("添加行");
        jButtonDelete32 = new JButton("删除指定行");

        jPanelClassesSet = new JPanel();
        String labels[] = {"选课表", "课程表", "授课表"};
        jComboBoxTablesSelecting = new JComboBox(labels);
        jComboBoxTablesSelecting.setEditable(false);
        jPanelClassesSet.add(jComboBoxTablesSelecting);
        jPanelClassesData.add(jScrollPane30);
        jPanelClassesData.add(jButtonSaveTheChange30);  //默认为选课表的监听按钮
        jPanelClassesData.add(jButtonAdd30);
        jPanelClassesData.add(jButtonDelete30);
        jPanelClassesSet.add(jPanelClassesData);


        jTabbedPane.addTab("学生表", jPanelStudent);
        jTabbedPane.addTab("教师表", jPanelTeacher);
        jTabbedPane.addTab("考勤表", jPanelAttendanceCheckingData);
        jTabbedPane.addTab("课程相关表", jPanelClassesSet);
        jTabbedPane.setSelectedComponent(jPanelStudent);

        this.add(jTabbedPane);
        this.setVisible(true);
        Event();

    }

    public void Event() {

        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前选中的选项卡" + jTabbedPane.getSelectedIndex());
            }
        });

        //用于测试
        jComboBoxTablesSelecting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected index = " + jComboBoxTablesSelecting.getSelectedIndex());
            }
        });

        /**
         * 增加删除行信息
         */
        //增加
        jButtonAdd0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //增加一行空白区域
                defaultTableModel0.addRow(new Vector());
            }
        });

        //删除
        jButtonDelete0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTableStudent.getSelectedRow();

                if (rowcount >= 0) {
                    defaultTableModel0.removeRow(rowcount);
                }
            }
        });

        //增加
        jButtonAdd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //增加一行空白区域
                defaultTableModel1.addRow(new Vector());
            }
        });

        //删除
        jButtonDelete1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTableTeacher.getSelectedRow();

                if (rowcount >= 0) {
                    defaultTableModel1.removeRow(rowcount);
                }
            }
        });

        //增加
        jButtonAdd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //增加一行空白区域
                defaultTableModel2.addRow(new Vector());
            }
        });

        //删除
        jButtonDelete2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTableAttendaceChecking.getSelectedRow();

                if (rowcount >= 0) {
                    defaultTableModel2.removeRow(rowcount);
                }
            }
        });

        //增加
        jButtonAdd30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //增加一行空白区域
                defaultTableModel30.addRow(new Vector());
            }
        });

        //删除
        jButtonDelete30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTableClassesData0.getSelectedRow();

                if (rowcount >= 0) {
                    defaultTableModel30.removeRow(rowcount);
                }
            }
        });

        //增加
        jButtonAdd31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //增加一行空白区域
                defaultTableModel31.addRow(new Vector());
            }
        });

        //删除
        jButtonDelete31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTableClassesData1.getSelectedRow();

                if (rowcount >= 0) {
                    defaultTableModel31.removeRow(rowcount);
                }
            }
        });

        //增加
        jButtonAdd32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //增加一行空白区域
                defaultTableModel32.addRow(new Vector());
            }
        });

        //删除
        jButtonDelete32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTableClassesData2.getSelectedRow();

                if (rowcount >= 0) {
                    defaultTableModel32.removeRow(rowcount);
                }
            }
        });



        jButtonSaveTheChange0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableStudent.getColumnCount();
                int row = jTableStudent.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableStudent.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from student");

                for (int i = 0; i < row; i++) {
                    db.executeUpdate("insert into student values(" + "'" + value[i][0] + "'" + "," +  "'" + value[i][1]+ "'"  + "," +
                            "'" + value[i][2] + "'" + "," +  "'" + value[i][3] + "'" + "," +  "'" +value[i][4] + "'" + "," + "'" +
                            value[i][5] + "'" + "," + "'" + value[i][6] + "'" + "," + "'" + value[i][7] + "'" +
                            "," +  "'" +value[i][8] +  "'" +  "," + "'" + value[i][9] + "'"+ "," + "" + value[i][10] + ""+ "," + "'" + value[i][11] + "'"
                           +")");
                    System.out.println("insert into student values(" + "'" + value[i][0] + "'" + "," +  "'" + value[i][1]+ "'"  + "," +
                            "'" + value[i][2] + "'" + "," +  "'" + value[i][3] + "'" + "," +  "'" +value[i][4] + "'" + "," + "'" +
                            value[i][5] + "'" + "," + "'" + value[i][6] + "'" + "," + "'" + value[i][7] + "'" +
                            "," +  "'" +value[i][8] +  "'" +  "," + "'" + value[i][9] + "'"+ "," + "" + value[i][10] + ""+ "," + "'" + value[i][11] + "'"
                            +")");
                }

                System.out.println("操作已完成");
            }
        });

        jButtonSaveTheChange1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableTeacher.getColumnCount();
                int row = jTableTeacher.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableTeacher.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from teacher");

                for (int i = 0; i < row; i++) {
                    db.executeUpdate("insert into teacher values(" + "'" + value[i][0] + "'" + "," +  "'" + value[i][1]+ "'"  + "," +
                            "'" + value[i][2] + "'" + "," +  "'" + value[i][3] + "'" + "," +  "'" +value[i][4] + "'" + "," + "'" +
                            value[i][5] + "'" + "," + "'" + value[i][6] + "'" + "," + "'" + value[i][7] + "'" +
                            "," +  "'" +value[i][8] +  "'" +")");
                    System.out.println("insert into teacher values(" + "'" + value[i][0] + "'" + "," +  "'" + value[i][1]+ "'"  + "," +
                            "'" + value[i][2] + "'" + "," +  "'" + value[i][3] + "'" + "," +  "'" +value[i][4] + "'" + "," + "'" +
                            value[i][5] + "'" + "," + "'" + value[i][6] + "'" + "," + "'" + value[i][7] + "'" +
                            "," +  "'" +value[i][8] +  "'" +")");
                }

                System.out.println("操作已完成");
            }
        });

        jButtonSaveTheChange2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableAttendaceChecking.getColumnCount();
                int row = jTableAttendaceChecking.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableAttendaceChecking.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

//                db.executeUpdate("delete from 考勤表");

                for (int i = 0; i < row; i++) {
//                    db.executeUpdate("insert into 考勤表 values(" + "'" + value[i][0] + "'" + "," +  "'" + value[i][1]+ "'"  + "," +
//                            "'" + value[i][2] + "'" + "," +  "'" + value[i][3] + "'" + "," +  "'" +value[i][4] + "'" + ")");
//                    System.out.println("insert into 考勤表 values(" + "'" + value[i][0] + "'" + "," +  "'" + value[i][1]+ "'"  + "," +
//                            "'" + value[i][2] + "'" + "," +  "'" + value[i][3] + "'" + "," +  "'" +value[i][4] + "'" + ")");
                    db.executeUpdate("update student set " +
                            "student_id = '" + value[i][0] + "'," +
                            "student_name='" + value[i][1] + "'," +
                            "student_flag='" + value[i][2] + "'," +
                            "student_lastchecktime='" + value[i][3] + "'," +
                            "student_remark='" + value[i][4] + "'" + " " +
                            "where student_id='" + value[i][0] + "'");
                }

                System.out.println("操作已完成");
            }
        });

        jButtonSaveTheChange30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableClassesData0.getColumnCount();
                int row = jTableClassesData0.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableClassesData0.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from classselecting");

                for (int i = 0; i < row; i++) {
                    db.executeUpdate("insert into classselecting values(" + "'" + value[i][0] + "'" + "," + "'" +
                                        value[i][1] + "'" + "," + "'" + value[i][2] + "'" +  ")");
                    System.out.println("insert into classselecting values(" + "'" + value[i][0] + "'" + "," + "'" +
                            value[i][1] + "'" + "," + "'" + value[i][2] + "'" +  ")");
                }

                System.out.println("操作已完成");
            }
        });

        jButtonSaveTheChange31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableClassesData1.getColumnCount();
                int row = jTableClassesData1.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableClassesData1.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from classes");

                for (int i = 0; i < row; i++) {
                                db.executeUpdate("insert into classes values(" + "'" + value[i][0] + "'" + "," + "'" +
                                        value[i][1] + "'" + "," + "'" + value[i][2] + "'" + "," + "'" + value[i][3] + "'" +  ")");
                    System.out.println("insert into classes values(" + "'" + value[i][0] + "'" + "," + "'" +
                            value[i][1] + "'" + "," + "'" + value[i][2] + "'" + "," + "'" + value[i][3] + "'" +  ")");
                }

                System.out.println("操作已完成");
            }
        });

        jButtonSaveTheChange32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTableClassesData2.getColumnCount();
                int row = jTableClassesData2.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTableClassesData2.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from 授课表");

                for (int i = 0; i < row; i++) {
                                db.executeUpdate("insert into 授课表 values(" + "'" + value[i][0] + "'" + "," + "'" +
                                        value[i][1] + "'" +  ")");
                    System.out.println("insert into 授课表 values(" + "'" + value[i][0] + "'" + "," + "'" +
                            value[i][1] + "'" +  ")");
                }

                System.out.println("操作已完成");
            }
        });

        /**
         * 重绘JPANEL以后不需要重新add
         * 不然会出现不想要的结果！！！
         * invalidate() & revalidate()重绘实现动态刷新
         */
        jComboBoxTablesSelecting.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = jComboBoxTablesSelecting.getSelectedIndex();

                if (index == 0) {
                    jPanelClassesData.invalidate();
                    jPanelClassesData.removeAll();
//                    jPanelClassesData.repaint();
                    jPanelClassesData.revalidate();
                    jPanelClassesData.add(jScrollPane30);
                    jPanelClassesData.add(jButtonSaveTheChange30);
                    jPanelClassesData.add(jButtonAdd30);
                    jPanelClassesData.add(jButtonDelete30);
//                    jPanelClassesSet.add(jPanelClassesData);
//                    jTabbedPane.add("ClassesData", jPanelClassesSet);

                } else if (index == 1) {
                    jPanelClassesData.invalidate();
                    jPanelClassesData.removeAll();
//                    jPanelClassesData.repaint();
                    jPanelClassesData.revalidate();
                    jPanelClassesData.add(jScrollPane31);
                    jPanelClassesData.add(jButtonSaveTheChange31);
                    jPanelClassesData.add(jButtonAdd31);
                    jPanelClassesData.add(jButtonDelete31);
//                    jPanelClassesSet.add(jPanelClassesData);
//                    jTabbedPane.add("ClassesData", jPanelClassesSet);
                } else if (index == 2) {
                    jPanelClassesData.invalidate();
                    jPanelClassesData.removeAll();
//                    jPanelClassesData.repaint();
                    jPanelClassesData.revalidate();
                    jPanelClassesData.add(jScrollPane32);
                    jPanelClassesData.add(jButtonSaveTheChange32);
                    jPanelClassesData.add(jButtonAdd32);
                    jPanelClassesData.add(jButtonDelete32);
//                    jPanelClassesSet.add(jPanelClassesData);
//                    jTabbedPane.add("ClassesData", jPanelClassesSet);
                }
            }
        });
    }


    public static void main(String[] args) {
        AdminDataManipulationPage adminDataManipulationPage = new AdminDataManipulationPage();
    }
}
