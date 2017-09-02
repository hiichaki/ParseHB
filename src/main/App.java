package main;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import model.DayRota;
import model.Month;
import service.RotaService;
import util.ParseUtil;
import util.StaticVars;

public class App {

	public static void main(String[] args) {

		StaticVars.RATE = getRate(7.75);

		ParseUtil.submitForm();
		HtmlPage page;
		switch (args.length) {
		case 0:
			System.out.println("0");
			page = ParseUtil.getRotaPage();
			break;
		case 1:
			System.out.println("1");
			// month
			page = ParseUtil.getRotaPage(Month.getMonthByName(args[0]));
			break;
		case 3:
			System.out.println("3");
			// month, dayFrom, dayTo
			page = ParseUtil.getRotaPage(Month.getMonthByName(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			break;
		case 4:
			System.out.println("4");
			// monthFrom, dayFrom, monthTo, dayTo
			page = ParseUtil.getRotaPage(Month.getMonthByName(args[0]), Integer.parseInt(args[1]), Month.getMonthByName(args[2]), Integer.parseInt(args[3]));
			break;
		default:
			System.out.println("default");
			page = ParseUtil.getRotaPage();
			break;
		}

		ArrayList<DayRota> rotaList = ParseUtil.getDayRota(page);

		RotaService.showRota(rotaList);
		RotaService.showPayment(rotaList);

	}

	private static double getRate(double rate) {
		return rate / 100 * 80;
	}

}
