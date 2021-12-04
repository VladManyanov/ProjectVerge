package com.pverge.core.be.util;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
	
	// Taken from Internet
	public static String bytesToHex(byte[] bytes) {
    	char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    
	// Taken from Internet
    public static byte[] fromHexString(String src) {
        byte[] biBytes = new BigInteger("10" + src.replaceAll("\\s", ""), 16).toByteArray();
        return Arrays.copyOfRange(biBytes, 1, biBytes.length);
    }
	
}
