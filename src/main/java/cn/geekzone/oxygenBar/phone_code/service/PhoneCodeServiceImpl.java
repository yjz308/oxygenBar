package cn.geekzone.oxygenBar.phone_code.service;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import cn.geekzone.oxygenBar.base.dao.ObApplicationUnitDAO;
import cn.geekzone.oxygenBar.base.entity.ObApplicationUnit;
import cn.geekzone.oxygenBar.common.entity.Constants;
import cn.geekzone.oxygenBar.common.entity.DataFormatUtils;
import cn.geekzone.oxygenBar.common.entity.PhoneCodeUtils;
import cn.geekzone.oxygenBar.common.entity.Result;
import cn.geekzone.oxygenBar.phone_code.SendMsg;
import cn.geekzone.oxygenBar.phone_code.dao.PhoneCodeMapper;
import cn.geekzone.oxygenBar.phone_code.entity.PhoneCode;
import cn.geekzone.oxygenBar.phone_code.entity.PhoneCodeConsts;



@Service("PhoneCodeService")
public class PhoneCodeServiceImpl implements PhoneCodeService {

	@Resource(name = "PhoneCodeMapper")
	private PhoneCodeMapper phoneCodeMapper;
//	@Autowired
//	ClavVolunteerDAO cvDao;
//	@Resource(name = "IdNumberService")
//	IdNumberService idService;
	@Autowired
	ObApplicationUnitDAO oauDao;

	@Override
	public boolean canSendPhoneCode(PhoneCode phoneCode) {
		PhoneCode p = phoneCodeMapper.getLastPhoneCode(phoneCode);
		if (p == null) {
			return true;
		}
		if (PhoneCode.USED.equals(p.getBeUsed())) {
			// 验证码已使用，则可以发送
			return true;
		}
		// 上次发送距离现在的时间
		long minutes = (System.currentTimeMillis() - p.getCreateTime().getTime()) / 1000;
		return minutes > PhoneCodeConsts.SEND_NEXT_CODE_TIME;
	}

	@Override
	public void save(PhoneCode phoneCode) {
		phoneCodeMapper.save(phoneCode);
	}

	@Override
	public Result isCodeValid(PhoneCode phoneCode, boolean setUsed) {
		Result result = new Result();
		System.out.println(phoneCode.getPhone());
		System.out.println(phoneCode.getCode());
		System.out.println(phoneCode.getOperation());
		PhoneCode p = phoneCodeMapper.getLastPhoneCode(phoneCode);
		if (p == null) {
			return result.setErrorCode().setData(Type.NOT_CORRECT);
		}
		System.out.println(p);
		System.out.println("1:"+p.getCode());
		System.out.println("2:"+phoneCode.getCode());
		System.out.println("3:"+PhoneCode.NOT_USED);
		System.out.println("3:"+p.getBeUsed());
		if (p.getCode().equals(phoneCode.getCode()) && PhoneCode.NOT_USED.equals(p.getBeUsed())) {
			// 上次发送距离现在的时间
			long minutes = (System.currentTimeMillis() - p.getCreateTime().getTime()) / 1000;
			if (minutes > PhoneCodeConsts.CODE_VALID_TIME) {
				return result.setErrorCode().setData(Type.EXPIRE);
			} else {
				if (setUsed) {
					phoneCodeMapper.updatePhoneCodeUsed(p.getId());
				}
				return result.setSuccessCode().setData(Type.CORRECT).add("id", p.getId());
			}
		}
		return result.setErrorCode().setData(Type.NOT_CORRECT);
	}

	@Override
	public Result verifyPhoneCode(PhoneCode phoneCode, boolean setUsed) {
		Result result = new Result();

		if (!DataFormatUtils.isPhone(phoneCode.getPhone())) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phoneCode.getPhone()));
		}
//		if (StringUtils.isBlank(phoneCode.getCode())) {
//			return result.setErrorCode().setMessage("验证码格式不正确");
//		}
		if (!DataFormatUtils.isValidOperation(phoneCode.getOperation())) {
			return result.setErrorCode().setMessage("不是有效的业务代码");
		}

		Result r = isCodeValid(phoneCode, setUsed);
		Type resultType = (Type) r.getData();
		switch (resultType) {
		case EXPIRE:
			return result.setErrorCode().setMessage("验证码已过期");
		case NOT_CORRECT:
			return result.setErrorCode().setMessage("验证码不正确");
		case CORRECT:
			return result.setSuccessCode().setMessage("验证码正确").add("id", r.get("id"));
		}

		return result.setErrorCode().setMessage("异常");
	}

	@Override
	public void setPhoneCodeUsed(Integer id) {
		phoneCodeMapper.updatePhoneCodeUsed(id);

	}

	@Override
	public Result register(String phone,String unitName) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
