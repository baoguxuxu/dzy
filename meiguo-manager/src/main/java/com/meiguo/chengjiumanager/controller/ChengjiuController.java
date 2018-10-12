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

import com.meiguo.chengjiumanager.domain.ChengjiuDO;
import com.meiguo.chengjiumanager.service.ChengjiuService;
import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;



/**
 * 成就列表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:54:27
 */
 
@Controller
@RequestMapping("/chengjiumanager/chengjiu")
public class ChengjiuController {
	@Autowired
	private ChengjiuService chengjiuService;
	
	@GetMapping()
	@RequiresPermissions("information:chengjiu:chengjiu")
	String Chengjiu(){
	    return "chengjiumanager/chengjiu/chengjiu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:chengjiu:chengjiu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ChengjiuDO> chengjiuList = chengjiuService.list(query);
		int total = chengjiuService.count(query);
		PageUtils pageUtils = new PageUtils(chengjiuList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:chengjiu:add")
	String add(){
	    return "chengjiumanager/chengjiu/add";
	}

	@GetMapping("/edit/{chengjiuId}")
	@RequiresPermissions("information:chengjiu:edit")
	String edit(@PathVariable("chengjiuId") Integer chengjiuId,Model model){
		ChengjiuDO chengjiu = chengjiuService.get(chengjiuId);
		model.addAttribute("chengjiu", chengjiu);
	    return "chengjiumanager/chengjiu/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:chengjiu:add")
	public R save( ChengjiuDO chengjiu){
		if(chengjiuService.save(chengjiu)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:chengjiu:edit")
	public R update( ChengjiuDO chengjiu){
		chengjiuService.update(chengjiu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:chengjiu:remove")
	public R remove( Integer chengjiuId){
		
		ChengjiuDO chengjiu = new ChengjiuDO();
        chengjiu.setChengjiuId(chengjiuId);
        chengjiu.setChengjiuFlag(0);
		chengjiuService.update(chengjiu);
		
//		if(chengjiuService.remove(chengjiuId)>0){
//		return R.ok();
//		}
//		return R.error();
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:chengjiu:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] chengjiuIds){
		chengjiuService.batchRemove(chengjiuIds);
		return R.ok();
	}
	
	
}
