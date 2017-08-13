package org.my.model;

import java.beans.Transient;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
		int tmp = (int) (this.payment * 100.0);
		this.payment = ((double) tmp) / 100.0;

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
		NumberFormat formatter = new DecimalFormat("#0.00");
		return date + ", " + place + ", " + hours + "h, £" + formatter.format(payment);
	}

}
