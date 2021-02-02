/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otp.generator;

import java.util.Random;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JTextField;

/**
 *
 * @author pushp
 */
public class OTPGenerator {
        public static String generateOTP(int len){
        //len denotes length of PASSWORD;
        String values ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@#$%";
        Random random = new Random();
        int rand;
        String OTP ="";
        for (int i = 0;i<len;i++){
             rand =random.nextInt(values.length());
             OTP=OTP+values.charAt(rand); 
        }
        return OTP;
        }
        
        
        //SEND EMAIL 
        
        public static void sendEmail(String OTP, String to) throws MessagingException{
            System.out.println("Prepariing to send email");
            Properties props = System.getProperties();

             props.put("mail.smtp.user","username"); 
props.put("mail.smtp.host", "smtp.gmail.com"); 
props.put("mail.smtp.port", "25"); 
props.put("mail.debug", "true"); 
props.put("mail.smtp.auth", "true"); 
props.put("mail.smtp.starttls.enable","true"); 
props.put("mail.smtp.EnableSSL.enable","true");

props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
props.setProperty("mail.smtp.socketFactory.fallback", "false");   
props.setProperty("mail.smtp.port", "465");   
props.setProperty("mail.smtp.socketFactory.port", "465"); 
             
             
             //Your Account Email & Password
             String Email= GmailLogin.eMail();
             String Password =GmailLogin.password();
             //Login
             Session session = Session.getInstance(props, new Authenticator(){
             @Override
             protected PasswordAuthentication getPasswordAuthentication(){
             return new PasswordAuthentication(Email,Password);
                     }
             });
             Message message = prepareMessage(session,Email/*from*/,to,OTP);
            Transport.send(message);
                 System.out.println("Sent message successfully....");
        }

    private static Message prepareMessage(Session session,String from,String to,String OTP ) {
        try {
                 // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                 message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                 message.setSubject("Email Verification");

                // Now set the actual message
                String htmlCode ="Your OTP is :<h1>"+OTP+"</h1><br> This OTP can only be used within <b>5 minutes</b>. Do not share your OTP with anyone.";
                 message.setContent(htmlCode,"text/html");
                //message.setText("Your One Time Password is "+OTP +". This OTP is only valid for 5 minutes.\n Kidenly do not share this OTP with anyone.\n ThankYou :)");
         
                 // Send message
                 
                 return message;
              
            } catch (MessagingException mex) {
                 mex.printStackTrace();
             }
        return null; 
    }
    public static boolean verifyOTP(JTextField tf, String OTP){
        if(tf.getText().equals(OTP)){
        return true;}
        else{
            return false;
        }
        }
    /**
     * @param args the command line arguments
     */
    
    
}
