package com.meiguo.owneruser.dao;

import com.meiguo.owneruser.domain.OwnerUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author tmn
 */
@Mapper
public interface OwnerUserDao {

	OwnerUserDO get(Long id);
	
	OwnerUserDO getbyname(String username);
	
	OwnerUserDO getList(Long inviterId);
	
	List<OwnerUserDO> list(Map<String,Object> map);
	
	int save(OwnerUserDO user);
	
	int count(Map<String,Object> map);
	
	int update(OwnerUserDO user);
	
	int remove(Long userId);
	
	OwnerUserDO getbyzhuce(String zhucema);
	
	
}
