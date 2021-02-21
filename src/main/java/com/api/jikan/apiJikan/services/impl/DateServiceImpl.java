package com.api.jikan.apiJikan.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.services.interfaces.DateService;

@Service
public class DateServiceImpl implements DateService {
	
	private final int MARCH = 3;
	private final int MAY = 5;
	private final int AUGUST = 8;
	private final int NOVEMBER = 11;

	@Override
	public int getCurrentMonth() {
		
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		return Calendar.MONTH + 1;
	}

	@Override
	public String getSeason() {
		
		int currentMonth = getCurrentMonth();
		String season = "";
		
		if(currentMonth > MARCH && currentMonth <= MAY) {
			season = "spring";
		} else if(currentMonth > MAY && currentMonth <= AUGUST) {
			season = "summer";
		} else if(currentMonth > AUGUST && currentMonth <= NOVEMBER) {
			season = "fail";
		} else {
			season = "winter";
		}
		
		return season;
	}

	@Override
	public int getYear() {
		
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		return calendar.get(Calendar.YEAR);
	}
}