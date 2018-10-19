package com.meiguo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.product.dao.CategoryDao;
import com.meiguo.product.domain.CategoryDO;
import com.meiguo.product.service.CategoryService;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public CategoryDO get(Long id){
		return categoryDao.get(id);
	}
	
	@Override
	public List<CategoryDO> list(Map<String, Object> map){
		return categoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return categoryDao.count(map);
	}
	
	@Override
	@Transactional
	public int save(CategoryDO category){
		category.setStatus(0);
		category.setCreateTime(new Date());
		category.setCreateBy(ShiroUtils.getUser().getUsername());
		category.setUpdateTime(new Date());
		category.setUpdateBy(ShiroUtils.getUser().getUsername());
		return categoryDao.save(category);
	}
	
	@Override
	@Transactional
	public int update(CategoryDO category){
		category.setUpdateTime(new Date());
		category.setUpdateBy(ShiroUtils.getUser().getUsername());
		return categoryDao.update(category);
	}
	
	@Override
	@Transactional
	public int remove(Long id){
		return categoryDao.remove(id);
	}
	
	@Override
	@Transactional
	public int batchRemove(Long[] ids){
		return categoryDao.batchRemove(ids);
	}
	
}
