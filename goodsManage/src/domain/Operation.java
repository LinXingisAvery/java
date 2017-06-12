package domain;

import java.util.Scanner;
/**
 * @author:李祖林
 * @description:操作类
 * @date:2017年6月9日下午2:43:47
 */
public class Operation {
	static Scanner scan=new Scanner(System.in);
	public static void printInfo(){
		System.out.println("**************请选择你所需要的操作*************");
		System.out.println("1商品信息\t 2采购信息\t 3销售信息\t4报表");
		int n=scan.nextInt();
		switch(n){
		case 1:
			OperationInfo.printGoodsDaoInfo();
			break;
		case 2:
			OperationInfo.printPurcharseDao();
			break;
		case 3:
			OperationInfo.salePrint();
			break;
		case 4:
			OperationInfo.printReport();
			break;
		default :
			System.out.println("你输入的信息有误请重新输入ctrl+z退出操作");
		}
		System.out.println("**********************************************");
	}
public static void main(String[] args) {
	do{
		printInfo();
		System.out.println("**************请选择你所需要的操作*************");
		System.out.println("1商品信息\t 2采购信息\t 3销售信息\t4报表");
	}while(scan.hasNext());
}
}
