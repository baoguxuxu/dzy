package com.meiguo.order.control;

import java.util.HashMap;
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
import com.meiguo.common.utils.ShiroUtils;
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
	String Address(){
//	    return "information/address/address";
		return "address_01/address";
	}
	@ResponseBody
	@GetMapping("/list")
	public List<AddressDO> list(Model model){
		//查询列表数据
       Map<String,Object> map = new HashMap<String,Object>();
       map.put("userId",ShiroUtils.getUserId());
	   List<AddressDO> addressList = addressService.list(map);
	/*   model.addAttribute("addressList", addressList);
		return "address_01/address";*/
	   return addressList;
	}
	
	@GetMapping("/add")
	String add(){
//	    return "address/add";
		return "address_01/add";
	}

	@GetMapping("/edit")
	String edit(Integer id,Model model){
//		AddressDO address = addressService.get(id);
//		model.addAttribute("address", address);
//	    return "address/edit";
		model.addAttribute("id",id);
		return "address_01/edit";
	}
	
	@ResponseBody
	@GetMapping("/info/{id}")
	public AddressDO info(@PathVariable("id") Integer id){
		AddressDO address = addressService.get(id);
		return address;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( @RequestBody AddressDO address){
		
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
	public R update(@RequestBody AddressDO address){
		addressService.update(address);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@GetMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(addressService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}
