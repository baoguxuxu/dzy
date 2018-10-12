package com.meiguo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.product.dao.SpecDao;
import com.meiguo.product.domain.SpecDO;
import com.meiguo.product.service.SpecService;

import java.util.List;
import java.util.Map;



@Service
public class SpecServiceImpl implements SpecService {
	@Autowired
	private SpecDao specDao;
	
	@Override
	public SpecDO get(Long id){
		return specDao.get(id);
	}
	
	@Override
	public List<SpecDO> list(Map<String, Object> map){
		return specDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specDao.count(map);
	}
	
	@Override
	public int save(SpecDO spec){
		return specDao.save(spec);
	}
	
	@Override
	public int update(SpecDO spec){
		return specDao.update(spec);
	}
	
	@Override
	public int remove(Long id){
		return specDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return specDao.batchRemove(ids);
	}

	@Override
	public int updateByParentId(SpecDO specDO) {
		return specDao.updateByParentId(specDO);
	}
	
}
