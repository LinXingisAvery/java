package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtity;

/**
 * @author:李祖林
 * @description:报表
 * @date:2017年6月10日下午12:24:06
 */
public class SalesReportin {
	/**查出采购总量  销售总量 剩余库存*/
	public int summary(String g_id){
		int n=0;
		try {
			Connection conn=DBUtity.openConnection();
			String sql="SELECT"+
					" g_id,g_name,"+
					"("+"SELECT SUM(p_number)FROM purcharse WHERE g_id='"+g_id+"')"+" AS 'purtotal',"+
					"("+"SELECT SUM(s_number)FROM sales WHERE g_id='"+g_id+"')"+" AS 'saletotal',"+
					"("+"SELECT g_number FROM goods WHERE g_id='"+g_id+"')"+" AS 'allowance'"+
					"FROM goods";
			PreparedStatement pst=conn.prepareStatement(sql);
		    ResultSet rs=pst.executeQuery();
			while(rs.next()){
				System.out.print("商品id:"+rs.getString(1)+"商品名称:"+rs.getString(2)+
						"采购总量"+rs.getInt(3)+"销售总量:"+(n=rs.getInt(4))+
						"剩余库存:"+rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	/**查询销售总金额并反回*/
	public double saleTotalM(String g_id ){
		double saleTotal=0;
		try {
			Connection conn=DBUtity.openConnection();
			String sql="SELECT SUM(s_number*s_price) FROM sales WHERE g_id=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, g_id);
			ResultSet rs=pst.executeQuery();
			rs.next();
			saleTotal=rs.getDouble(1);
			DBUtity.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saleTotal;
	}
	//内部类存放查询出的 采购数量和单价
	class Purch{
		public double price;
		public int count;
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	}
	//查询采购数量和单价并处理
		public void serch(String g_id){
			List<Purch>purch=new ArrayList<Purch>();
			try {
				Connection conn=DBUtity.openConnection();
				String sql="select p_price,p_number from purcharse where g_id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, g_id);
				ResultSet rs=pst.executeQuery();
				while(rs.next()){
					Purch p=new Purch();
					p.setPrice(rs.getDouble("p_price"));
					p.setCount(rs.getInt("p_number"));
					purch.add(p);
				}
				int saletoal=summary(g_id);//查出采购总量  销售总量 剩余库存
				double sale=saleTotalM(g_id);//销售总金额
				int coun=0;
				double cost=0;//总成本
				for(Purch p:purch){
					coun=coun+p.getCount();
					if(coun<saletoal){
						cost+=(double)(p.getPrice())*(double)p.getCount();
					}else if(coun==saletoal){
						cost+=(double)p.getPrice()*(double)p.getCount();
						break;
					}else
					{
						cost+=(double)p.getPrice()*(double)(saletoal-(coun-p.getCount()));
						break;
					}
				}
				//sale-cost ==总利润
				System.out.println("盈利总额:"+(sale-cost));
				DBUtity.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void report(String g_id){
			SalesReportin sr=new SalesReportin();
			sr.serch(g_id);
		}
}
