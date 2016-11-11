package sk.simon;

import sk.simon.exceptions.FbUserNotDeleted;
import sk.simon.exceptions.FbUserNotFound;
import sk.simon.exceptions.FbUserNotRetrieved;
import sk.simon.exceptions.FbUserNotSaved;

/**
 * Created by Simon on 10.11.2016.
 */

public interface FbUserService {

    void retrieveAndSaveFbUser(String accessToken, String userFbId) throws FbUserNotSaved, FbUserNotRetrieved;

    void deleteFbUser(String userFbId) throws FbUserNotFound, FbUserNotDeleted;

}
