package com.example.vendaa;

import com.example.vendaa.utils.InterswitchAuth;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class AuthentcateUser {
    public static final String QUICKTELLER_BASE_URL = "http://sandbox.interswitchng.com/api/v1/quickteller";
    public static final String QUICKTELLER_BASE_URL2 = "https://sandbox.interswitchng.com/api/v1/quickteller";

    private static final String TIMESTAMP = "TIMESTAMP";
    private static final String NONCE = "NONCE";
    private static final String SIGNATURE_METHOD = "SIGNATURE_METHOD";
    private static final String SIGNATURE = "SIGNATURE";
    private static final String AUTHORIZATION = "AUTHORIZATION";

    private static final String CLIENT_ID = "your client ID";
    private static final String CLIENT_SECRET = "your client secret";

    public static void main(String args[]) throws NoSuchAlgorithmException,
            ClientProtocolException, JSONException, IOException {
        authenticateUser();
    }

    public static void authenticateUser() throws NoSuchAlgorithmException,
            ClientProtocolException, IOException, JSONException {

        // Quickteller User email address.
        String quicktellerUser = "smusa87@gmail.com";

        // Quickteller User Password.
        String quicktellerUserPassword = "6553";

        // Authentication is done via a POST Method.
        String httpMethod = "POST";

        // This is the request resource URL.
        String resourceUrl = QUICKTELLER_BASE_URL + "/users/" + quicktellerUser
                + "/authentications";

        String resourceUrl2 = QUICKTELLER_BASE_URL2 + "/users/"
                + quicktellerUser + "/authentications";

        // get clientId from Interswitch Developer Console.
        String clientId = CLIENT_ID;

        // get clientSecretKey from Interswitch Developer Console
        String clientSecretKey = CLIENT_SECRET;

        // Signature Method is the discretion of developer,
        // but we recommend at least SHA-256
        String signatureMethod = "SHA-256";

        // JSONObject is used to properly generate json string for the request
        // body.
        JSONObject json = new JSONObject();

        //add password to json
        json.put("password", quicktellerUserPassword);

//        StringWriter out = new StringWriter();
//        JsonWriter kson = new JsonWriter(json);
//        kson.beginObject();
//
//        out.write(json);
//        json.write(out);

        String data = json.toString();

        // This our Authorization details that we'll add to our headers,
        // the InterswitchAuth configuration can be found under Authentications
        // above.
        HashMap<String, String> interswitchAuth = InterswitchAuth
                .generateInterswitchAuth(httpMethod, resourceUrl2, clientId,
                        clientSecretKey, "", signatureMethod);

        // Write HTTP request to post
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(resourceUrl);

        // Set headers for authorization
        post.setHeader("Authorization", interswitchAuth.get(AUTHORIZATION));
        post.setHeader("Timestamp", interswitchAuth.get(TIMESTAMP));
        post.setHeader("Nonce", interswitchAuth.get(NONCE));
        post.setHeader("Signature", interswitchAuth.get(SIGNATURE));
        post.setHeader("SignatureMethod", interswitchAuth.get(SIGNATURE_METHOD));

        StringEntity entity = new StringEntity(data);

        entity.setContentType("application/json");

        // attach json to body of request
        post.setEntity(entity);

        // post
        HttpResponse response = client.execute(post);

        // Get response Code
        int responseCode = response.getStatusLine().getStatusCode();

        // get response string
        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        StringBuffer responseString = new StringBuffer();

        int c;
        // Reading Response from server
        while ((c = inputStream.read()) != -1) {
            responseString.append((char) c);
        }

        // Printout response code
        System.out.println(responseCode);

        // Printout response string
        System.out.println(responseString);
    }

}
