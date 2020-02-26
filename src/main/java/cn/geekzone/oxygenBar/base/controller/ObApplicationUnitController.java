package cn.geekzone.oxygenBar.base.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.geekzone.oxygenBar.base.entity.ObApplicationUnit;
import cn.geekzone.oxygenBar.base.service.ObApplicationUnitService;
import cn.geekzone.oxygenBar.common.entity.Constants;
import cn.geekzone.oxygenBar.common.entity.Result;
import cn.geekzone.oxygenBar.phone_code.entity.PhoneCode;
import cn.geekzone.oxygenBar.phone_code.service.PhoneCodeService;

@RestController
@RequestMapping({ "/applicationUnit" })
public class ObApplicationUnitController {
	
	@Resource(name = "ObApplicationUnitService")
	ObApplicationUnitService oauService;
	@Resource(name = "PhoneCodeService")
	PhoneCodeService phoneCodeService;
	
	@RequestMapping({ "/ObApplicationUnitRegister" })
	public Result ObApplicationUnitRegister(ObApplicationUnit a) {
		PhoneCode phoneCode = new PhoneCode();
		phoneCode.setCode(a.getCode());
		phoneCode.setPhone(a.getPhone());
		phoneCode.setOperation(Constants.OPERATION_USER_REG);
		Result result = phoneCodeService.verifyPhoneCode(phoneCode, false);
		if(result.isError()) {
			return result;
		}
		Integer phoneCodeId = (Integer) result.get("id");
		result = oauService.ObApplicationUnitRegister(a);
		if(result.isError()) {
			return result;
		}
		phoneCodeService.setPhoneCodeUsed(phoneCodeId);
		return result;
//		return oauService.ObApplicationUnitRegister(a);
	}
	
	@RequestMapping({ "/ObApplicationUnitQuickLogin" })
	public Result ObApplicationUnitQuickLogin(ObApplicationUnit a) {
		PhoneCode phoneCode = new PhoneCode();
		phoneCode.setCode(a.getCode());
		phoneCode.setPhone(a.getPhone());
		phoneCode.setOperation(Constants.OPERATION_QUICK_LOGIN);
		Result result = phoneCodeService.verifyPhoneCode(phoneCode, false);
		if(result.isError()) {
			return result;
		}
		Integer phoneCodeId = (Integer) result.get("id");
		result = oauService.ObApplicationUnitQuickLogin(a);
		if(result.isError()) {
			return result;
		}
		phoneCodeService.setPhoneCodeUsed(phoneCodeId);
		return result;
//		return oauService.ObApplicationUnitRegister(a);
	}
	
	@RequestMapping({ "/ObApplicationUnitLogin" })
	public Result ObApplicationUnitLogin(ObApplicationUnit a) {
//		PhoneCode phoneCode = new PhoneCode();
//		phoneCode.setCode(a.getCode());
//		phoneCode.setPhone(a.getPhone());
//		phoneCode.setOperation(Constants.OPERATION_QUICK_LOGIN);
//		Result result = phoneCodeService.verifyPhoneCode(phoneCode, false);
//		if(result.isError()) {
//			return result;
//		}
//		Integer phoneCodeId = (Integer) result.get("id");
//		result = oauService.ObApplicationUnitQuickLogin(a);
//		if(result.isError()) {
//			return result;
//		}
//		phoneCodeService.setPhoneCodeUsed(phoneCodeId);
//		return result;
		return oauService.ObApplicationUnitLogin(a);
	}
	
	@RequestMapping({ "/ObApplicationUnitForgetPassword" })
	public Result ObApplicationUnitForgetPassword(ObApplicationUnit a) {
		PhoneCode phoneCode = new PhoneCode();
		phoneCode.setCode(a.getCode());
		phoneCode.setPhone(a.getPhone());
		phoneCode.setOperation(Constants.OPERATION_USER_FORGET_PWD);
		Result result = phoneCodeService.verifyPhoneCode(phoneCode, false);
		if(result.isError()) {
			return result;
		}
		Integer phoneCodeId = (Integer) result.get("id");
		result = oauService.ObApplicationUnitForgetPassword(a);
		if(result.isError()) {
			return result;
		}
		phoneCodeService.setPhoneCodeUsed(phoneCodeId);
		return result;
//		return oauService.ObApplicationUnitRegister(a);
	}
	
	@RequestMapping({ "/getFormerYearsList" })
	public Result getFormerYearsList() {
		return oauService.getFormerYearsList();
	}
	
	@RequestMapping({ "/getYearUnitList" })
	public Result getYearUnitList(String year) {
		return oauService.getYearUnitList(year);
	}
	
	@RequestMapping({ "/getApplicationUnitByToken" })
	public Result getApplicationUnitByToken() {
		return oauService.getApplicationUnitByToken();
	}
	
}
