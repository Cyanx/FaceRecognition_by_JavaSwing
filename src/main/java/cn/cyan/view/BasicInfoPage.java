package cn.cyan.view;

import cn.cyan.util.DB;
import cn.cyan.util.OpertareDBSets.OperateDB;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

/**
 * @Author: Cyan
 * @Date: 2019/5/27 21:21
 * @Reference: https://blog.csdn.net/without_scruple/article/details/78635864#commentBox
 */
public class BasicInfoPage extends JFrame {

    DefaultTableModel tableModel;         //默认显示的表格
    JButton add, delete, exit, save;      //各处理按钮
    JTable jTable;                        //表格
    JPanel jPanel;   //增加信息的面板

    //构造函数
    public BasicInfoPage() {
        this.setBounds(500, 600, 800, 450);
        this.setTitle("基本信息界面");
        this.setLayout(new BorderLayout());
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

        //新建各按钮组件
        add = new JButton("增加");
        delete = new JButton("删除");
        exit = new JButton("保存");
        save = new JButton("退出");

        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(add);
        jPanel.add(delete);
        jPanel.add(save);
        jPanel.add(exit);

        //取得数据库的学生表的各行数据
        Vector rowData = OperateDB.getRows();
        //取得数据库的学生表的表头数据
        Vector columnNames = OperateDB.getHead();


        //新建表格
        tableModel = new DefaultTableModel(rowData, columnNames);
        jTable = new JTable(tableModel);

        JScrollPane jScrollPane = new JScrollPane(jTable);

        //将面板和表格分别添加到窗体中
        this.add(jPanel, BorderLayout.NORTH);
        this.add(jScrollPane);


    }

    //事件处理
    public void myEvent(){

        //增加
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //增加一行空白区域
                    tableModel.addRow(new Vector());
            }
        });

        //删除
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除指定行
                int rowcount = jTable.getSelectedRow();

                if (rowcount >= 0) {
                    tableModel.removeRow(rowcount);
                }
            }
        });

        /**
         * 保存
         * 原表格数据全部删除
         * 新表格中数据全部获取
         * 重新写入原表格
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = jTable.getColumnCount();
                int row = jTable.getRowCount();

                //value数组存放表格中的所有数据
                String[][] value = new String[row][column];

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        value[i][j] = jTable.getValueAt(i, j).toString();
                    }
                }

                //以下为数据库操作
                DB db = new DB();

                db.executeUpdate("delete from 学生表");

                for (int i = 0; i < row; i++) {
                    db.executeUpdate("insert into 学生表(" + value[i][0] + "," + value[i][1] + "," +
                            value[i][2] + "," + value[i][3] + "," + value[i][4] + ")");
                }

                System.out.println("操作已完成");

            }
        });

        //退出
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
    }

    //测试函数
    public static void main(String[] args) {
        BasicInfoPage basicInfoPage = new BasicInfoPage();
        basicInfoPage.setVisible(true);
        basicInfoPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
