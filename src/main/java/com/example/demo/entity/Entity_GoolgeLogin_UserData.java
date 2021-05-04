	package com.example.demo.entity;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Entity_GoolgeLogin_UserData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String email,name,img_url,gender,date,branch;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "foreginKey_userid",nullable = false)
	private Entiy_GoogleLogin Entiy_GoogleLogin;
	

	
	public Entity_GoolgeLogin_UserData() {
	}
	

	
	public Entity_GoolgeLogin_UserData(String email, String name, String img_url, String gender, String date,
			String branch, com.example.demo.entity.Entiy_GoogleLogin entiy_GoogleLogin) {
		super();
		this.email = email;
		this.name = name;
		this.img_url = img_url;
		this.gender = gender;
		this.date = date;
		this.branch = branch;
		Entiy_GoogleLogin = entiy_GoogleLogin;
	}


	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Entiy_GoogleLogin getEntiy_GoogleLogin() {
		return Entiy_GoogleLogin;
	}

	public void setEntiy_GoogleLogin(Entiy_GoogleLogin entiy_GoogleLogin) {
		Entiy_GoogleLogin = entiy_GoogleLogin;
	}


	
}
