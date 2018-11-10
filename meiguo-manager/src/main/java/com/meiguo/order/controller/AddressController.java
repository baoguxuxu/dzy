package com.meiguo.order.controller;

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
import com.meiguo.order.domain.AddressDO;
import com.meiguo.order.service.AddressService;


/**
 * 收货地址
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-11-08 13:24:18
 */
 
@Controller
@RequestMapping("/information/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping()
	@RequiresPermissions("information:address:address")
	String Address(){
	    return "information/address/address";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:address:address")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AddressDO> addressList = addressService.list(query);
		int total = addressService.count(query);
		PageUtils pageUtils = new PageUtils(addressList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:address:add")
	String add(){
	    return "information/address/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:address:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AddressDO address = addressService.get(id);
		model.addAttribute("address", address);
	    return "information/address/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:address:add")
	public R save( AddressDO address){
		if(addressService.save(address)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:address:edit")
	public R update( AddressDO address){
		addressService.update(address);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:address:remove")
	public R remove( Integer id){
		if(addressService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:address:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		addressService.batchRemove(ids);
		return R.ok();
	}
	
}
