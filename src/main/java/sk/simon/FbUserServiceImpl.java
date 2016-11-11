package sk.simon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Simon on 10.11.2016.
 */

@Service
@Transactional
public class FbUserServiceImpl implements FbUserService {

    @Autowired
    private FbUserRepository fbUserRepository;

    public void retrieveAndSaveFbUser(String accessToken, String userId) {
        String [] fields = { "id", "gender", "first_name", "last_name" };
        // I did not use facebook.userOperations().getUserProfile() because this method is not compatible with Fb API 2.8
        Facebook facebook = new FacebookTemplate(accessToken);
        User userProfile = facebook.fetchObject(userId, User.class, fields);
        FbUser fbUser= new FbUser();
        fbUser.setFbId(userProfile.getId());
        fbUser.setGender(userProfile.getGender());
        fbUser.setFirstName(userProfile.getFirstName());
        fbUser.setLastName(userProfile.getLastName());
        fbUserRepository.save(fbUser);
    }
}
