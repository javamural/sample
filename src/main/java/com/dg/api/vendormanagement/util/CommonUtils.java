package com.dg.api.vendormanagement.util;

import java.util.Date;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static void prepareServiceHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(null, null);
	}

	public static Date addMinutesToDate(Date otpGenDtm, int mints) {
		Date date = null;
		if (otpGenDtm != null) {
			date = new Date(otpGenDtm.getTime() + mints * 60 * 1000);
		}
		return date;
	}

	public static enum Gender {
		MALE("male"), FEMALE("female"), OTHER("other");

		private String value;

		Gender(String value) {
			this.value = value;
		}
	}
	
	public static String writeAsJsonString(Object obj) {
		String jsonString = null;
		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        return jsonString;
    }
}
