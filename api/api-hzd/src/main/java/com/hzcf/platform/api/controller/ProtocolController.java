package com.hzcf.platform.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProtocolController {
	
	@RequestMapping(value="api/100/register/protocol")
	public ModelAndView showAutoDebitProtocol(HttpServletRequest request, HttpServletResponse response, Model model
			) {
		ModelAndView mv = new ModelAndView("/about");
		return mv;
	}

}
