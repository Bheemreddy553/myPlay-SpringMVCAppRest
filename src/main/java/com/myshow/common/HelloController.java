package com.myshow.common;

import com.reddy.my_show.server.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

	@RequestMapping( method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "index";
	}


}