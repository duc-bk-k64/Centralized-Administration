package hust.admin.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.Application;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Service.ApplicationService;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("/app/createApp")
	@ResponseBody
	public ResponMessage createApp(@RequestBody Application application) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(applicationService.createApplication(application));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}
	@PutMapping("/app/updateApp")
	@ResponseBody
	public ResponMessage updateApp(@RequestParam String app_code, @RequestBody Application application) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(applicationService.updateApplication(app_code, application));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}
	@DeleteMapping("/app/deleteApp")
	@ResponseBody
	public ResponMessage deleteApp(@RequestParam String app_code)  {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(applicationService.deleteApplication(app_code));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
		
	}
	@GetMapping("/app/getApp")
	@ResponseBody
	public ResponMessage getApp(@RequestParam String app_code)  {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(applicationService.getApplication(app_code));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
		
	}

}
