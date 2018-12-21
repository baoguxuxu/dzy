package com.meiguo.goods.service;


import java.util.List;
import java.util.Map;


import com.meiguo.goods.domain.CartDO;
import com.meiguo.goods.domain.CategoryDO;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.domain.ProductDO;

/**
 * 货品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-17 15:41:03
 */
public interface GoodsService {
	
	GoodsDO get(Long id);
	
	List<CategoryDO> listCategory(Map<String, Object> map);
	
	



	List<ProductDO> getProductByCategoryName(String name);


	List<GoodsDO> getGoodsByProductId(Long id);



	List<ImgDO> listGoodsimgAndDetailimg(Long id, int i);



	List<GoodsDO> list(Map<String, Object> map);
	String byGoods(List<CartDO> cartDOList);
}
