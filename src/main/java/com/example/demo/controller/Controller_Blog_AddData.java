package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Entity_Blog_AddData;
import com.example.demo.entity.Entity_GoolgeLogin_UserData;
import com.example.demo.entity.*;
import com.example.demo.service.Service_Blog_AddData;
import com.example.demo.service.Service_GoogleLogin_UserData;
import com.example.demo.service.Service_Leaves;

@Controller
public class Controller_Blog_AddData {
	@Autowired
	private Service_GoogleLogin_UserData Service_GoogleLogin_UserData;
	@Autowired
	private Service_Blog_AddData Service_Blog_AddData;
	@Autowired
	private FileStoragePojo FileStoragePojo;
	String email,branch,designation,name,userbranch;
	
	@RequestMapping("/Blog_AddData")
	public ModelAndView goTo_Blog_AddData(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException
	{	
		String email =httpServletRequest.getParameter("email");
		List<Entity_GoolgeLogin_UserData> storeDataInListFormat = Service_GoogleLogin_UserData.getUserByEmail(email);
    	for(Entity_GoolgeLogin_UserData getdata : storeDataInListFormat)
    	{
    		 email =  getdata.getEmail();
    		 userbranch = getdata.getEntiy_GoogleLogin().getBranch();
    		 designation = getdata.getEntiy_GoogleLogin().getDesignation();
    		 name =  getdata.getName();
    	}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("email", email);
		modelAndView.addObject("branch", userbranch);
		modelAndView.addObject("designation", designation);
		modelAndView.addObject("name", name);
        modelAndView.setViewName("Blog_AddData.jsp");
        return modelAndView;
	}	
	
	@PostMapping(value="/SaveBlogData")
	public ModelAndView SaveUserProfile(@RequestParam("imageFile") MultipartFile  image,@RequestParam("documentFile") MultipartFile  document,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException
	{
		 
		String subject =httpServletRequest.getParameter("Subject");
		String name =httpServletRequest.getParameter("name");
		String email =httpServletRequest.getParameter("email");
		String designation =httpServletRequest.getParameter("designation");
		String branch =httpServletRequest.getParameter("SendTo");
		String description =httpServletRequest.getParameter("Description");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		 
		  
		  String imgname = org.springframework.util.StringUtils.cleanPath(image.getOriginalFilename());
	      String uploadDir = FileStoragePojo.getUploadDir() ;
		  Path uploadPath = Paths.get(uploadDir);
	  	  if(imgname.length()!=0)
	  	  {
		       if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		       }
		        try (InputStream inputStream = image.getInputStream()) {
		            Path filePath = uploadPath.resolve(imgname);
		            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		            System.out.println("upload"+imgname);
		        } catch (IOException ioe) {        
		            throw new IOException("Could not save image file: " + imgname, ioe);
		        }  
	  	  } 
	  	  
	  	String documentFile = org.springframework.util.StringUtils.cleanPath(document.getOriginalFilename());
	  	if(documentFile.length()!=0)
	  	{
	  		Service_Blog_AddData.uploadFile(document);
	  	}
		Entity_Blog_AddData  data =new Entity_Blog_AddData(name, email, designation, branch, subject, description, imgname, documentFile, dtf.format(now));
		Service_Blog_AddData.save(data);
		
		ModelAndView modelAndView = new ModelAndView();
		List<Entity_Blog_AddData>  Entity_Blog_AddData = Service_Blog_AddData.getBlogs();
		modelAndView.addObject("email", email);
		modelAndView.addObject("UserBranch", userbranch);
		modelAndView.addObject("Entity_Blog_AddData", Entity_Blog_AddData);
		modelAndView.setViewName("Blog.jsp");
        return modelAndView;
	}
}
