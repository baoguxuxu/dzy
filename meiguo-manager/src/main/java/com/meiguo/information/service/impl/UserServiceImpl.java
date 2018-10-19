package com.meiguo.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.meiguo.information.dao.UserDao;
import com.meiguo.information.domain.UserDO;
import com.meiguo.information.service.UserService;



@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDO get(Integer id){
		return userDao.get(id);
	}
	
	@Override
	public List<UserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO user){
		return userDao.save(user);
	}
	
	@Override
	public int update(UserDO user){
		return userDao.update(user);
	}
	
	@Override
	public int remove(Integer id){
		return userDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userDao.batchRemove(ids);
	}

	@Override
	public int updateStatus(UserDO user) {
		return userDao.update(user);
	}

	@Override
	public List<Map<String, Object>> exeList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.exeList(map);
	}
	
}
