package kkg.fuber.Controller;

import kkg.fuber.Entity.UserEntity;
import kkg.fuber.Service.UserService;
import kkg.fuber.Util.Result;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kushal
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Result getUserById(@RequestParam("id") Long id){
		try {
			UserEntity user = userService.getUserById(id);
			if(user!=null){
				return new Result(true,"Successfull",user);
			}
			else{
				return new Result(true,"User doesn't exist.",null);
			}
		} catch (Exception e) {
			logger.error("Exception while getting user for id: "+id, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Result createUser(@RequestBody UserEntity user){
		try {
			user = userService.create(user);
			if(user!=null){
				return new Result(true,"User created successfully",user);
			}
			else{
				return new Result(true,"Unable to create user",null);
			}
		} catch (Exception e) {
			logger.error("Exception while creating a new user"+user, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Result updateUser(@RequestBody UserEntity user){
		try {
			user = userService.update(user);
			if(user!=null){
				return new Result(true,"User updated successfully",user);
			}
			else{
				return new Result(true,"Unable to update user",null);
			}
		} catch (Exception e) {
			logger.error("Exception while updating user"+user, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Result deleteUser(@RequestParam(value = "id", required = true) Long id){
		try {
			Boolean userRemoved = userService.removeUser(id);
			if(userRemoved){
				return new Result(true,"User removed successfully",null);
			}
			else{
				return new Result(true,"Unable to remove user",null);
			}
		} catch (Exception e) {
			logger.error("Error while removing user with id: "+id, e);
			return new Result(false,"Error while removing user with id: "+id,null);
			
		}
	}
}
