package com.meiguo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.product.dao.ProductDao;
import com.meiguo.product.domain.ProductDO;
import com.meiguo.product.domain.Spec_Product;
import com.meiguo.product.service.ProductService;

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
	
	@Override
	public int save(ProductDO product){
		return productDao.save(product);
	}
	
	@Override
	public int update(ProductDO product){
		return productDao.update(product);
	}
	
	@Override
	public int remove(Long id){
		return productDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return productDao.batchRemove(ids);
	}

	@Override
	public int saveSpec(Spec_Product spec_Product) {
		return productDao.saveSpec(spec_Product);
	}

	@Override
	public List<Spec_Product> listSpec(Long id) {
		// TODO Auto-generated method stub
		return productDao.listSpec(id);
	}

	@Override
	public int updateSpec(Spec_Product spec_Product) {
		return productDao.updateSpec(spec_Product);
	}
	
}
