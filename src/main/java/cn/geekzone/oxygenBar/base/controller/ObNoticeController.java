package cn.geekzone.oxygenBar.base.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.geekzone.oxygenBar.base.entity.ObNotice;
import cn.geekzone.oxygenBar.base.service.ObNoticeService;
import cn.geekzone.oxygenBar.common.entity.Result;

@RestController
@RequestMapping({ "/notice" })
public class ObNoticeController {
	
	@Resource(name = "NoticeService")
	ObNoticeService oncService;
	
	@RequestMapping({ "/getNoticeList" })
	public Result getNoticeList(ObNotice a) {
		return oncService.getNoticeList(a);
	}
	
	@RequestMapping({ "/updateNotice" })
	public Result updateNotice(ObNotice a) {
		return oncService.updateNotice(a);
	}
	
	@RequestMapping({ "/addNotice" })
	public Result addNotice(ObNotice a) {
		return oncService.addNotice(a);
	}
	
	@RequestMapping({ "/deleteNotice" })
	public Result deleteNotice(Integer id) {
		return oncService.deleteNotice(id);
	}
	
	@RequestMapping({ "/getNoticeById" })
	public Result getNoticeById(Integer id) {
		return oncService.getNoticeById(id);
	}
	
}
