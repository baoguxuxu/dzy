package com.meiguo.product.controller;

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
import com.meiguo.product.domain.CategoryDO;
import com.meiguo.product.service.CategoryService;

/**
 * 分类表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 13:16:05
 */
 
@Controller
@RequestMapping("/information/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@RequiresPermissions("information:category:category")
	String Category(){
	    return "information/category/category";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:category:category")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CategoryDO> categoryList = categoryService.list(query);
		int total = categoryService.count(query);
		PageUtils pageUtils = new PageUtils(categoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:category:add")
	String add(){
	    return "information/category/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:category:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CategoryDO category = categoryService.get(id);
		model.addAttribute("category", category);
	    return "information/category/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:category:add")
	public R save( CategoryDO category){
		if(categoryService.save(category)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:category:edit")
	public R update( CategoryDO category){
		categoryService.update(category);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:category:remove")
	public R remove( Long id){
		CategoryDO categoryDO = new CategoryDO();
		categoryDO.setStatus(1);
		categoryDO.setId(id);
		categoryService.update(categoryDO);
		return R.ok();
		
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody		  
	@RequiresPermissions("information:category:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		categoryService.batchRemove(ids);
		return R.ok();
	}
	
}
