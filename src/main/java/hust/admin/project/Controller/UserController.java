package hust.admin.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Service.UserService;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/user/active")
	@ResponseBody
	public ResponMessage activeUser(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(userService.activeUser(id));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}

	@PostMapping("/user/deactive")
	@ResponseBody
	public ResponMessage deactiveUser(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(userService.deactiveUser(id));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}

	@GetMapping("/user/getDeactiveUser")
	@ResponseBody
	public ResponMessage getDeactiveUser() {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(userService.getUserByStatus(Constant.STATUS.DE_ACTIVE));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}

	@GetMapping("/user/getActiveUser")
	@ResponseBody
	public ResponMessage getActiveUser() {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(userService.getUserByStatus(Constant.STATUS.ACTIVE));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}

	@GetMapping("/user/getUserByGroupId")
	@ResponseBody
	public ResponMessage getUserByGroupId(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(userService.getUserByGroupId(id));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}

}
