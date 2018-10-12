package com.meiguo.product.service;


import java.util.List;
import java.util.Map;

import com.meiguo.product.domain.ProductDO;
import com.meiguo.product.domain.Spec_Product;

/**
 * 产品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:09:27
 */
public interface ProductService {
	
	ProductDO get(Long id);
	
	List<ProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int saveSpec(Spec_Product spec_Product);

	List<Spec_Product> listSpec(Long id);

	int updateSpec(Spec_Product spec_Product);
}
