package notification.service.service;

import notification.service.domain.DeleteIdDto;
import notification.service.domain.NodeRegistrationDto;
import notification.service.domain.message.MessageId;
import notification.service.domain.notification.rich.component.RichWrapper;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements SenderService {
    @Override
    public boolean isEnabledRequest(String sourceIp) {
        //TODO Work in progress...
        return false;
    }

    @Override
    public void signUpUser(String sourceIp, NodeRegistrationDto signUpDto) {
        //TODO Work in progress...
    }

    @Override
    public boolean sendNotification(RichWrapper notification) {
        //TODO Work in progress...
        return false;
    }

    @Override
    public void deliveredStatusForMessageId(MessageId messageId) {
        //TODO Work in progress...
    }

    @Override
    public void deleteUserId(String sourceIp, DeleteIdDto deleteIdDto) {
        //TODO Work in progress...
    }
}
