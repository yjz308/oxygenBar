package cn.geekzone.oxygenBar.base.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekzone.oxygenBar.base.dao.ObApplicationUnitDAO;
import cn.geekzone.oxygenBar.base.dao.SysUserDAO;
import cn.geekzone.oxygenBar.base.entity.ObApplicationUnit;
import cn.geekzone.oxygenBar.base.entity.SysUser;
import cn.geekzone.oxygenBar.common.entity.Constants;
import cn.geekzone.oxygenBar.common.entity.MessageDigestUtils;
import cn.geekzone.oxygenBar.common.entity.Result;
import cn.geekzone.oxygenBar.utils.RandomUtil;

@Service("ObApplicationUnitService")
public class ObApplicationUnitServiceImpl implements ObApplicationUnitService{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	ObApplicationUnitDAO oauDao;
	
	@Autowired
	SysUserDAO suDao;

	@Override
	public Result ObApplicationUnitQuickLogin(ObApplicationUnit a) {
		// TODO Auto-generated method stub
		Result r = new Result();
//		ObApplicationUnit b = new ObApplicationUnit();
//		b.setPhone(a.getPhone());
//		ObApplicationUnit c = oauDao.selectByCondition(b);
//		if(c == null) {
//			return 
//		}
//		ObApplicationUnit b = new ObApplicationUnit();
//		b.setPhone(a.getPhone());
//		ObApplicationUnit c = oauDao.selectByCondition(b);
//		SysUser d = suDao.selectByPrimaryKey(c.getId());
//		if(d.getPassword().equals(MessageDigestUtils.getMD5(a.getPassword(), Constants.SALT))) {
//			
//		}
		ObApplicationUnit b = new ObApplicationUnit();
		b.setPhone(a.getPhone());
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c == null) {
			return r.setErrorCode().setMessage("该手机号尚未注册,请点击注册");
		}
		SysUser d = suDao.selectByPrimaryKey(c.getId());
		RandomUtil x = new RandomUtil();
		d.setToken(x.randomString(32));
		suDao.updateByPrimaryKeySelective(d);
		c.setToken(d.getToken());
		return r.setData(c).setSuccessCode();
	}

	@Override
	public Result ObApplicationUnitLogin(ObApplicationUnit a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		ObApplicationUnit b = new ObApplicationUnit();
		b.setUnitName(a.getUnitName());
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c == null) {
			return r.setErrorCode().setMessage("该单位尚未注册,请点击注册");
		}
		SysUser d = suDao.selectByPrimaryKey(c.getId());
		if(!d.getPassword().equals(MessageDigestUtils.getMD5(a.getPassword(), Constants.SALT))) {
			return r.setErrorCode().setMessage("密码错误,如忘记密码,请点击忘记密码找回");
		}
		RandomUtil x = new RandomUtil();
		d.setToken(x.randomString(32));
		suDao.updateByPrimaryKeySelective(d);
		c.setToken(d.getToken());
		return r.setData(c).setSuccessCode();
	}

	@Override
	public Result ObApplicationUnitRegister(ObApplicationUnit a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		ObApplicationUnit b = new ObApplicationUnit();
		b.setUnitName(a.getUnitName());
		b.setLinkman(a.getLinkman());
		b.setPhone(a.getPhone());
		oauDao.insertSelective(b);
		ObApplicationUnit c = new ObApplicationUnit();
		c.setPhone(a.getPhone());
		ObApplicationUnit d = oauDao.selectByCondition(c);
		SysUser e = new SysUser();
		e.setUnitId(d.getId());
		e.setPhone(a.getPhone());
		e.setPassword(MessageDigestUtils.getMD5(a.getPassword(), Constants.SALT));
		suDao.insertSelective(e);
		return r.setSuccessCode();
	}

	@Override
	public Result ObApplicationUnitForgetPassword(ObApplicationUnit a) {
		// TODO Auto-generated method stub
		Result r = new Result();
		ObApplicationUnit b = new ObApplicationUnit();
		b.setPhone(a.getPhone());
		ObApplicationUnit c = oauDao.selectByCondition(b);
		if(c == null) {
			return r.setErrorCode().setMessage("该手机号尚未注册,请点击注册");
		}
		SysUser d = suDao.selectByPrimaryKey(c.getId());
		d.setPassword(MessageDigestUtils.getMD5(a.getPassword(), Constants.SALT));
		suDao.updateByPrimaryKeySelective(d);
		return r.setSuccessCode();
	}

	@Override
	public Result getFormerYearsList() {
		// TODO Auto-generated method stub
		Result r = new Result();
		List<String> list = oauDao.selectFormerYears();
		return r.setData(list).setSuccessCode();
	}

	@Override
	public Result getYearUnitList(String year) {
		// TODO Auto-generated method stub
		Result r = new Result();
		List<ObApplicationUnit> list = oauDao.getYearUnitList(year);
		return r.setData(list).setSuccessCode();
	}

	@Override
	public Result getApplicationUnitByToken() {
		// TODO Auto-generated method stub
		Result r = new Result();
		String token = request.getHeader("Authorization");
		if(token == null) {
			return r.setErrorCode().setMessage("请登录");
		}
		ObApplicationUnit a = new ObApplicationUnit();
		a.setToken(token);
		ObApplicationUnit b = oauDao.getApplicationUnitByToken(a);
		if(b == null) {
			return r.setErrorCode().setMessage("请登录");
		}
		return r.setData(b).setSuccessCode();
	}

	@Override
	public Result ObApplicationUnitChangePhone(ObApplicationUnit a) {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void main(String[] args) {
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		System.out.println(sdf.format(date));
//	}
	
}
