package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Entity_Leaves;
import com.example.demo.entity.Entity_UploadFileResponse;

public interface repo_UploadFileResponse extends JpaRepository<Entity_UploadFileResponse, Integer> {
	public List<Entity_UploadFileResponse>findByEmail(String email);
	public List<Entity_UploadFileResponse>findByUsernameAndFileTypeAndStartAndEnd(String email,String type,String start,String end);
}
