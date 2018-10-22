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
import com.meiguo.goods.domain.GoodsDO;
import com.meiguo.goods.domain.ImgDO;
import com.meiguo.goods.service.GoodsService;
import com.meiguo.product.domain.ProductDO;
import com.meiguo.product.service.ProductService;


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
	private ProductService ProductService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("information:goods:goods")
	String Goods(Model model){
		model.addAttribute("goodsList",goodsService.list(new HashMap<String,Object>()));
	    return "information/goods/goods";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:goods:goods")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GoodsDO> goodsList = goodsService.list(query);
		int total = goodsService.count(query);
		PageUtils pageUtils = new PageUtils(goodsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:goods:add")
	String add(Model model){
		List<ProductDO> productList = ProductService.list(new HashMap<String,Object>());
	    model.addAttribute("productList", productList);
		return "information/goods/add";
	}

	@GetMapping("/edit/{id}/{type}")
	@RequiresPermissions("information:goods:edit")
	String edit(@PathVariable("id") Integer id,@PathVariable("type") Integer type,   Model model){
		GoodsDO goods = goodsService.get(id);
		model.addAttribute("goods", goods);
		ImgDO imgDO = new ImgDO();
		List<ImgDO> list =goodsService.listimg(id,0); 
		if(list.size()>0)
			imgDO=list.get(0);
		model.addAttribute("listimg",imgDO);
		model.addAttribute("goodsimg",goodsService.listimg(id,1));
		model.addAttribute("detailimg",goodsService.listimg(id,2));
		if(type==1)
			return "information/goods/edit";
		else
			return "information/goods/xiangqing";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:goods:add")
	public R save( GoodsDO goods,HttpServletRequest request){
		goods.setStatus(0);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tgoodsNumber", goods.getTgoodsNumber());
		map.put("goodsNumber", goods.getGoodsNumber());
		List<GoodsDO> list  = goodsService.list(map);
		if(list.size()>0)
			return R.error("编号或条形码号已存，请重新输入");
		if(goodsService.save(goods)<=0){
			return R.error();
		}
		//上传图片，保存图片相关信息
		//保存列表图
		
		String fileName = goods.getListimg().getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		ImgDO imgDO = new ImgDO();
		try {
			FileUtil.uploadFile(goods.getListimg().getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
			imgDO.setType(0);
			imgDO.setAddTime(new Date());
			imgDO.setGoodsId(goods.getId());
			imgDO.setCreateBy(ShiroUtils.getUser().getUsername());
			imgDO.setUrl("/files/goods/"+fileName);
			goodsService.saveImg(imgDO);
			//保存货品图

			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("goodsimg");
			for(int i=0;i<files.size();i++){
				fileName = files.get(i).getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				imgDO = new ImgDO();
				FileUtil.uploadFile(files.get(i).getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
				imgDO.setType(1);
				imgDO.setAddTime(new Date());
				imgDO.setGoodsId(goods.getId());
				imgDO.setCreateBy(ShiroUtils.getUser().getUsername());
				imgDO.setUrl("/files/goods/"+fileName);
				goodsService.saveImg(imgDO);
			}
			//保存货品详图
			files = ((MultipartHttpServletRequest) request).getFiles("detailimg");
			for(int i=0;i<files.size();i++){
				fileName = files.get(i).getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				imgDO = new ImgDO();
				FileUtil.uploadFile(files.get(i).getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
				imgDO.setType(2);
				imgDO.setAddTime(new Date());
				imgDO.setGoodsId(goods.getId());
				imgDO.setCreateBy(ShiroUtils.getUser().getUsername());
				imgDO.setUrl("/files/goods/"+fileName);
				goodsService.saveImg(imgDO);
			}
		} catch (Exception e) {
			return R.error();
		}
		
		return R.ok();	
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:goods:edit")
	public R update( GoodsDO goods,HttpServletRequest request){
		//删除无效的图片
		String imgids = goods.getImgids();
		if(imgids!=null && !imgids.equals("")){
			String[] ids = imgids.split(",");
			for(String i :ids){
				goodsService.removeImgByID(Integer.parseInt(i));
			}
		}
		goodsService.update(goods);
		//修改货品有关图片
		
		try {
			String fileName="";
			ImgDO imgDO = new ImgDO();
			if(goods.getListimg() != null && goods.getListimg().getSize() > 0){
				fileName = goods.getListimg().getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				if(!fileName.substring(fileName.indexOf(".")+1).equals("")){//保存时，确保上传的文件具有有效的后缀名
					FileUtil.uploadFile(goods.getListimg().getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
					imgDO.setType(0);
					imgDO.setAddTime(new Date());
					imgDO.setGoodsId(goods.getId());
					imgDO.setCreateBy(ShiroUtils.getUser().getUsername());
					imgDO.setUrl("/files/goods/"+fileName);
					goodsService.saveImg(imgDO);
				}
			}
			//保存货品图

			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("goodsimg");
			 if(files.size()>0){
				 for(int i=0;i<files.size();i++){
					fileName = files.get(i).getOriginalFilename();
					fileName = FileUtil.renameToUUID(fileName);
					
					imgDO = new ImgDO();
					if(!fileName.substring(fileName.indexOf(".")+1).equals("")){
						FileUtil.uploadFile(files.get(i).getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
						imgDO.setType(1);
						imgDO.setAddTime(new Date());
						imgDO.setGoodsId(goods.getId());
						imgDO.setCreateBy(ShiroUtils.getUser().getUsername());
						imgDO.setUrl("/files/goods/"+fileName);
						goodsService.saveImg(imgDO);
					}
				}
			 }
			//保存货品详图
			files = ((MultipartHttpServletRequest) request).getFiles("detailimg");
			if(files.size()>0){
				for(int i=0;i<files.size();i++){
					fileName = files.get(i).getOriginalFilename();
					fileName = FileUtil.renameToUUID(fileName);
					imgDO = new ImgDO();
					if(!fileName.substring(fileName.indexOf(".")+1).equals("")){
						FileUtil.uploadFile(files.get(i).getBytes(), bootdoConfig.getUploadPath()+"goods/", fileName);
						imgDO.setType(2);
						imgDO.setAddTime(new Date());
						imgDO.setGoodsId(goods.getId());
						imgDO.setCreateBy(ShiroUtils.getUser().getUsername());
						imgDO.setUrl("/files/goods/"+fileName);
						goodsService.saveImg(imgDO);
					}
				}
			}
		} catch (Exception e) {
			return R.error();
		}
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:goods:remove")
	public R remove( Integer id){
		GoodsDO goodsDO = new GoodsDO();
		goodsDO.setId(id);
		goodsDO.setDeleteEnable(1);
		goodsService.update(goodsDO);
		return R.ok();
	}
	
	
	@PostMapping( "/updateEnable")
	@ResponseBody
	@RequiresPermissions("information:goods:remove")
	public R updateEnable( Integer id,Integer enable){
		GoodsDO goodsDO = new GoodsDO();
		goodsDO.setId(id);
		goodsDO.setStatus(enable);
		goodsService.update(goodsDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:goods:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		goodsService.batchRemove(ids);
		return R.ok();
	}
	
}
