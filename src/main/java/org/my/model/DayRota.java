package org.my.model;

import java.beans.Transient;

import org.my.util.StaticVars;

public class DayRota {

	private String date;
	private String place;
	private double hours;
	private double payment;

	public DayRota(String date, String place, double hours) {
		this.date = date;
		this.place = place;
		this.hours = hours;
		this.payment = hours * StaticVars.RATE;
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

	@Transient
	public double getBreak() {
		return hours * 7;
	}

	@Override
	public String toString() {
		return date + ", " + place + ", " + hours + "h, £" + payment;
	}

}
