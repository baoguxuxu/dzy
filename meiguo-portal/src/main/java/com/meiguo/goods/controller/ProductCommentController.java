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
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.domain.OrderCommentImageDO;
import com.meiguo.goods.domain.ProductCommentDO;
import com.meiguo.goods.service.OrderCommentImageService;
import com.meiguo.goods.service.ProductCommentService;

import springfox.documentation.service.ResponseMessage;


/**
 * 评价表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:37:33
 */
 
@Controller
@RequestMapping("/system/productComment")
public class ProductCommentController {
	@Autowired
	private ProductCommentService productCommentService;
	@Autowired
	private OrderCommentImageService orderCommentImageService;
	@GetMapping()
	@RequiresPermissions("system:productComment:productComment")
	String ProductComment(){
	    return "system/productComment/productComment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:productComment:productComment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductCommentDO> productCommentList = productCommentService.list(query);
		int total = productCommentService.count(query);
		PageUtils pageUtils = new PageUtils(productCommentList, total);
		return pageUtils;
	}
	
	/**
	 * 商品评价
	 */
	@RequestMapping("/add")
	String add(Long goodsId,Long orderProductId,Long orderId,Model model){
		model.addAttribute("goodsId", goodsId);//商品id
		model.addAttribute("orderId", orderId);//订单id
		model.addAttribute("orderProductId", orderProductId);//子订单id
	    return "goods/pingjia";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:productComment:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ProductCommentDO productComment = productCommentService.get(id);
		model.addAttribute("productComment", productComment);
	    return "system/productComment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( @RequestBody ProductCommentDO productComment){
		if(productCommentService.save(productComment)>0){
			productComment.setUserId(ShiroUtils.getUserId());
			productComment.setNickname(ShiroUtils.getUser().getNickname());
			productComment.setHeadimgurl(ShiroUtils.getUser().getHeardUrl());
			List<OrderCommentImageDO> list = productComment.getOrderCommentImageDOList();
			String namespace = "user/"+ShiroUtils.getUserId();
			for(int sort=0;sort<list.size();sort++){
				OrderCommentImageDO orderCommentImageDO = list.get(sort);
				String imgStr = orderCommentImageDO.getPicImg();
				String  r = orderCommentImageService.uploadImg(namespace,imgStr, "png");
				OrderCommentImageDO oCommentImageDO2 =  new OrderCommentImageDO();
				oCommentImageDO2.setPicImg(imgStr);
				oCommentImageDO2.setSort(1);
				oCommentImageDO2.setCommentId(productComment.getId());
				orderCommentImageService.save(oCommentImageDO2);
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:productComment:edit")
	public R update( ProductCommentDO productComment){
		productCommentService.update(productComment);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:productComment:remove")
	public R remove( Long id){
		if(productCommentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:productComment:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		productCommentService.batchRemove(ids);
		return R.ok();
	}
	
}
