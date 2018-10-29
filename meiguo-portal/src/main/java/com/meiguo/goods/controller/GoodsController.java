package com.meiguo.goods.controller;

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
	 * 查询所有的分类
	 */
	
	@GetMapping()
	String Goods(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		List<CategoryDO> goodsList = goodsService.listCategory(map);
		model.addAttribute("goodsList", goodsList);
	    return "goods/shangcheng";
	}
	
	/**
	 * 查询同一个商品分类下的所有产品
	 */
	
	@ResponseBody
	@RequestMapping("/product")
	 public List<ProductDO> getProductDOByCategoryName(String name){
		 List<ProductDO> productList = goodsService.getProductByCategoryName(name);
		 return productList;
	 }
	
	/**
	 * 查询货品的列表图和一些相关信息
	 */
	
	@ResponseBody
	@RequestMapping("/goodsImg")
	public List<GoodsDO> getGoodsByProductId(Integer product_id){
		List<GoodsDO> list  = goodsService.getGoodsByProductId(product_id);
		return list;
	}
	
	/**
	 * 按货品的id查询货品详情
	 */
	@RequestMapping("/getGoodsDetail")
	public List<GoodsDO> getGoodsDetail(Integer id){
		return null;
		
	}
}
