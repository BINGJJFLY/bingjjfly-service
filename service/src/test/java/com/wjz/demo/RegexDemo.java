package com.wjz.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	private static final String REGEX_XS_TIME_ZONE = "Z|(?:[-+][0-9]{2}:[0-9]{2})";
	private static final String REGEX_ISO8601_BASIC_TIME_ZONE = "Z|(?:[-+][0-9]{2}(?:[0-9]{2})?)";
	private static final String REGEX_ISO8601_EXTENDED_TIME_ZONE = "Z|(?:[-+][0-9]{2}(?::[0-9]{2})?)";

	private static final String REGEX_XS_OPTIONAL_TIME_ZONE = "(" + REGEX_XS_TIME_ZONE + ")?";
	private static final String REGEX_ISO8601_BASIC_OPTIONAL_TIME_ZONE = "(" + REGEX_ISO8601_BASIC_TIME_ZONE + ")?";
	private static final String REGEX_ISO8601_EXTENDED_OPTIONAL_TIME_ZONE = "(" + REGEX_ISO8601_EXTENDED_TIME_ZONE
			+ ")?";

	private static final String REGEX_XS_DATE_BASE = "(-?[0-9]+)-([0-9]{2})-([0-9]{2})";
	private static final String REGEX_ISO8601_BASIC_DATE_BASE = "(-?[0-9]{4,}?)([0-9]{2})([0-9]{2})";
	private static final String REGEX_ISO8601_EXTENDED_DATE_BASE = "(-?[0-9]{4,})-([0-9]{2})-([0-9]{2})";

	private static final String REGEX_XS_TIME_BASE = "([0-9]{2}):([0-9]{2}):([0-9]{2})(?:\\.([0-9]+))?";
	private static final String REGEX_ISO8601_BASIC_TIME_BASE = "([0-9]{2})(?:([0-9]{2})(?:([0-9]{2})(?:[\\.,]([0-9]+))?)?)?";
	private static final String REGEX_ISO8601_EXTENDED_TIME_BASE = "([0-9]{2})(?::([0-9]{2})(?::([0-9]{2})(?:[\\.,]([0-9]+))?)?)?";

	private static final Pattern PATTERN_XS_DATE = Pattern.compile(REGEX_XS_DATE_BASE + REGEX_XS_OPTIONAL_TIME_ZONE);
	private static final Pattern PATTERN_XS_TIME = Pattern.compile(REGEX_XS_TIME_BASE + REGEX_XS_OPTIONAL_TIME_ZONE);
	private static final Pattern PATTERN_ISO8601_BASIC_TIME = Pattern
			.compile(REGEX_ISO8601_BASIC_TIME_BASE + REGEX_ISO8601_BASIC_OPTIONAL_TIME_ZONE);
	private static final Pattern PATTERN_ISO8601_EXTENDED_TIME = Pattern
			.compile(REGEX_ISO8601_EXTENDED_TIME_BASE + REGEX_ISO8601_EXTENDED_OPTIONAL_TIME_ZONE);
	private static final Pattern PATTERN_XS_DATE_TIME = Pattern
			.compile(REGEX_XS_DATE_BASE + "T" + REGEX_XS_TIME_BASE + REGEX_XS_OPTIONAL_TIME_ZONE);
	private static final Pattern PATTERN_ISO8601_BASIC_DATE_TIME = Pattern.compile(REGEX_ISO8601_BASIC_DATE_BASE + "T"
			+ REGEX_ISO8601_BASIC_TIME_BASE + REGEX_ISO8601_BASIC_OPTIONAL_TIME_ZONE);
	private static final Pattern PATTERN_ISO8601_EXTENDED_DATE_TIME = Pattern.compile(REGEX_ISO8601_EXTENDED_DATE_BASE
			+ "T" + REGEX_ISO8601_EXTENDED_TIME_BASE + REGEX_ISO8601_EXTENDED_OPTIONAL_TIME_ZONE);

	public static void main(String[] args) {
		match(REGEX_XS_TIME_ZONE, "+18:13");
		match(REGEX_ISO8601_BASIC_TIME_ZONE, "-1813");
		match(REGEX_ISO8601_EXTENDED_TIME_ZONE, "+18:13");

		match(REGEX_XS_OPTIONAL_TIME_ZONE, "-18:13");
		match(REGEX_ISO8601_BASIC_OPTIONAL_TIME_ZONE, "+1813");
		match(REGEX_ISO8601_EXTENDED_OPTIONAL_TIME_ZONE, "+18:13");

		match(REGEX_XS_DATE_BASE, "-1813-46-89");
		match(REGEX_ISO8601_BASIC_DATE_BASE, "181388774689");
		match(REGEX_ISO8601_EXTENDED_DATE_BASE, "18138877-46-89"); // 2018-09-16

		match(REGEX_XS_TIME_BASE, "18:79:01.12746");
		match(REGEX_ISO8601_BASIC_TIME_BASE, "187901.12746"); // 18、1879
		match(REGEX_ISO8601_EXTENDED_TIME_BASE, "18:79:01.12746");

		match(PATTERN_XS_DATE, "2018-09-16+18:13");
		match(PATTERN_XS_TIME, "18:79:01.127-18:13");
		match(PATTERN_ISO8601_BASIC_TIME, "187901.127+1813");
		match(PATTERN_ISO8601_EXTENDED_TIME, "18:79:01.127-18:13");
		match(PATTERN_XS_DATE_TIME, "2018-09-16T18:79:01.127-18:13");
		match(PATTERN_ISO8601_BASIC_DATE_TIME, "20180916T187901.127+1813");
		match(PATTERN_ISO8601_EXTENDED_DATE_TIME, "2018-09-16T18:79:01.127+18:13");
	}

	private static void match(Pattern pattern, CharSequence input) {
		Matcher matcher = pattern.matcher(input);
		System.out.println(matcher.matches() + " - " + pattern.pattern() + " - " + input + " - " + matcher.groupCount());
	}

	private static void match(String regex, CharSequence input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		System.out.println(matcher.matches() + " - " + regex + " - " + input + " - " + + matcher.groupCount());
	}
}
