package main.test;

import java.util.ArrayList;

import org.junit.Test;
import org.my.model.DayRota;
import org.my.service.RotaService;
import org.my.util.ParseUtil;
import org.my.util.StaticVars;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MainTest {

	@Test
	public void checkPage() {

		StaticVars.RATE = 5.64;
		String email = "alexandruhapco@gmail.com";

		HtmlPage homePage = ParseUtil.submitForm(email);
		
		System.out.println("start");
		ParseUtil.getIdByEmail(homePage, email);
		System.out.println("end");

//		HtmlPage page = ParseUtil.getRotaPage();
//		
//		ArrayList<DayRota> rotaList = ParseUtil.getDayRota(page);
//
//		RotaService.showRota(rotaList);
//
//		RotaService.showPayment(rotaList);


	}

}
