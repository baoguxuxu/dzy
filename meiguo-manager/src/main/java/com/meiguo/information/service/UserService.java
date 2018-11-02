package com.meiguo.information.service;


import com.meiguo.information.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 10:03:45
 */
public interface UserService {
	
	UserDO get(Integer id);
	
	List<UserDO> list(Map<String, Object> map);
	List<Map<String, Object>> exeList(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int updateStatus(UserDO user);
	
	UserDO userCheng(Integer id);
}
