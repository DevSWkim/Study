package com.mark.book;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	private Map<String, String> userData = new HashMap<String, String>();
	
	@RequestMapping("/")
	public String getMain() {
		return "Hello";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String getId(@RequestParam(value="id") String id) {
		
		System.out.println("Entered ID : " + id);
		
		if(!userData.isEmpty()) {
			if(!userData.containsKey(id)) {
				
				System.out.println(userData.size());
				
				return "We have the Id information.";
			}
		} else {
			return "We have no user Data.";
		}
		
		return userData.get(id);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String setId(@RequestParam(value="id") String id) {
		
		System.out.println("added ID : " + id);
		
		return userData.put(id, "TEST");
	}
	
	
//	@RequestMapping(value="/users", method=RequestMethod.POST)
//	public int increaseWins(){   
//		{ return ++Score.WINS;
//	}
//
//	@RequestMapping(value="/users", method=RequestMethod.GET)
//	public Data getData() {
//		return Score.WINS;   
//	}

}
