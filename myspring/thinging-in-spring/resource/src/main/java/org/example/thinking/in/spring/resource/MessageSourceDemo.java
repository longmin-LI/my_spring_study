package org.example.thinking.in.spring.resource;

import org.springframework.context.MessageSource;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageSourceDemo {

    public static void main(String[] args) {

        int planet = 7;
        String event = "a disturbance in the Force";
        String format = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(format);

        String result = messageFormat.format(new Object[]{planet, new Date(), event});

        System.out.println(result);
        //1.调整消息格式
        format = "This is a text : {0}, {1}";
        messageFormat.applyPattern(format);
        result = messageFormat.format(new Object[]{"hello,world", "666"});
        System.out.println(result);

        //2.调整locale（即调整国家）
        messageFormat.setLocale(Locale.ENGLISH);
        format = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        messageFormat.applyPattern(format);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //3.调整format
        messageFormat.setFormat(1, new SimpleDateFormat("yy-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
