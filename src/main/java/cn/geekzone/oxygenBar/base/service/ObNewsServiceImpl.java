package cn.geekzone.oxygenBar.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekzone.oxygenBar.base.dao.ObNewsDAO;
import cn.geekzone.oxygenBar.base.entity.ObBanner;
import cn.geekzone.oxygenBar.base.entity.ObNews;
import cn.geekzone.oxygenBar.common.entity.Result;


@Service("NewsService")
public class ObNewsServiceImpl implements ObNewsService{
	
	@Autowired
	ObNewsDAO onDao;

	@Override
	public Result getNewsList(ObNews a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		if(a.getType() == 0) {
			ObNews b = new ObNews();
			b.setTitle(a.getTitle());
			List<ObNews> list = onDao.selectByCondition(b);
			return r.setData(list).setSuccessCode().add("count", onDao.selectCountByCondition(b));
		}
		List<ObNews> list = onDao.selectByCondition(a);
		if(list.isEmpty()) {
			return r.setErrorCode().setMessage("暂无新闻");
		}
//		for(int i=0; i<list.size(); i++) {
//			
//			
//		}
		return r.setData(list).setSuccessCode().add("count", onDao.selectCountByCondition(a));
	}

	@Override
	public Result updateNews(ObNews a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		onDao.updateByPrimaryKeySelective(a);
		return r.setSuccessCode();
	}

	@Override
	public Result addNews(ObNews a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		onDao.insertSelective(a);
		return r.setSuccessCode();
	}

	@Override
	public Result deleteNews(Integer id) {
		// TODO Auto-generated method stub
		Result r = new Result();
		onDao.deleteByPrimaryKey(id);
		return r.setSuccessCode();
	}

	@Override
	public Result getNewsById(Integer id) {
		// TODO Auto-generated method stub
		Result r = new Result();
		ObNews a = onDao.selectByPrimaryKey(id);
		if(a == null) {
			return r.setErrorCode().setMessage("暂无新闻");
		}
		return r.setData(a).setSuccessCode();
	}

	@Override
	public Result getHotNewsList(ObNews a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		List<ObNews> list = onDao.selectNewsByReadCount(a);
		if(list.isEmpty()) {
			return r.setErrorCode().setMessage("暂无热门新闻");
		}
		return r.setSuccessCode().setData(list);
	}

	@Override
	public Result getRecommendNewsList(ObNews a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		a.setStatus(1);
		List<ObNews> list = onDao.selectByCondition(a);
		if(list.isEmpty()) {
			return r.setErrorCode().setMessage("暂无推荐新闻");
		}
		return r.setSuccessCode().setData(list);
	}
	
}
