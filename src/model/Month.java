package model;

public enum Month {
	
	December("december", "11"),
	January("january", "0"),
	February("february", "1"),
	March("march", "2"),
	April("april", "3"),
	May("may", "4"),
	June("june", "5"),
	July("july", "6"),
	August("august", "7"),
	September("september", "8"),
	October("october", "9"),
	November("november", "10");

	private String name;
	private String value;

	private Month(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Month getMonthByName(String month) {
		for (Month m : Month.values()) {
			if (m.name.equals(month)) {
				return m;
			}
		}
		return null;
	}

}
