package com.neusoft.ehr.util.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Email工具类
 *
 * @author 吉兆鹏
 */
@RequiredArgsConstructor
@Component
public class EmailUtil {
    private final MailSender mailSender;

    public SimpleMailMessage getSimpleMailMessage(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("15935970573@163.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        return simpleMailMessage;
    }

    @Async
    public void send(SimpleMailMessage... mails) {
        mailSender.send(mails);
    }
}
