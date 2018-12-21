package com.meiguo.owneruser.controller;

import com.meiguo.common.annotation.Log;
import com.meiguo.common.config.BootdoConfig;
import com.meiguo.common.config.Constant;
import com.meiguo.common.controller.BaseController;
import com.meiguo.common.utils.*;
import com.meiguo.information.domain.FilesDO;
import com.meiguo.owneruser.comment.WeinXinUtil;
import com.meiguo.owneruser.comment.WinXinEntity;
import com.meiguo.owneruser.domain.OwnerUserDO;
import com.meiguo.owneruser.domain.UserCouponDO;
import com.meiguo.owneruser.domain.UserPlotDO;
import com.meiguo.owneruser.domain.UserRewardMidDO;
import com.meiguo.owneruser.service.OwnerUserService;
import com.meiguo.owneruser.service.UserPlotService;
import com.meiguo.owneruser.service.UserRewardMidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/owner")
@Controller
public class OwnerUserController extends BaseController {
	private String prefix="user"  ;
	@Autowired
	OwnerUserService userService;
	@Autowired
	UserPlotService plotService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	UserRewardMidService userRewardMidService;
	/**
	 * 个人中心
	 * @return
	 */
	@GetMapping("/user")
	String user(Model model) {
		OwnerUserDO udo= userService.get(getUserId());
		model.addAttribute("user", udo);
		return prefix + "/gerenzhongxin";
	}
	/**
	 * 获取用户信息
	 * @return
	 */
	@GetMapping("/userInfo")
	String userInfo(Model model) {
		OwnerUserDO udo=userService.get(getUserId());
		model.addAttribute("user", udo);
		return prefix + "/gerenxinxi";
	}
	/**
	 * 编辑用户信息
	 * @return
	 */
	@Log("更新用户")
	@PostMapping("/editInfo")
	@ResponseBody
	R editInfo(OwnerUserDO user) {
		OwnerUserDO userd = userService.getbyname(user.getUsername());
		if(user.getHeardUrl()!=null){
			userd.setHeardUrl(user.getHeardUrl());
		}
		if(user.getNickname()!=null){
			userd.setNickname(user.getNickname());
		}
		if(user.getName()!=null){
			userd.setName(user.getName());
		}
		if(user.getIdentityCard()!=null){
			userd.setIdentityCard(user.getIdentityCard());
		}
		if(user.getFileImg() != null && user.getFileImg().getSize() > 0){
				MultipartFile sysFile = user.getFileImg();
				String fileName = sysFile.getOriginalFilename();
				fileName = FileUtil.renameToUUID(fileName);
				try {
					FileUtil.uploadFile(sysFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				userd.setHeardUrl("/files/" + fileName);
		}
		if (userService.update(userd) > 0) {
			
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<OwnerUserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}
	@ResponseBody
	@GetMapping(value="/info")
	List<OwnerUserDO> info(Model model,Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.getUserId());
		List<OwnerUserDO> list = userService.list(map);
		model.addAttribute("user", list);
		return list;
	}
	
	@Log("查看用户")
	@GetMapping("/show/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		OwnerUserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		return prefix+"/show";
	}

	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(OwnerUserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/updateEnable")
	public R updateEnable(Long id,Integer enable) {
		OwnerUserDO oudo = userService.get(id);
		oudo.setId(id);
		oudo.setDeleteFlag(enable);
		userService.update(oudo);

		return R.ok();
	}

	@ResponseBody
	@RequestMapping(value="/payNum")
	R payNum(Long id){
		try {
			OwnerUserDO uDO = userService.get(id);
			UserRewardMidDO urm = new UserRewardMidDO();
			if(uDO.getPayNum() == 350){
				urm.setUserId(uDO.getId());
				urm.setRewardId(6);
				urm.setWinTime(new Date());
				userRewardMidService.save(urm);
			}else if(uDO.getPayNum() == 1000){
				urm.setUserId(uDO.getId());
				urm.setRewardId(7);
				urm.setWinTime(new Date());
				userRewardMidService.save(urm);
			}else if(uDO.getPayNum() == 2000){
				urm.setUserId(uDO.getId());
				urm.setRewardId(8);
				urm.setWinTime(new Date());
				urm.setUserId(uDO.getId());
				urm.setRewardId(1234546);
				urm.setWinTime(new Date());
				userRewardMidService.save(urm);
			}else if(uDO.getPayNum() == 5000){
				urm.setUserId(uDO.getId());
				urm.setRewardId(9);
				urm.setWinTime(new Date());
				userRewardMidService.save(urm);
			}
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
		
	}
	
	public String share(HttpServletRequest request){
		String strUrl = "http://www.xxxxx.com"
	            + request.getContextPath()   //项目名称  
	            + request.getServletPath()   //请求页面或其他地址  
	            + "?" + (request.getQueryString()); //参数  
	    WinXinEntity wx = WeinXinUtil.getWinXinEntity(strUrl);
	    //将wx的信息到给页面
	    request.setAttribute("wx", wx);
		return prefix + "/zhucema";
	}
	
}
