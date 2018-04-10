package ru.les.dav.mantis.model;

/**
 * Created by saakovamr on 10.04.18.
 */
public class MailMessage {
   public String to;
   public String text;

   public MailMessage(String to, String text){
      this.to = to;
      this.text = text;
   }
}
