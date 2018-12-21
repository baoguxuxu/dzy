package com.meiguo.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.meiguo.reward.dao.UserTitleDao;
import com.meiguo.reward.domain.UserTitleDO;
import com.meiguo.reward.service.UserTitleService;



@Service
public class UserTitleServiceImpl implements UserTitleService {
	@Autowired
	private UserTitleDao userTitleDao;
	
	@Override
	public UserTitleDO get(Integer id){
		return userTitleDao.get(id);
	}
	
	@Override
	public List<UserTitleDO> list(Map<String, Object> map){
		return userTitleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userTitleDao.count(map);
	}
	
	@Override
	public int save(UserTitleDO userTitle){
		return userTitleDao.save(userTitle);
	}
	
	@Override
	public int update(UserTitleDO userTitle){
		return userTitleDao.update(userTitle);
	}
	
	@Override
	public int remove(Integer id){
		return userTitleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userTitleDao.batchRemove(ids);
	}
	
}
