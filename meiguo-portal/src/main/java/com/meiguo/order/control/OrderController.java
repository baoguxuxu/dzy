package com.meiguo.order.control;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.service.GoodsService;
import com.meiguo.order.domain.AddressDO;
import com.meiguo.order.domain.CartDO;
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.domain.OrderProductDO;
import com.meiguo.order.service.AddressService;
import com.meiguo.order.service.CartService;
import com.meiguo.order.service.OrderProductService;
import com.meiguo.order.service.OrderService;



/**
 * 订单表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-02 10:51:27
 */
 
@Controller
@RequestMapping("/system/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderProductService orderProductService;
	
	@GetMapping()
	@RequiresPermissions("system:order:order")
	String Order(){
	    return "system/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:order:add")
	String add(){
	    return "system/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:order:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "system/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
		 //保存订单详情
			for(OrderProductDO o:order.getList()){
				o.setOrderId(order.getId());
				orderProductService.save(o);
			}
		}
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:order:remove")
	public R remove( Long id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:order:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 立即购买生成订单信息
	 */
	@GetMapping("/bull/{id}/{buyNumber}")
	public String createBull(@PathVariable("id") Long id,@PathVariable("buyNumber") Integer buyNumber  ,Model model){
		List<OrderProductDO> oplist = new ArrayList<OrderProductDO>();
		OrderProductDO orderProductDO = new OrderProductDO();
		//所购商品信息
		GoodsDO goodsDO = goodsService.get(id);
		orderProductDO.setGoodsId(id);
		orderProductDO.setName(goodsDO.getName());
		orderProductDO.setBuyNumber(buyNumber);
		orderProductDO.setPrice(goodsDO.getPayPrice());
		orderProductDO.setProductSpecName(goodsDO.getSpec());
		List<ImgDO> imgDOList = goodsService.getGoodsImgByGoodsDO(id,0);
		if(imgDOList.size()>0){
			orderProductDO.setPicImg(imgDOList.get(0).getUrl());
		}
		oplist.add(orderProductDO);
		model.addAttribute("oplist",oplist);
		//收获联系人信息
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",ShiroUtils.getUserId());
		map.put("defaultFlag","1");
		List<AddressDO> list = addressService.list(map);
		OrderDO orderDO = new OrderDO();
		AddressDO addressDO = new AddressDO();
		if(list.size()>0)
			addressDO = list.get(0);
		orderDO.setAddress(addressDO.getAddress());
		orderDO.setMobile(addressDO.getMobile());
		orderDO.setConsignee(addressDO.getConsignee());
		BigDecimal num = new BigDecimal(buyNumber);
		orderDO.setOrderAmount(num.multiply(orderProductDO.getPrice()));
		model.addAttribute("orderDO", orderDO);
		return "order/order";
	}
	
	/**
	 * 购物车生成订单信息
	 */
	@GetMapping("/bullcart/{ids}")
	public String createBullfromCart(@PathVariable("ids") Long[] ids,Model model){
		List<OrderProductDO> oplist = new ArrayList<OrderProductDO>();
		for(int i=0;i<ids.length;i++){
			CartDO cartDO =  cartService.get(ids[i]);
			GoodsDO goodsDO= goodsService.get(cartDO.getGoodsId());
			if(cartDO!=null && goodsDO!=null){
				OrderProductDO orderProductDO = new OrderProductDO();
				orderProductDO.setGoodsId(cartDO.getGoodsId());
				orderProductDO.setBuyNumber(cartDO.getBuyNumber());
				orderProductDO.setName(goodsDO.getName());
				orderProductDO.setPrice(goodsDO.getPayPrice());
				orderProductDO.setProductSpecName(goodsDO.getSpec());
				List<ImgDO> imgDOList = goodsService.getGoodsImgByGoodsDO(cartDO.getGoodsId(),0);
				if(imgDOList.size()>0){
					orderProductDO.setPicImg(imgDOList.get(0).getUrl());
				}
				oplist.add(orderProductDO);
			}
			
		}
		model.addAttribute("oplist",oplist);
		//收获联系人信息
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",ShiroUtils.getUserId());
		map.put("defaultFlag","1");
		List<AddressDO> list = addressService.list(map);
		OrderDO orderDO = new OrderDO();
		AddressDO addressDO = new AddressDO();
		if(list.size()>0)
			addressDO = list.get(0);
		orderDO.setAddress(addressDO.getAddress());
		orderDO.setMobile(addressDO.getMobile());
		orderDO.setConsignee(addressDO.getConsignee());
		//计算价格
		BigDecimal orderMount = new BigDecimal(0);
		for(OrderProductDO opdo :oplist){
			BigDecimal num = new BigDecimal(opdo.getBuyNumber());
			BigDecimal omount = num.multiply(opdo.getPrice());
			orderMount=orderMount.add(omount);
		}
		
		orderDO.setOrderAmount(orderMount);
		model.addAttribute("orderDO", orderDO);
		return "order/order";
	}
	/**
	 * 	购买记录
	 */
	@GetMapping("/jilu")
	public String lookJilu(Integer orderStatus,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderStatus",orderStatus);
		List<OrderDO> list = orderService.list(map);
		return "order/goumaijilu";
	}
	
	/**
	 * 查看我的购买记录
	 */
	
	@ResponseBody
	@RequestMapping("/checkjilu")
	public List<OrderDO> lookJilu(Integer orderStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderStatus",orderStatus);
		List<OrderDO> list = orderService.list(map);
		//获取订单中的第一个商品图片展示
		for(OrderDO o:list){
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("orderId",o.getId());
			List<OrderProductDO> oList = orderProductService.list(map1);
			if(oList.size()>0)
			o.setUrl(oList.get(0).getPicImg());
		}
		return list;
 	}
	
	/**
	 * 查看订单详情
	 */
	
	@RequestMapping("/getBullDetail")
	public String getBullDetail(Long id,Model model){
		OrderDO orderDO = orderService.get(id);
		model.addAttribute("orderDO", orderDO);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId",orderDO.getId());
		List<OrderProductDO> oList = orderProductService.list(map);
		model.addAttribute("oplist", oList);
		return "order/bulldetail";
	} 
}
