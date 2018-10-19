package com.meiguo.goods.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;

/**
 * 货品表
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-17 15:41:03
 */
@Mapper
public interface GoodsDao {

	GoodsDO get(Integer id);
	
	List<GoodsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GoodsDO goods);
	
	int update(GoodsDO goods);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int saveImg(ImgDO imgDO);

	List<ImgDO> listimg(@Param("goods_id")Integer id,@Param("type") Integer type);

	void deleteImg(Integer id);

	void removeImgByID(int parseInt);
}
