package com.meiguo.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.meiguo.reward.dao.UserRewardMidDao;
import com.meiguo.reward.domain.UserRewardMidDO;
import com.meiguo.reward.service.UserRewardMidService;



@Service
public class UserRewardMidServiceImpl implements UserRewardMidService {
	@Autowired
	private UserRewardMidDao userRewardMidDao;
	
	@Override
	public UserRewardMidDO get(Integer id){
		return userRewardMidDao.get(id);
	}
	
	@Override
	public List<UserRewardMidDO> list(Map<String, Object> map){
		return userRewardMidDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userRewardMidDao.count(map);
	}
	
	@Override
	public int save(UserRewardMidDO userRewardMid){
		return userRewardMidDao.save(userRewardMid);
	}
	
	@Override
	public int update(UserRewardMidDO userRewardMid){
		return userRewardMidDao.update(userRewardMid);
	}
	
	@Override
	public int remove(Integer id){
		return userRewardMidDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userRewardMidDao.batchRemove(ids);
	}

	@Override
	public List<UserRewardMidDO> byListId(Integer id) {
		return userRewardMidDao.byListId(id);
	}
	
}
