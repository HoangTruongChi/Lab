package com.hust.labregister.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public static String formatDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
}
