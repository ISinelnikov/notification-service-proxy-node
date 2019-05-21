package notification.service.service;

import notification.service.domain.NodeConfiguration;
import notification.service.utils.YamlConfigUtils;

import java.util.Objects;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ConfigurationService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    private final NodeConfiguration nodeConfiguration;

    public ConfigurationService() {
        this.nodeConfiguration = Objects.requireNonNull(YamlConfigUtils
                .loadYamlConfig(getConfigPath(), NodeConfiguration.class),
                "Node configuration can't be null.");

        logger.info("Application configuration: {}.", nodeConfiguration);
    }

    @Nullable
    public String getSecurityToken() {
        return nodeConfiguration.getSecurityToken();
    }

    @Nullable
    public String getMainNodeAddress() {
        return nodeConfiguration.getMainNodeAddress();
    }

    public long getGuardValue() {
        return nodeConfiguration.getGuardValue();
    }

    public boolean verifyToken(String token) {
        String securityToken = nodeConfiguration.getSecurityToken();

        if (StringUtils.hasText(securityToken)) {
            return token.equals(securityToken);
        }
        logger.error("Can't verify token: {}. Application security token not found.", token);
        return false;
    }

    private static String getConfigPath() {
        String configPath = System.getProperty("application.config.path");

        if (!StringUtils.hasText(configPath)) {
            throw new IllegalStateException("Can't start application without config.");
        }
        logger.info("Start application with config path: {}.", configPath);
        return configPath;
    }
}
