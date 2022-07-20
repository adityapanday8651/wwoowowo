package com.wowment.ws;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wowment.logging.api.VCLogger;
import com.wowment.logging.api.impl.VCLogManager;
import com.wowment.mongo.User;
import com.wowment.mongo.manager.UserManager;
import com.wowment.util.APIEndpoints;
import com.wowment.view.ResponseView;
import com.wowment.view.UserView;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path=APIEndpoints.BASE_API_URL_V1+"/user")
public class UserService extends GenericService {

	private static VCLogger logger = VCLogManager.getLogger(UserService.class);

	@Autowired UserManager userManager;

	@CrossOrigin
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="This API is to save user", 
	notes="Mandatory fields - TBD",
	response = User.class)
	public ResponseEntity<Object> registerUser(@RequestBody UserView view) throws Exception {
		logger.info("register user service call started - {0}", new Date());
		ResponseView res = new ResponseView();
		User user = new User();
		BeanUtils.copyProperties(view, user);
		userManager.save(user);
		res.setMessage("User added successfully");
		res.setStatus(true);
		logger.info("register user service call end - {0}", new Date());
		return toSucessCreate(res);
	}
}