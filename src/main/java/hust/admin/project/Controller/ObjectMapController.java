package hust.admin.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hust.admin.project.Common.Constant;
import hust.admin.project.Entity.ObjectMap;
import hust.admin.project.Model.ResponMessage;
import hust.admin.project.Repository.ObjectMapRepository;
import hust.admin.project.Service.ObjectMapService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Constant.API.PREFIX)
public class ObjectMapController {
	@Autowired
	private ObjectMapRepository objectMapRepository;
	@Autowired
	private ObjectMapService objectMapService;

	@PostMapping("/object/createObjectMap")
	@ResponseBody
	@ApiOperation("insert into table object map")
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

	@PostMapping("/object/createListObjectMap")
	@ResponseBody
	@ApiOperation("insert into table list object map")
	public ResponMessage insertListMapping(@RequestBody List<ObjectMap> objectMap) {
		ResponMessage responMessage = new ResponMessage();
		try {
			if (objectMap != null) {
				objectMapService.createListObjectMap(objectMap);
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

	@PutMapping("/object/updateObjectMapApp")
	@ResponseBody
	@ApiOperation("update object map group map application")
	public ResponMessage updateMappingApp(@RequestParam Long app_id, @RequestBody List<ObjectMap> objectMap) {
		ResponMessage responMessage = new ResponMessage();
		try {
			if (objectMap != null) {
				objectMapService.updateObjectMapApp(app_id, objectMap);
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

	@PutMapping("/object/deleteObjectMap")
	@ResponseBody
	@ApiOperation("delete object map")
	public ResponMessage deleteObjectMapp(@RequestBody ObjectMap objectMap) {
		ResponMessage responMessage = new ResponMessage();
		try {
			if (objectMap != null) {
				objectMapRepository.removeObjectMap(objectMap.getObject_id(), objectMap.getObject_name(),
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
