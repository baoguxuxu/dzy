package com.meiguo.information.controller;

import java.util.Date;
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

import com.meiguo.common.utils.FileUtil;
import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.information.domain.UserDO;
import com.meiguo.information.service.UserService;


/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 10:03:45
 */
 
@Controller("UserController")
@RequestMapping("/information/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping()
	@RequiresPermissions("information:user:user")
	String User(){
	    return "information/users/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:user:add")
	String add(){
	    return "information/users/add";
	}
	

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:user:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserDO user = userService.get(id);
		model.addAttribute("user", user);
	    return "information/users/edit";
	}
	
	@GetMapping("/show/{id}")
	@RequiresPermissions("information:user:chakan")
	String show(@PathVariable("id") Integer id,Model model){
		UserDO user = userService.get(id);
		model.addAttribute("user", user);
	    return "information/users/show";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:user:add")
	public R save( UserDO user){
		
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:user:edit")
	public R update( UserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:user:remove")
	public R remove( Integer id){
		if(userService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:user:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(Integer id,Integer enable) {
		UserDO sysFile = new UserDO();
		sysFile.setId(id);
		sysFile.setDeleteFlag(enable);
		userService.updateStatus(sysFile);

		return R.ok();
	}	
}
