package notification.service.controller;

import notification.service.domain.ApiResponse;
import notification.service.domain.DeleteIdDto;
import notification.service.domain.NodeRegistrationDto;
import notification.service.domain.message.MessageId;
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
@RequestMapping("/api/security")
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final SenderService senderService;

    public RegistrationController(SenderService senderService) {
        this.senderService = Objects.requireNonNull(senderService,
                "Sender service can't be null.");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUpUser(@RequestBody NodeRegistrationDto signUpDto, HttpServletRequest request) {
        String sourceIp = RequestUtils.getSourceIp(request);
        logger.info("New sign up request: {}, source ip: {}.", signUpDto, sourceIp);
        if (senderService.isEnabledRequest(sourceIp)) {
            senderService.signUpUser(sourceIp, signUpDto);
            return ResponseEntity.ok(ApiResponse.getSuccessResponse("Registry registration operation."));
        }
        logger.debug("Can't validate ip address: {}.", sourceIp);
        return new ResponseEntity<>(ApiResponse.getFailedResponse("Ip check not passed."),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete-user-id")
    public ResponseEntity<ApiResponse> deleteUserId(@RequestBody DeleteIdDto deleteIdDto, HttpServletRequest request) {
        String sourceIp = RequestUtils.getSourceIp(request);
        logger.info("Delete id request: {}, source ip: {}.", deleteIdDto, sourceIp);
        if (senderService.isEnabledRequest(sourceIp)) {
            senderService.deleteUserId(sourceIp, deleteIdDto);
            return ResponseEntity.ok(ApiResponse.getSuccessResponse("Registry delete operation."));
        }
        logger.debug("Can't validate ip address: {}.", sourceIp);
        return new ResponseEntity<>(ApiResponse.getFailedResponse("Ip check not passed."),
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delivered-message-id")
    public ResponseEntity<ApiResponse> registryMessageId(@RequestBody MessageId messageId, HttpServletRequest request) {
        String sourceIp = RequestUtils.getSourceIp(request);

        logger.info("Delivered message id: {}.", messageId);
        if (senderService.isEnabledRequest(sourceIp)) {
            senderService.deliveredStatusForMessageId(messageId);
            return ResponseEntity.ok(ApiResponse.getSuccessResponse("Successful registration message id."));
        }
        return new ResponseEntity<>(ApiResponse.getFailedResponse("Can't registry message: " + messageId),
                HttpStatus.BAD_REQUEST);
    }
}
