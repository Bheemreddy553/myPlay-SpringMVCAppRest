package com.myshow.common.controller;

import com.myshow.common.FileValidator;
import com.myshow.common.UploadedFile;
import com.reddy.my_show.common.MyShowException;
import com.reddy.my_show.server.model.UserDetails;
import com.reddy.my_show.server.service.AdminService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;


@Controller
@RequestMapping("uploadFile")
public class UploadController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UploadController.class);
  
 @Autowired
 FileValidator fileValidator;

 @Autowired
 private AdminService adminService;

 @Autowired
 public void setAdminService(AdminService adminService){
  this.adminService = adminService;
 }


 public void setFileValidator(FileValidator fileValidator){
  this.fileValidator= fileValidator;
 }

 @RequestMapping(method = RequestMethod.GET)
 public String getUploadForm(@ModelAttribute("uploadedFile") UploadedFile uploadedFile,BindingResult result) {
  return "uploadFile";
 }

 @RequestMapping(method = RequestMethod.POST)
 public ModelAndView fileUploaded(@ModelAttribute("uploadedFile") UploadedFile uploadedFile,BindingResult result,HttpServletRequest request) {

  ModelAndView modelAndView  = new ModelAndView();

  MultipartFile file = uploadedFile.getFile();
  fileValidator.validate(uploadedFile, result);  
  
  String fileName = file.getOriginalFilename();  
try {

    UserDetails userDetails = (UserDetails) request.getSession().getAttribute("user");

    if(userDetails == null){
        logger.info("user not login" );
        modelAndView.addObject("message","user not login");
        modelAndView.setViewName("uploadFile");
        return modelAndView;
    }
  adminService.uploadVideo(file,userDetails);
  modelAndView.addObject("message",fileName);
  modelAndView.setViewName("uploadFile");




/*
  if (result.hasErrors()) {  
   return new ModelAndView("uploadForm");  
  }  
  
  try {  */
  // inputStream = file.getInputStream();
  
   /*File newFile = new File("/home/varshini/srinu/"+ fileName);
   if (!newFile.exists()) {  
    newFile.createNewFile();
   }  
   outputStream = new FileOutputStream(newFile);
   int read = 0;  
   byte[] bytes = new byte[1024];
  
   while ((read = inputStream.read(bytes)) != -1) {
    outputStream.write(bytes, 0, read);  
   }

   Videos videos = new Videos();
   videos.setTitle("demo");
   UserDetails user = new UserDetails();
   user.setEmail("my@gmail.com");
   videos.setUserDetails(user);
   videos.setPath(newFile.getAbsolutePath());
   adminService.uploadVideo(videos);
*/
   System.out.println("success");
  } catch (IOException e) {
   // TODO Auto-generated catch block  
   e.printStackTrace();
  }
  catch (Exception e){
   e.printStackTrace();
   modelAndView.addObject("message",fileName);

  }
  
  return modelAndView;
 }  
  
}  