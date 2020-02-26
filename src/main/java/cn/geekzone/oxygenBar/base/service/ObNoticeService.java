package cn.geekzone.oxygenBar.base.service;

import cn.geekzone.oxygenBar.base.entity.ObNotice;
import cn.geekzone.oxygenBar.common.entity.Result;

public interface ObNoticeService {
	
	Result getNoticeList(ObNotice a);
	
	Result updateNotice(ObNotice a);
	
	Result addNotice(ObNotice a);
	
	Result deleteNotice(Integer id);
	
	Result getNoticeById(Integer id);
	
}
