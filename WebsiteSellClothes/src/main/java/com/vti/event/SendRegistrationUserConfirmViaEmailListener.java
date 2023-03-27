package com.vti.event;

import com.vti.service.implement.IEmailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SendRegistrationUserConfirmViaEmailListener
		implements ApplicationListener<OnSendRegistrationUserConfirmViaEmailEvent> {

	@Autowired
	private IEmailService emailService;

	@SneakyThrows
	@Override
	public void onApplicationEvent(OnSendRegistrationUserConfirmViaEmailEvent event) {

			sendConfirmViaEmail(event.getEmail());

	}

	private void sendConfirmViaEmail(String email){
		emailService.sendRegistrationUserConfirm(email);
	}

}