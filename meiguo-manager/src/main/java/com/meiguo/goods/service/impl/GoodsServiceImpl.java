package com.meiguo.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.dao.GoodsDao;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.GoodsDO.GoodsSpec;
import com.meiguo.goods.domain.ImgDO;
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
	
	@Override
	public List<GoodsDO> list(Map<String, Object> map){
		return goodsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goodsDao.count(map);
	}
	
	@Transactional
	@Override
	public int save(GoodsDO goods){
		List<GoodsSpec> list = goods.getList();
		if(list!=null){
			String rel="";
			for(GoodsSpec goodsSpec :list){
				rel+=goodsSpec.getSpecname()+":"+goodsSpec.getSpecdetail()+"  ";
			}
			goods.setSpec(rel);
		}
		goods.setSurplus(goods.getTotal());
		goods.setCreateBy(ShiroUtils.getUser().getUsername());
		goods.setCreateTime(new Date());
		goods.setUpdateBy(ShiroUtils.getUser().getUsername());
		goods.setUpdateTime(new Date());
		return goodsDao.save(goods);
	}
	
	@Override
	public int update(GoodsDO goods){
		List<GoodsSpec> list = goods.getList();
		if(list!=null){
			String rel="";
			for(GoodsSpec goodsSpec :list){
				rel+=goodsSpec.getSpecname()+":"+goodsSpec.getSpecdetail()+"  ";
			}
			goods.setSpec(rel);
		}
		goods.setUpdateBy(ShiroUtils.getUser().getUsername());
		goods.setUpdateTime(new Date());
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
	
}
