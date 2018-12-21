package com.meiguo.product.controller;

import java.io.IOException;
import java.util.Date;
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

import com.meiguo.common.config.BootdoConfig;
import com.meiguo.common.utils.FileUtil;
import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.product.domain.CategoryDO;
import com.meiguo.product.domain.ProductDO;
import com.meiguo.product.domain.SpecDO;
import com.meiguo.product.service.CategoryService;
import com.meiguo.product.service.ProductService;
import com.meiguo.product.service.SpecService;


/**
 * 产品表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-11 15:09:27
 */
 
@Controller
@RequestMapping("/information/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private SpecService specService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("information:product:product")
	String Product(Model model){
		List<ProductDO> productList = productService.list(new HashMap<String,Object>());
		model.addAttribute("productList",productList);
	    return "information/product/product";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:product:product")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        query.put("status",params.get("status"));
        query.put("name",params.get("name"));
		List<ProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:product:add")
	String add(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		List<CategoryDO> list1 = categoryService.list(map);
		model.addAttribute("list1",list1);
		map.put("parent_id", 0);
		List<SpecDO> list = specService.list(map);
		model.addAttribute("list",list);
	    return "information/product/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:product:edit")
	String edit(@PathVariable("id") Long id,Model model){
		model.addAttribute("product", productService.get(id));
		model.addAttribute("list1",categoryService.list(new HashMap<String,Object>()));
//		model.addAttribute("list",productService.listSpec(id));
	    return "information/product/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:product:add")
	public R save( ProductDO product){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productNumber",product.getProductNumber());
		List<ProductDO> listP = productService.list(map);
		if(listP.size()>0)
			return R.error("产品编号已存在，请重新输入");
		try {
			String fileName = product.getImgFile().getOriginalFilename();
			fileName = FileUtil.renameToUUID(fileName);
			FileUtil.uploadFile(product.getImgFile().getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
			product.setUrl("/files/goods/"+fileName);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		if(productService.save(product)>0){
			return R.ok(); 
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:product:edit")
	public R update( ProductDO product){
		if(product.getImgFile() != null && product.getImgFile().getSize() > 0){
			String fileName = product.getImgFile().getOriginalFilename();
			fileName = FileUtil.renameToUUID(fileName);
			try {
				FileUtil.uploadFile(product.getImgFile().getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
				product.setUrl("/files/goods/"+fileName);
			} catch (Exception e) {
				return R.error();
			}
			
		}
		productService.update(product);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:product:remove")
	public R remove( Long id){
		ProductDO productDO = new ProductDO();
		productDO.setId(id);
		productDO.setDeleteEnable(1);
		if(productService.update(productDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	@PostMapping( "/updateEnable")
	@ResponseBody
	@RequiresPermissions("information:product:remove")
	public R updateEnable( Long id,Integer enable){
		ProductDO productDO = new ProductDO();
		productDO.setId(id);
		productDO.setStatus(enable);
		if(productService.update(productDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:product:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		productService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 检查父规格下是否有详细的子规格
	 */
	@ResponseBody
	@PostMapping("/getSpec")
	@RequiresPermissions("information:product:add")
	public List<SpecDO> getSpec(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parent_id",id);
		List<SpecDO> list = specService.list(map);
		System.out.println(list);
		return list;
	}
}
