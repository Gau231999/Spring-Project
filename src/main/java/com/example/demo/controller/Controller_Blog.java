package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Entity_Blog_AddData;
import com.example.demo.entity.Entity_GoolgeLogin_UserData;
import com.example.demo.entity.Entiy_GoogleLogin;
import com.example.demo.service.Service_Blog_AddData;
import com.example.demo.service.Service_GoogleLogin;
import com.example.demo.service.Service_GoogleLogin_UserData;

@RestController
public class Controller_Blog {
	@Autowired
	private Service_Blog_AddData Service_Blog_AddData;
	@Autowired
	private Service_GoogleLogin Service_GoogleLogin;
	 String UserBranch;
	@RequestMapping("/Blog")
	public ModelAndView goTobblog(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException
	{	
		
		String email =httpServletRequest.getParameter("email");
		List<Entiy_GoogleLogin>  Entity_user_Data = Service_GoogleLogin.getUserByEmailID(email);
		for(Entiy_GoogleLogin Entiy_GoogleLogin :Entity_user_Data)
		{
			 UserBranch = Entiy_GoogleLogin.getBranch();
		}
		List<Entity_Blog_AddData>  Entity_Blog_AddData = Service_Blog_AddData.getBlogs();
		List<Entity_Blog_AddData>  NoOfPrinciple = Service_Blog_AddData.getByDesignation("Principle");
		List<Entity_Blog_AddData>  NoOfHod = Service_Blog_AddData.getByDesignation("HOD");
		List<Entity_Blog_AddData>  NoOfTeacher = Service_Blog_AddData.getByDesignation("Employee");
		

		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("Entity_Blog_AddData", Entity_Blog_AddData);
    	modelAndView.addObject("NoOfPrinciple", NoOfPrinciple.size());
    	modelAndView.addObject("NoOfHod", NoOfHod.size());
    	modelAndView.addObject("NoOfTeacher", NoOfTeacher.size());
    	modelAndView.addObject("UserBranch", UserBranch);
    	modelAndView.addObject("email", email);
        modelAndView.setViewName("Blog.jsp");
        return modelAndView;
	}	
	@RequestMapping("/Blog_Delete")
	public ModelAndView blodDelete(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException
	{	
		
		String email =httpServletRequest.getParameter("email");
		String id =httpServletRequest.getParameter("id");
		int Id = Integer.parseInt(id);
		System.out.println("delete  :  "+email);
		Service_Blog_AddData.deleteUser(Id);
		List<Entiy_GoogleLogin>  Entity_user_Data = Service_GoogleLogin.getUserByEmailID(email);
		for(Entiy_GoogleLogin Entiy_GoogleLogin :Entity_user_Data)
		{
			 UserBranch = Entiy_GoogleLogin.getBranch();
		}
		List<Entity_Blog_AddData>  Entity_Blog_AddData = Service_Blog_AddData.getBlogs();
		List<Entity_Blog_AddData>  NoOfPrinciple = Service_Blog_AddData.getByDesignation("Principle");
		List<Entity_Blog_AddData>  NoOfHod = Service_Blog_AddData.getByDesignation("HOD");
		List<Entity_Blog_AddData>  NoOfTeacher = Service_Blog_AddData.getByDesignation("Teacher");
		
		
		
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("Entity_Blog_AddData", Entity_Blog_AddData);
    	modelAndView.addObject("NoOfPrinciple", NoOfPrinciple.size());
    	modelAndView.addObject("NoOfHod", NoOfHod.size());
    	modelAndView.addObject("NoOfTeacher", NoOfTeacher.size());
    	modelAndView.addObject("UserBranch", UserBranch);
    	modelAndView.addObject("email", email);
        modelAndView.setViewName("Blog.jsp");
        return modelAndView;
	}	
	@RequestMapping("/Blog_ByName")
	public ModelAndView blogByName(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException
	{	
		
		String email =httpServletRequest.getParameter("email");
		String name =httpServletRequest.getParameter("name");
		
		List<Entiy_GoogleLogin>  Entity_user_Data = Service_GoogleLogin.getUserByEmailID(email);
		for(Entiy_GoogleLogin Entiy_GoogleLogin :Entity_user_Data)
		{
			 UserBranch = Entiy_GoogleLogin.getBranch();
		}
		List<Entity_Blog_AddData>  Entity_Blog_AddData = Service_Blog_AddData.getByName(name);
		List<Entity_Blog_AddData>  NoOfPrinciple = Service_Blog_AddData.getByDesignation("Principle");
		List<Entity_Blog_AddData>  NoOfHod = Service_Blog_AddData.getByDesignation("HOD");
		List<Entity_Blog_AddData>  NoOfTeacher = Service_Blog_AddData.getByDesignation("Teacher");
		
		
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("Entity_Blog_AddData", Entity_Blog_AddData);
    	modelAndView.addObject("NoOfPrinciple", NoOfPrinciple.size());
    	modelAndView.addObject("NoOfHod", NoOfHod.size());
    	modelAndView.addObject("NoOfTeacher", NoOfTeacher.size());
    	modelAndView.addObject("UserBranch", UserBranch);
    	modelAndView.addObject("email", email);
        modelAndView.setViewName("Blog.jsp");
        return modelAndView;
	}
}
