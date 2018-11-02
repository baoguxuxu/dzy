package com.meiguo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.product.dao.ProductDao;
import com.meiguo.product.domain.ProductDO;
import com.meiguo.product.service.ProductService;

import java.util.Date;
import java.util.List;
import java.util.Map;




@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductDO get(Long id){
		return productDao.get(id);
	}
	
	@Override
	public List<ProductDO> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Transactional
	@Override
	public int save(ProductDO product){
		product.setCreateBy(ShiroUtils.getUser().getUsername());
		product.setCreateTime(new Date());
		product.setUpdateBy(ShiroUtils.getUser().getUsername());
		product.setUpdateTime(new Date());
		return productDao.save(product);
	}
	
	@Transactional
	@Override
	public int update(ProductDO product){
		product.setUpdateBy(ShiroUtils.getUser().getUsername());
		product.setUpdateTime(new Date());
		return productDao.update(product);
	}
	
	@Transactional
	@Override
	public int remove(Long id){
		return productDao.remove(id);
	}
	
	@Transactional
	@Override
	public int batchRemove(Long[] ids){
		return productDao.batchRemove(ids);
	}
	
	/*@Transactional
	@Override
	public int saveSpec(Spec_Product spec_Product) {
		return productDao.saveSpec(spec_Product);
	}

	@Override
	public List<Spec_Product> listSpec(Long id) {
		// TODO Auto-generated method stub
		return productDao.listSpec(id);
	}

	@Transactional
	@Override
	public int updateSpec(Spec_Product spec_Product) {
		return productDao.updateSpec(spec_Product);
	}*/
	
}
