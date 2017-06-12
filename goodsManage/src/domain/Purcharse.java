package domain;

/**
 * @author:李祖林
 * @description:采购信息表
 * @date:2017年6月8日上午11:28:39
 */
public class Purcharse {
	private String p_id;//采购id
	private String g_id;//商品id
	private String g_name;//商品名称
	private double p_price;//商品进价
	private String p_date;//进货日期
	private int p_number;//进货数量
	private String p_unit;//单位
	private String p_supplier;//供货商
	
	/**
	 * 采购无参购造方法
	 */
	public Purcharse() {
		super();
	}


	/**
	 * @param p_id 采购id
	 * @param g_id 商品id
	 * @param g_name 商品名称
	 * @param p_price 商品进价
	 * @param p_date  进货日期
	 * @param p_number 进货数量
	 * @param p_unit  进货单位
	 * @param p_supplier 供货商
	 */
	public Purcharse(String p_id, String g_id, String g_name, double p_price, String p_date, int p_number,
			String p_unit,String p_supplier) {
		super();
		this.p_id = p_id;
		this.g_id = g_id;
		this.g_name = g_name;
		this.p_price = p_price;
		this.p_date = p_date;
		this.p_number = p_number;
		this.p_supplier = p_supplier;
		this.p_unit =p_unit;
	}
	//geter and seter
	public String getP_id() {
		return p_id;
	}


	public void setP_id(String p_id) {
		this.p_id = p_id;
	}


	public String getG_id() {
		return g_id;
	}


	public void setG_id(String g_id) {
		this.g_id = g_id;
	}


	public String getG_name() {
		return g_name;
	}


	public void setG_name(String g_name) {
		this.g_name = g_name;
	}


	public double getP_price() {
		return p_price;
	}


	public void setP_price(double p_price) {
		this.p_price = p_price;
	}


	public String getP_date() {
		return p_date;
	}


	public void setP_date(String p_date) {
		this.p_date = p_date;
	}


	public int getP_number() {
		return p_number;
	}


	public void setP_number(int p_number) {
		this.p_number = p_number;
	}


	public String getP_supplier() {
		return p_supplier;
	}


	public void setP_supplier(String p_supplier) {
		this.p_supplier = p_supplier;
	}


	public String getP_unit() {
		return p_unit;
	}


	public void setP_unit(String p_unit) {
		this.p_unit = p_unit;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g_id == null) ? 0 : g_id.hashCode());
		result = prime * result + ((g_name == null) ? 0 : g_name.hashCode());
		result = prime * result + ((p_date == null) ? 0 : p_date.hashCode());
		result = prime * result + ((p_id == null) ? 0 : p_id.hashCode());
		result = prime * result + p_number;
		long temp;
		temp = Double.doubleToLongBits(p_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((p_supplier == null) ? 0 : p_supplier.hashCode());
		result = prime * result + ((p_unit == null) ? 0 : p_unit.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purcharse other = (Purcharse) obj;
		if (g_id == null) {
			if (other.g_id != null)
				return false;
		} else if (!g_id.equals(other.g_id))
			return false;
		if (g_name == null) {
			if (other.g_name != null)
				return false;
		} else if (!g_name.equals(other.g_name))
			return false;
		if (p_date == null) {
			if (other.p_date != null)
				return false;
		} else if (!p_date.equals(other.p_date))
			return false;
		if (p_id == null) {
			if (other.p_id != null)
				return false;
		} else if (!p_id.equals(other.p_id))
			return false;
		if (p_number != other.p_number)
			return false;
		if (Double.doubleToLongBits(p_price) != Double.doubleToLongBits(other.p_price))
			return false;
		if (p_supplier == null) {
			if (other.p_supplier != null)
				return false;
		} else if (!p_supplier.equals(other.p_supplier))
			return false;
		if (p_unit == null) {
			if (other.p_unit != null)
				return false;
		} else if (!p_unit.equals(other.p_unit))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "[采购id " + p_id + ",商品id" + g_id + ",商品名称" + g_name + ", 商品进价 " + p_price
				+ ",进货日期 " + p_date + ",进货数量" + p_number+" 单位 "+p_unit + ",供货商" + p_supplier + "]";
	}
	
}
