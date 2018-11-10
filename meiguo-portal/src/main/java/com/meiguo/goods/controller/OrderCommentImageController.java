package com.meiguo.goods.controller;

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
import com.meiguo.goods.domain.OrderCommentImageDO;
import com.meiguo.goods.service.OrderCommentImageService;


/**
 * 评价图片表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:55:12
 */
 
@Controller
@RequestMapping("/system/orderCommentImage")
public class OrderCommentImageController {
	@Autowired
	private OrderCommentImageService orderCommentImageService;
	
	@GetMapping()
	@RequiresPermissions("system:orderCommentImage:orderCommentImage")
	String OrderCommentImage(){
	    return "system/orderCommentImage/orderCommentImage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:orderCommentImage:orderCommentImage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderCommentImageDO> orderCommentImageList = orderCommentImageService.list(query);
		int total = orderCommentImageService.count(query);
		PageUtils pageUtils = new PageUtils(orderCommentImageList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:orderCommentImage:add")
	String add(){
	    return "system/orderCommentImage/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:orderCommentImage:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OrderCommentImageDO orderCommentImage = orderCommentImageService.get(id);
		model.addAttribute("orderCommentImage", orderCommentImage);
	    return "system/orderCommentImage/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:orderCommentImage:add")
	public R save( OrderCommentImageDO orderCommentImage){
		if(orderCommentImageService.save(orderCommentImage)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:orderCommentImage:edit")
	public R update( OrderCommentImageDO orderCommentImage){
		orderCommentImageService.update(orderCommentImage);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:orderCommentImage:remove")
	public R remove( Long id){
		if(orderCommentImageService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:orderCommentImage:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderCommentImageService.batchRemove(ids);
		return R.ok();
	}
	
}
