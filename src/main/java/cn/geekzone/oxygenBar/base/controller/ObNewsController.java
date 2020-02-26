package cn.geekzone.oxygenBar.base.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.geekzone.oxygenBar.base.entity.ObNews;
import cn.geekzone.oxygenBar.base.service.ObNewsService;
import cn.geekzone.oxygenBar.common.entity.Result;


@RestController
@RequestMapping({ "/news" })
public class ObNewsController {
	
	@Resource(name = "NewsService")
	ObNewsService onService;
	
	@RequestMapping({ "/getNewsList" })
	public Result getNewsList(ObNews a) {
		return onService.getNewsList(a);
	}
	
	@RequestMapping({ "/updateNews" })
	public Result updateNews(ObNews a) {
		return onService.updateNews(a);
	}
	
	@RequestMapping({ "/addNews" })
	public Result addNews(ObNews a) {
		return onService.addNews(a);
	}
	
	@RequestMapping({ "/deleteNews" })
	public Result deleteNews(Integer id) {
		return onService.deleteNews(id);
	}
	
	@RequestMapping({ "/getNewsById" })
	public Result getNewsById(Integer id) {
		return onService.getNewsById(id);
	}
	
	@RequestMapping({ "/getHotNewsList" })
	public Result getHotNewsList(ObNews a) {
		return onService.getHotNewsList(a);
	}
	
	@RequestMapping({ "/getRecommendNewsList" })
	public Result getRecommendNewsList(ObNews a) {
		return onService.getRecommendNewsList(a);
	}
	
}
