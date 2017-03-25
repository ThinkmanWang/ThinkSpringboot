package com.thinkman.springboot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonUtil {
    public static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
	}

    public static Date getDefaultDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, 1, 1, 12, 0, 0);
        return calendar.getTime();
    }

    public static String randomMACAddress(){
        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte)(macAddr[0] & (byte)254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

        StringBuilder sb = new StringBuilder(18);
        for(byte b : macAddr){

            if(sb.length() > 0)
                sb.append("-");

            sb.append(String.format("%02x", b));
        }


        return sb.toString();
    }
}
