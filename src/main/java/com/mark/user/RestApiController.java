package com.mark.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	private Map<String, Data> userData = new HashMap<String, Data>();
	
	@RequestMapping("/")
	public String getMain() {
		return "총 회원 수 : " + userData.size();
	}
	
	@RequestMapping(value="/users", method= {RequestMethod.GET, RequestMethod.POST})
	public String getAllUser() {
		
		return userData.toString();
	}
	
	@RequestMapping(value="/user", method= {RequestMethod.GET, RequestMethod.POST})
	public String getUser(@RequestParam(value="id") String id) {
		
		if(!userData.containsKey(id)) {
			return id + "님은 회원이 아닙니다.";
		} else {
			return userData.get(id).toString();
		}
	}
	
	@RequestMapping(value="/users/add", method= {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Data> addUser(@RequestParam(value="id") String id,
									 @RequestParam(value="name") String name,
									 @RequestParam(value="password") String password) {

		Data data = new Data(id, password, name);
		
		userData.put(id, data);
		
		return userData;
	}
	
	@RequestMapping(value="/users/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateUser(@RequestParam(value="id") String id,
									 @RequestParam(value="name") String name,
									 @RequestParam(value="password") String password) {

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
	
	@RequestMapping(value="/users/delete", method=RequestMethod.GET)
	public String deleteUser(@RequestParam(value="id") String id) {

		if(!userData.containsKey(id)) {
			return id + "님은 회원이 아닙니다.";
		} else {
			userData.remove(id);
			return id + "님이 정상적으로 삭제되었습니다.";
		}
		
	}

}
