package domain;

/**
 * @author:李祖林
 * @description:Goods商品信息表
 * @date:2017年6月8日上午10:02:17
 */
public class Goods {
	private String g_id;//商品id
	private String g_name;//商品名称
	private String g_unit;//商品单位
	private int g_number;//商品数量总数量
    private int g_status;//商品状态
	/**
	 * 商品信息无参构造方法
	 */
	public Goods() {
		super();
	}
	/**
	 * 商品信息有参构造方法
	 * @param g_id 商品id
	 * @param g_name 商品名称
	 * @param g_unit 商品单位
	 * @param g_number 商品总数量
	 * @param g_status 商品状态  0正常-1  已删除
	 */
	public Goods(String g_id, String g_name,  int g_number,String g_unit, int g_status) {
		super();
		this.g_id = g_id;
		this.g_name = g_name;
		this.g_unit = g_unit;
		this.g_number = g_number;
		this.g_status = g_status;
	}
	//geter  and seter
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
	public String getG_unit() {
		return g_unit;
	}
	public void setG_unit(String g_unit) {
		this.g_unit = g_unit;
	}
	public int getG_number() {
		return g_number;
	}
	public void setG_number(int g_number) {
		this.g_number = g_number;
	}
	public int getG_status() {
		return g_status;
	}
	public void setG_status(int g_status) {
		this.g_status = g_status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g_id == null) ? 0 : g_id.hashCode());
		result = prime * result + ((g_name == null) ? 0 : g_name.hashCode());
		result = prime * result + g_number;
		result = prime * result + g_status;
		result = prime * result + ((g_unit == null) ? 0 : g_unit.hashCode());
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
		Goods other = (Goods) obj;
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
		if (g_number != other.g_number)
			return false;
		if (g_status != other.g_status)
			return false;
		if (g_unit == null) {
			if (other.g_unit != null)
				return false;
		} else if (!g_unit.equals(other.g_unit))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[商品id " + g_id + ", 商品名称 " + g_name+ ", 数量 " + g_number + ", 单位 " 
				+g_unit +  ", 状态 " + g_status + "]";
	}
    
}
