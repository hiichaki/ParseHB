package service;

import java.util.ArrayList;

import model.DayRota;
import util.FormatUtil;

public class RotaService {

	public static double getPayment(ArrayList<DayRota> rotaList) {
		double payment = 0;
		double hours = 0;
		for (DayRota dayRota : rotaList) {
			payment += dayRota.getPayment();
			hours+= dayRota.getHours();
		}
		System.out.println("hours:"+FormatUtil.format(hours));
		return FormatUtil.format(payment);
	}

	public static void showPayment(ArrayList<DayRota> rotaList) {
		System.out.println("\u00A3" + RotaService.getPayment(rotaList));
	}
	
	public static void showRota(ArrayList<DayRota> rotaList) {
		for(DayRota dayRota : rotaList) {
			System.out.println(dayRota);
		}
	}
}
