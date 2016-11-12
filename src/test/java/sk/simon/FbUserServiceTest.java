package sk.simon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.simon.exceptions.FbUserNotDeleted;
import sk.simon.exceptions.FbUserNotRetrieved;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Simon on 10.11.2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FbUserServiceTest {
    //Token expires after few hours
    private String accessToken = "EAAQx8CaKKZCoBAIY0UkTThZABVu3unZA78w8zACOyf5ZCxty5xZCyEqMURoSJKpZA4VH1QyhlMxhsUbGY3sjtimenibyMl29MBZC3TDyWZCLo9hkJslG0sVJT2aPCQnTTdmr2uAuQBflOgAB5CZAMW2EUXZBw6GvZAO6qHou7pwi4wrAAZDZD";
    private String userFbId = "10206248904662029";

    @Autowired
    private FbUserService fbUserService;

    @Autowired
    private FbUserRepository fbUserRepository;

    @Before
    public void clean() {
        fbUserRepository.deleteAll();
    }

    @Test
    public void retrieveAndSaveFbUserTest() throws Exception {
        fbUserService.retrieveAndSaveFbUser(accessToken, userFbId);

        List<FbUserEntity> fbUsers = fbUserRepository.findByFbId(FbUserEntity.class, userFbId);
        assertNotEquals(fbUsers.size(),0);
    }

    @Test(expected = FbUserNotRetrieved.class)
    public void retrieveAndSaveFbUserWrongFbIdTest() throws Exception {
        String wrongUserId = "";
        fbUserService.retrieveAndSaveFbUser(accessToken, wrongUserId);
    }

    @Test(expected = FbUserNotRetrieved.class)
    public void retrieveAndSaveFbUserWrongAccessTokenTest() throws Exception {
        //Timeout of token is one day
        String wrongAccessToken = "iiii";
        fbUserService.retrieveAndSaveFbUser(wrongAccessToken, userFbId);
    }

    @Test
    public void deleteFbUserTest() throws Exception {
        FbUserEntity fbUser = new FbUserEntity();
        fbUser.setLastName("Sudora");
        fbUser.setFirstName("Simon");
        fbUser.setGender("male");
        fbUser.setFbId(userFbId);
        fbUserRepository.save(fbUser);

        List<FbUserEntity> fbUsers = fbUserRepository.findByFbId(FbUserEntity.class, userFbId);
        assertNotEquals(fbUsers.size(),0);
        fbUserService.deleteFbUser(userFbId);

        fbUsers = fbUserRepository.findByFbId(FbUserEntity.class, userFbId);
        assertEquals(fbUsers.size(),0);
    }

    @Test(expected = FbUserNotDeleted.class)
    public void deleteFbUserWrongFdIdTest() throws Exception {
        String userFbId = "10206248904662029";
        String wrongUserFbId = "10206249";
        FbUserEntity fbUser = new FbUserEntity();
        fbUser.setLastName("Sudora");
        fbUser.setFirstName("Simon");
        fbUser.setGender("male");
        fbUser.setFbId(userFbId);
        fbUserRepository.save(fbUser);
        fbUserService.deleteFbUser(wrongUserFbId);

    }

    @Test
    public void getUserByFbIdTest() throws Exception {
        FbUserEntity fbUser = new FbUserEntity();
        fbUser.setLastName("Sudora");
        fbUser.setFirstName("Simon");
        fbUser.setGender("male");
        fbUser.setFbId(userFbId);
        fbUserRepository.save(fbUser);

        FbUserEntity foundFbUser = fbUserService.getUserByFbId(userFbId);
        assertEquals(foundFbUser,fbUser);
    }
}
