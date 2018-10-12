package com.meiguo.chengjiumanager.controller;

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

import com.meiguo.chengjiumanager.domain.ChengjiuAddDO;
import com.meiguo.chengjiumanager.service.ChengjiuAddService;
import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;


/**
 * 成就新增列表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-12 10:59:41
 */
 
@Controller
@RequestMapping("/chengjiumanager/chengjiuadd")
public class ChengjiuAddController {
	@Autowired
	private ChengjiuAddService chengjiuAddService;
	
	@GetMapping()
	@RequiresPermissions("information:chengjiuAdd:chengjiuAdd")
	String ChengjiuAdd(){
	    return "chengjiumanager/chengjiuadd/chengjiuadd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:chengjiuAdd:chengjiuAdd")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ChengjiuAddDO> chengjiuAddList = chengjiuAddService.list(query);
		int total = chengjiuAddService.count(query);
		PageUtils pageUtils = new PageUtils(chengjiuAddList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:chengjiuAdd:add")
	String add(){
	    return "chengjiumanager/chengjiuadd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:chengjiuAdd:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ChengjiuAddDO chengjiuAdd = chengjiuAddService.get(id);
		model.addAttribute("chengjiuAdd", chengjiuAdd);
	    return "chengjiumanager/chengjiuadd/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:chengjiuAdd:add")
	public R save( ChengjiuAddDO chengjiuAdd){
		if(chengjiuAddService.save(chengjiuAdd)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:chengjiuAdd:edit")
	public R update( ChengjiuAddDO chengjiuAdd){
		chengjiuAddService.update(chengjiuAdd);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:chengjiuAdd:remove")
	public R remove( Integer id){
		if(chengjiuAddService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:chengjiuAdd:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		chengjiuAddService.batchRemove(ids);
		return R.ok();
	}
	
}
