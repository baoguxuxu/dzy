package com.meiguo.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.product.domain.CategoryDO;

/**
 * 分类表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 13:16:05
 */
@Mapper
public interface CategoryDao {

	CategoryDO get(Long id);
	
	List<CategoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CategoryDO category);
	
	int update(CategoryDO category);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
