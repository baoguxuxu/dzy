package com.meiguo.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.product.dao.SpecDao;
import com.meiguo.product.domain.SpecDO;
import com.meiguo.product.service.SpecService;

import java.util.Date;
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
	
	@Transactional
	@Override
	public int save(SpecDO spec){
		spec.setCreateBy(ShiroUtils.getUser().getUsername());
		spec.setCreateTime(new Date());
		spec.setUpdateBy(ShiroUtils.getUser().getUsername());
		spec.setUpdateTime(new Date());
		return specDao.save(spec);
	}
	
	@Transactional
	@Override
	public int update(SpecDO spec){
		spec.setUpdateBy(ShiroUtils.getUser().getUsername());
		spec.setUpdateTime(new Date());
		return specDao.update(spec);
	}
	
	@Transactional
	@Override
	public int remove(Long id){
		return specDao.remove(id);
	}
	
	@Transactional
	@Override
	public int batchRemove(Long[] ids){
		return specDao.batchRemove(ids);
	}

	@Transactional
	@Override
	public int updateByParentId(SpecDO specDO) {
		return specDao.updateByParentId(specDO);
	}
	
}
