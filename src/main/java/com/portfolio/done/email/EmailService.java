package com.portfolio.done.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

	private final JavaMailSender javaMailSender;
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	

	@Override
	@Async //TODO gegen Queue austauschen? Wieso? klingt sinnig, muss ich aber noch erörtern. Hilft glaube ich, wenn man sich das als Massenvorgang vorstellt.
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
			mimeMessageHelper.setText(email, true);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject("Confirm your email");
			mimeMessageHelper.setFrom("mchlzj@gmail.com"); //TODO get from Configuration File
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("failed to send email", e);
			throw new IllegalStateException("failed to send email");
		}
		
	}

}