//		if(idService.checkIdNumberAndReturnInfo(idNumber).getResultCode() == 2) {
//			return result.setErrorCode().setMessage(String.format("%s 不是有效的身份证号", idNumber));
//		}
		ObApplicationUnit b = new ObApplicationUnit();
		b.setUnitName(unitName);
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c != null) {
			return result.setErrorCode().setMessage("该单位已注册,如忘记密码,请点击忘记密码找回");
		}else {
			ObApplicationUnit d = new ObApplicationUnit();
			d.setPhone(phone);
			ObApplicationUnit e = oauDao.selectByCondition(d);
			if(e != null) {
				return result.setErrorCode().setMessage("该手机号已注册,如忘记密码,请点击忘记密码找回");
			}
			String code = PhoneCodeUtils.getRandomCode(6);
			// 参数
			String param = "{\"code\":\"" + code + "\"}";
			// 模板
			String templateCode = "SMS_144195771";
			 SendSmsResponse response = SendMsg.sendSms(phone, param,
			 templateCode);
			 if (!response.getCode().equals("OK")) {
			 return result.setErrorCode().setMessage(response.getMessage());
			 }
			PhoneCode phonecode = new PhoneCode();
			phonecode.setPhone(phone);
			phonecode.setCode(code);
			phonecode.setOperation(Constants.OPERATION_USER_REG);
			// 存入数据库
			save(phonecode);
			result.add("phoneCode", code);
			return result.setSuccessCode().setMessage("success");
		}
	}

	@Override
	public Result forgetPassword(String phone) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		ObApplicationUnit b = new ObApplicationUnit();
		b.setPhone(phone);
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c == null) {
			return result.setErrorCode().setMessage("该手机号未注册,请点击注册");
		}
		String code = PhoneCodeUtils.getRandomCode(6);
		// 参数
		String param = "{\"code\":\"" + code + "\"}";
		// 模板
		String templateCode = "SMS_144195770";
		 SendSmsResponse response = SendMsg.sendSms(phone, param,
		 templateCode);
		 if (!response.getCode().equals("OK")) {
		 return result.setErrorCode().setMessage(response.getMessage());
		 }
		PhoneCode phonecode = new PhoneCode();
		phonecode.setPhone(phone);
		phonecode.setCode(code);
		phonecode.setOperation(Constants.OPERATION_USER_FORGET_PWD);
		// 存入数据库
		save(phonecode);
		result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result quickLogin(String phone) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		ObApplicationUnit b = new ObApplicationUnit();
		b.setPhone(phone);
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c == null) {
			return result.setErrorCode().setMessage("该手机号未注册,请点击注册");
		}
		String code = PhoneCodeUtils.getRandomCode(6);
		// 参数
		String param = "{\"code\":\"" + code + "\"}";
		// 模板
		String templateCode = "SMS_144195773";
		 SendSmsResponse response = SendMsg.sendSms(phone, param,
		 templateCode);
		 if (!response.getCode().equals("OK")) {
		 return result.setErrorCode().setMessage(response.getMessage());
		 }
		PhoneCode phonecode = new PhoneCode();
		phonecode.setPhone(phone);
		phonecode.setCode(code);
		phonecode.setOperation(Constants.OPERATION_QUICK_LOGIN);
		// 存入数据库
		save(phonecode);
		result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result changePhone(String phone) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
//		ClavVolunteer d = new ClavVolunteer();
//		d.setTelephone(phone);
//		ClavVolunteer e = cvDao.selectByCondition(d);
//		if(e != null) {
//			return result.setErrorCode().setMessage("该手机号已绑定其他账户");
//		}
		ObApplicationUnit b = new ObApplicationUnit();
		b.setPhone(phone);
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c != null) {
			return result.setErrorCode().setMessage("该手机号已绑定其他账户,请修改");
		}
		String code = PhoneCodeUtils.getRandomCode(6);
		// 参数
		String param = "{\"code\":\"" + code + "\"}";
		// 模板
		String templateCode = /* "SMS_130085073" */ "SMS_151635893";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		PhoneCode phonecode = new PhoneCode();
		phonecode.setPhone(phone);
		phonecode.setCode(code);
//		phonecode.setOperation(Constants.OPERATION_USER_CHANGE_PHONE);
		// 存入数据库
		save(phonecode);
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result thirdPartyReg(String phone) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		String code = PhoneCodeUtils.getRandomCode(6);
		// 参数
		String param = "{\"code\":\"" + code + "\"}";
		// 模板
		String templateCode = /* "SMS_130085075" */ "SMS_144195771";
		// SendSmsResponse response = SendMsg.sendSms(phone, param,
		// templateCode);
		// if (!response.getCode().equals("OK")) {
		// return result.setErrorCode().setMessage(response.getMessage());
		// }
		PhoneCode phonecode = new PhoneCode();
		phonecode.setPhone(phone);
		phonecode.setCode(code);
