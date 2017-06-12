package domain;

/**
 * @author:李祖林
 * @description:销售表
 * @date:2017年6月8日上午11:54:12
 */
public class Sales {
	private String s_id;//销售id
	private String g_id;//商品id
	private String g_name;//商品名称
	private double s_privce;//销售价格
	private String s_date;//销售时间
	private int s_number;//销售数量
	/**
	 * 无参构造方法
	 */
	public Sales() {
		super();
	}
	/**
	 * 有参构造方法
	 * @param s_id 销售id
	 * @param g_id 商品id
	 * @param g_name 商品名称
	 * @param s_privce 销售价格
	 * @param s_date 销售时间
	 * @param s_number 销售数量
	 */
	public Sales(String s_id, String g_id, String g_name, double s_privce, String s_date, int s_number) {
		super();
		this.s_id = s_id;
		this.g_id = g_id;
		this.g_name = g_name;
		this.s_privce = s_privce;
		this.s_date = s_date;
		this.s_number = s_number;
	}
	//geter and seter
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
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
	public double getS_privce() {
		return s_privce;
	}
	public void setS_privce(double s_privce) {
		this.s_privce = s_privce;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public int getS_number() {
		return s_number;
	}
	public void setS_number(int s_number) {
		this.s_number = s_number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g_id == null) ? 0 : g_id.hashCode());
		result = prime * result + ((g_name == null) ? 0 : g_name.hashCode());
		result = prime * result + ((s_date == null) ? 0 : s_date.hashCode());
		result = prime * result + ((s_id == null) ? 0 : s_id.hashCode());
		result = prime * result + s_number;
		long temp;
		temp = Double.doubleToLongBits(s_privce);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Sales other = (Sales) obj;
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
		if (s_date == null) {
			if (other.s_date != null)
				return false;
		} else if (!s_date.equals(other.s_date))
			return false;
		if (s_id == null) {
			if (other.s_id != null)
				return false;
		} else if (!s_id.equals(other.s_id))
			return false;
		if (s_number != other.s_number)
			return false;
		if (Double.doubleToLongBits(s_privce) != Double.doubleToLongBits(other.s_privce))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[销售id " + s_id + ", 商品id " + g_id + ",商品名称 " + g_name + ",销售价格 " + s_privce + ",销售时间 "
				+ s_date + ",销售数量 " + s_number + "]";
	}
	
}
