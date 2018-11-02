package com.meiguo.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.meiguo.information.dao.UserDengluLogDao;
import com.meiguo.information.domain.UserDengluLogDO;
import com.meiguo.information.service.UserDengluLogService;



@Service
public class UserDengluLogServiceImpl implements UserDengluLogService {
	@Autowired
	private UserDengluLogDao userDengluLogDao;
	
	@Override
	public UserDengluLogDO get(Integer id){
		return userDengluLogDao.get(id);
	}
	
	@Override
	public List<UserDengluLogDO> list(Map<String, Object> map){
		return userDengluLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDengluLogDao.count(map);
	}
	
	@Override
	public int save(UserDengluLogDO userDengluLog){
		return userDengluLogDao.save(userDengluLog);
	}
	
	@Override
	public int update(UserDengluLogDO userDengluLog){
		return userDengluLogDao.update(userDengluLog);
	}
	
	@Override
	public int remove(Integer id){
		return userDengluLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userDengluLogDao.batchRemove(ids);
	}
	
}
