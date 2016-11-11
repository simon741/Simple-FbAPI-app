package sk.simon;

/**
 * Created by Simon on 10.11.2016.
 */

public interface FbUserService {

    void retrieveAndSaveFbUser(String accessToken, String userId);
}
