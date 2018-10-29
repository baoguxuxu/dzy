package com.meiguo.owneruser.controller;

import com.meiguo.common.annotation.Log;
import com.meiguo.common.config.BootdoConfig;
import com.meiguo.common.config.Constant;
import com.meiguo.common.controller.BaseController;
import com.meiguo.common.utils.*;
import com.meiguo.information.domain.FilesDO;
import com.meiguo.owneruser.domain.OwnerUserDO;
import com.meiguo.owneruser.domain.UserPlotDO;
import com.meiguo.owneruser.service.OwnerUserService;
import com.meiguo.owneruser.service.UserPlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * 获取用户基本信息
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

}
