package model;

import util.StaticVars;

public class DayRota {

	private String date;
	private String place;
	private double hours;
	private double payment;

	public DayRota(String date, String place, double hours) {
		this.date = date;
		this.place = place.replaceAll("[0-9]", "").trim();
		this.hours = hours;
		int tmp = (int) (hours * StaticVars.RATE * 100);
		this.payment = tmp / 100.00;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return date + ", " + place + ", " + hours + "h, " + "\u00A34" + +payment;
	}

}
