package cn.geekzone.oxygenBar.base.service;

import cn.geekzone.oxygenBar.base.entity.ObNews;
import cn.geekzone.oxygenBar.common.entity.Result;

public interface ObNewsService {
	
	Result getNewsList(ObNews a);
	
	Result updateNews(ObNews a);
	
	Result addNews(ObNews a);
	
	Result deleteNews(Integer id);
	
	Result getNewsById(Integer id);
	
	Result getHotNewsList(ObNews a);
	
	Result getRecommendNewsList(ObNews a);
	
}
