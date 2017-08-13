package org.my.service;

import java.util.ArrayList;

import org.my.model.DayRota;

public class RotaService {

	public static double getPayment(ArrayList<DayRota> rotaList) {
		double payment = 0;
		for (DayRota dayRota : rotaList) {
			payment += dayRota.getPayment();
		}
		return payment;
	}

	public static void showPayment(ArrayList<DayRota> rotaList) {
		System.out.println("£" + RotaService.getPayment(rotaList));
	}
	
	public static void showRota(ArrayList<DayRota> rotaList) {
		for(DayRota dayRota : rotaList) {
			System.out.println(dayRota);
		}
	}
	
	
	
}
