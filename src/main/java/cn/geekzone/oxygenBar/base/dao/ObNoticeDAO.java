package cn.geekzone.oxygenBar.base.dao;

import cn.geekzone.oxygenBar.base.entity.ObNotice;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ObNoticeDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ObNotice record);

    int insertSelective(ObNotice record);

    ObNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ObNotice record);

    int updateByPrimaryKeyWithBLOBs(ObNotice record);

    int updateByPrimaryKey(ObNotice record);
    
    List<ObNotice> selectByCondition(ObNotice a);
    
    Integer selectCountByCondition(ObNotice a);
    
}