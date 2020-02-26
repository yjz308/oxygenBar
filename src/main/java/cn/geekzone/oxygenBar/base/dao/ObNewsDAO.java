package cn.geekzone.oxygenBar.base.dao;

import cn.geekzone.oxygenBar.base.entity.ObNews;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ObNewsDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ObNews record);

    int insertSelective(ObNews record);

    ObNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ObNews record);

    int updateByPrimaryKeyWithBLOBs(ObNews record);

    int updateByPrimaryKey(ObNews record);
    
    List<ObNews> selectByCondition(ObNews a);
    
    Integer selectCountByCondition(ObNews a);
    
    List<ObNews> selectNewsByReadCount(ObNews a);
    
}