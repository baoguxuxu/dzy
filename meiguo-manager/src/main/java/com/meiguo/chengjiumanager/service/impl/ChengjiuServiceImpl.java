package com.meiguo.chengjiumanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.chengjiumanager.dao.ChengjiuDao;
import com.meiguo.chengjiumanager.domain.ChengjiuDO;
import com.meiguo.chengjiumanager.service.ChengjiuService;

import java.util.List;
import java.util.Map;





@Service
public class ChengjiuServiceImpl implements ChengjiuService {
	@Autowired
	private ChengjiuDao chengjiuDao;
	
	@Override
	public ChengjiuDO get(Integer chengjiuId){
		return chengjiuDao.get(chengjiuId);
	}
	
	@Override
	public List<ChengjiuDO> list(Map<String, Object> map){
		return chengjiuDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chengjiuDao.count(map);
	}
	
	@Override
	public int save(ChengjiuDO chengjiu){
		return chengjiuDao.save(chengjiu);
	}
	
	@Override
	public int update(ChengjiuDO chengjiu){
		return chengjiuDao.update(chengjiu);
	}
	
	@Override
	public int remove(Integer chengjiuId){
		return chengjiuDao.remove(chengjiuId);
	}
	
	@Override
	public int batchRemove(Integer[] chengjiuIds){
		return chengjiuDao.batchRemove(chengjiuIds);
	}


}
