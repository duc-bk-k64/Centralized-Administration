package hust.admin.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.Application;
import hust.admin.project.Repository.ApplicationRepository;
import hust.admin.project.Repository.ObjectMapRepository;

@Service
public class ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private ObjectMapRepository objectMapRepository;

	public Application createApplication(Application application) throws Exception {
		try {
			return applicationRepository.save(application);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Application updateApplication(String app_code, Application application) throws Exception {
		try {
			Application existApplication = applicationRepository.findApplicationByAppCode(app_code);
//			System.out.println(existApplication.getApp_name());
			if (existApplication == null)
				return applicationRepository.save(application);
			else {
				existApplication.setApp_name(application.getApp_name());
				existApplication.setStatus(application.getStatus());
				applicationRepository.save(existApplication);
				return existApplication;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Application getApplication(String app_code) throws Exception {
		try {
			return applicationRepository.findApplicationByAppCode(app_code);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean deleteApplication(String app_code) throws Exception {
		try {
			Application application = applicationRepository.findApplicationByAppCode(app_code);
			if (application == null)
				return true;
			else {
				objectMapRepository.removeObjectMap(application.getId(), Constant.NAME.APPLICATION);
				applicationRepository.delete(application);
				return true;
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	public boolean activeApp(Long id) throws Exception {
		try {
			applicationRepository.activeApp(id);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public boolean deactiveApp(Long id) throws Exception {
		try {
			applicationRepository.deactiveApp(id);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public List<Application> allApplications() throws Exception {
		try {
			return applicationRepository.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
