package com.meiguo.information.dao;

import com.meiguo.information.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户信息表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 10:03:45
 */
@Mapper
@Repository("UserDao")
public interface UserDao{

	UserDO get(Integer id);
	
	List<UserDO> list(Map<String,Object> map);
	List<Map<String, Object>> exeList(Map<String, Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	

}
