package hust.admin.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.admin.project.Entity.AccessLog;
import hust.admin.project.Repository.AccessLogRepository;

@Service
public class AccessLogService {
	@Autowired
	private AccessLogRepository accessLogRepository;

	public AccessLog createLog(AccessLog accessLog) throws Exception {
		try {
			return accessLogRepository.save(accessLog);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<AccessLog> getLogByUsername(String username) throws Exception {
		try {
			return accessLogRepository.getLogByUsername(username);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
