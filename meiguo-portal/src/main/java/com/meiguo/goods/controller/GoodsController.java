package com.meiguo.goods.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.meiguo.common.config.BootdoConfig;
import com.meiguo.common.utils.FileUtil;
import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.goods.domain.CategoryDO;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.domain.ProductDO;
import com.meiguo.goods.domain.SpecDO;
import com.meiguo.goods.service.GoodsService;
import com.meiguo.order.domain.AddressDO;
import com.meiguo.order.domain.OrderDO;
import com.meiguo.order.service.AddressService;


/**
 * 货品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-17 15:41:03
 */
 
@Controller
@RequestMapping("/information/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AddressService addressService;
	/**
	 * 查询所有的商品分类
	 */
	
	@GetMapping()
	String Goods(Model model){
		CategoryDO categoryDO = new CategoryDO();
		categoryDO.setName("全部");
		List<CategoryDO> categoryList =  new ArrayList<CategoryDO>();
		categoryList.add(0, categoryDO);
		Map<String,Object> map = new HashMap<String,Object>();
		categoryList.addAll(1,goodsService.listCategory(map));
		model.addAttribute("categoryList", categoryList);
	    return "goods/shangcheng";
	}
	
	/**
	 * 查询同一个商品分类下的所有产品
	 */
	
	@ResponseBody
	@RequestMapping("/product")
	 public List<ProductDO> getProductDOByCategoryName(String name){
		 List<ProductDO> productList = goodsService.getProductByCategoryName(name);
		 //获取第一个商品的列表图作为所属产品的列表图
		 for(ProductDO productDO :productList){
			 List<GoodsDO> list = goodsService.getGoodsByProductId(productDO.getId());
			 if(list.size()>0){
				 GoodsDO goodsDO = list.get(0);
				 productDO.setUrl(goodsDO.getUrl());
			 }
		 }
		 return productList;
	 }
	
	
	/**
	 * 查询产品中的第一个商品
	 */
	@GetMapping("/getGoodsDetail/{id}")
	public String getGoodsDetail(Model model,@PathVariable("id") Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productId", id);
		GoodsDO goodsDO = new GoodsDO();
		List<GoodsDO> goodsDOList = goodsService.list(map);
		if(goodsDOList.size()>0)
			goodsDO=goodsDOList.get(0);
		model.addAttribute("goods", goodsDO);
		model.addAttribute("goodsimgList", goodsService.listGoodsimgAndDetailimg(goodsDO.getId(),1));
		model.addAttribute("detailimgList", goodsService.listGoodsimgAndDetailimg(goodsDO.getId(),2));
		return "goods/detail";
	}
	
	/**
	 * 立即购买
	 */
	
	@GetMapping("/buy/{id}")
	public String ByGoods(@PathVariable("id") Long id,Model model){
		//购买页，默认展示第一个商品
		model.addAttribute("goodsimgList", goodsService.listGoodsimgAndDetailimg(id,1));
		model.addAttribute("detailimgList", goodsService.listGoodsimgAndDetailimg(id,2));
		GoodsDO goodsDO = goodsService.get(id);
		model.addAttribute("goods", goodsDO);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productId", goodsDO.getProductId());
		List<GoodsDO> goodsDOList = goodsService.list(map);
		model.addAttribute("goodsDOList", goodsDOList);
		return "goods/buy";
	}
	
	/**
	 * 选择购买商品的规格参数等
	 */
	
	@ResponseBody
	@PostMapping("/selectGoods")
	public GoodsDO selectGoods(Long goodsId,Model model){
		GoodsDO goodsDO = goodsService.get(goodsId);
		return goodsDO;
	}
}
