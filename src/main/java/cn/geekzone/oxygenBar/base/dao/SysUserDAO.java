package cn.geekzone.oxygenBar.base.dao;

import cn.geekzone.oxygenBar.base.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}