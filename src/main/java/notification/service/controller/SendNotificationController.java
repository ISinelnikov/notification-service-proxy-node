package notification.service.controller;

import notification.service.domain.ApiResponse;
import notification.service.domain.notification.rich.component.RichWrapper;
import notification.service.service.ConfigurationService;
import notification.service.service.SenderService;
import notification.service.utils.RequestUtils;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class SendNotificationController {
    private static final Logger logger = LoggerFactory.getLogger(SendNotificationController.class);

    private final ConfigurationService configurationService;
    private final SenderService senderService;

    public SendNotificationController(ConfigurationService configurationService,
            SenderService senderService) {
        this.configurationService = Objects.requireNonNull(configurationService,
                "Configuration service can't be null.");
        this.senderService = Objects.requireNonNull(senderService,
                "Sender service can't be null.");
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessageTest(@RequestBody RichWrapper notification,
            HttpServletRequest request) {
        String securityToken = RequestUtils.getSecurityToken(request);

        boolean isVerified = configurationService.verifyToken(securityToken);

        if (isVerified) {
            if (senderService.sendNotification(notification)) {
                return ResponseEntity.ok(ApiResponse.getSuccessResponse("Successful send operation."));
            }
            return new ResponseEntity<>(ApiResponse.getFailedResponse("Can't send notification."),
                    HttpStatus.BAD_REQUEST);
        }
        logger.warn("Can't verify token: {} for notification: {}.", securityToken, notification);
        return new ResponseEntity<>(ApiResponse.getFailedResponse("Can't verify token."),
                HttpStatus.UNAUTHORIZED);
    }
}
