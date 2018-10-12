package com.meiguo.product.service;


import java.util.List;
import java.util.Map;

import com.meiguo.product.domain.SpecDO;

/**
 * 购物车表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 15:18:22
 */
public interface SpecService {
	
	SpecDO get(Long id);
	
	List<SpecDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecDO spec);
	
	int update(SpecDO spec);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateByParentId(SpecDO specDO);
}
