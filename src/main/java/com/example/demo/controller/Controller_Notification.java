package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Entity_Notification;
import com.example.demo.service.Service_Notification;

@Controller
public class Controller_Notification {
	@Autowired
	private Service_Notification Service_Notification;
	@GetMapping("/Notification")
	public ModelAndView gotoNotifiavtion(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		String email =httpServletRequest.getParameter("email");
		List<Entity_Notification>  data =Service_Notification.getByEmail(email);
		for(Entity_Notification Entity_Notification:data)
		{
			System.out.println(Entity_Notification.getId());
		}
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("email", email);
    	modelAndView.addObject("Entity_Notification", data);
    	modelAndView.setViewName("Notification.jsp");
		return modelAndView;
		
	}
}

