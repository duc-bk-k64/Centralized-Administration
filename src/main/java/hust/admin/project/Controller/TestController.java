package hust.admin.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Repository.AccountRepository;



@RestController
@RequestMapping(Constant.API.PREFIX)
public class TestController {
	@Autowired
	private AccountRepository accountRepository;
	@GetMapping("/")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_EMPLOYEE') and  hasAuthority('READ')")
	public ResponMessage test() {
		ResponMessage responMessage= new ResponMessage();
		responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		responMessage.setMessage(Constant.MESSAGE.SUCCESS);
		responMessage.setData(accountRepository.findAll());
		return responMessage;
	}

}
