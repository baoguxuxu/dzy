package com.meiguo.goods.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.domain.CartDO;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.service.CartService;
import com.meiguo.goods.service.GoodsService;



/**
 * 购物车表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-09 09:44:50
 */
 
@Controller
@RequestMapping("/system/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private GoodsService goodService;
	@GetMapping()
	String Cart(){
	    return "order/cart";
	}
	
	/**
	 * 查询购物车
	 */
	@ResponseBody
	@GetMapping("/list")
	public List<CartDO> list(Model model){
       Map<String,Object> map = new HashMap<String,Object>();
       map.put("userId",ShiroUtils.getUserId());
	   List<CartDO> cartList = cartService.list(map);
	   for(CartDO cartDO :cartList){
		   Long goodsId  = cartDO.getGoodsId();
		   GoodsDO goodsDO =  goodService.get(goodsId);
		   List<ImgDO> imgDO =   goodService.listGoodsimgAndDetailimg(goodsId, 1);
		   if(imgDO.size()>0)
			   goodsDO.setUrl(imgDO.get(0).getUrl());
		 cartDO.setGoodsDO(goodsDO);
	   }  
		return cartList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:cart:add")
	String add(){
	    return "system/cart/add";
	}

	@GetMapping("/edit/{cartId}")
	@RequiresPermissions("system:cart:edit")
	String edit(@PathVariable("cartId") Long cartId,Model model){
		CartDO cart = cartService.get(cartId);
		model.addAttribute("cart", cart);
	    return "system/cart/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( Long goodsId){
		int i=  cartService.save(goodsId);
		if(i>0){
			return R.ok("加入购物车成功");
		}
		if(i==-1)
			return R.ok("购物车中已存在");
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:cart:edit")
	public R update( CartDO cart){
		cartService.update(cart);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:cart:remove")
	public R remove( Long cartId){
		if(cartService.remove(cartId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cartIds){
		cartService.batchRemove(cartIds);
		return R.ok();
	}
	
}
