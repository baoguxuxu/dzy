package com.meiguo.goods.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.goods.domain.OrderCommentImageDO;

/**
 * 评价图片表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:55:12
 */
@Mapper
public interface OrderCommentImageDao {

	OrderCommentImageDO get(Long id);
	
	List<OrderCommentImageDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrderCommentImageDO orderCommentImage);
	
	int update(OrderCommentImageDO orderCommentImage);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
