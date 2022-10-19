package hust.admin.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Service.AccessLogService;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class AccessLogController {
	@Autowired
	private AccessLogService accessLogService;

	@GetMapping("/log/getLog")
	@ResponseBody
	public ResponMessage getLogByUsername(@RequestParam String username) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(accessLogService.getLogByUsername(username));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

}
