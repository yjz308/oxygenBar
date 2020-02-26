package cn.geekzone.oxygenBar.base.dao;

import cn.geekzone.oxygenBar.base.entity.ObApplicationUnit;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ObApplicationUnitDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ObApplicationUnit record);

    int insertSelective(ObApplicationUnit record);

    ObApplicationUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ObApplicationUnit record);

    int updateByPrimaryKey(ObApplicationUnit record);
    
    ObApplicationUnit selectByCondition(ObApplicationUnit a);
    
    List<String> selectFormerYears();
    
    List<ObApplicationUnit> getYearUnitList(String year);
    
    ObApplicationUnit getApplicationUnitByToken(ObApplicationUnit a);
}