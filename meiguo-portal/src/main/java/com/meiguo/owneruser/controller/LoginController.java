package com.meiguo.owneruser.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.meiguo.carousel.domain.BannerDO;
import com.meiguo.carousel.service.BannerService;
import com.meiguo.common.annotation.Log;
import com.meiguo.common.controller.BaseController;
import com.meiguo.common.utils.MD5Utils;
import com.meiguo.common.utils.R;
import com.meiguo.common.utils.ShiroUtils;
import com.meiguo.information.domain.NoticeDO;
import com.meiguo.information.service.NoticeService;
import com.meiguo.owneruser.comment.GenerateCode;
import com.meiguo.owneruser.comment.SMSContent;
import com.meiguo.owneruser.comment.SMSPlatform;
import com.meiguo.owneruser.comment.SMSTemplate;
import com.meiguo.owneruser.comment.ZhuCeUtil;
import com.meiguo.owneruser.domain.OwnerUserDO;
import com.meiguo.owneruser.domain.UserCouponDO;
import com.meiguo.owneruser.domain.UserRewardMidDO;
import com.meiguo.owneruser.service.OwnerUserService;
import com.meiguo.owneruser.service.UserRewardMidService;
import com.meiguo.smsservice.service.ISMSService;



@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private BannerService bannerService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private OwnerUserService userService;
	@Autowired
	private ISMSService sMSService;
	@Autowired
	private UserRewardMidService userRewardMidService;
	
	@Log("请求访问主页")
	@GetMapping({ "" })
	String indexs(Model model) {
		
		// 查询banner数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isEnable", "0");
		List<BannerDO> bannerList = bannerService.list(params);
		// 查询banner数据
		Map<String, Object> paramsNotice = new HashMap<String, Object>();
		OwnerUserDO udo = this.getUser();
		if(udo != null)
			paramsNotice.put("user_id", this.getUserId());
		List<NoticeDO> noticeList = noticeService.list(paramsNotice);

		model.addAttribute("bannerList", bannerList);
		model.addAttribute("noticeList", noticeList);
		return "index";
	}
	

	
	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model, Long inviterId) {
		
		
		// 查询banner数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isEnable", "0");
		List<BannerDO> bannerList = bannerService.list(params);
		// 查询banner数据
		Map<String, Object> paramsNotice = new HashMap<String, Object>();
		OwnerUserDO udo = this.getUser();
		if(udo != null)
			paramsNotice.put("user_id", this.getUserId());
		List<NoticeDO> noticeList = noticeService.list(paramsNotice);
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("noticeList", noticeList);
		return "index";

	}	
	
	@GetMapping({ "/jieshao" })
	String jieshao(Model model) {
		return "jieshao";
	}

	@GetMapping("/login")
	String login() {
		return "user/denglu";
	}
	@GetMapping("/zhuce")
	String zhuce() {
		return "user/zhuce";
	}
	@GetMapping("/wangjimima")
	String wangjimima() {
		return "user/wangjimima";
	}

	//手机号、密码登录
	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			Map<String, Object> mapP = new HashMap<String, Object>();
			mapP.put("username", username);
			boolean flag = userService.exit(mapP);
			if (!flag) {
				return R.error("该手机号码未注册");
			}
			OwnerUserDO udo= userService.getbyname(username);
			if(udo.getDeleteFlag()==1){
				return R.error("禁止登录，请联系客服");
			}
			
			//OwnerUserDO user= userService.getbyname(username);
			UserRewardMidDO urmDO = new UserRewardMidDO();
			//UserCouponDO ucDO = new UserCouponDO();
			if(udo != null && !"".equals(udo)){
				Calendar calendar = Calendar.getInstance();
				Date loginTime = udo.getLoginTime();
				if(loginTime == null || "".equals(loginTime)){
					calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),0,0,0);
				}else{
					calendar.setTime(loginTime);
				}
				Calendar today = Calendar.getInstance();
				today.set(today.get(Calendar.YEAR),today.get(Calendar.MARCH),today.get(Calendar.DATE),0,0,0);
				Calendar yesterday = calendar.getInstance();
				yesterday.set(yesterday.get(Calendar.YEAR),yesterday.get(Calendar.MARCH),yesterday.get(Calendar.DATE)-1,0,0,0);
				
				if(calendar.before(today)){
					if(calendar.before(yesterday)){
						udo.setDengluNum(1);
					}else{
						Integer dengluNum = udo.getDengluNum();
						dengluNum++;
						if(dengluNum==7){
							urmDO.setRewardId(1);
							urmDO.setUserId(udo.getId());
							urmDO.setWinTime(new Date());
							//userRewardMidService.save(urmDO);
						}else if(dengluNum==30){
							urmDO.setRewardId(2);
							urmDO.setUserId(udo.getId());
							urmDO.setWinTime(new Date());
							userRewardMidService.save(urmDO);
						}else if(dengluNum==180){
							urmDO.setRewardId(3);
							urmDO.setUserId(udo.getId());
							urmDO.setWinTime(new Date());
							userRewardMidService.save(urmDO);
							urmDO.setRewardId(1234546);
							urmDO.setUserId(udo.getId());
							urmDO.setWinTime(new Date());
							userRewardMidService.save(urmDO);
						}
						udo.setDengluNum(dengluNum);
					}
//					user.setLoginTime(new Date());
//					userService.update(user);
//					return R.ok();
				}
			}
			subject.login(token);
			udo.setLoginTime(new Date());
			userService.update(udo);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	private Integer zhucemaNum = 0;
	//手机号密码注册
	@Log("注册")
	@PostMapping("/zhuce")
	@ResponseBody
	R ajaxZhuce(String username, String password, String nickname, String codenum,String zhucema,Integer zhucemaNum,
			Model model) {		
		if (StringUtils.isBlank(username)) {
			return R.error("手机号码不能为空");
		}
		password = MD5Utils.encrypt(username, password);
		OwnerUserDO udo= new OwnerUserDO();
		Map<String, Object> mapP = new HashMap<String, Object>();
		mapP.put("username", username);		
		boolean flag = userService.exit(mapP);
		if (flag) {
			return R.error("手机号码已存在");
		}
		
//		Long userId = GenerateCode.gen16(9);
//		udo.setUserId(userId);
		udo.setUsername(username);
		udo.setPhone(username);
		udo.setPassword(password);
		udo.setNickname(nickname);
		udo.setBalance(0.00);
		udo.setDeleteFlag(0);
		udo.setRegisterTime(new Date());
		String yaoqing = ZhuCeUtil.getNewUserId();
		udo.setZhucema(Long.parseLong(yaoqing.substring(8,14)));
		udo.setZhucemaNum(0);
		udo.setDengluNum(1);
		
		if(StringUtils.isNotBlank(zhucema)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("zhucema", zhucema);		
			boolean zhu = userService.exit(map);
			if (zhu) {
				try {
					OwnerUserDO ownerUserDO = userService.getbyzhuce(zhucema);
					Long ids =ownerUserDO.getUserId();
					Integer num = ownerUserDO.getZhucemaNum();
					ownerUserDO.setZhucemaNum(num+1);
					udo.setInviterId(ids);
					if(userService.update(ownerUserDO)>0){
						//return R.ok();
					}
					//OwnerUserDO list = userService.getList(map);
					
					if(ownerUserDO.getZhucemaNum().equals(1)){
						UserRewardMidDO midDO = new UserRewardMidDO();						
						midDO.setUserId(ids);
						midDO.setRewardId(1234546);
						midDO.setWinTime(new Date());
						userRewardMidService.save(midDO);
					}else if(ownerUserDO.getZhucemaNum().equals(3)){
						UserRewardMidDO midDO = new UserRewardMidDO();						
						midDO.setUserId(ids);
						midDO.setRewardId(1234546);
						midDO.setWinTime(new Date());
						userRewardMidService.save(midDO);
					}else if(ownerUserDO.getZhucemaNum().equals(5)){
						UserRewardMidDO midDO = new UserRewardMidDO();						
						midDO.setUserId(ids);
						midDO.setRewardId(1234546);
						midDO.setWinTime(new Date());
						userRewardMidService.save(midDO);
					}			
				} catch (Exception e) {
					e.printStackTrace();
				}
																	
			//	return R.error("存在");
			}else if(!zhu){
				return R.error("分享码不正确");
			}
		}
		if(userService.save(udo)>0){
			return R.ok();
		}
		
		return R.error();

	}
	
	/*@Log("注册")
	@PostMapping("/zhuce")
	@ResponseBody
	R ajaxZhuce(String username, String password, String nickname, String codenum) {
		if (StringUtils.isBlank(username)) {
			return R.error("手机号码不能为空");
		}
		password = MD5Utils.encrypt(username, password);
		OwnerUserDO udo= new OwnerUserDO();
		Subject subject = SecurityUtils.getSubject();
		String captcha =subject.getSession().getAttribute("sys.login.check.code").toString();
		if (captcha == null || "".equals(captcha)) {
			return R.error("验证码已失效，请重新点击发送验证码");
		}
		// session中存放的验证码是手机号+验证码
		if (!captcha.equalsIgnoreCase(username + codenum)) {
			return R.error("手机验证码错误");
		}
		Map<String, Object> mapP = new HashMap<String, Object>();
		mapP.put("username", username);
		boolean flag = userService.exit(mapP);
		if (flag) {
			return R.error("手机号码已存在");
		}
		udo.setUsername(username);
		udo.setPhone(username);
		udo.setPassword(password);
		udo.setNickname(nickname);
		udo.setBalance(0.00);
		udo.setDeleteFlag(0);
		udo.setRegisterTime(new Date());
		if (userService.save(udo) > 0) {
			return R.ok();
		}
		return R.error();
	}*/
	
	@Log("忘记密码")
	@PostMapping("/retpwd")
	@ResponseBody
	R retpwd(String username, String password,  String codenum) {
		if (StringUtils.isBlank(username)) {
			return R.error("手机号码不能为空");
		}
		Map<String, Object> mapP = new HashMap<String, Object>();
		mapP.put("username", username);
		boolean flag = userService.exit(mapP);
		if (!flag) {
			return R.error("该手机号码未注册");
		}
		password = MD5Utils.encrypt(username, password);
		OwnerUserDO udo= userService.getbyname(username);
		Subject subject = SecurityUtils.getSubject();
		String captcha =subject.getSession().getAttribute("sys.login.check.code").toString();
		if (captcha == null || "".equals(captcha)) {
			return R.error("验证码已失效，请重新点击发送验证码");
		}
		// session中存放的验证码是手机号+验证码
		if (!captcha.equalsIgnoreCase(username + codenum)) {
			return R.error("手机验证码错误");
		}
		udo.setPassword(password);
		if (userService.update(udo) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}
	
	/**
	 * @说明 发送验证码
	 * 暂时只有注册
	 * @author tmn
	 * @update tmn
	 * @param phone		手机号
	 * @param type		类型 0：注册   1实名认证   
	 * @param request
	 * @param response
	 */
	@PostMapping("/verificationcodeNumber")
	@ResponseBody
	   R verificationcodeNumber(String phone,String type) {
		if (phone == null || "".equals(phone)) {
			return R.error("手机号码不能为空");
		}
		
		//短信内容
		SMSTemplate contentType = SMSContent.ZHUCE;
		//短信内容
		if("0".equals(type)){
			contentType = SMSContent.ZHUCE;//注册
		}
		if("1".equals(type)){
			contentType = SMSContent.ZHAOHUI_LOGIN;//找回密码
			Map<String, Object> mapP = new HashMap<String, Object>();
			mapP.put("username", phone);
			boolean flag = userService.exit(mapP);
			if (!flag) {
				return R.error("该手机号码未注册");
			}
		}
		
		try {
				Map<String, Object> map = sMSService.sendCodeNumber(SMSPlatform.meiguo, phone, contentType);
				if(map == null){
					return R.error("验证码发送出现问题,请三分钟再试");
				}
				String code = map.get("randomCode").toString();
				Subject subject = SecurityUtils.getSubject();
				subject.getSession().setAttribute("sys.login.check.code", phone + code);
				
				return R.ok();
		} catch (Exception e) {
			logger.info("SMS==================验证码发送出现问题========" + phone + "======");
			return R.error("验证码发送出现问题,请三分钟再试");
		}
	}
	
	/*
	 * 邀请码
	 */
	
	@GetMapping("/zhucema")
	String zhucema(Model model){
		OwnerUserDO zcMa = userService.get(getUserId());		
		model.addAttribute("zcMa",zcMa);
		return "zhucema";			
  			
	}
	

}
