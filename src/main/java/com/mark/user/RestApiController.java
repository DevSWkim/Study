package com.mark.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	private Map<String, Data> userData = new HashMap<String, Data>();
	
	@RequestMapping("")
	public String getMain() {
		return "Server is running...";
	}
	
	@RequestMapping(value="/users", method= {RequestMethod.GET})
	public String getAllUser() {
		
		return userData.toString();
	}
	
	@RequestMapping(value="/user/{id}", method= {RequestMethod.GET})
	public String getUser(@PathVariable(value="id") String id) {
		
		if(!userData.containsKey(id)) {
			return id + "님은 회원이 아닙니다.";
		} else {
			return userData.get(id).toString();
		}
	}
	
	@RequestMapping(value="/user/{id}", method= {RequestMethod.PUT})
	public String modifyUser(@PathVariable(value="id") String id,
							 @PathVariable(value="name") String name,
							 @PathVariable(value="password") String password) {
		
		if(!userData.containsKey(id)) {
			return id + "님은 회원이 아닙니다.";
		} else {
			if(userData.get(id).getPassword().equals(password)) {
				String beforeName = userData.get(id).getName();
				userData.get(id).setName(name);
				return id + "님의 이름을 " + beforeName + "에서 " + name + "으로 변경하였습니다.";
			} else {
				return id + "님의 패스워드가 올바르지 않습니다.";
			}
		}
	}
	
	@RequestMapping(value="/user/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable(value="id") String id) {

		if(!userData.containsKey(id)) {
			return id + "님은 회원이 아닙니다.";
		} else {
			userData.remove(id);
			return id + "님이 정상적으로 삭제되었습니다.";
		}
		
	}
	
	@RequestMapping(value="/user/add/{id}/{name}/{password}", method= {RequestMethod.POST})
	public Map<String, Data> addUser(@PathVariable(value="id") String id,
									 @PathVariable(value="name") String name,
									 @PathVariable(value="password") String password) {

		Data data = new Data(id, password, name);
		
		userData.put(id, data);
		
		return userData;
	}
	
//	@RequestMapping("/error")
//	public String errorPage() {
//		return "사용자 요청을 처리할 수 없습니다.";
//	}

}
