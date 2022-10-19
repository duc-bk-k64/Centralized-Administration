package hust.admin.project.Controller;

import java.time.Instant;
//import java.util.HashSet;
//import java.util.Set;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Configuration.JwtConfig.JwtProvider;
import hust.admin.project.Entity.AccessLog;
import hust.admin.project.Entity.Account;
import hust.admin.project.Entity.Application;
import hust.admin.project.Entity.Group;
//import hust.admin.project.Entity.Permission;
//import hust.admin.project.Entity.Role;
import hust.admin.project.Entity.User;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Model.SignInData;
import hust.admin.project.Model.UserModel;
import hust.admin.project.Repository.AccountRepository;
import hust.admin.project.Repository.ApplicationRepository;
import hust.admin.project.Repository.GroupRepository;
//import hust.admin.project.Repository.PermissionRepository;
//import hust.admin.project.Repository.RoleRepository;
import hust.admin.project.Repository.UserRepository;
import hust.admin.project.Service.AccessLogService;

@RestController
@RequestMapping(Constant.API.PREFIX_AUTH)
public class AuthController {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private JwtProvider jwtProvider;
//	@Autowired
//	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private PermissionRepository permissionRepository;
	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private AccountService accountService;
	@Autowired
	private AccessLogService accessLogService;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private ApplicationRepository applicationRepository;
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/signin")
	@ResponseBody
	public ResponMessage signIn(@RequestBody SignInData data) {

		Account account = accountRepository.findUserByUsername(data.getUserName());
		ResponMessage responMessage = new ResponMessage();
//		logger.info(jwtProvider.getClientIp());

		if (account == null) {
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setMessage(Constant.MESSAGE.NOT_FOUND_USER);
			return responMessage;
		} else if (!passwordEncoder.matches(data.getPassWord(), account.getPassword())) {
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setMessage(Constant.MESSAGE.PASSWORD_INCORRECT);
			return responMessage;

		} else if (account.getStatus() == Constant.STATUS.DE_ACTIVE) {
			responMessage.setResultCode(Constant.RESULT_CODE.BAN_USER);
			responMessage.setMessage(Constant.MESSAGE.BAN_USER);
			return responMessage;

		} else {
			String token = jwtProvider.generateToken(data.getUserName());
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setData(token);
			return responMessage;
		}
	}

//	@PostMapping("/forgotpw")
//	@ResponseBody
//	public ResponMessage forgotPW(@RequestBody fotgotPW forgot) throws Exception {
//
//		ResponMessage responMessage = new ResponMessage();
//		try {
//			String message = accountService.forgotPassword(forgot.getUserName());
//			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
//			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
//			responMessage.setData(message);
//			return responMessage;
//		} catch (Exception e) {
//			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
//			responMessage.setMessage(Constant.MESSAGE.ERROR);
//			responMessage.setData(e.getMessage());
//			return responMessage;
//		}
//
//	}
//
//	@PostMapping("/resetpw")
//	@ResponseBody
//	public ResponMessage resetPW(@RequestBody hust.project3.model.resetPW reset) {
//		ResponMessage responMessage = new ResponMessage();
//		try {
//			String message = accountService.resetPassword(reset.getToken(), reset.getPassword());
//			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
//			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
//			responMessage.setData(message);
//			return responMessage;
//		} catch (Exception e) {
//			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
//			responMessage.setMessage(Constant.MESSAGE.ERROR);
//			responMessage.setData(e.getMessage());
//			return responMessage;
//		}
//	}
	@GetMapping("/authFail")
	@ResponseBody
	public ResponMessage authFail() {
		ResponMessage responMessage = new ResponMessage();
		responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		responMessage.setMessage(Constant.MESSAGE.ERROR);
		responMessage.setData("Authentication failure");
		return responMessage;
	}

	@GetMapping("/accessDenied")
	@ResponseBody
	public ResponMessage accessDenied() {
		ResponMessage responMessage = new ResponMessage();
		responMessage.setResultCode(Constant.RESULT_CODE.ACCESS_DENIED);
		responMessage.setMessage(Constant.MESSAGE.DENIED);
		responMessage.setData("Access denied");
		return responMessage;
	}

	@PostMapping("/createUser")
	@ResponseBody
	public ResponMessage createUser(@RequestBody UserModel data) {
		ResponMessage responMessage = new ResponMessage();
		if (data == null) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData("Data  is null");
			return responMessage;
		} else {
			User user = new User();
			user.setEmail(data.getEmail());
			user.setUsername(data.getUserName());
			user.setStatus(Constant.STATUS.DE_ACTIVE);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(userRepository.save(user));
			return responMessage;

		}

	}

	@GetMapping("/canSignIn")
	@ResponseBody
	public ResponMessage canSignIn(@RequestParam String username, @RequestParam String app_code) throws Exception {
		ResponMessage responMessage = new ResponMessage();
		AccessLog accessLog = new AccessLog();
		try {
			User user = userRepository.findByUsername(username);
			accessLog.setAction(Constant.ACTION.LOGIN);
			accessLog.setTime_log(Instant.now());
			accessLog.setIp_address(jwtProvider.getClientIp());
			accessLog.setUsername(username);

			if (user == null) {
				responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
				responMessage.setMessage(Constant.MESSAGE.NOT_FOUND_USER);
				accessLog.setStatus(Constant.MESSAGE.ERROR);
				accessLogService.createLog(accessLog);
				return responMessage;
			} else if (user.getStatus() == Constant.STATUS.DE_ACTIVE) { // check if user is de active
				responMessage.setResultCode(Constant.RESULT_CODE.BAN_USER);
				responMessage.setMessage(Constant.MESSAGE.BAN_USER);
				accessLog.setStatus(Constant.MESSAGE.ERROR);
				accessLogService.createLog(accessLog);

				return responMessage;

			} else { // check application and group is de-active
				Group group = groupRepository.findGroupByUserId(user.getId(), Constant.NAME.GROUP, Constant.NAME.USER);
				Long groupId = group.getId();
				List<Application> applications = applicationRepository.findApplicationByGroupId(groupId,
						Constant.NAME.APPLICATION, Constant.NAME.GROUP);
				int error = 0;
				if (group == null || applications == null || group.getStatus() == Constant.STATUS.DE_ACTIVE) {
					responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
					responMessage.setMessage(Constant.MESSAGE.ERROR);
					accessLog.setStatus(Constant.MESSAGE.ERROR);
				} else {
					for (int i = 0; i < applications.size(); i++) {
						if (applications.get(i).getApp_code() == app_code) {
							if (applications.get(i).getStatus() == Constant.STATUS.DE_ACTIVE) {
								error = 1;
							} else
								error = 0;
							break;
						} else
							error = 1;
					}
					if (error == 0) {
						responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
						responMessage.setMessage(Constant.MESSAGE.SUCCESS);
						accessLog.setStatus(Constant.MESSAGE.SUCCESS);
					} else {
						responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
						responMessage.setMessage(Constant.MESSAGE.ERROR);
						accessLog.setStatus(Constant.MESSAGE.ERROR);
					}
				}
				accessLogService.createLog(accessLog);
				return responMessage;

			}
		} catch (Exception e) {
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setData(e.getMessage());
			accessLog.setStatus(Constant.MESSAGE.ERROR);
			accessLogService.createLog(accessLog);
			return responMessage;
		}

	}

}
