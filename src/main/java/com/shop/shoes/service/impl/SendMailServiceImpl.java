package com.shop.shoes.service.impl;

import com.shop.shoes.dto.MailInfo;
import com.shop.shoes.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    JavaMailSender sender;
    List<MailInfo> list = new ArrayList<>();
    @Override
    @Scheduled(fixedDelay = 5000)
    public void run() {
        while (!list.isEmpty()){
            MailInfo mail = list.remove(0);
            try{
                this.send(mail);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void queue(String to, String subject, String body) {
        queue(new MailInfo(to, subject, body));
    }

    @Override
    public void queue(MailInfo mail) {
        list.add(mail);
    }

    @Override
    public void send(MailInfo mail) throws MessagingException, IOException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(),true);
        helper.setReplyTo(mail.getFrom());
        if(mail.getAttachments()!=null){
            FileSystemResource file = new FileSystemResource(new File(mail.getAttachments()));
            helper.addAttachment(mail.getAttachments(),file);
        }
        sender.send(message);
    }
}
