package com.meiguo.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.dao.ProductCommentDao;
import com.meiguo.goods.domain.ProductCommentDO;
import com.meiguo.goods.service.ProductCommentService;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class ProductCommentServiceImpl implements ProductCommentService {
	@Autowired
	private ProductCommentDao productCommentDao;
	
	@Override
	public ProductCommentDO get(Long id){
		return productCommentDao.get(id);
	}
	
	@Override
	public List<ProductCommentDO> list(Map<String, Object> map){
		return productCommentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productCommentDao.count(map);
	}
	
	@Override
	public int save(ProductCommentDO productComment){
		productComment.setCreateBy(ShiroUtils.getUser().getName());
		productComment.setCreateTime(new Date());
		productComment.setUserId(ShiroUtils.getUserId());
		productComment.setNickname(ShiroUtils.getUser().getNickname());
		productComment.setHeadimgurl(ShiroUtils.getUser().getHeardUrl());
		return productCommentDao.save(productComment);
	}
	
	@Override
	public int update(ProductCommentDO productComment){
		return productCommentDao.update(productComment);
	}
	
	@Override
	public int remove(Long id){
		return productCommentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return productCommentDao.batchRemove(ids);
	}
	
}
