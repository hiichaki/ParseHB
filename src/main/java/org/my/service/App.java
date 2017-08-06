package org.my.service;

import java.util.ArrayList;

import org.my.model.DayRota;
import org.my.util.ParseUtil;
import org.my.util.StaticVars;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class App {

	public static ArrayList<DayRota> mainN() {
		
		StaticVars.RATE = getRate(7.05);

//		double[] a = { 11, 23, 9, 14.5, 9, 18.5, 9, 20.5, 9, 15, 9, 16, 9, 22, 9, 16, 8, 16, 9, 18, 8, 22, 9, 22, 9, 14, 8, 22, 12, 17, 9, 22, 10, 15, 9, 16, 9, 18 };
//		double[] start = new double[a.length / 2];
//		double[] end = new double[a.length / 2];
//
//		int startI = 0;
//		int endI = 0;
//		for (int i = 0; i < a.length; ++i) {
//			if (i % 2 == 0) {
//				start[startI] = a[i];
//				startI++;
//			} else {
//				end[endI] = a[i];
//				endI++;
//			}
//
//		}
	
		// System.out.println(getSalaryByAzaza());

		ParseUtil.submitForm();

		HtmlPage page = ParseUtil.getRotaPage();

		ArrayList<DayRota> rotaList = ParseUtil.getDayRota(page);

		RotaService.showRota(rotaList);

		RotaService.showPayment(rotaList);
		
//		double monthSalary = getSalary(start, end);
//		System.out.println("MONTH: £" + (monthSalary+monthSalary*0.2));
//		System.out.println("MONTH after taxes: £" + monthSalary);
		
		return rotaList;

	}

	private static double getRate(double rate) {
		return rate / 100 * 80;
	}

	private static double getSalary(double[] start, double[] end) {
		double salary = 0;
		double hours = 0;
		;

		for (int i = 0; i < start.length; ++i) {
			salary += getDaySalary(start[i], end[i]);
			hours += end[i] - start[i];
		}
		System.out.println("hours: " + hours);
		return salary;
	}

	private static double getSalaryByAzaza() {
		double salary = 0;
		salary = getDaySalary(11, 23) + getDaySalary(9, 13.5) + getDaySalary(9, 18.5) + getDaySalary(9, 20.5) + getDaySalary(9, 15);

		return salary;
	}

	private static double getDaySalary(double fromHour, double toHour) {
		return (toHour - fromHour) * StaticVars.RATE;
	}

}
