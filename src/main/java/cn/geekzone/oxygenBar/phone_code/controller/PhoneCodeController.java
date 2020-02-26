package cn.geekzone.oxygenBar.phone_code.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;

import cn.geekzone.oxygenBar.phone_code.service.PhoneCodeService;



@Controller
@RequestMapping("/phonecode")
public class PhoneCodeController {

	@Resource(name = "PhoneCodeService")
	private PhoneCodeService phoneCodeService;

	// 注册时获取验证码SMS_151790722
	@ResponseBody
	@RequestMapping(value = "/register")
	public Object register(String phone,String unitName) throws ClientException {
		return phoneCodeService.register(phone,unitName);
	}

	// 忘记密码获取验证码 SMS_151790721
	@ResponseBody
	@RequestMapping(value = "/forgetPassword")
	public Object forgetPassword(String phone) throws ClientException {
		return phoneCodeService.forgetPassword(phone);
	}

	// 快捷登录 SMS_151790724
	@ResponseBody
	@RequestMapping(value = "/quickLogin")
	public Object quickLogin(String phone) throws ClientException {
		return phoneCodeService.quickLogin(phone);
	}

	// 更换手机号 SMS_133967151
	@ResponseBody
	@RequestMapping(value = "/changePhone")
	public Object changePhone(String phone) throws ClientException {
		return phoneCodeService.changePhone(phone);
	}

	// 第三方绑定/注册 SMS_133967151
	@ResponseBody
	@RequestMapping(value = "/thirdPartyReg")
	public Object thirdPartyReg(String phone) throws ClientException {
		return phoneCodeService.thirdPartyReg(phone);
	}

	/////////////////////////////////////////////////////////////
	// 案件进度通知 SMS_133971567
	@ResponseBody
	@RequestMapping(value = "/caseFlowNotice")
	public Object caseFlowNotice(String phone, String caseNumber, String caseStatus) throws ClientException {
		return phoneCodeService.caseFlowNotice(phone, caseNumber, caseStatus);
	}

	// 除智能商标注册外其他产品资料审核通过 SMS_133976558
	@ResponseBody
	@RequestMapping(value = "/agreeAuditNotTM")
	public Object agreeAuditNotTM(String phone, String orderNumber) throws ClientException {
		return phoneCodeService.agreeAuditTM(phone, orderNumber);
	}

	// 付款成功通知 SMS_133976551
	@ResponseBody
	@RequestMapping(value = "/paySuccess")
	public Object paySuccess(String phone, String orderNumber) throws ClientException {
		return phoneCodeService.paySuccess(phone, orderNumber);
	}

	// 用户注册成功通知 SMS_133971568
	@ResponseBody
	@RequestMapping(value = "/registerSuccess")
	public Object registerSuccess(String phone) throws ClientException {
		return phoneCodeService.registerSuccess(phone);
	}

	// 退款成功通知 SMS_134327772
	@ResponseBody
	@RequestMapping(value = "/refundSuccess")
	public Object refundSuccess(String phone, String orderNumber) throws ClientException {
		return phoneCodeService.refundSuccess(phone, orderNumber);
	}

	// 拒绝退款通知 SMS_134317793
	@ResponseBody
	@RequestMapping(value = "/refundFail")
	public Object refundFail(String phone, String orderNumber, String remark) throws ClientException {
		return phoneCodeService.refundFail(phone, orderNumber, remark);
	}

}
