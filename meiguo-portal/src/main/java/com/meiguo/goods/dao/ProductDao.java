package com.meiguo.goods.dao;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.goods.domain.ProductDO;



/**
 * 产品表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:09:27
 */
@Mapper
public interface ProductDao {

	ProductDO get(Long id);
}
