package com.meiguo.owneruser.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.owneruser.domain.UserCouponDO;
import com.meiguo.owneruser.service.UserCouponService;



/**
 * 奖券表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
 
@Controller
@RequestMapping("/information/userCoupon")
public class UserCouponController {
	@Autowired
	private UserCouponService userCouponService;
	
	@GetMapping()
	@RequiresPermissions("information:userCoupon:userCoupon")
	String UserCoupon(){
	    return "information/userCoupon/userCoupon";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:userCoupon:userCoupon")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserCouponDO> userCouponList = userCouponService.list(query);
		int total = userCouponService.count(query);
		PageUtils pageUtils = new PageUtils(userCouponList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:userCoupon:add")
	String add(){
	    return "information/userCoupon/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:userCoupon:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserCouponDO userCoupon = userCouponService.get(id);
		model.addAttribute("userCoupon", userCoupon);
	    return "information/userCoupon/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:userCoupon:add")
	public R save( UserCouponDO userCoupon){
		if(userCouponService.save(userCoupon)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:userCoupon:edit")
	public R update( UserCouponDO userCoupon){
		userCouponService.update(userCoupon);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:userCoupon:remove")
	public R remove( Integer id){
		if(userCouponService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:userCoupon:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userCouponService.batchRemove(ids);
		return R.ok();
	}
	
}
