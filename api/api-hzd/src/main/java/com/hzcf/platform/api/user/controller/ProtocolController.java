package com.hzcf.platform.api.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProtocolController {
	
	@RequestMapping(value="api/100/register/protocol")
	public Object showAutoDebitProtocol(HttpServletRequest request, HttpServletResponse response, Model model
			) {
		ModelAndView mv = new ModelAndView("about.html");
		return mv;
	}

}
