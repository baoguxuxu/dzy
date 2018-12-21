package com.meiguo.reward.service;


import java.util.List;
import java.util.Map;

import com.meiguo.reward.domain.UserCouponDO;

/**
 * 奖券表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
public interface UserCouponService {
	
	UserCouponDO get(Integer id);
	
	List<UserCouponDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserCouponDO userCoupon);
	
	int update(UserCouponDO userCoupon);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
