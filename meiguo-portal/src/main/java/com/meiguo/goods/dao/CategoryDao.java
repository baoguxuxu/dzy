package com.meiguo.goods.dao;



import org.apache.ibatis.annotations.Mapper;

import com.meiguo.goods.domain.CategoryDO;


/**
 * 分类表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 13:16:05
 */
@Mapper
public interface CategoryDao {

	CategoryDO get(Long id);

}
