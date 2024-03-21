package com.example.publisher.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Service
public class JobPublisherService {




    public static String authorizationCode(String client_id,String redirect_uri,String scope) throws URISyntaxException, UnsupportedEncodingException {
        String url = "https://www.linkedin.com/oauth/v2/authorization";

        Map<String, String> parameters = new HashMap<>();
        parameters.put("response_type", "code");
        parameters.put("client_id", client_id);
        parameters.put("redirect_uri", redirect_uri);
        parameters.put("scope", scope);

        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?");

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            stringBuilder.append("&");
        }


        stringBuilder.setLength(stringBuilder.length() - 1);

        String authorizationUrl = stringBuilder.toString();

        System.out.println("Please log in to LinkedIn by opening the following URL in your browser:");
        System.out.println(authorizationUrl);

        System.out.println("Paste the URL you were redirected to after logging in:");
        Scanner scanner = new Scanner(System.in);
        String redirectUrl = scanner.nextLine();


        URI uri = new URI(redirectUrl);
        String query = uri.getQuery();
        String[] pairs = query.split("&");
        String authorizationCode = null;
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("code")) {
                authorizationCode = keyValue[1];
                break;
            }
        }

        return authorizationCode;
    }

    public static String authorizationTokenForAccess(String authorizationCode,String client_id,String client_secret,String redirect_uri)
            throws URISyntaxException, InterruptedException, ExecutionException, IOException {
        String url = "https://www.linkedin.com/oauth/v2/accessToken";

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("code", authorizationCode);
        parameters.put("client_id", client_id);
        parameters.put("client_secret", client_secret);
        parameters.put("redirect_uri", redirect_uri);

        URIBuilder uriBuilder = new URIBuilder(url);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }
        URI uri = uriBuilder.build();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);

        HttpResponse response = client.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());

        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("Access Token Response:");
            System.out.println(responseBody);

            String accessToken = GetAccessTokenFromResponse(responseBody);
            return accessToken;
        } else {
            System.out.println("Failed to exchange authorization code for access token.");
            System.out.println("Status Code: " + response.getStatusLine().getStatusCode());
            return null;
        }
    }

    private static String GetAccessTokenFromResponse(String responseContent) {
        int accessTokenStartIndex = responseContent.indexOf("access_token") + 15;
        int accessTokenEndIndex = responseContent.indexOf('"', accessTokenStartIndex);
        String accessToken = responseContent.substring(accessTokenStartIndex, accessTokenEndIndex);
        return accessToken;
    }



}

