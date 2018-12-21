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
import com.meiguo.owneruser.domain.UserTitleDO;
import com.meiguo.owneruser.service.UserTitleService;



/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-12-12 11:08:57
 */
 
@Controller
@RequestMapping("/information/userTitle")
public class UserTitleController {
	@Autowired
	private UserTitleService userTitleService;
	
	@GetMapping()
	@RequiresPermissions("information:userTitle:userTitle")
	String UserTitle(){
	    return "information/userTitle/userTitle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:userTitle:userTitle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserTitleDO> userTitleList = userTitleService.list(query);
		int total = userTitleService.count(query);
		PageUtils pageUtils = new PageUtils(userTitleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:userTitle:add")
	String add(){
	    return "information/userTitle/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:userTitle:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserTitleDO userTitle = userTitleService.get(id);
		model.addAttribute("userTitle", userTitle);
	    return "information/userTitle/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:userTitle:add")
	public R save( UserTitleDO userTitle){
		if(userTitleService.save(userTitle)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:userTitle:edit")
	public R update( UserTitleDO userTitle){
		userTitleService.update(userTitle);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:userTitle:remove")
	public R remove( Integer id){
		if(userTitleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:userTitle:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userTitleService.batchRemove(ids);
		return R.ok();
	}
	
}
