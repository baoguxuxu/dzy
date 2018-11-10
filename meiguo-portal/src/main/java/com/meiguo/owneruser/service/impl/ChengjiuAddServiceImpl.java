package com.meiguo.owneruser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.owneruser.dao.ChengjiuAddDao;
import com.meiguo.owneruser.domain.ChengjiuAddDO;
import com.meiguo.owneruser.service.ChengjiuAddService;

import java.util.List;
import java.util.Map;




@Service
public class ChengjiuAddServiceImpl implements ChengjiuAddService {
	@Autowired
	private ChengjiuAddDao chengjiuAddDao;
	
	@Override
	public ChengjiuAddDO get(Integer id){
		return chengjiuAddDao.get(id);
	}
	
	@Override
	public List<ChengjiuAddDO> list(Map<String, Object> map){
		return chengjiuAddDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chengjiuAddDao.count(map);
	}
	
	@Override
	public int save(ChengjiuAddDO chengjiuAdd){
		return chengjiuAddDao.save(chengjiuAdd);
	}
	
	@Override
	public int update(ChengjiuAddDO chengjiuAdd){
		return chengjiuAddDao.update(chengjiuAdd);
	}
	
	@Override
	public int remove(Integer id){
		return chengjiuAddDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return chengjiuAddDao.batchRemove(ids);
	}
	
}
