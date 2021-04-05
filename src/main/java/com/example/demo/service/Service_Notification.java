package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Entity_Notification;
import com.example.demo.entity.Entity_UploadFileResponse;
import com.example.demo.repo.repo_Notification;

@Service
public class Service_Notification {
		@Autowired
		private repo_Notification repo_Notification;
		
		public Entity_Notification SaveFiles(Entity_Notification data)
		{
			return repo_Notification.save(data);
		}
		public List<Entity_Notification> getByEmail(String  email)
		{
			return repo_Notification.findByEmail(email);
		}
}
