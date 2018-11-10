package com.meiguo.information.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.meiguo.common.utils.FileUtil;
import com.meiguo.common.utils.PageUtils;
import com.meiguo.common.utils.Query;
import com.meiguo.common.utils.R;
import com.meiguo.information.domain.UserChengjiuMidDO;
//import com.meiguo.information.dao.UserShopDao;
import com.meiguo.information.domain.UserDO;
import com.meiguo.information.service.UserChengjiuMidService;
//import com.meiguo.information.domain.UserShopDO;
import com.meiguo.information.service.UserService;
//import com.meiguo.information.service.UserShopService;
import com.meiguo.common.utils.ExcelExportUtil4DIY;


/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-10-10 10:03:45
 */
 
@Controller("UserController")
@RequestMapping("/information/users")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserChengjiuMidService userChengjiuMidService;
	
	@GetMapping()
	@RequiresPermissions("information:user:user")
	String User(){
	    return "information/users/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("information:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("information:user:add")
	String add(){
	    return "information/users/add";
	}
	

	@GetMapping("/edit/{id}")
	@RequiresPermissions("information:user:edit")
	String edit(@PathVariable("id") Integer id, Model model){				
		UserDO user = userService.get(id);		
		model.addAttribute("user", user);
	    return "information/users/edit";
	}
	
	@GetMapping("/show/{id}")
	@RequiresPermissions("information:user:chakan")
	String show(@PathVariable("id") Integer id, Model model){
		UserChengjiuMidDO listId = userChengjiuMidService.getListId(id);
		UserDO user = userService.get(id);		
		model.addAttribute("user", user);
		model.addAttribute("listId", listId);
	    return "information/users/show";
	   	    
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("information:user:add")
	public R save( UserDO user){
		
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("information:user:edit")
	public R update( UserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("information:user:remove")
	public R remove( Integer id){
		if(userService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("information:user:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(Integer id,Integer enable) {
		UserDO sysFile = new UserDO();
		sysFile.setId(id);
		sysFile.setDeleteFlag(enable);
		userService.update(sysFile);

		return R.ok();
	}	
	
	/**
	 * 导出
	 * @param request
	 * @param response
	 * @author 小帅丶
	 * @return
	 * @throws Exception
	*/
	@RequestMapping(value="/exportExcel")
	public void exportExcel(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "用户列表"+format.format(new Date().getTime())+".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
	try {
		Query query = new Query(params);
		String type = request.getParameter("type");
		//导出当前页面数据
		if(type.equals("1")){
			List<Map<String, Object>> XxxDOs = userService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		//导出全部数据
		if(type.equals("2")){
			List<Map<String, Object>> XxxDOs = userService.exeList(null);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		//导出符合条件的全部数据
		if(type.equals("3")){
			query.remove("offset");
			query.remove("limit");
			List<Map<String, Object>> XxxDOs = userService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
		//导选中部分
		if(type.equals("4")){
			query.remove("offset");
			query.remove("limit");
			System.out.println("ids:"+query.get("ids"));
			List<Map<String, Object>> XxxDOs = userService.exeList(query);
			ExcelExportUtil4DIY.exportToFile(XxxDOs,out);
		}
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("exportExcel出错"+e.getMessage());
		}finally{
			out.close();
		}
	}
}
