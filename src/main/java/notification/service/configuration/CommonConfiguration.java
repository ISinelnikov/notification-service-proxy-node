package notification.service.configuration;

import notification.service.cache.RequestTimeCache;
import notification.service.service.ConfigurationService;

import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class CommonConfiguration {
    public static final String BASE_SCHEDULER = "Base Scheduler";

    private static final int REFRESH_DURATION = Integer.getInteger("proxy.node.guard.cache.refresh.duration.minutes", 1);

    @Bean(BASE_SCHEDULER)
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(BASE_SCHEDULER);
        return threadPoolTaskScheduler;
    }

    @Bean
    public RequestTimeCache getRequestTimeCache(ConfigurationService configuration) {
        Objects.requireNonNull(configuration, "Configuration can't be null.");
        return new RequestTimeCache(configuration.getGuardValue(), REFRESH_DURATION, null);
    }
}
