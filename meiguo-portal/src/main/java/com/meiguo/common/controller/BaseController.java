package com.meiguo.common.controller;

import org.springframework.stereotype.Controller;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.owneruser.domain.OwnerUserDO;
import com.meiguo.system.domain.UserToken;

@Controller
public class BaseController {
	public OwnerUserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}