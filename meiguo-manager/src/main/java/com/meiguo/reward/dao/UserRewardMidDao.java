package com.meiguo.reward.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.reward.domain.UserRewardMidDO;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
@Mapper
public interface UserRewardMidDao {

	UserRewardMidDO get(Integer id);
	
	List<UserRewardMidDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserRewardMidDO userRewardMid);
	
	int update(UserRewardMidDO userRewardMid);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<UserRewardMidDO> byListId(Integer id);
}
