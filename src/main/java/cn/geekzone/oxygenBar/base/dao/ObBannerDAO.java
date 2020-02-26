package cn.geekzone.oxygenBar.base.dao;

import cn.geekzone.oxygenBar.base.entity.ObBanner;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ObBannerDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ObBanner record);

    int insertSelective(ObBanner record);

    ObBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ObBanner record);

    int updateByPrimaryKey(ObBanner record);
    
    List<ObBanner> selectByCondition(ObBanner a);
    
    Integer selectCountByCondition(ObBanner a);
}