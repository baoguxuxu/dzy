package com.meiguo.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.order.dao.AddressDao;
import com.meiguo.order.domain.AddressDO;
import com.meiguo.order.service.AddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Override
	public AddressDO get(Integer id){
		return addressDao.get(id);
	}
	
	@Override
	public List<AddressDO> list(Map<String, Object> map){
		return addressDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return addressDao.count(map);
	}
	
	@Override
	public int save(AddressDO address){
		if(address.getDefaultFlag().equals("1")){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId",ShiroUtils.getUserId());
			map.put("defaultFlag",'1');
			List<AddressDO> list = addressDao.list(map);
			for(AddressDO addressDO :list){
				addressDO.setDefaultFlag("0");//默认收获地址只能有一个
				addressDao.update(addressDO);
			}
		}
		address.setUserId(ShiroUtils.getUserId());
		return addressDao.save(address);
	}
	
	@Override
	public int update(AddressDO address){
		if(address.getDefaultFlag().equals("1")){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId",ShiroUtils.getUserId());
			map.put("defaultFlag",'1');
			List<AddressDO> list = addressDao.list(map);
			for(AddressDO addressDO :list){
				addressDO.setDefaultFlag("0");//默认收获地址只能有一个
				addressDao.update(addressDO);
			}
		}
		return addressDao.update(address);
	}
	
	@Override
	public int remove(Integer id){
		return addressDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return addressDao.batchRemove(ids);
	}
	
}
