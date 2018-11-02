package com.meiguo.information.dao;

import com.meiguo.information.domain.UserChengjiuMidDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户成就中间表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-02 09:14:44
 */
@Mapper
public interface UserChengjiuMidDao {

	UserChengjiuMidDO get(Integer id);
	
	List<UserChengjiuMidDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserChengjiuMidDO userChengjiuMid);
	
	int update(UserChengjiuMidDO userChengjiuMid);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	UserChengjiuMidDO getListId(Long id);
}
