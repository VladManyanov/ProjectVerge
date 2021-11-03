package com.pverge.core.be.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Collection of various utils
 * @author Hypernucle
 */
public class MiscUtils {

	public String IntListToStr(List<Integer> list) {
		return list.stream().map(String::valueOf).collect(Collectors.joining(","));
	}
	
}
