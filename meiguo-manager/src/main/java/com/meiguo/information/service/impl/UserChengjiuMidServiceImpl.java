package com.meiguo.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.meiguo.information.dao.UserChengjiuMidDao;
import com.meiguo.information.domain.UserChengjiuMidDO;
import com.meiguo.information.service.UserChengjiuMidService;



@Service
public class UserChengjiuMidServiceImpl implements UserChengjiuMidService {
	@Autowired
	private UserChengjiuMidDao userChengjiuMidDao;
	
	@Override
	public UserChengjiuMidDO get(Integer id){
		return userChengjiuMidDao.get(id);
	}
	
	@Override
	public List<UserChengjiuMidDO> list(Map<String, Object> map){
		return userChengjiuMidDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userChengjiuMidDao.count(map);
	}
	
	@Override
	public int save(UserChengjiuMidDO userChengjiuMid){
		return userChengjiuMidDao.save(userChengjiuMid);
	}
	
	@Override
	public int update(UserChengjiuMidDO userChengjiuMid){
		return userChengjiuMidDao.update(userChengjiuMid);
	}
	
	@Override
	public int remove(Integer id){
		return userChengjiuMidDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userChengjiuMidDao.batchRemove(ids);
	}

	@Override
	public UserChengjiuMidDO getListId(Integer id) {
		// TODO Auto-generated method stub
		return userChengjiuMidDao.getListId(id);
	}
	
}
