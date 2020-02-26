package cn.geekzone.oxygenBar.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekzone.oxygenBar.base.dao.ObNoticeDAO;
import cn.geekzone.oxygenBar.base.entity.ObNotice;
import cn.geekzone.oxygenBar.common.entity.Result;

@Service("NoticeService")
public class ObNoticeServiceImpl implements ObNoticeService{
	
	@Autowired
	ObNoticeDAO oncDao;

	@Override
	public Result getNoticeList(ObNotice a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		List<ObNotice> list = oncDao.selectByCondition(a);
		if(list.isEmpty()) {
			return r.setErrorCode().setMessage("暂无通知");
		}
		return r.setData(list).setSuccessCode().add("count", oncDao.selectCountByCondition(a));
	}

	@Override
	public Result updateNotice(ObNotice a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		oncDao.updateByPrimaryKeySelective(a);
		return r.setSuccessCode();
	}

	@Override
	public Result addNotice(ObNotice a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		oncDao.insertSelective(a);
		return r.setSuccessCode();
	}

	@Override
	public Result deleteNotice(Integer id) {
		// TODO Auto-generated method stub
		Result r = new Result();
		oncDao.deleteByPrimaryKey(id);
		return r.setSuccessCode();
	}

	@Override
	public Result getNoticeById(Integer id) {
		// TODO Auto-generated method stub
		Result r = new Result();
		ObNotice a = oncDao.selectByPrimaryKey(id);
		if(a == null) {
			return r.setErrorCode().setMessage("暂无新闻");
		}
		return r.setData(a).setSuccessCode();
	}
	
}
