package com.meiguo.chengjiumanager.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.chengjiumanager.domain.ChengjiuDO;

/**
 * 成就列表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:54:27
 */
@Mapper
public interface ChengjiuDao {

	ChengjiuDO get(Integer chengjiuId);
	
	List<ChengjiuDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ChengjiuDO chengjiu);
	
	int update(ChengjiuDO chengjiu);
	
	int remove(Integer chengjiu_id);
	
	int batchRemove(Integer[] chengjiuIds);
	
}
