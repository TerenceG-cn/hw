package com.tce.DateAndTime;

import java.time.LocalDate;

public class DateAndTime {
    public static void main(String[] args) {
        //当天日期
        LocalDate today = LocalDate.now(); System.out.println("Today's Local date : " + today);
        //当天年月
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
        //获取某个特定的日期
        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        System.out.println("Your Date of birth is : " + dateOfBirth);
        //检查两个日期是否相等
        LocalDate date1 = LocalDate.of(2014, 01, 14);
        if(date1.equals(today))
            System.out.printf("Today %s and date1 %s are same date %n", today, date1);
        else
            System.out.printf("Today %s and date1 %s are not same date %n", today, date1);
        //...todo http://ifeve.com/20-examples-of-date-and-time-api-from-java8/
    }
}
