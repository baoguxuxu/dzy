package com.meiguo.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.meiguo.reward.dao.UserCouponDao;
import com.meiguo.reward.domain.UserCouponDO;
import com.meiguo.reward.service.UserCouponService;



@Service
public class UserCouponServiceImpl implements UserCouponService {
	@Autowired
	private UserCouponDao userCouponDao;
	
	@Override
	public UserCouponDO get(Integer id){
		return userCouponDao.get(id);
	}
	
	@Override
	public List<UserCouponDO> list(Map<String, Object> map){
		return userCouponDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userCouponDao.count(map);
	}
	
	@Override
	public int save(UserCouponDO userCoupon){
		return userCouponDao.save(userCoupon);
	}
	
	@Override
	public int update(UserCouponDO userCoupon){
		return userCouponDao.update(userCoupon);
	}
	
	@Override
	public int remove(Integer id){
		return userCouponDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userCouponDao.batchRemove(ids);
	}
	
}
