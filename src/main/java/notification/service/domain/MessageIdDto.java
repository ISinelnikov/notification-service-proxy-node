package notification.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageIdDto {
    private final String messageId;

    @JsonCreator
    public MessageIdDto(@JsonProperty("message_id") String messageId) {
        this.messageId = Objects.requireNonNull(messageId, "Message id can't be null.");
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "MessageIdDto{" +
                "messageId='" + messageId + '\'' +
                '}';
    }
}
