package org.my.controller;

import org.my.service.App;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController implements ErrorController{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("message", "qwer");
		return "welcome";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		model.addAttribute("rotaList", App.mainN());
		return "test";
	}

	@Override
	public String getErrorPath() {
		return "qwe";
	}

}