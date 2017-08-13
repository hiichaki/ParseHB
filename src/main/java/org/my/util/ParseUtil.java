package org.my.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.my.model.DayRota;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import ch.qos.logback.core.net.SyslogOutputStream;

public class ParseUtil {

	private static final WebClient WEB_CLIENT = new WebClient(BrowserVersion.CHROME);
	private static String REQUEST_URL = null;

	public static String getRequestURL() {
		return REQUEST_URL;
	}

	private static HtmlPage submitFormQ(String login) {
		try {

			WEB_CLIENT.getOptions().setThrowExceptionOnFailingStatusCode(false);
			WEB_CLIENT.getOptions().setThrowExceptionOnScriptError(false);
			WEB_CLIENT.getOptions().setJavaScriptEnabled(true);
			WEB_CLIENT.getOptions().setDoNotTrackEnabled(true);

			final HtmlPage page = WEB_CLIENT.getPage(StaticVars.SITE);

			final HtmlForm form = (HtmlForm) page.getByXPath("/html/body/form").get(0);

			final HtmlInput button = (HtmlInput) form.getByXPath("/html/body/form/input").get(1);

			final HtmlTextInput loginField = form.getInputByName("email");
			final HtmlPasswordInput passField = form.getInputByName("password");

			loginField.setValueAttribute(login);
			passField.setValueAttribute(StaticVars.PASS);

			HtmlPage homePage = button.click();

			System.out.println("login");

			return homePage;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("failed to submit");
			return null;
		}

	}

	public static HtmlPage submitForm(String login) {
		return submitFormQ(login);
	}

	public static HtmlPage submitForm() {
		return submitFormQ(StaticVars.LOGIN);
	}

	private static HtmlPage getRotaPageQ(int id) {
		try {
			return WEB_CLIENT.getPage(new URL(StaticVars.ROTA_URL+id));
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HtmlPage getRotaPage() {
		return getRotaPageQ(528);
	}
	
	public static HtmlPage getRotaPage(int id) {
		return getRotaPageQ(id);
	}

	public static HtmlPage getRotaPage(int monthF, int dayF, int yearF, int monthT, int dayT, int yearT) {
		try {
			final HtmlPage page = WEB_CLIENT.getPage(StaticVars.ROTA_URL);
			List<HtmlOption> options;

			HtmlSelect monthFrom = (HtmlSelect) page.getElementById("view_from_Month_ID");
			options = monthFrom.getOptions();
			monthFrom.setSelectedAttribute(options.get(2), true);
			options.forEach(opt -> System.out.println(opt.asXml()));

			HtmlSelect dayFrom = (HtmlSelect) page.getElementById("view_from_Day_ID");
			options = dayFrom.getOptions();
			dayFrom.setSelectedAttribute(options.get(0), true);

			HtmlTextInput yearFrom = (HtmlTextInput) page.getElementById("view_from_Year_ID");
			yearFrom.setValueAttribute("2017");

			HtmlSelect monthTo = (HtmlSelect) page.getElementById("view_to_Month_ID");
			options = monthTo.getOptions();
			monthTo.setSelectedAttribute(options.get(0), true);

			HtmlSelect dayTo = (HtmlSelect) page.getElementById("view_to_Day_ID");
			options = dayTo.getOptions();
			dayTo.setSelectedAttribute(options.get(0), true);

			HtmlTextInput yearTo = (HtmlTextInput) page.getElementById("view_to_Year_ID");
			yearTo.setValueAttribute("2017");

			final HtmlInput button = (HtmlInput) page.getByXPath("/html/body/form/input").get(0);

			return button.click();

		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

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

	public static int getIdByEmail(HtmlPage homePage, String email) {
		String text = homePage.asXml();
		int index = StringUtils.ordinalIndexOf(text, "br", 75);
		text = text.substring(index);
		index = text.substring(0, text.indexOf(email)).indexOf("idemployeeview=");
		return Integer.valueOf(text.substring(index + 15, text.indexOf("&", index)));
	}

}
