package hust.admin.project.Controller;

import java.util.ArrayList;
import java.util.List;

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
import hust.admin.project.Entity.Group;
import hust.admin.project.Model.GroupUserModel;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Service.GroupService;
import hust.admin.project.Service.UserService;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired 
	private UserService userService;
	
	@PostMapping("/group/createGroup")
	@ResponseBody
	public ResponMessage createGroup(@RequestBody Group group) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(groupService.createGroup(group));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}
	@PutMapping("/group/updateGroup")
	@ResponseBody
	public ResponMessage updateGroup(@RequestParam String group_code,@RequestBody Group group) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(groupService.updateGroup(group_code, group));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}
	@GetMapping("/group/getGroup")
	@ResponseBody
	public ResponMessage getGroup(@RequestParam String group_code) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(groupService.getGroup(group_code));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}
	@DeleteMapping("/group/deleteGroup")
	@ResponseBody
	public ResponMessage deleteGroup(@RequestParam String group_code) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			responMessage.setData(groupService.deleteGroup(group_code));
		} catch (Exception e) {
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			responMessage.setData(e.getMessage());
		}
		return responMessage;
	}
	@PostMapping("/group/active")
	@ResponseBody
	public ResponMessage activeGroup(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(groupService.activeGroup(id));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}
	@PostMapping("/group/deactive")
	@ResponseBody
	public ResponMessage deactiveGroup(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(groupService.deactiveGroup(id));
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}
	@GetMapping("/group/findGroupByAppId")
	@ResponseBody
	public ResponMessage findGroupByAppId(@RequestParam Long id) {
		ResponMessage responMessage = new ResponMessage();
		try {
			List<Group> groups = groupService.getGroupByAppId(id);
			List<GroupUserModel> data = new ArrayList();
			groups.forEach(group -> {
				GroupUserModel groupUserModel = new GroupUserModel();
				groupUserModel.setGroup_code(group.getGroup_code());
				groupUserModel.setGroup_name(group.getGroup_name());
				groupUserModel.setId(group.getId());
				groupUserModel.setStatus(group.getStatus());
				try {
					groupUserModel.setUser(userService.getUserByGroupId(group.getId()));
					data.add(groupUserModel);
				} catch (Exception e) {
					responMessage.setData(e.getMessage());
					responMessage.setMessage(Constant.MESSAGE.ERROR);
					responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
				}
			});
			responMessage.setData(data);
			responMessage.setMessage(Constant.MESSAGE.SUCCESS);
			responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
		} catch (Exception e) {
			responMessage.setData(e.getMessage());
			responMessage.setMessage(Constant.MESSAGE.ERROR);
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}
	@GetMapping("/group/")
	@ResponseBody
	public ResponMessage findAllGroup() {
		ResponMessage responMessage = new ResponMessage();
		try {
			responMessage.setData(groupService.findAllGroup());
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
