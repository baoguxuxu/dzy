package com.meiguo.product.controller;

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
import com.meiguo.product.domain.SpecDO;
import com.meiguo.product.service.SpecService;


/**
 * 购物车表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 15:18:22
 */
 
@Controller
@RequestMapping("/information/spec")
public class SpecController {
	@Autowired
	private SpecService specService;
	
	@GetMapping()
	@RequiresPermissions("information:spec:spec")
	String Spec(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parent_id", 0);
		List<SpecDO> specList  = specService.list(map);
		model.addAttribute("specList", specList);
	    return "information/spec/spec";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:spec:spec")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(params.get("id")!=null){
        	query.put("parent_id",Integer.parseInt(params.get("id").toString()));
        }
        else{
        	query.put("parent_id",0);
        }
		List<SpecDO> specList = specService.list(query);
		int total = specService.count(query);
		PageUtils pageUtils = new PageUtils(specList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:spec:add")
	String add(){
	    return "information/spec/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:spec:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SpecDO spec = specService.get(id);
		model.addAttribute("spec", spec);
	    return "information/spec/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:spec:add")
	public R save( SpecDO spec){
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("productSpecId", spec.getProductSpecId());
		map.put("parent_id", spec.getParentId()==null?0:spec.getParentId());
		List<SpecDO> list = specService.list(map);
		if(list.size()>0)
			return R.error("规格编号已存在，请重新输入");
		if(specService.save(spec)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:spec:edit")
	public R update( SpecDO spec){
		if(spec.getParentId()==null){
			specService.update(spec);//修改父规格
			SpecDO specDO = new SpecDO();
			specDO.setParentId(spec.getId());
			specDO.setParentName(spec.getName());
			specService.updateByParentId(specDO);//修改自规格中保存的父规格名称
		}
			specService.update(spec);
			return R.ok();
	}
		
	
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:spec:remove")
	public R remove( Long id){
		SpecDO specDO = new SpecDO();
		specDO.setId(id);
		specDO.setDeleteEnable(1);
		specService.update(specDO);
		return R.ok();
		
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:spec:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		specService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 规格明细
	 */
	@GetMapping("/specMX")
	@RequiresPermissions("information:spec:spec")
	String Spec(Integer id,Model model){
		model.addAttribute("id",id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parent_id", id);
		List<SpecDO> specList  = specService.list(map);
		model.addAttribute("specList", specList);
	    return "information/spec/specMX";
	}
	
	/**
	 * 增加子规格
	 */
	@GetMapping("/addMX/{parent_id}")
	@RequiresPermissions("information:spec:add")
	String add(@PathVariable("parent_id") Long id,Model model){
		SpecDO spec = specService.get(id);
		model.addAttribute("spec", spec);
	 
	    return "information/spec/addMX";
	}
}
