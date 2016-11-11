package sk.simon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.simon.exceptions.FbUserNotDeleted;
import sk.simon.exceptions.FbUserNotRetrieved;
import sk.simon.exceptions.FbUserNotSaved;

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
    //Timeout of token is one day
    private String accessToken = "EAAQx8CaKKZCoBAJdHeHNruwv8vg7rGmRKwflrNbwEYc3eAxPUPzk2UB0RcVcg7fnJr6NS8ZB4L5SKNBpxtgN8F0zgNEZAc6RxFvPyMQvcO3U9obQ72Cq715f3gPPBd1dpccX3YbpfZCjmtSJLBj0fLqvdJvgC7KnnnUXh0jh2gZDZD";
    String userFbId = "10206248904662029";

    @Autowired
    private FbUserServiceImpl fbUserServiceImpl;

    @Autowired
    private FbUserRepository fbUserRepository;

    @Before
    public void clean() {
        fbUserRepository.deleteAll();
    }

    @Test
    public void retrieveAndSaveFbUserTest(){
        try {
            fbUserServiceImpl.retrieveAndSaveFbUser(accessToken, userFbId);
        } catch (FbUserNotSaved fbUserNotSaved) {
            fbUserNotSaved.printStackTrace();
        } catch (FbUserNotRetrieved fbUserNotRetrieved) {
            fbUserNotRetrieved.printStackTrace();
        }

        List<FbUser> fbUsers = fbUserRepository.findByFbId(FbUser.class, userFbId);
        assertNotEquals(fbUsers.size(),0);
    }

    @Test(expected = FbUserNotRetrieved.class)
    public void retrieveAndSaveFbUserWrongFbIdTest() throws FbUserNotRetrieved, FbUserNotSaved {
        String wrongUserId = "";
        fbUserServiceImpl.retrieveAndSaveFbUser(accessToken, wrongUserId);
    }

    @Test(expected = FbUserNotRetrieved.class)
    public void retrieveAndSaveFbUserWrongAccessTokenTest() throws FbUserNotRetrieved, FbUserNotSaved {
        //Timeout of token is one day
        String wrongAccessToken = "iiii";
        fbUserServiceImpl.retrieveAndSaveFbUser(wrongAccessToken, userFbId);
    }

    @Test
    public void deleteFbUserTest(){
        FbUser fbUser = new FbUser();
        fbUser.setLastName("Sudora");
        fbUser.setFirstName("Simon");
        fbUser.setGender("male");
        fbUser.setFbId(userFbId);
        fbUserRepository.save(fbUser);

        List<FbUser> fbUsers = fbUserRepository.findByFbId(FbUser.class, userFbId);
        assertNotEquals(fbUsers.size(),0);
        try {
            fbUserServiceImpl.deleteFbUser(userFbId);
        } catch (FbUserNotDeleted fbUserNotDeleted) {
            fbUserNotDeleted.printStackTrace();
        }
        fbUsers = fbUserRepository.findByFbId(FbUser.class, userFbId);
        assertEquals(fbUsers.size(),0);
    }

    @Test(expected = FbUserNotDeleted.class)
    public void deleteFbUserWrongFdIdTest() throws FbUserNotDeleted {
        String userFbId = "10206248904662029";
        String wrongUserFbId = "10206249";
        FbUser fbUser = new FbUser();
        fbUser.setLastName("Sudora");
        fbUser.setFirstName("Simon");
        fbUser.setGender("male");
        fbUser.setFbId(userFbId);
        fbUserRepository.save(fbUser);
        fbUserServiceImpl.deleteFbUser(wrongUserFbId);

    }
}
