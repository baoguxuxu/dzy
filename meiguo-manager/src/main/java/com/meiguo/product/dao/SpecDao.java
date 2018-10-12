package com.meiguo.product.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.product.domain.SpecDO;

/**
 * 购物车表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 15:18:22
 */
@Mapper
public interface SpecDao {

	SpecDO get(Long id);
	
	List<SpecDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SpecDO spec);
	
	int update(SpecDO spec);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateByParentId(SpecDO specDO);
}
