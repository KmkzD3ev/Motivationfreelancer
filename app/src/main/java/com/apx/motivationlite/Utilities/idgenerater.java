package com.apx.motivationlite.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class idgenerater {
    public static String getId(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String data = dateFormat.format(new Date());
        String id = "";
        id = id + data;
                return id;
    }
}
