package notification.service.domain;

import javax.annotation.Nullable;

public class NodeConfiguration {
    @Nullable
    private String securityToken;
    @Nullable
    private String mainNodeAddress;

    private long guardValue;

    @Nullable
    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(@Nullable String securityToken) {
        this.securityToken = securityToken;
    }

    @Nullable
    public String getMainNodeAddress() {
        return mainNodeAddress;
    }

    public void setMainNodeAddress(@Nullable String mainNodeAddress) {
        this.mainNodeAddress = mainNodeAddress;
    }

    public long getGuardValue() {
        return guardValue;
    }

    public void setGuardValue(long guardValue) {
        this.guardValue = guardValue;
    }

    @Override
    public String toString() {
        return "NodeConfiguration{" +
                "securityToken='" + securityToken + '\'' +
                ", mainNodeAddress='" + mainNodeAddress + '\'' +
                ", guardValue=" + guardValue +
                '}';
    }
}
