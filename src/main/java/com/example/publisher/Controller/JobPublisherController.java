package com.example.publisher.Controller;

import com.example.publisher.Service.JobPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@Controller
public class JobPublisherController {

    @Autowired
    private JobPublisherService jobPublisherService;
    private String CLIENT_ID="77kn8autbvgluw";

    private String CLIENT_SECRET= "kUUVwkS4ZwO29BLa";;

    private  String REDIRECT_URI= "https://oauth.pstmn.io/v1/callback";;

    private  String SCOPE= "openid email w_member_social profile";
    private String authorizationToken;
    private String accessToken;
    @GetMapping("/authroization")
    public String getAuthorizationToken() throws UnsupportedEncodingException, URISyntaxException {
        authorizationToken = jobPublisherService.authorizationCode(CLIENT_ID,REDIRECT_URI,SCOPE);

        return authorizationToken;
    }
    @GetMapping("/access")
    public String getAccessToken() throws IOException, URISyntaxException, ExecutionException, InterruptedException {
        accessToken =  jobPublisherService.authorizationTokenForAccess(authorizationToken,CLIENT_ID,CLIENT_SECRET,REDIRECT_URI);

        return accessToken;
    }

}