//		phonecode.setOperation(Constants.OPERATION_THIRD_PARTY_REG);
		// 存入数据库
		save(phonecode);
		result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result caseFlowNotice(String phone, String caseNumber, String caseStatus) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		caseNumber = caseNumber.substring(caseNumber.length() - 8, caseNumber.length());
		// 参数
		String param = "{\"caseNumber\":\"" + caseNumber + "\",\"message\":\"" + caseStatus + "\"}";
		// 模板
		// String templateCode = "SMS_134328237";
		String templateCode = "SMS_140730074";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result agreeAuditNotTM(String phone, String orderNumber) throws ClientException {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		orderNumber = orderNumber.substring(orderNumber.length() - 8, orderNumber.length());
		// 参数
		String param = "{\"orderNumber\":\"" + orderNumber + "\"}";
		// 模板
		String templateCode = "SMS_133964333";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result agreeAuditTM(String phone, String orderNumber) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		orderNumber = orderNumber.substring(orderNumber.length() - 8, orderNumber.length());
		// 参数
		String param = "{\"orderNumber\":\"" + orderNumber + "\"}";
		// 模板
		String templateCode = "SMS_134312684";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result refuseAudit(String phone, String orderNumber) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		orderNumber = orderNumber.substring(orderNumber.length() - 8, orderNumber.length());
		// 参数
		String param = "{\"orderNumber\":\"" + orderNumber + "\"}";
		// 模板
		String templateCode = "SMS_133969422";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result paySuccess(String phone, String orderNumber) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		orderNumber = orderNumber.substring(orderNumber.length() - 8, orderNumber.length());
		// 参数
		String param = "{\"orderNumber\":\"" + orderNumber + "\"}";
		// 模板
		String templateCode = "SMS_133969423";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result registerSuccess(String phone) throws ClientException {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		// 参数
		String param = "";
		// 模板
		String templateCode = "SMS_133971568";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result refundSuccess(String phone, String orderNumber) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		orderNumber = orderNumber.substring(orderNumber.length() - 8, orderNumber.length());
		// 参数
		String param = "{\"orderNumber\":\"" + orderNumber + "\"}";
		// 模板
		String templateCode = "SMS_134327772";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result refundFail(String phone, String orderNumber, String remark) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		orderNumber = orderNumber.substring(orderNumber.length() - 8, orderNumber.length());
		// 参数
		String param = "{\"orderNumber\":\"" + orderNumber + "\",\"remark\":\"" + remark + "\"}";
		// 模板
		String templateCode = "SMS_134317793";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result resetPassword(String phone, String password) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		// 参数
		String param = "{\"password\":\"" + password + "\"}";
		// 模板
		String templateCode = "SMS_142945929";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result chuangjianzhanghao(String phone, String password) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		// 参数
		String param = "{\"phone\":\"" + phone + "\",\"password\":\"" + password + "\"}";
		// 模板
		// String templateCode = "SMS_134328237";
		String templateCode = "SMS_157453924";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result banjutongguo(String phone) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		// 参数
		String param = "";
		// 模板
		String templateCode = "SMS_157448429";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result banjujujue(String phone) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		// 参数
		String param = "";
		// 模板
		String templateCode = "SMS_157448428";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result shenfenrenzheng(String phone, String status) throws ClientException {
		Result result = new Result();
		if (!DataFormatUtils.isPhone(phone)) {
			return result.setErrorCode().setMessage(String.format("%s 不是有效的手机号", phone));
		}
		// 参数
		String param = "{\"status\":\"" + status + "\"}";
		// 模板
		// String templateCode = "SMS_134328237";
		String templateCode = "SMS_165675455";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result memberPasswordReset(String phone, String newPwd) throws ClientException {
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + newPwd + "\"}";
		String templateCode = "SMS_175425507";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result memberPass(String phone) throws ClientException {
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + 1 + "\"}";
		String templateCode = "SMS_175495427";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result memberNotPass(String phone) throws ClientException {
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + 1 + "\"}";
		String templateCode = "SMS_176910623";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result lishiPass(String phone) throws ClientException {
		// TODO Auto-generated method stub
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + 1 + "\"}";
		String templateCode = "SMS_175485439";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result lishiNotPass(String phone) throws ClientException {
		// TODO Auto-generated method stub
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + 1 + "\"}";
		String templateCode = "SMS_175475417";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result memberUnitPass(String phone) throws ClientException {
		// TODO Auto-generated method stub
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + 1 + "\"}";
		String templateCode = "SMS_175485510";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result memberUnitNotPass(String phone) throws ClientException {
		Result result = new Result();
		// 参数
		String param = "{\"newPwd\":\"" + 1 + "\"}";
		String templateCode = "SMS_175490517";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

	@Override
	public Result systemNotice(String phone,String title,String detail) throws ClientException {
		Result result = new Result();
		// 参数
		String param = "{\"detail\":\"" + detail + "\"}";
		String templateCode = "SMS_175480704";
		SendSmsResponse response = SendMsg.sendSms(phone, param, templateCode);
		if (!response.getCode().equals("OK")) {
			return result.setErrorCode().setMessage(response.getMessage());
		}
		// result.add("phoneCode", code);
		return result.setSuccessCode().setMessage("success");
	}

}
