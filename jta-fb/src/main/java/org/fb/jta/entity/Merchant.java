package org.fb.jta.entity;

/**
 * 商户
 * @datetime 2017-3-8 下午10:20:12
 * @author wh
 **/

public class Merchant {
	private int id;
	private String name;
	private String address;
	private Integer menoy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMenoy() {
		return menoy;
	}
	public void setMenoy(Integer menoy) {
		this.menoy = menoy;
	}

}



