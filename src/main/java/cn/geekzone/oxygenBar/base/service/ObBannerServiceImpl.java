package cn.geekzone.oxygenBar.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekzone.oxygenBar.base.dao.ObBannerDAO;
import cn.geekzone.oxygenBar.base.entity.ObBanner;
import cn.geekzone.oxygenBar.common.entity.Result;

@Service("BannerService")
public class ObBannerServiceImpl implements ObBannerService{
	
	@Autowired
	ObBannerDAO obDao;

	@Override
	public Result getBannerList(ObBanner a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		a.setType(1);
		List<ObBanner> list = obDao.selectByCondition(a);
		if(list.isEmpty()) {
			return r.setErrorCode().setMessage("暂无轮播图");
		}
		return r.setData(list).setSuccessCode().add("count", obDao.selectCountByCondition(a));
	}

	@Override
	public Result updateBanner(ObBanner a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		obDao.updateByPrimaryKeySelective(a);
		return r.setSuccessCode();
	}

	@Override
	public Result addBanner(ObBanner a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		obDao.insertSelective(a);
		return r.setSuccessCode();
	}

	@Override
	public Result deleteBanner(Integer id) {
		// TODO Auto-generated method stub
		Result r = new Result();
		obDao.deleteByPrimaryKey(id);
		return r.setSuccessCode();
	}

	@Override
	public Result getBannerById(Integer id) {
		// TODO Auto-generated method stub
		Result r = new Result();
		ObBanner a = obDao.selectByPrimaryKey(id);
		if(a == null) {
			return r.setErrorCode().setMessage("暂无图片");
		}
		return r.setData(a).setSuccessCode();
	}

	@Override
	public Result getPicList(ObBanner a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		a.setType(2);
		List<ObBanner> list = obDao.selectByCondition(a);
		if(list.isEmpty()) {
			return r.setErrorCode().setMessage("暂无宣传图片");
		}
		return r.setData(list).setSuccessCode().add("count", obDao.selectCountByCondition(a));
	}
	
}
