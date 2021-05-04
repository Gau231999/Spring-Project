package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Entity_Blog_AddData;
import com.example.demo.entity.Entity_GoolgeLogin_UserData;
import com.example.demo.entity.Entity_UploadFileResponse;
import com.example.demo.entity.Entiy_GoogleLogin;
import com.example.demo.service.Service_Documents;
import com.example.demo.service.Service_GoogleLogin;
import com.example.demo.service.Service_GoogleLogin_UserData;
import com.example.demo.service.Service_UploadFileResponse;



@RestController
public class Controller_Documents {
	
	@Autowired
    private Service_Documents fileStorageService;
	@Autowired
	private Service_UploadFileResponse Service_UploadFileResponse;
	@Autowired
	private Service_GoogleLogin_UserData Service_GoogleLogin_UserData;
	public String email,name,Designation,Branch;
	@RequestMapping("/Documents")
	public ModelAndView goToDocs(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException
	{	
		
	    email =httpServletRequest.getParameter("email");
		List<Entity_GoolgeLogin_UserData>  Data = Service_GoogleLogin_UserData.getUserByEmail(email);
		  for(Entity_GoolgeLogin_UserData Entity_GoolgeLogin_UserData:Data)
		  {
			  Designation =Entity_GoolgeLogin_UserData.getEntiy_GoogleLogin().getDesignation();
			  Branch =Entity_GoolgeLogin_UserData.getBranch();
			  	
		  }
		List<Entity_GoolgeLogin_UserData>  BranchData = Service_GoogleLogin_UserData.getUserByBranch(Branch);
		List<Entity_GoolgeLogin_UserData>  BranchDataAll = Service_GoogleLogin_UserData.getusers();
	    List<Entity_UploadFileResponse>  Entity_UploadFileResponse = Service_UploadFileResponse.getByEmail(email);
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("email", email);
    	modelAndView.addObject("Designation", Designation);
    	if(Designation.equals("Principle")){
    		modelAndView.addObject("Entity_GoolgeLogin_UserData", BranchDataAll);
    	}
    	else {
    		modelAndView.addObject("Entity_GoolgeLogin_UserData", BranchData);
    	}
    	
    	modelAndView.addObject("Entity_UploadFileResponse", Entity_UploadFileResponse);
        modelAndView.setViewName("Document.jsp");
        return modelAndView;
	}	
	@RequestMapping("/Search")
	public ModelAndView getDocs(HttpServletRequest httpServletRequest ) 
	{	
		
	    email =httpServletRequest.getParameter("email");
	    String empname =httpServletRequest.getParameter("empname");
	    String type =httpServletRequest.getParameter("type");
	    String start =httpServletRequest.getParameter("start");
	    String end =httpServletRequest.getParameter("end");
	    System.out.println(empname);
	    System.out.println(type);
	    System.out.println(start);
	    System.out.println(end);
	    System.out.println(email);
	    
		List<Entity_GoolgeLogin_UserData>  Data = Service_GoogleLogin_UserData.getUserByEmail(email);
		  for(Entity_GoolgeLogin_UserData Entity_GoolgeLogin_UserData:Data)
		  {
			  Designation =Entity_GoolgeLogin_UserData.getEntiy_GoogleLogin().getDesignation();
			  Branch =Entity_GoolgeLogin_UserData.getBranch();
			  	
		  }
		List<Entity_GoolgeLogin_UserData>  BranchData = Service_GoogleLogin_UserData.getUserByBranch(Branch);
		List<Entity_GoolgeLogin_UserData>  BranchDataAll = Service_GoogleLogin_UserData.getusers();
	    List<Entity_UploadFileResponse>  Entity_UploadFileResponse = Service_UploadFileResponse.getAllFilesByName(empname, type, start, end);
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("email", email);
    	modelAndView.addObject("Designation", Designation);
    	if(Designation.equals("Principle")){
    		modelAndView.addObject("Entity_GoolgeLogin_UserData", BranchDataAll);
    	}
    	else {
    		modelAndView.addObject("Entity_GoolgeLogin_UserData", BranchData);
    	}
    	modelAndView.addObject("Entity_UploadFileResponse", Entity_UploadFileResponse);
        modelAndView.setViewName("Document.jsp");
        return modelAndView;
	}
	  @PostMapping("/uploadsinglefile")
	    public ModelAndView uploadSingleFile(@RequestParam("file") MultipartFile file,HttpServletRequest httpServletRequest) {
		  List<Entity_GoolgeLogin_UserData>  Data = Service_GoogleLogin_UserData.getUserByEmail(email);
		  for(Entity_GoolgeLogin_UserData Entity_GoolgeLogin_UserData:Data)
		  {
			  name =Entity_GoolgeLogin_UserData.getName();
		  }
		  List<Entity_GoolgeLogin_UserData>  Data1 = Service_GoogleLogin_UserData.getUserByEmail(email);
			  for(Entity_GoolgeLogin_UserData Entity_GoolgeLogin_UserData:Data1)
			  {
				  Designation =Entity_GoolgeLogin_UserData.getEntiy_GoogleLogin().getDesignation();
				  Branch =Entity_GoolgeLogin_UserData.getBranch();
				  
			  }
		  List<Entity_GoolgeLogin_UserData>  BranchData = Service_GoogleLogin_UserData.getUserByBranch(Branch); 
		  List<Entity_GoolgeLogin_UserData>  BranchDataAll = Service_GoogleLogin_UserData.getusers();
		  String type =httpServletRequest.getParameter("type");
		  String start =httpServletRequest.getParameter("start");
		  String end =httpServletRequest.getParameter("end");
		  String fileName = fileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/download-file/")
	                .path(fileName)
	                .toUriString();
	        
	        Entity_UploadFileResponse Entity_UploadFileResponse = new Entity_UploadFileResponse(fileName, fileDownloadUri, type, email, start, end, name,file.getSize());
	        Service_UploadFileResponse.SaveFiles(Entity_UploadFileResponse);
	        
		    List<Entity_UploadFileResponse>  Entity_UploadFileResponses = Service_UploadFileResponse.getByEmail(email);
			ModelAndView modelAndView = new ModelAndView();
	    	modelAndView.addObject("email", email);
	    	modelAndView.addObject("Designation", Designation);
	    	if(Designation.equals("Principle")){
	    		modelAndView.addObject("Entity_GoolgeLogin_UserData", BranchDataAll);
	    	}
	    	else {
	    		modelAndView.addObject("Entity_GoolgeLogin_UserData", BranchData);
	    	}
	    	modelAndView.addObject("Entity_UploadFileResponse", Entity_UploadFileResponses);
	        modelAndView.setViewName("Document.jsp");
	        return modelAndView;
	        
	    }
	 
	  @GetMapping("/download-file")
	    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		  System.out.println("-----------------download---------------");   
		  email =httpServletRequest.getParameter("email");
		  String fileName =httpServletRequest.getParameter("fileName");
		  // Load file as Resource
	        org.springframework.core.io.Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            System.out.print("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
}
