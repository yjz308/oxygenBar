package cn.geekzone.oxygenBar.base.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.geekzone.oxygenBar.base.entity.ObBanner;
import cn.geekzone.oxygenBar.base.service.ObBannerService;
import cn.geekzone.oxygenBar.common.entity.Result;

@RestController
@RequestMapping({ "/banner" })
public class ObBannerController {
	
	@Resource(name = "BannerService")
	ObBannerService obService;
	
	@RequestMapping({ "/getBannerList" })
	public Result getBannerList(ObBanner a) {
		return obService.getBannerList(a);
	}
	
	@RequestMapping({ "/updateBanner" })
	public Result updateBanner(ObBanner a) {
		return obService.updateBanner(a);
	}
	
	@RequestMapping({ "/addBanner" })
	public Result addBanner(ObBanner a) {
		return obService.addBanner(a);
	}
	
	@RequestMapping({ "/getPicList" })
	public Result getPicList(ObBanner a) {
		return obService.getPicList(a);
	}
	
	@RequestMapping({ "/deleteBanner" })
	public Result deleteBanner(Integer id) {
		return obService.deleteBanner(id);
	}
	
	@RequestMapping({ "/getBannerById" })
	public Result getBannerById(Integer id) {
		return obService.getBannerById(id);
	}
	
}
