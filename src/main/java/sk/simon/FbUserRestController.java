package sk.simon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.simon.exceptions.FbUserNotRetrieved;
import sk.simon.exceptions.FbUserNotSaved;

/**
 * Created by Simon on 10.11.2016.
 */

@RestController
@RequestMapping("/users")
public class FbUserRestController {

    @Autowired
    private FbUserService fbUserService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrieveAndSaveFbUser(@RequestBody FbUserAccessDetailsDTO fbUserAccessDetails){
        try {
            fbUserService.retrieveAndSaveFbUser(fbUserAccessDetails.getAccessToken(),fbUserAccessDetails.getUserId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("User details were successfully tetrieved form Fb and store in database ");
        } catch (FbUserNotSaved fbUserNotSaved) { //vyhodit erroro cody
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User was not stored into database");
        } catch (FbUserNotRetrieved fbUserNotRetrieved) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User details was not retrieved from Facebook");        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUserAndHisPages(){

    }

    @RequestMapping(method = RequestMethod.GET)
    public void getFbUserDetails(){

    }

}
