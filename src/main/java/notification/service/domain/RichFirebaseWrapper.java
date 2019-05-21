package notification.service.domain;

import notification.service.domain.notification.rich.RichNotification;
import notification.service.utils.JsonUtils;

import java.util.Objects;

public class RichFirebaseWrapper {
    private final String to;
    private final CustomData data;

    public RichFirebaseWrapper(RichNotification notification) {
        this.to = Objects.requireNonNull(notification.getTo(),
                "To can't be null.");
        this.data = new CustomData(Objects.requireNonNull(JsonUtils
                        .convertObjectToJson(notification.getTemplate()),
                "Data can't be null."));
    }

    public String getTo() {
        return to;
    }

    public CustomData getData() {
        return data;
    }

    public static class CustomData {
        private final String data;

        private CustomData(String data) {
            this.data = Objects.requireNonNull(data, "Data can't be null.");
        }

        public String getData() {
            return data;
        }

        @Override
        public String toString() {
            return "CustomData{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RichFirebaseWrapper{" +
                "to='" + to + '\'' +
                ", data=" + data +
                '}';
    }
}
