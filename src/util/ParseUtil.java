package util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import model.DayRota;
import model.Month;

public class ParseUtil {

	private static final WebClient WEB_CLIENT = new WebClient(BrowserVersion.CHROME);

	public static void submitForm() {
		try {

			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

			WEB_CLIENT.getOptions().setThrowExceptionOnFailingStatusCode(false);
			WEB_CLIENT.getOptions().setThrowExceptionOnScriptError(false);
			WEB_CLIENT.getOptions().setJavaScriptEnabled(true);
			WEB_CLIENT.getOptions().setDoNotTrackEnabled(true);

			final HtmlPage page = WEB_CLIENT.getPage(StaticVars.SITE);

			final HtmlForm form = (HtmlForm) page.getByXPath("/html/body/form").get(0);

			final HtmlInput button = (HtmlInput) form.getByXPath("/html/body/form/input").get(1);

			final HtmlTextInput loginField = form.getInputByName("email");
			final HtmlPasswordInput passField = form.getInputByName("password");

			loginField.setValueAttribute(StaticVars.LOGIN);
			passField.setValueAttribute(StaticVars.PASS);

			button.click();

			System.out.println("login");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("failed to submit");
		}

	}

	/**
	 * Get Rota for current week
	 * 
	 * @return current page with rota
	 */
	public static HtmlPage getRotaPage() {
		try {
			return WEB_CLIENT.getPage(new URL(StaticVars.ROTA_URL));
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get Rota page for the whole month
	 * 
	 * @param month
	 * @return Rota page by month
	 */
	public static HtmlPage getRotaPage(Month month) {
		return getRotaPage(month, 1, month, 31);
	}
	
	
	/**
	 * Get Rota page for exact month by dates 
	 * @param month
	 * @param dayFrom
	 * @param dayTo
	 * @return Rota page by exact month and dates
	 */
	public static HtmlPage getRotaPage(Month month, int dayFrom, int dayTo) {
		return getRotaPage(month, dayFrom, month, dayTo);
	}

	/**
	 * 
	 * @param monthFrom
	 * @param dayFrom
	 * @param monthTo
	 * @param dayTo
	 * @return Rota page by provided dates
	 */
	public static HtmlPage getRotaPage(Month monthFrom, int dayFrom, Month monthTo, int dayTo) {

		try {
			final HtmlPage page = WEB_CLIENT.getPage(StaticVars.ROTA_URL);

			setMonthSelect(page, monthFrom, "view_from_Month_ID");

			setDaySelect(page, dayFrom, "view_from_Day_ID");

			setMonthSelect(page, monthTo, "view_to_Month_ID");

			setDaySelect(page, dayTo, "view_to_Day_ID");

			setYear(page, StaticVars.CURRENT_YEAR);

			final HtmlInput button = (HtmlInput) page.getByXPath("/html/body/form/table/tbody/tr/td/input").get(2);

			HtmlPage returnPage = button.click();

			return returnPage;

		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void setYear(final HtmlPage page, String year) {
		setYearInput(page, year, "view_from_Year_ID");
		setYearInput(page, year, "view_to_Year_ID");
	}

	private static void setYearInput(final HtmlPage page, String year, String id) {
		HtmlTextInput yearInput = (HtmlTextInput) page.getElementById(id);
		yearInput.setValueAttribute(year);
	}

	private static void setDaySelect(final HtmlPage page, int day, String id) {
		HtmlSelect dayFromSelect = (HtmlSelect) page.getElementById(id);
		while (true) {
			try {
				HtmlOption option = dayFromSelect.getOptionByValue(day + "");
				dayFromSelect.setSelectedAttribute(option, true);
				break;
			} catch (ElementNotFoundException ex) {
				day--;
			}

		}
	}

	private static void setMonthSelect(final HtmlPage page, Month month, String id) {
		HtmlSelect monthFromSelect = (HtmlSelect) page.getElementById(id);
		HtmlOption option = monthFromSelect.getOptionByValue(month.getValue());
		monthFromSelect.setSelectedAttribute(option, true);
	}

	/**
	 * Parsing HTML page to create a list of Rota
	 * 
	 * @param page
	 *            HTML page with Rota
	 * @return list of DayRota
	 */
	public static ArrayList<DayRota> getDayRota(HtmlPage page) {
		HtmlTableBody table = (HtmlTableBody) page.getByXPath("/html/body/table/tbody").get(1);
		ArrayList<DayRota> dayList = new ArrayList<DayRota>();
		Iterable<DomElement> rowList = table.getChildElements();
		rowList.iterator().next();
		for (DomElement row : rowList) {
			String date = row.getFirstChild().asText();
			for (DomElement cell : row.getChildElements()) {
				if (cell.hasAttribute("colspan")) {
					double hours = Double.parseDouble(cell.getAttribute("colspan")) / 2;
					DayRota dayRota = new DayRota(date, cell.asText(), hours);
					dayList.add(dayRota);
				}
			}
		}
		return dayList;

	}

}
