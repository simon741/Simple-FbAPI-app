package sk.simon;

import javax.persistence.Entity;

/**
 * Created by Simon on 12.11.2016.
 */

public class FbUserAccessDetailsDTO {
    private String userId;
    private String accessToken;

    public FbUserAccessDetailsDTO(String userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public FbUserAccessDetailsDTO() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
