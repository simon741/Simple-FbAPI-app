package sk.simon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.simon.exceptions.FbUserNotDeleted;
import sk.simon.exceptions.FbUserNotFound;
import sk.simon.exceptions.FbUserNotRetrieved;
import sk.simon.exceptions.FbUserNotSaved;

import java.util.List;

/**
 * Created by Simon on 10.11.2016.
 */

@Service
@Transactional
public class FbUserService{

    @Autowired
    private FbUserRepository fbUserRepository;

    public void retrieveAndSaveFbUser(String accessToken, String userId) throws FbUserNotSaved, FbUserNotRetrieved {
        String [] fields = { "id", "gender", "first_name", "last_name" };
        // I did not use facebook.userOperations().getUserProfile() because this method is not compatible with Fb API 2.8
        FbUserEntity fbUser;
        try {
            Facebook facebook = new FacebookTemplate(accessToken);
            User userProfile = facebook.fetchObject(userId, User.class, fields);
            fbUser = new FbUserEntity();
            fbUser.setFbId(userProfile.getId());
            fbUser.setGender(userProfile.getGender());
            fbUser.setFirstName(userProfile.getFirstName());
            fbUser.setLastName(userProfile.getLastName());
        } catch(Exception e){
            throw new FbUserNotRetrieved(e.getMessage());
        }
        try {
            if(fbUser != null) fbUserRepository.save(fbUser);
        } catch(DataAccessException e){
            throw new FbUserNotSaved("exception on persistance layer");
        }
    }

    public void deleteFbUser(String userFbId) throws FbUserNotDeleted {
        List<FbUserEntity> fbUsers = fbUserRepository.findByFbId(FbUserEntity.class, userFbId);
        if (fbUsers.size() == 0) throw new FbUserNotDeleted("There is no user with FbId:" + userFbId);
        FbUserEntity fbUser = fbUsers.get(0);
        try {
            fbUserRepository.delete(fbUser);
        } catch(DataAccessException e){
            throw new FbUserNotDeleted("exception on persistance layer");
        }
    }

    public FbUserEntity getUserByFbId(String userFbId) throws FbUserNotFound {
        List<FbUserEntity> fbUsers = fbUserRepository.findByFbId(FbUserEntity.class, userFbId);
        if (fbUsers.size() == 0) throw new FbUserNotFound("There is no user with FbId:" + userFbId);
        FbUserEntity fbUser = fbUsers.get(0);
        return fbUser;
    }

}
