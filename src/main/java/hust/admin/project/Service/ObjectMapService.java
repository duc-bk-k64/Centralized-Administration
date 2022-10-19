package hust.admin.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.Group;
import hust.admin.project.Entity.ObjectMap;
import hust.admin.project.Repository.ObjectMapRepository;

@Service
public class ObjectMapService {
	@Autowired
	private ObjectMapRepository objectMapRepository;

	public ObjectMap createObjectMap(ObjectMap objectMap) throws Exception {
		try {
			if (objectMap != null) {
				objectMapRepository.insertObjectMap(objectMap.getObject_id(), objectMap.getObject_name(),
						objectMap.getMap_id(), objectMap.getMap_name());

			}
			return objectMap;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Group> findGroupByUserId(Long id) throws Exception {
		try {
			return objectMapRepository.findGroupByUserId(id, Constant.NAME.GROUP, Constant.NAME.USER);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean createListObjectMap(List<ObjectMap> objectMaps) throws Exception {
		try {
			if (objectMaps != null) {
				objectMaps.forEach(objectMap -> {
					objectMapRepository.insertObjectMap(objectMap.getObject_id(), objectMap.getObject_name(),
							objectMap.getMap_id(), objectMap.getMap_name());
				});
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public boolean updateObjectMapApp(Long app_id,List<ObjectMap> objectMaps) throws Exception {
		try {
			if (objectMaps != null) {
				objectMapRepository.removeObjectMap(app_id,Constant.NAME.APPLICATION);
				objectMaps.forEach(objectMap -> {
					objectMapRepository.insertObjectMap(objectMap.getObject_id(), objectMap.getObject_name(),
							objectMap.getMap_id(), objectMap.getMap_name());
				});
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
