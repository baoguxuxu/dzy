package com.meiguo.goods.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.goods.domain.ProductCommentDO;

/**
 * 评价表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:37:33
 */
@Mapper
public interface ProductCommentDao {

	ProductCommentDO get(Long id);
	
	List<ProductCommentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductCommentDO productComment);
	
	int update(ProductCommentDO productComment);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
