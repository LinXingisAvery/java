package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Goods;
import domain.Purcharse;
import domain.Sales;
import util.DBUtity;

public class GoodsDao {
	/**
	 * 商品信息添加 将采购来的商品添加进商品信息表 逻辑分析：假如同一商品采购了两次g_number=原数据库中数据+本次数量
	 */
	public void add(Purcharse p) {
		try {
			Connection conn = DBUtity.openConnection();
			// 如果商品信息存在就要修改 如果不存在 采购来的商品就要添加到商品信息表里
			Goods goods = search(p.getG_id());// 通过采购信息表里的g_id(商品id查)
			if (goods.getG_id() != null && goods.getG_name().equals(p.getG_name())) {
				String sql = "update goods set g_number=g_number+ ? where g_id=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, p.getP_number());
				pst.setString(2, goods.getG_id());
				// 执行sql语句
				pst.executeUpdate();
				// id相同商品名称不同不是同一商品
			} else if (goods.getG_id() != null && !goods.getG_name().equals(p.getG_name())) {
				System.out.println("不是同一商品  该商品id对应的商品已存在，" + "请更改新的商品id作为新的商品的id");
			} else {
				String sql = "insert into goods(g_id,g_name,g_number,g_unit) values(?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, p.getG_id());
				pst.setString(2, p.getG_name());
				pst.setInt(3, p.getP_number());
				pst.setString(4, p.getP_unit());
				pst.executeUpdate();
			}
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 精确查询 通过id查询
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public Goods search(String g_Id) {
		Goods goods = new Goods();
		;
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from goods where g_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, g_Id);
			ResultSet rs = pst.executeQuery();
			// 精确查询结果为1条数据信息为一个结果集
			// 将查出的结果集放到一个局部变量 goods对象中便于输出(Goods类重写了toString())和返回
			while (rs.next()) {
				goods.setG_id(rs.getString("g_id"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_number(rs.getInt("g_number"));
				goods.setG_unit(rs.getString("g_unit"));
				goods.setG_status(rs.getInt("g_status"));
			}
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return goods;
		}
	}

	/** 全部查询 */
	public void search() {
		List<Goods> goodList = new ArrayList<Goods>();
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "select *from goods";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// 查出的结果放入集合
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setG_id(rs.getString("g_id"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_number(rs.getInt("g_number"));
				goods.setG_unit(rs.getString("g_unit"));
				goods.setG_status(rs.getInt("g_status"));
				goodList.add(goods);
			}
			DBUtity.closeConnection(conn);
			// 遍历集合输出结果
			for (Goods g : goodList) {
				System.out.println(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 模糊查询 通过商品名称相似 如 水杯 水瓶
	public void search1(String name) {
		try {
			List<Goods> goodList = new ArrayList<Goods>();
			Connection conn = DBUtity.openConnection();
			String sql = "select *from goods where g_name like " + "'%" + name + "%'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// 精确查询结果为1条数据信息为一个结果集
			// 将查出的结果集放到一个局部变量 goods对象中便于输出(Goods类重写了toString())和返回
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setG_id(rs.getString("g_id"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_number(rs.getInt("g_number"));
				goods.setG_unit(rs.getString("g_unit"));
				goods.setG_status(rs.getInt("g_status"));
				goodList.add(goods);
			}
			DBUtity.closeConnection(conn);
			// 遍历集合输出结果
			for (Goods g : goodList) {
				System.out.println(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品信息的修改 通过商品id将查出的数据修改 传一个Goods的对象 通过id找到数据库表中 信息对信息进行修改
	 */
	public void update(Goods goods) {
		Connection conn;
		try {
			conn = DBUtity.openConnection();
			String sql = "update goods set g_name=?,g_number=?,g_unit=?,g_status=? where g_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, goods.getG_name());
			pst.setInt(2, goods.getG_number());
			pst.setString(3, goods.getG_unit());
			pst.setInt(4, goods.getG_status());
			pst.setString(5, goods.getG_id());
			int b = pst.executeUpdate();
			if (b > 0) {
				System.out.println("修改成功");
				// 显示所有商品信息
				search();
			} else {
				System.out.println("修改失败");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 商品销售后减库存 */
	public void update(Sales s) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "update goods set g_number=g_number-? where g_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, s.getS_number());
			pst.setString(2, s.getG_id());
			pst.executeUpdate();
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 删除 */
	public void delete(String id) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "delete from goods where g_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			if (!pst.execute()) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败请检查商品id号是否正确");
			}
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 商品信息添加 */
	public void add(Goods good) {
		try {
			Connection conn = DBUtity.openConnection();
			String sql = "insert into goods values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, good.getG_id());
			pst.setString(2, good.getG_name());
			pst.setInt(3, good.getG_number());
			pst.setString(4, good.getG_unit());
			pst.setInt(5, good.getG_status());
			if (!pst.execute()) {
				System.out.println("添加成功");
				search();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
