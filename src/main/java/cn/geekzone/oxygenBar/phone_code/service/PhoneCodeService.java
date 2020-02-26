package cn.geekzone.oxygenBar.phone_code.service;

import com.aliyuncs.exceptions.ClientException;

import cn.geekzone.oxygenBar.common.entity.Result;
import cn.geekzone.oxygenBar.phone_code.entity.PhoneCode;




public interface PhoneCodeService {
	/**
	 * 验证结果
	 */
	enum Type {
		/** 过期 */
		EXPIRE,
		/** 不正确 */
		NOT_CORRECT,
		/** 正确 */
		CORRECT;
	}

	/**
	 * <pre>
	 * 是否可以发送验证码
	 * 如果在禁止重发间隔内，将不能重发
	 * </pre>
	 */
	boolean canSendPhoneCode(PhoneCode phoneCode);

	void save(PhoneCode phoneCode);

	/**
	 * 验证码是否有效，验证码不正确，不在有效期内均为无效
	 * 
	 * @param setUsed
	 *            是否设置为已使用状态
	 */
	Result isCodeValid(PhoneCode phoneCode, boolean setUsed);

	/**
	 * 验证码验证
	 */
	Result verifyPhoneCode(PhoneCode phoneCode, boolean setUsed);

	/** 标记验证码为已使用状态 */
	void setPhoneCodeUsed(Integer id);

	// 注册时获取验证码 SMS_151790722
	Result register(String phone,String unitName) throws ClientException;

	// 忘记密码获取验证码SMS_151790721
	Result forgetPassword(String phone) throws ClientException;

	// 快捷登录 SMS_151790724
	Result quickLogin(String phone) throws ClientException;

	// 更换手机号 SMS_133967151
	Result changePhone(String phone) throws ClientException;

	// 第三方绑定/注册SMS_144195771
	Result thirdPartyReg(String phone) throws ClientException;

	// 案件进度通知 SMS_133971567 换成了 SMS_134328237
	Result caseFlowNotice(String phone, String caseNumber, String caseStatus) throws ClientException;

	// 除智能商标注册外其他产品资料审核通过 SMS_133976558 换成了 SMS_133964333
	Result agreeAuditNotTM(String phone, String orderNumber) throws ClientException;

	// 智能商标注册资料审核通过 SMS_133966625 换成了 SMS_133979329，再次变更 为 SMS_134312684
	Result agreeAuditTM(String phone, String orderNumber) throws ClientException;

	// 资料审核未通过SMS_133969422
	Result refuseAudit(String phone, String orderNumber) throws ClientException;

	// 付款成功通知 SMS_133976551 换成了 SMS_133969423
	Result paySuccess(String phone, String orderNumber) throws ClientException;

	// 用户注册成功通知 SMS_133971568
	Result registerSuccess(String phone) throws ClientException;

	// 退款成功通知 SMS_134327772
	Result refundSuccess(String phone, String orderNumber) throws ClientException;

	// 拒绝退款通知 SMS_134317793
	Result refundFail(String phone, String orderNumber, String remark) throws ClientException;

	// 重置密码通知 SMS_142945929
	Result resetPassword(String phone, String password) throws ClientException;

	// =====================
	// 创建账号SMS_157453924
	Result chuangjianzhanghao(String phone, String password) throws ClientException;

	// 版局通过SMS_157448429
	Result banjutongguo(String phone) throws ClientException;

	// 版局拒绝SMS_157448428
	Result banjujujue(String phone) throws ClientException;

	// 身份认证SMS_165675455
	Result shenfenrenzheng(String phone, String status) throws ClientException;
	
	// 会员密码重置通知SMS_175425507
	Result memberPasswordReset(String phone, String newPwd) throws ClientException;
	
	// 会员审核通过通知SMS_175495427
	Result memberPass(String phone) throws ClientException;
	
	// 会员审核不通过通知SMS_175490422
	Result memberNotPass(String phone) throws ClientException;
	
	// 理事审核通过通知SMS_175490422
	Result lishiPass(String phone) throws ClientException;
	
	// 理事审核不通过通知SMS_175490422
	Result lishiNotPass(String phone) throws ClientException;
	
	// 会员单位审核通过通知SMS_175490422
	Result memberUnitPass(String phone) throws ClientException;
	
	// 会员单位审核不通过通知SMS_175490422
	Result memberUnitNotPass(String phone) throws ClientException;
	
	// 系统通知SMS_175475582
	Result systemNotice(String phone,String title,String detail) throws ClientException;
}
