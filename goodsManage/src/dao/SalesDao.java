package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Goods;
import domain.Sales;
import util.DBUtity;

/**
 * @author:李祖林
 * @description:销售信息添加
 * @date:2017年6月9日上午9:43:37
 */
public class SalesDao {
	/** 销售信息的添加 */
	public void addSeles(Sales sale) {
		try {
			// 判读销售数量是否>库存如果大于不应该销售
			GoodsDao gs = new GoodsDao();
			Goods g = gs.search(sale.getG_id());
			System.out.println("************商品销售前库存余量信息************");
			System.out.println(g);
			System.out.println("***************************************");
			if (g.getG_number() >= sale.getS_number()) {// 如果库存量大于等于销售量允许销售
				Connection conn = DBUtity.openConnection();
				String sql = "insert into sales values(?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, sale.getS_id());
				pst.setString(2, sale.getG_id());
				pst.setString(3, sale.getG_name());
				pst.setDouble(4, sale.getS_privce());
				pst.setString(5, sale.getS_date());
				pst.setInt(6, sale.getS_number());
				pst.executeUpdate();
				DBUtity.closeConnection(conn);
				gs.update(sale);// 成功销售减库存
				System.out.println("************商品销售后库存余量信息************");
				System.out.println(g);
				System.out.println("***************************************");
			} else {
				System.out.println("操作失败库存不足以销售");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/** 销售信息全部查询 */
	public void serchSales() {
		List<Sales> salesList = new ArrayList<Sales>();
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from sales";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sales s = new Sales();
				s.setG_id(rs.getString("g_id"));
				s.setG_name(rs.getString("g_name"));
				s.setS_date(rs.getString("s_date"));
				s.setS_id(rs.getString("s_id"));
				s.setS_number(rs.getInt("s_number"));
				s.setS_privce(rs.getDouble("s_price"));
				salesList.add(s);
			}
			DBUtity.closeConnection(conn);
			for (Sales s : salesList) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 精确查询 */
	public void serchSales(String id) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from sales where s_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			Sales s = new Sales();
			while (rs.next()) {
				s.setG_id(rs.getString("g_id"));
				s.setG_name(rs.getString("g_name"));
				s.setS_date(rs.getString("s_date"));
				s.setS_id(rs.getString("s_id"));
				s.setS_number(rs.getInt("s_number"));
				s.setS_privce(rs.getDouble("s_price"));
			}
			System.out.println(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 模糊查询 */
	public void serchSales1(String name) {
		List<Sales> salesList = new ArrayList<Sales>();
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from sales where g_name like '%" + name + "%'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Sales s = new Sales();
				s.setG_id(rs.getString("g_id"));
				s.setG_name(rs.getString("g_name"));
				s.setS_date(rs.getString("s_date"));
				s.setS_id(rs.getString("s_id"));
				s.setS_number(rs.getInt("s_number"));
				s.setS_privce(rs.getDouble("s_price"));
				salesList.add(s);
			}
			DBUtity.closeConnection(conn);
			for (Sales s : salesList) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 通过id删除 */
	public void delete(String id) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "delete from Sales where s_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			if (!pst.execute()) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败，请检查id是否正确");
			}
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
