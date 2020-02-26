package cn.geekzone.oxygenBar.phone_code.entity;

import java.util.Date;

public class PhoneCode{
	/** 未使用 */
	public final static Integer NOT_USED = 0;

	/** 已使用 */
	public final static Integer USED = 1;

	// 手机号
	private String phone;

	// 验证码
	private String code;

	// 是否被验证过 0否 1是 默认0
	private Integer beUsed;

	// 业务代码，表示这个验证码的使用业务。不同业务验证码不冲突
	private String operation;
	
	private Date createTime;
	
	private Integer id;

	public PhoneCode() {
		super();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getBeUsed() {
		return beUsed;
	}

	public void setBeUsed(Integer beUsed) {
		this.beUsed = beUsed;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PhoneCode [phone=" + phone + ", code=" + code + ", beUsed=" + beUsed + ", operation=" + operation
				+ ", createTime=" + createTime + ", id=" + id + "]";
	}
	
}
