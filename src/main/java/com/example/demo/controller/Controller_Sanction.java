package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Entity_GoolgeLogin_UserData;
import com.example.demo.entity.Entity_Leave_Request;
import com.example.demo.entity.Entity_Leaves;
import com.example.demo.entity.Entity_Notification;
import com.example.demo.service.Service_GoogleLogin_UserData;
import com.example.demo.service.Service_Leaves;
import com.example.demo.service.Service_Leaves_Request;
import com.example.demo.service.Service_Notification;

@Controller
public class Controller_Sanction {
	@Autowired 
	public Service_Leaves_Request Service_Leaves_Request;
	@Autowired 
	public Service_Leaves Service_Leaves;
	@Autowired 
	public Service_GoogleLogin_UserData Service_GoogleLogin_UserData; 
	@Autowired
	public  Service_Notification Service_Notification;
	
	String email,designation,branch,reqemail;
	String approveByHOD ="your leave request has been  approve by HOD";
	String rejectByHOD ="your leave request has been  reject by HOD";
	String approveByPrinciple ="your leave request has been  approve by Principle";
	String rejectByPrinciple ="your leave request has been  reject by Principle";
	
	@GetMapping("/Sanction")
	public ModelAndView gotopage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		email =httpServletRequest.getParameter("email");
		reqemail =httpServletRequest.getParameter("reqemail");
		List<Entity_GoolgeLogin_UserData> userbrach = Service_GoogleLogin_UserData.getUserByEmail(email);
		
		String leavetype =httpServletRequest.getParameter("type");
		Entity_Leaves data = Service_Leaves.getByEmail(email);
		
		for(Entity_GoolgeLogin_UserData Entity_GoolgeLogin_UserData :userbrach)
		{
			branch =Entity_GoolgeLogin_UserData.getEntiy_GoogleLogin().getBranch();
			designation=Entity_GoolgeLogin_UserData.getEntiy_GoogleLogin().getDesignation();
		}
		if(designation.equals("HOD"))
		{
			List<Entity_Leave_Request> leaves = Service_Leaves_Request.getByReceiver(designation, branch);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("leaves", leaves);
			modelAndView.addObject("email", email);
			modelAndView.setViewName("Sanction_LeaveRequest .jsp");
			return modelAndView;
		}
		else {
			List<Entity_Leave_Request> leaves = Service_Leaves_Request.getPrincipleRequest(designation);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("leaves", leaves);
			modelAndView.addObject("email", email);
			modelAndView.setViewName("Sanction_LeaveRequest .jsp");
			return modelAndView;
		}
		
	}
	@GetMapping("/Approve")
	public ModelAndView approve(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		String  id = httpServletRequest.getParameter("reqid");
		int Id=Integer.parseInt(id);
		reqemail =httpServletRequest.getParameter("reqemail");
		if(designation.equals("Principle"))
		{
			Entity_Leave_Request Entity_Leave_Request =Service_Leaves_Request.getById(Id);
			String  leavetype = Entity_Leave_Request.getType();
			Entity_Leaves  Entity_Leaves = Service_Leaves.getByEmail(Entity_Leave_Request.getEmail());
			if(leavetype.equals("Casual"))
			{
				int databaseleave=Entity_Leaves.getCasualLeave();
				int nod = Entity_Leave_Request.getNod();
				int newleave = databaseleave-nod;
				Entity_Leaves.setCasualLeave(newleave);
				Service_Leaves.setLeaves(Entity_Leaves);
			}
			else if(leavetype.equals("Medical")){
				int databaseleave=Entity_Leaves.getMedicalLeave();
				int nod = Entity_Leave_Request.getNod();
				int newleave = databaseleave-nod;
				Entity_Leaves.setMedicalLeave(newleave);
				Service_Leaves.setLeaves(Entity_Leaves);
			}
			else if(leavetype.equals("Sick")){
				int databaseleave=Entity_Leaves.getSickLeave();
				int nod = Entity_Leave_Request.getNod();
				int newleave = databaseleave-nod;
				Entity_Leaves.setSickLeave(newleave);
				Service_Leaves.setLeaves(Entity_Leaves);
			}
			else if(leavetype.equals("Earned")){
				int databaseleave=Entity_Leaves.getEarnedLeave();
				int nod = Entity_Leave_Request.getNod();
				int newleave = databaseleave-nod;
				Entity_Leaves.setEarnedLeave(newleave);
				Service_Leaves.setLeaves(Entity_Leaves);
			}
			else {
				int databaseleave=Entity_Leaves.getOutdoorDuty();
				int nod = Entity_Leave_Request.getNod();
				int newleave = databaseleave-nod;
				Entity_Leaves.setOutdoorDuty(newleave);
				Service_Leaves.setLeaves(Entity_Leaves);
			}
			Service_Leaves_Request.deleteRequest(Id);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDateTime now = LocalDateTime.now();  
			Entity_Notification  entity_Notification = new Entity_Notification(reqemail, approveByPrinciple, dtf.format(now));
			Service_Notification.SaveFiles(entity_Notification)	;
			
			List<Entity_Leave_Request> leaves = Service_Leaves_Request.getPrincipleRequest(designation);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("leaves", leaves);
			modelAndView.addObject("email", email);
			modelAndView.setViewName("Sanction_LeaveRequest .jsp");
			return modelAndView;
		}
		else
		{
				Entity_Leave_Request data = Service_Leaves_Request.getById(Id);
					data.setToemp("Principle");
					Service_Leaves_Request.saveRequest(data);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
				LocalDateTime now = LocalDateTime.now();  
				Entity_Notification  entity_Notification = new Entity_Notification(reqemail, approveByHOD, dtf.format(now));
				Service_Notification.SaveFiles(entity_Notification)	;
				
				List<Entity_Leave_Request> leaves = Service_Leaves_Request.getByReceiver(designation, branch);
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.addObject("leaves", leaves);
				modelAndView.addObject("email", email);
				modelAndView.setViewName("Sanction_LeaveRequest .jsp");
				return modelAndView;
		}

		
	}
	@GetMapping("/Reject")
	public ModelAndView reject(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		String  id = httpServletRequest.getParameter("reqid");
		int Id=Integer.parseInt(id);
		Service_Leaves_Request.deleteRequest(Id);
		reqemail =httpServletRequest.getParameter("reqemail");
		if(designation.equals("HOD"))
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDateTime now = LocalDateTime.now();  
			Entity_Notification  entity_Notification = new Entity_Notification(reqemail, rejectByHOD, dtf.format(now));
			Service_Notification.SaveFiles(entity_Notification)	;
			
			List<Entity_Leave_Request> leaves = Service_Leaves_Request.getByReceiver(designation, branch);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("leaves", leaves);
			modelAndView.setViewName("Sanction_LeaveRequest .jsp");
			return modelAndView;
		}
		else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDateTime now = LocalDateTime.now();  
			Entity_Notification  entity_Notification = new Entity_Notification(reqemail, rejectByPrinciple, dtf.format(now));
			Service_Notification.SaveFiles(entity_Notification)	;
			
			List<Entity_Leave_Request> leaves = Service_Leaves_Request.getPrincipleRequest(designation);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("leaves", leaves);
			modelAndView.addObject("email", email);
			modelAndView.setViewName("Sanction_LeaveRequest .jsp");
			return modelAndView;
		}
		
	}
}

