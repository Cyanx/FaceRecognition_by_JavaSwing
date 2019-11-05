package cn.cyan.util.OpertareDBSets;

import cn.cyan.util.DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @Author: Cyan
 * @Date: 2019/5/29 22:48
 * 操作考勤表
 */
public class OperateDB4 {
    public static Vector getRows() {
        Vector rows = null;
        Vector columnHeads = null;

        try {
            //通过构造方法加载数据库驱动
            DB db = new DB();
            //执行SQL语句
            ResultSet rs = null;
            rs = db.executeQuery("select * from view_attendancechecking");


            if (rs.wasNull()) {
                System.out.println("记录为空");
            }

            rows = new Vector();

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                rows.addElement(getNextRow(rs, rsmd));
            }


        } catch (SQLException e) {
            System.out.println("未成功读取数据");
            e.printStackTrace();
        }

        return rows;
    }

    //得到数据库表头
    public static Vector getHead() {
        Vector columnHeads = null;

        try {
            DB db = new DB();
            ResultSet rs = db.executeQuery("select * from view_attendancechecking");

            boolean moreRecords = rs.next();
            if (!moreRecords) {
                System.out.println("记录为空");
            }

            columnHeads = new Vector();
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnHeads.addElement(rsmd.getColumnName(i));
            }

        } catch (SQLException e) {
            System.out.println("未成功打开数据库");
            e.printStackTrace();
        }

        return columnHeads;
    }


    //得到数据库中下一行数据
    private static Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Vector currentRow = new Vector();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            currentRow.addElement(rs.getString(i));
        }

        return currentRow;
    }


    //测试
    public static void main(String[] args) {
        getRows();
    }
}
