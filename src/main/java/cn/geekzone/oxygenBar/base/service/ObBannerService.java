package cn.geekzone.oxygenBar.base.service;

import cn.geekzone.oxygenBar.base.entity.ObBanner;
import cn.geekzone.oxygenBar.common.entity.Result;

public interface ObBannerService {
	
	Result getBannerList(ObBanner a);
	
	Result updateBanner(ObBanner a);
	
	Result addBanner(ObBanner a);
	
	Result deleteBanner(Integer id);
	
	Result getBannerById(Integer id);
	
	Result getPicList(ObBanner a);
	
}
