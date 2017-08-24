package main;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import model.DayRota;
import service.RotaService;
import util.ParseUtil;
import util.StaticVars;

public class App {

	public static void main(String[] args) {

		StaticVars.RATE = getRate(7.75);

		ParseUtil.submitForm();

		HtmlPage page = ParseUtil.getRotaPage(1);

		ArrayList<DayRota> rotaList = ParseUtil.getDayRota(page);

		RotaService.showRota(rotaList);
		RotaService.showPayment(rotaList);

	}

	private static double getRate(double rate) {
		return rate / 100 * 80;
	}

}
