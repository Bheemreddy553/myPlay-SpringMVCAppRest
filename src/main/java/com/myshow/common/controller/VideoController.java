package com.myshow.common.controller;

import com.myshow.common.Result;
import com.reddy.my_show.server.model.UserDetails;
import com.reddy.my_show.server.model.Videos;
import com.reddy.my_show.server.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by varshini on 2/10/15.
 */
@Controller
@RequestMapping("/file")
public class VideoController {
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService){
        this.adminService =adminService;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(MultipartHttpServletRequest request){
        String result = null;
        try {
            System.out.println(request);

            //byte[] bytes = file.getBytes();

            Videos videos = new Videos();
            videos.setTitle("demo");
            UserDetails user = new UserDetails();
            user.setEmail("my@gmail.com");
            videos.setUserDetails(user);

           // adminService.uploadVideo(bytes, videos);
            result = "success";
        }
        catch (Exception e){
            e.printStackTrace();
            logger.info(" error "+e.toString() + e.getMessage() + e.getCause() + e.fillInStackTrace());
            result = "fail";
        }

        return result;
    }


}
