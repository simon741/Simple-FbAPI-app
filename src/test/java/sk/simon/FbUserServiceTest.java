package sk.simon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Simon on 10.11.2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FbUserServiceTest {

    @Autowired
    private FbUserServiceImpl fbUserServiceImpl;

    @Autowired
    private FbUserRepository fbUserRepository;

    @Test
    public void retrieveAndSaveFbUserTest(){
        String accessToken = "EAAQx8CaKKZCoBAMyT0QHfYdoHSJwMAwVTDXx01bRwhcoZBDDO1PnUEEZCOy2jCMj1MIcfMhA1wCZCaLPrio3PqUV4BQZC271g4dhFjvcR9wLZA8ZAmBVgngv6TT4VxV84FT613vi56ZCKtSRaPFPY5kzZAWDFTZCM8dgEgFvZC5zg8yNgZDZD";
        String userId = "10206248904662029";
        fbUserServiceImpl.retrieveAndSaveFbUser(accessToken, userId);

        List<FbUser> fbUsers = null;
        fbUsers = fbUserRepository.findByFbId(userId);
        assertNotNull(fbUsers);
    }
}
