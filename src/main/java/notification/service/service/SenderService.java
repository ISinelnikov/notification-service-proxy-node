package notification.service.service;

import notification.service.domain.DeleteIdDto;
import notification.service.domain.NodeRegistrationDto;
import notification.service.domain.message.MessageId;
import notification.service.domain.notification.rich.component.RichWrapper;

public interface SenderService {
    /**
     * Guard for DOS-attack
     *
     * @param sourceIp
     *
     * @return
     */
    boolean isEnabledRequest(String sourceIp);

    /**
     * Proxy for sign up request
     *
     * @param sourceIp  sender ip
     * @param signUpDto sender data
     */
    void signUpUser(String sourceIp, NodeRegistrationDto signUpDto);

    /**
     * Proxy for firebase notification
     *
     * @param notification notification wrapper (firebase key and notification)
     *
     * @return true if send is successful
     */
    boolean sendNotification(RichWrapper notification);

    /**
     * Delivered message id
     *
     * @param messageId set delivered status for message id
     */
    void deliveredStatusForMessageId(MessageId messageId);

    /**
     * Delete user id from data component
     *
     * @param sourceIp    user ip
     * @param deleteIdDto user id dto
     */
    void deleteUserId(String sourceIp, DeleteIdDto deleteIdDto);
}
