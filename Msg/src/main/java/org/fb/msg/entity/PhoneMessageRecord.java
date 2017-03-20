package org.fb.msg.entity;

/**
 * 短信
 * DateTime: 2017年3月13日上午10:32:23
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public class PhoneMessageRecord implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String serno;
	private String phonenum;
	private String phonemsg;
	private String createtime;
	private String sendtime;
	private String sendtype;
	private String count;
	private String status;
	private String checkstatus;
	private String checkdate;	

	public PhoneMessageRecord() {
	}

	public PhoneMessageRecord(String serno) {
		this.serno = serno;
	}

	public PhoneMessageRecord(String serno, String phonenum, String phonemsg, String createtime, String sendtime,
			String sendtype, String count, String status, String checkstatus, String signed, String checkdate) {
		this.serno = serno;
		this.phonenum = phonenum;
		this.phonemsg = phonemsg;
		this.createtime = createtime;
		this.sendtime = sendtime;
		this.sendtype = sendtype;
		this.count = count;
		this.status = status;
		this.checkstatus = checkstatus;
		this.checkdate = checkdate;
	}

	public String getSerno() {
		return this.serno;
	}

	public void setSerno(String serno) {
		this.serno = serno;
	}

	public String getPhonenum() {
		return this.phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getPhonemsg() {
		return this.phonemsg;
	}

	public void setPhonemsg(String phonemsg) {
		this.phonemsg = phonemsg;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	public String getSendtime() {
		return sendtime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

}

