package cn.cyan.util;

import javax.swing.*;
import java.sql.*;

public class DB {
	/*
	 * jdbc驱动要能够正确的编码和解码（java语言内存使用unicode编码）        插入数据时（编码），查询数据时(解码)      
	 *  jdbc
	 * :mysql://localhost:3306/jsd1406db？useUnicode=true&characterEncoding=utf8
	 *        通知数据库如何编码
	 */
	private final String url = "jdbc:mysql://localhost:3306/acs?useUnicode=true&characterEncoding=utf8";
	private final String userName = "root";
	private final String password = "xly520lisa";
	private Connection con = null;
	private Statement stm = null;

	/* 通过构造方法加载数据库驱动 */
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); // 加载com.mysql.jdbc.Driver(初始化类)这个类然后创建实例化Object对象
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载数据库驱动失败！");
		}
	}

	/* 创建数据库连接 */
	public void createCon() {
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取数据库连接失败！");
		}
	}

	/* 获取Statement对象 */
	public void getStm() {
		createCon();
		try {
			stm = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建Statement对象失败！");
		}
	}

	/**
	 * @功能 对数据库的增加、修改和删除的操作
	 * @参数 sql为要执行的SQL语句
	 * @返回值 boolean型值
	 */
	public boolean executeUpdate(String sql) {
		System.out.println(sql);
		boolean mark = false;
		Savepoint sp = null; //事务回滚

		try {
			getStm();
			sp = con.setSavepoint();
			int iCount = stm.executeUpdate(sql);
			if (iCount > 0)
				mark = true;
			else if (iCount <= 0) {
				mark = false;
				con.rollback(sp);
				con.commit();
				JOptionPane.showMessageDialog(null,"数据库数据操作出错，执行事务回滚，请检查，数据输入不得为空！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			mark = false;
			JOptionPane.showMessageDialog(null,"数据库操作异常，请检查，数据输入不得为空");
		}
		return mark;
	}

	/* 查询数据库 */
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			getStm();
			try {
				rs = stm.executeQuery(sql);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("查询数据库失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	/* 关闭数据库的操作 */
	public void closed() {
		if (stm != null)
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭stm对象失败！");
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭con对象失败！");
			}
	}
}
