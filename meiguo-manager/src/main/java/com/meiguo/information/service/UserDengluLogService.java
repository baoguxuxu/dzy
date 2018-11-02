package com.meiguo.information.service;

import com.meiguo.information.domain.UserDengluLogDO;

import java.util.List;
import java.util.Map;

/**
 * 用户登陆记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-02 09:14:45
 */
public interface UserDengluLogService {
	
	UserDengluLogDO get(Integer id);
	
	List<UserDengluLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDengluLogDO userDengluLog);
	
	int update(UserDengluLogDO userDengluLog);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
