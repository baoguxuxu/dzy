package com.meiguo.owneruser.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.owneruser.domain.UserTitleDO;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
@Mapper
public interface UserTitleDao {

	UserTitleDO get(Integer id);
	
	List<UserTitleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserTitleDO userTitle);
	
	int update(UserTitleDO userTitle);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
