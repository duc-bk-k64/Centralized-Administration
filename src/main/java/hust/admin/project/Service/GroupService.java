package hust.admin.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.Group;
import hust.admin.project.Repository.GroupRepository;
import hust.admin.project.Repository.ObjectMapRepository;

@Service
public class GroupService {
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private ObjectMapRepository objectMapRepository;

	public Group createGroup(Group group) throws Exception {
		try {
			groupRepository.save(group);
			return group;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Group updateGroup(String group_code, Group group) throws Exception {
		try {
			Group existGroup = groupRepository.findApplicationByGroupCode(group_code);
			if (existGroup == null)
				return groupRepository.save(group);
			else {
				existGroup.setGroup_name(group.getGroup_name());
				existGroup.setStatus(group.getStatus());
				return groupRepository.save(existGroup);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Group getGroup(String group_code) throws Exception {
		try {
			return groupRepository.findApplicationByGroupCode(group_code);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public List<Group> getGroupByAppId(Long appId) throws Exception {
		try {
			return groupRepository.findGroupByAppId(appId, Constant.NAME.APPLICATION,Constant.NAME.GROUP);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean deleteGroup(String group_code) throws Exception {
		try {
			Group group = groupRepository.findApplicationByGroupCode(group_code);
			if (group == null)
				return true;
			else {
				objectMapRepository.removeObjectMap(group.getId(), Constant.NAME.GROUP);
				objectMapRepository.removeObjectMap(Constant.NAME.GROUP, group.getId());
				groupRepository.delete(group);
				return true;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public boolean activeGroup(Long id) throws Exception {
		try {
			groupRepository.activeGroup(id);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public boolean deactiveGroup(Long id) throws Exception {
		try {
			groupRepository.deactiveGroup(id);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public List<Group> findAllGroup() throws Exception {
		try {
			return groupRepository.findAll();
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
