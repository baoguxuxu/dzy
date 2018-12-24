package com.meiguo.goods.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.meiguo.common.utils.R;
import com.meiguo.goods.domain.CartDO;
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;

import com.meiguo.goods.domain.ProductDO;
import com.meiguo.goods.service.GoodsService;




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
	/**
	 * 查询所有的商品分类
	 */
	
	@GetMapping()
	String Goods(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		model.addAttribute("categoryList", goodsService.listCategory(map));
	    return "goods/shangcheng";
	}
	
	/**
	 * 查询商品分类下的商品
	 */
	
	@ResponseBody
	@RequestMapping("/product")
	 public List<ProductDO> getProductDOByCategoryName(String name){
		 List<ProductDO> productList = goodsService.getProductByCategoryName(name);
		 return productList;
	 }
	
	/**
	 * 立即购买，生成订单
	 */
	@ResponseBody
	@PostMapping("/buy")
	public R ByGoods(@RequestBody List<CartDO> cartDOList){
		String orderNo = goodsService.byGoods(cartDOList);
		return R.ok(orderNo);
	}
	/**
	 * 商品详情
	 */
	@GetMapping("/getGoodsDetail")
	public String getGoodsDetail(Long goodsId,Long productId,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productId", productId);
		List<GoodsDO> goodsDOList = goodsService.list(map);
		if(goodsId!=null){
			for(GoodsDO goodsDO :goodsDOList){
				if(goodsId==goodsDO.getId()){
					goodsDOList.remove(goodsDO);
					goodsDOList.add(0,goodsDO);
					break;
				}
			}
		}
		model.addAttribute("goodsDOList",goodsDOList);
		return "goods/buy";
	}
	
	
	/**
	 * 选择购买商品的规格参数等
	 */
	
	@ResponseBody
	@PostMapping("/selectGoods")
	public Map<String,Object> selectGoods(Long goodsId,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		 GoodsDO goodsDO = goodsService.get(goodsId);
		 List<ImgDO> goodsimgList  = goodsService.listGoodsimgAndDetailimg(goodsId,1);
		 List<ImgDO> detailimgList=goodsService.listGoodsimgAndDetailimg(goodsId,2);
		 map.put("goodsDO", goodsDO);
		 map.put("goodsimgList", goodsimgList);
		 map.put("detailimgList", detailimgList);
		 return map;
	}
	

}
