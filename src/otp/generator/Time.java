/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otp.generator;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pushp
 */
public class Time {
    public static Timestamp getTimestemp(){
        Date date = new Date();
        
    return new Timestamp(date.getTime());
    }
    public static long getMinutes(Timestamp t1 ,Timestamp t2){
    long old=t1.getTime();
        long current = t2.getTime();
        long min =(current-old)*(60*1000);
        return min;
    }
    
    
}
