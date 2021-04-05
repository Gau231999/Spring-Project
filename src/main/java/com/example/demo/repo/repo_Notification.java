package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Entity_Notification;
import com.example.demo.entity.Entity_UploadFileResponse;

public interface repo_Notification extends JpaRepository<Entity_Notification, Integer> {
	public List<Entity_Notification>findByEmail(String email);
}
