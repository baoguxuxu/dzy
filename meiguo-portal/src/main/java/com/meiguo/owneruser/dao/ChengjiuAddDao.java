package com.meiguo.owneruser.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.meiguo.owneruser.domain.ChengjiuAddDO;



/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-12 10:59:41
 */
@Mapper
public interface ChengjiuAddDao {

	ChengjiuAddDO get(Integer id);
	
	List<ChengjiuAddDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ChengjiuAddDO chengjiuAdd);
	
	int update(ChengjiuAddDO chengjiuAdd);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
