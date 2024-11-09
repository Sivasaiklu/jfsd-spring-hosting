package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
public class Service {
	
	@Autowired
	DAO dao;
	
	@GetMapping("/")
	public String fun1() {
		return "This is Home Page";
	}
	
	@GetMapping("/welcome/{name}")
	public String fun2(@PathVariable("name") String name) {
		return "Welcome "+name;
	}
	
	@PostMapping("/user")
	public String fun3(@RequestBody User user) {
		dao.insert(user);
		return "User Inserted";
	}
	
	//	@GetMapping("/user")
	@RequestMapping(value = "/user",method = RequestMethod.GET )
	public String fun4(@RequestParam("email") String email) {
		User u = dao.findUser(email);
		return "User Details "+ u;
	}
	
//	@GetMapping("/page")
//	public String fun5(@RequestParam("page") int page, @RequestParam("limit") int limit) {
//		return dao.findPage(page, limit).toString();
//	}
	
	@GetMapping("/findPage")
	public List<User> show() {
		return dao.findPage();
	}
	
	
	@GetMapping("/all")
	public String fun6() {
		List<User> ulist = dao.findAllUsers();
		return ulist.toString();
	}
	
	@DeleteMapping("/delete")
	public String fun7(@RequestParam("email") String email) {
		return dao.deleteUser(email);
	}
	
	@PutMapping("/update")
	public String fun8(@RequestBody User user) {
		System.out.println(user);
		return dao.editUser(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		User u = dao.findUser(user.getEmail());
		if(u == null) {
			System.out.println("User is not found");
			return user;
		}
		else if(user.password.equals(u.password)) {
			System.out.println("password is correct");
			return u;
		}
		System.out.println("Password is wrong");
		return user;
	}
	
}
