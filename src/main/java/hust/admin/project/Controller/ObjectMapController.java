package hust.admin.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.ObjectMap;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Repository.ObjectMapRepository;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class ObjectMapController {
	@Autowired
	private ObjectMapRepository objectMapRepository;

	@PostMapping("/object/createObjectMap")
	@ResponseBody
	public ResponMessage insertMapping(@RequestBody ObjectMap objectMap) {
		ResponMessage responMessage = new ResponMessage();
		try {
			if (objectMap != null) {
				objectMapRepository.insertObjectMap(objectMap.getObject_id(), objectMap.getObject_name(),
						objectMap.getMap_id(), objectMap.getMap_name());
				responMessage.setMessage(Constant.MESSAGE.SUCCESS);
				responMessage.setResultCode(Constant.RESULT_CODE.SUCCESS);
			} else {
				responMessage.setMessage(Constant.MESSAGE.DATA_NULL);
				responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
			}

		} catch (Exception e) {
			responMessage.setMessage(e.getMessage());
			responMessage.setResultCode(Constant.RESULT_CODE.ERROR);
		}
		return responMessage;
	}

}
