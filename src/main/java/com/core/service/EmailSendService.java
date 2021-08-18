package com.core.service;

import com.core.config.SendMailConfig;
import com.core.domain.Usuario;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmailSendService {

    SendMailConfig getSendGrid;
    
    @Transactional
    public String send(Usuario user) throws IOException {
        Email from = new Email("alexiskremis@gmail.com");
        String subject = "Welcome to DysneyApp";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain", "Welcome, to enter login at the following link: auth/login");
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = getSendGrid.getSendGrid();

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        return "Revisa tu email";
    }

}
