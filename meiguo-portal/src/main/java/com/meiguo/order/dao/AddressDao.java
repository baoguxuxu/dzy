package com.meiguo.order.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.order.domain.AddressDO;

/**
 * 收货地址
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-08 13:24:18
 */
@Mapper
public interface AddressDao {

	AddressDO get(Integer id);
	
	List<AddressDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AddressDO address);
	
	int update(AddressDO address);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
