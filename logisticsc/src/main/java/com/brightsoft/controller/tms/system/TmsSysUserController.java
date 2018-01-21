package com.brightsoft.controller.tms.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tms/sys/user")
public class TmsSysUserController {

	@RequestMapping("/list")
	public String toListUser(){
		return "/tms/system/listuserinfo";
	}
}
