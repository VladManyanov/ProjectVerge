package com.pverge.core.be.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collection of various utils
 * @author Hypernucle
 */
public class MiscUtils {

	public static String intListToStr(List<Integer> list) {
		return list.stream().map(String::valueOf).collect(Collectors.joining(","));
	}
	
	public static int[] strArrayToIntArray(String[] strArray) {
	    return Stream.of(strArray).mapToInt(Integer::parseInt).toArray();
	}
	
	public static String getCurrentTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		return LocalDateTime.now().format(formatter);
	}
	
}
