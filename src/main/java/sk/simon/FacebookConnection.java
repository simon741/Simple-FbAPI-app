package sk.simon;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FacebookConnection {

    private Facebook facebook;

    public FacebookConnection(String accessToken) {
        accessToken = "EAAQx8CaKKZCoBABFDEdHi3lGi5ZBvpoKOIYFRghEiyZAujkcs1rxWVIaKznyQA239gOGAybPhW5h3wtP1qCszdYGOf0D4Enxhj6fL0DFywLfzAIbvSYm8s5K5XzbTirMtZANXkazU2PIlWYOWJUkr9RBZC0AOdZAE6BqEnmDqCaAZDZD";
        facebook = new FacebookTemplate(accessToken);    }

    
    public void helloFacebook(Model model) {

        String [] fields = { "id", "gender",  "first_name", "last_name" };
        // I did not use facebook.userOperations().getUserProfile() because this method is not compatible with Fb API 2.8
        User userProfile = facebook.fetchObject("10206248904662029", User.class, fields);
        System.out.println(userProfile.getId());
        System.out.println(userProfile.getGender());
        System.out.println(userProfile.getFirstName());
        System.out.println(userProfile.getLastName());
    }

}