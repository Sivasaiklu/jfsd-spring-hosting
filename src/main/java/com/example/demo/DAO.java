package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DAO 
{
	@Autowired
	UserInterface repo;
	
	public void insert(User u1) {
		repo.save(u1);
	}
	
	public User findUser(String email) {
		return repo.findByEmail(email);
	}
	
	public List<User> findAllUsers(){
		return repo.findAll();
	}
	
//	public List<User> findPage(int page, int limit){
//		Sort sort = Sort.by(Sort.Direction.DESC, "name");
//		Pageable pageable = PageRequest.of(page, limit, sort); 
//		return repo.findAll(pageable).toList();
//	}
	
	public List<User> findPage(){
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		Pageable pageable = PageRequest.of(0, 5, sort); 
		return repo.findAll(pageable).toList();
	}
	
	public String deleteUser(String email) {
		repo.delete(repo.findByEmail(email));
		return "Deleted " + email;
	}
	
	public String editUser(User user) {
		System.out.println("edit function");
		deleteUser(user.getEmail());
		System.out.println("deleted");
		insert(user);
		System.out.println("editted");
		return "Edited Successfully";
	}
}
