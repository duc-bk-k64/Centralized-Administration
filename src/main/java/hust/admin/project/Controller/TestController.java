package hust.admin.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Repository.AccountRepository;
import hust.admin.project.Repository.ApplicationRepository;
import hust.admin.project.Repository.GroupRepository;
import hust.admin.project.Service.ObjectMapService;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class TestController {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ObjectMapService objectMapService;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private ApplicationRepository applicationRepository;

	@GetMapping("/")
	@ResponseBody
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE') and  hasAuthority('READ')")
	public ResponMessage test(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setData(applicationRepository.findApplicationByGroupId(id, Constant.NAME.APPLICATION, Constant.NAME.GROUP));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}

}
