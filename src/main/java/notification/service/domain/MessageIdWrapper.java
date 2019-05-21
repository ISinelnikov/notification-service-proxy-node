package notification.service.domain;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageIdWrapper {
    @Nullable
    private final List<MessageIdDto> messageIds;

    @JsonCreator
    public MessageIdWrapper(@JsonProperty("results") @Nullable List<MessageIdDto> messageIds) {
        this.messageIds = messageIds;
    }

    public Optional<String> getFirstMessageId() {
        if (messageIds != null) {
            return messageIds.stream().findFirst().map(MessageIdDto::getMessageId);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "MessageIdWrapper{" +
                "messageIds=" + getFirstMessageId() +
                '}';
    }
}
