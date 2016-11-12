package sk.simon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.simon.exceptions.FbUserNotDeleted;
import sk.simon.exceptions.FbUserNotFound;
import sk.simon.exceptions.FbUserNotRetrieved;
import sk.simon.exceptions.FbUserNotSaved;

/**
 * Created by Simon on 10.11.2016.
 */

@RestController
@RequestMapping(value = "/users")
public class FbUserRestController {

    @Autowired
    private FbUserService fbUserService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrieveAndSaveFbUser(@RequestBody FbUserAccessDetailsDTO fbUserAccessDetails){
        try {
            fbUserService.retrieveAndSaveFbUser(fbUserAccessDetails.getAccessToken(),fbUserAccessDetails.getUserId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("User details were successfully retrieved from Fb and store in database ");
        } catch (FbUserNotSaved fbUserNotSaved) { //vyhodit erroro cody
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User was not stored into database");
        } catch (FbUserNotRetrieved fbUserNotRetrieved) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User details was not retrieved from Facebook");        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserAndHisPages(@PathVariable String userId){
        try {
            fbUserService.deleteFbUser(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User was successfully deleted from database");
        } catch (FbUserNotDeleted fbUserNotDeleted) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User was not deleted from database");
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FbUserEntity getFbUserDetails(@PathVariable String userId){
        try {
            return fbUserService.getUserByFbId(userId);
        } catch (FbUserNotFound fbUserNotFound) {
            return new FbUserEntity();
        }
    }

}
