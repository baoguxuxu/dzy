package com.meiguo.common.controller;

import org.springframework.stereotype.Controller;

import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.system.domain.UserDO;
import com.meiguo.system.domain.UserToken;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}