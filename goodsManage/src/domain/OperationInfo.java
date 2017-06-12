package domain;

import java.util.Scanner;

import dao.GoodsDao;
import dao.PurcharseDao;
import dao.SalesDao;

/**
 * @author:李祖林
 * @description:(类似)操作工厂
 * @date:2017年6月9日下午9:27:40
 */
public class OperationInfo {
	static Scanner scan=new Scanner(System.in);
	/**商品信息交互*/
	public static void printGoodsDaoInfo(){
		GoodsDao gd=new GoodsDao();
		System.out.println("*************请选择你需要的操作************************");
		System.out.println("商品信息1添加\t2全部查询\t3精确查询\t4模糊查询\t5修改");
		int n=scan.nextInt();
		switch(n){
		case 1://添加
		      {
		    	//Goods good=new Goods("g0000001","牛奶",0,"件",0);
		    		System.out.println("input:g_id g_name g_number g_unit g_status");
		    		scan=new Scanner(System.in);//新new一个对象相当于清空之前的输入流缓存
		    		String str=scan.nextLine();
		    		str=str.trim();//去除前后空格
		    		String[] st=str.split("\\s+");//以一个或多个空白字符拆分成String数组
		    		int g_number=Integer.parseInt(st[2]);
		    		int g_status=Integer.parseInt(st[4]);
		    		Goods good=new Goods(st[0],st[1],g_number,st[3],g_status);
		    		gd.add(good);
		      }
		      break;
		case 2://全部查询
			gd.search();
			break;
		case 3://精确查询
			System.out.println("请输入商品Id g_id");
			String g_id=scan.next();
			Goods goods=gd.search(g_id);
			System.out.println(goods);
			break;
		case 4://模糊查询
			System.out.println("请输入商品名称关键字 如:牛");
			String name=scan.next();
			gd.search1(name);
			break;
		case 5://商品修改
		{
			System.out.println("input:g_id g_name g_number g_unit g_status");
			scan=new Scanner(System.in);
    		String str=scan.nextLine();
    		str=str.trim();//去除前后空格
    		String[] st=str.split("\\s+");//以空1个或多个白字符拆分成String数组
    		int g_number=Integer.parseInt(st[2]);
    		int g_status=Integer.parseInt(st[4]);
    		Goods good=new Goods(st[0],st[1],g_number,st[3],g_status);
    		gd.update(good);
		}
    		break;
    	default:
    		System.out.println("你的输入信息有误");
		}
		System.out.println("******************************************************");
	}
	/**采购信息操作*/
	public static void printPurcharseDao(){
		PurcharseDao p=new PurcharseDao();
		System.out.println("*****************请选择你所需要的操作*****************");
		System.out.println("1采购信息添加\t2采购信息全部查询\t3精确查询\t4模糊查询\t5采购信息修改");
		int n=scan.nextInt();
		switch(n){
		//Purcharse purcharse=new Purcharse("p0001","g0000001","牛奶",4,"2017-06-08",20,"件","林兴批发商");
		case 1:	
			{
				System.out.println("input:p_id g_id g_name p_price p_date p_number p_unit p_supplier");
				scan=new Scanner(System.in);
				String str=scan.nextLine();
				str=str.trim();//去除前后空格
				String[] st=str.split("\\s+");//以1个或多个空白字符拆分成String数组
				double p_price=Double.parseDouble(st[3]);
				int p_number=Integer.parseInt(st[5]);
				Purcharse purcharse=new Purcharse(st[0],st[1],st[2],p_price,st[4],p_number,st[6],st[7]);
				p.add(purcharse);
				//采购的数据添加到商品信息表
				GoodsDao g=new GoodsDao();
				g.add(purcharse);
			}
			break;
		case 2://全部查询
			p.searchPurcharse();
			break;
		case 3://精确查询
			System.out.println("请输入采购ID p_id如:p0001");
			String p_id=scan.next();
			p.searchPurcharse(p_id);
			break;
		case 4://模糊查询p.searchPurcharse1("牛");
			System.out.println("请输入商品名称关键字如：牛");
			String name=scan.next();
			p.searchPurcharse1(name);
		case 5://update  传入一个修改的对象
		{
			System.out.println("input:p_id g_id g_name p_price p_date p_number p_unit p_supplier");
			scan=new Scanner(System.in);
			String str=scan.nextLine();
			str=str.trim();//去除前后空格
			String[] st=str.split("\\s+");//以1个或多个空白字符拆分成String数组
			double p_price=Double.parseDouble(st[3]);
			int p_number=Integer.parseInt(st[5]);
			Purcharse purcharse=new Purcharse(st[0],st[1],st[2],p_price,st[4],p_number,st[6],st[7]);
			p.updatePurcharse(purcharse);//调用修改的方法
		}
		default :
			System.out.println("你的输入有误");
		}
		System.out.println("*********************************************************************");
	}
	/**销售信息 与用户交互*/
	public static void salePrint(){
		SalesDao s=new SalesDao();
		System.out.println("****************请选择你所需要的操作**********************************");
		System.out.println("1销售信息添加\t2销售信息全查询\t3精确查询\t4模糊查询\t5删除");
		int n=scan.nextInt();
		switch(n){
		case 1:
			//Sales sale=new Sales("s0000001","g0000001","牛奶",5,"2017-06-09",10);
			System.out.println("请输入销售信息:s_id g_id g_name p_price s_date s_number");
			String s_id;//声明局部变量
			Sales sale;
			{
				scan=new Scanner(System.in);
				String str=scan.nextLine();
				str=str.trim();//去除前后空格
				String[] st=str.split("\\s+");//以1个或多个空白拆分成String数组
				double s_price=Double.parseDouble(st[3]);
				int s_number=Integer.parseInt(st[5]);
				sale=new Sales(st[0],st[1],st[2],s_price,st[4],s_number);
			}
			s.addSeles(sale);
			break;
		case 2:
			s.serchSales();//查询全部销售信息
			break;
		case 3://精确查询通过s_id  s0000001
			System.out.println("请输入销售ID  s_id");
		    s_id=scan.next();
			s.serchSales(s_id);
			break;
		case 4://s.serchSales1("牛");  模糊查询 通过商品名称相似
			System.out.println("请输入商品名称关键字");
			String name=scan.next();
			s.serchSales1(name);
			break;
		case 5://通过id 删除（用于商品信息录入错误比如顾客说买什么录入了进去后  顾客又突然不要了）
			System.out.println("请输入销售s_id");//s.delete("s0000001");
		    s_id=scan.next();
			s.delete(s_id);
			break;
		default :
			System.out.println("你的输入有误");
		}
		System.out.println("*********************************************************************");
	}
	/**报表*/
	public static void printReport(){
		System.out.println("*************请输入商品id号g_id*****************");
		String g_id=scan.next();
		SalesReportin.report(g_id);
	}
}
