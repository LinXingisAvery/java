package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Purcharse;
import util.DBUtity;

/**
 * @author:李祖林
 * @description:对采购信息的操作
 * @date:2017年6月8日下午12:06:40
 */
public class PurcharseDao {
	/** 采购信息的添加 */
	public void add(Purcharse p) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "insert into purcharse values(?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getP_id());
			pst.setString(2, p.getG_id());
			pst.setString(3, p.getG_name());
			pst.setDouble(4, p.getP_price());
			pst.setString(5, p.getP_date());
			pst.setInt(6, p.getP_number());
			pst.setString(7, p.getP_unit());
			pst.setString(8, p.getP_supplier());
			// 执行sql语句
			pst.executeUpdate();
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 采购信息的修改 */
	public void updatePurcharse(Purcharse p) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "update purcharse set p_price=?,p_number=?,p_supplier=? where p_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, p.getP_price());
			pst.setInt(2, p.getP_number());
			pst.setString(3, p.getP_supplier());
			pst.setString(4, p.getP_id());
			int b = pst.executeUpdate();
			if (b > 0) {
				System.out.println("修改成功");
			}
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 采购信息全部查询 */
	public void searchPurcharse() {
		List<Purcharse> purcharseList = new ArrayList<Purcharse>();
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from purcharse";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// 查出的结果放入集合
			while (rs.next()) {
				Purcharse p = new Purcharse();
				p.setG_id(rs.getString("g_id"));
				p.setP_id(rs.getString("p_id"));
				p.setG_name(rs.getString("g_name"));
				p.setP_price(rs.getDouble("p_price"));
				p.setP_date(rs.getString("p_date"));
				p.setP_supplier(rs.getString("p_supplier"));
				p.setP_number(rs.getInt("p_number"));
				p.setP_unit(rs.getString("p_unit"));
				purcharseList.add(p);
			}
			DBUtity.closeConnection(conn);
			// 遍历集合输出结果
			for (Purcharse p : purcharseList) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 精确查询 */
	public void searchPurcharse(String id) {
		try {
			Purcharse p = new Purcharse();// 用于精确查询存放数据
			Connection conn = DBUtity.openConnection();
			String sql = "select *from purcharse where p_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				p.setG_id(rs.getString("g_id"));
				p.setP_id(rs.getString("p_id"));
				p.setG_name(rs.getString("g_name"));
				p.setP_price(rs.getDouble("p_price"));
				p.setP_date(rs.getString("p_date"));
				p.setP_supplier(rs.getString("p_supplier"));
				p.setP_number(rs.getInt("p_number"));
				p.setP_unit(rs.getString("p_unit"));
			}
			System.out.println(p);
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 模糊查询 返回结果是一个集合
	 */
	public void searchPurcharse1(String name) {
		List<Purcharse> purcharseList = new ArrayList<Purcharse>();
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from purcharse where g_name like '%" + name + "%'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Purcharse p = new Purcharse();
				p.setG_id(rs.getString("g_id"));
				p.setP_id(rs.getString("p_id"));
				p.setG_name(rs.getString("g_name"));
				p.setP_price(rs.getDouble("p_price"));
				p.setP_date(rs.getString("p_date"));
				p.setP_supplier(rs.getString("p_supplier"));
				p.setP_number(rs.getInt("p_number"));
				p.setP_unit(rs.getString("p_unit"));
				purcharseList.add(p);// 添加进集合
			}
			DBUtity.closeConnection(conn);
			// 遍历集合输出结果
			for (Purcharse p : purcharseList) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
