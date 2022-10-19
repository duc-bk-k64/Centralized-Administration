package hust.admin.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.User;
import hust.admin.project.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean activeUser(Long id) throws Exception {
		try {
			userRepository.activeUser(id);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean deactiveUser(Long id) throws Exception {
		try {
			userRepository.deactiveUser(id);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<User> getUserByStatus(Long status) throws Exception {
		try {
			return userRepository.getUserByStatus(status);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<User> getUserByGroupId(Long groupId) throws Exception {
		try {
			return userRepository.findUserByGroupId(groupId, Constant.NAME.GROUP, Constant.NAME.USER);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
