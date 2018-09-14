package com.iscale.ProjectCalculator.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public String formatDate(Date date) {		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        return DATE_FORMAT.format(date);		
	}
	
}
