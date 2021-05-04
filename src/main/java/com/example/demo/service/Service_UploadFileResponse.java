package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Entity_UploadFileResponse;
import com.example.demo.repo.repo_UploadFileResponse;

@Service
public class Service_UploadFileResponse {
	@Autowired
	private repo_UploadFileResponse repo_UploadFileResponse;
	
	public List<Entity_UploadFileResponse> getAllFiles()
	{
		return repo_UploadFileResponse.findAll();
	}
	public Entity_UploadFileResponse SaveFiles(Entity_UploadFileResponse data)
	{
		return repo_UploadFileResponse.save(data);
	}
	public List<Entity_UploadFileResponse> getByEmail(String  email)
	{
		return repo_UploadFileResponse.findByEmail(email);
	}
	
	public void  deleteById(int id)
	{
		 repo_UploadFileResponse.deleteById(id);
	}
	
	public List<Entity_UploadFileResponse> getAllFilesByName(String name,String type,String start,String end)
	{
		return repo_UploadFileResponse.findByUsernameAndFileTypeAndStartAndEnd(name, type, start, end);
	}
}

