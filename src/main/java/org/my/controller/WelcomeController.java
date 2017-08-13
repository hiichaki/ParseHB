package org.my.controller;

import java.util.ArrayList;

import org.my.model.DayRota;
import org.my.service.App;
import org.my.service.RotaService;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String testGet(@RequestParam("l") String login, @RequestParam("r") String rate, Model model) {
		ArrayList<DayRota> rotaList = App.getRota(login,rate);
		model.addAttribute("rotaList", rotaList);
		model.addAttribute("total", RotaService.getPayment(rotaList));
		return "test";
	}

}