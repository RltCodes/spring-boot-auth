package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
	List<Role> findByName(String name);
}
