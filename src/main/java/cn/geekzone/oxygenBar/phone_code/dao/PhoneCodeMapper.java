package cn.geekzone.oxygenBar.phone_code.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.geekzone.oxygenBar.phone_code.entity.PhoneCode;





@Repository("PhoneCodeMapper")
public interface PhoneCodeMapper {

	int save(@Param("e") PhoneCode e);

	PhoneCode getLastPhoneCode(@Param("e") PhoneCode e);

	int updatePhoneCodeUsed(Integer id);
}
