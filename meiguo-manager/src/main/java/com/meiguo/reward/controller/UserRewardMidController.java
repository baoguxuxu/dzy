package com.meiguo.reward.controller;

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
import com.meiguo.reward.domain.UserRewardMidDO;
import com.meiguo.reward.service.UserRewardMidService;


/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
 
@Controller
@RequestMapping("/reward/userRewardMid")
public class UserRewardMidController {
	@Autowired
	private UserRewardMidService userRewardMidService;
	
	@GetMapping()
	@RequiresPermissions("information:userRewardMid:userRewardMid")
	String UserRewardMid(){
	    return "reward/userRewardMid/userRewardMid";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:userRewardMid:userRewardMid")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserRewardMidDO> userRewardMidList = userRewardMidService.list(query);
		int total = userRewardMidService.count(query);
		PageUtils pageUtils = new PageUtils(userRewardMidList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:userRewardMid:add")
	String add(){
	    return "reward/userRewardMid/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:userRewardMid:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserRewardMidDO userRewardMid = userRewardMidService.get(id);
		model.addAttribute("userRewardMid", userRewardMid);
	    return "reward/userRewardMid/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:userRewardMid:add")
	public R save( UserRewardMidDO userRewardMid){
		if(userRewardMidService.save(userRewardMid)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:userRewardMid:edit")
	public R update( UserRewardMidDO userRewardMid){
		userRewardMidService.update(userRewardMid);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:userRewardMid:remove")
	public R remove( Integer id){
		if(userRewardMidService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:userRewardMid:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userRewardMidService.batchRemove(ids);
		return R.ok();
	}
	
}
