package cn.geekzone.oxygenBar.base.service;

import cn.geekzone.oxygenBar.base.entity.ObApplicationUnit;
import cn.geekzone.oxygenBar.common.entity.Result;

public interface ObApplicationUnitService {
	
	Result ObApplicationUnitQuickLogin(ObApplicationUnit a);
	
	Result ObApplicationUnitLogin(ObApplicationUnit a);
	
	Result ObApplicationUnitRegister(ObApplicationUnit a);
	
	Result ObApplicationUnitForgetPassword(ObApplicationUnit a);
	
	Result ObApplicationUnitChangePhone(ObApplicationUnit a);
	
	Result getFormerYearsList();
	
	Result getYearUnitList(String year);
	
	Result getApplicationUnitByToken();
}
