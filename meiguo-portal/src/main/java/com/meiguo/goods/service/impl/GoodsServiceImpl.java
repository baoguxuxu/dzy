package com.meiguo.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.dao.GoodsDao;
import com.meiguo.goods.domain.CategoryDO;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.domain.ProductDO;
import com.meiguo.goods.service.GoodsService;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public GoodsDO get(Integer id){
		return goodsDao.get(id);
	}
	//查询产品分类信息
	@Override
	public List<CategoryDO> listCategory(Map<String, Object> map){
		return goodsDao.listCategory(map);
	}
	
	@Override
	public int countCategory(Map<String, Object> map){
		return goodsDao.countCategory(map);
	}
	
	@Transactional
	@Override
	public int save(GoodsDO goods){
		goods.setCreateBy(ShiroUtils.getUser().getUsername());
		goods.setCreateTime(new Date());
		goods.setUpdateBy(ShiroUtils.getUser().getUsername());
		goods.setUpdateTime(new Date());
		return goodsDao.save(goods);
	}
	
	@Override
	public int update(GoodsDO goods){
		return goodsDao.update(goods);
	}
	
	@Override
	public int remove(Integer id){
		return goodsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return goodsDao.batchRemove(ids);
	}
	
	@Transactional
	@Override
	public int saveImg(ImgDO imgDO) {
		return goodsDao.saveImg(imgDO);
	}

	@Override
	public List<ImgDO> listimg(Integer id,Integer type) {
		return goodsDao.listimg(id,type);
	}

	@Override
	public void deleteImg(Integer id) {
		goodsDao.deleteImg(id);
	}

	@Override
	public void removeImgByID(int parseInt) {
		goodsDao.removeImgByID(parseInt);
	}
	/**
	 * 根据分类名称查询该分类下的商品
	 */
	@Override
	public List<ProductDO> getProductByCategoryName(String name) {
		return goodsDao.getProductByCategoryName( name);
	}
	@Override
	public List<GoodsDO> getProductByProductId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<GoodsDO> getGoodsByProductId(Integer id) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsByProductId(id);
	}
	@Override
	public List<ImgDO> getGoodsImgByGoodsDO(List<GoodsDO> listGoodsDO, int i) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsImgByGoodsDO(listGoodsDO,i);
	}
	
}
