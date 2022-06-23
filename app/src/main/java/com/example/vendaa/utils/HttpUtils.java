package com.example.vendaa.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.logging.Level;
import java.util.logging.Logger;


public class HttpUtils {
    // the signature of our app
    private static final String signatureMethod = "SHA1";
    //get the user
    private final static Logger log = Logger.getLogger(HttpUtils.class.getName());

    //This function make a post request
    // it  has the following parametters
    // 1. URL., The data, the ID and secret key plus terminal ID

    public String postClient(String url, Object reqData, String quicktellerClientId, String quicktellerClientSecret, String terminalId) throws Exception {
        //Utills create encoding and manage certificate
        Utils utils = new Utils();
        //time stamp
        long timestamp = utils.getCurrentTime();

        // any random number unique to a transaction
        String nonce = utils.nonce();

        //you are require to send schema along with your headers
        String signature = utils.signature(quicktellerClientId, quicktellerClientSecret, signatureMethod, url, timestamp, nonce, "POST");

        // This would creat a http connection

        HttpClient client = HttpClientBuilder.create().build();
        //this would turn my client into base64
        String clientIdBase64 = new String(Base64.encodeBase64(quicktellerClientId
                .getBytes()));
        // request would hold the url and if it logged an error would appear asking for parameteres
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "InterswitchAuth " + clientIdBase64);
        request.setHeader("Timestamp", "" + timestamp);
        request.setHeader("Nonce", nonce);
        request.setHeader("SignatureMethod", signatureMethod);
        request.setHeader("Signature", signature);
        request.setHeader("TerminalId", terminalId);

        // this would map the above data in reqData into
        //objectmapper would read from json or write into json
        String req = new ObjectMapper().writeValueAsString(reqData);
        //print the data to be submitted
        log.log(Level.INFO, req);
        log.log(Level.INFO, url);

        //load the data to the url using objectmapper object
        request.setEntity(new StringEntity(req));
        //this object would hold the response after execution
        HttpResponse serviceResponse = client.execute(request);
        //this would display the json code on the board
        log.log(Level.INFO, "" + serviceResponse.getStatusLine().getStatusCode());

        String resp = EntityUtils.toString(serviceResponse.getEntity());
        //this would display the response on the board as string
        log.log(Level.INFO, resp);
        return resp;

    }

    public String getClient(String url, String quicktellerClientId, String quicktellerClientSecret, String terminalId) throws Exception {
        log.log(Level.INFO, url);

        Utils utils = new Utils();
        long timestamp = utils.getCurrentTime();
        String nonce = utils.nonce();

        String signature = utils.signature(quicktellerClientId, quicktellerClientSecret, signatureMethod, url, timestamp, nonce, "GET");

        HttpClient client = HttpClientBuilder.create().build();
        String clientIdBase64 = new String(Base64.encodeBase64(quicktellerClientId
                .getBytes()));
        // request would hold the url and if it logged an error would appear asking for parameteres
        HttpGet request = new HttpGet(url);

        // add request header
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "InterswitchAuth " + clientIdBase64 );
        request.setHeader("Timestamp", "" + timestamp);
        request.setHeader("Nonce", nonce);
        request.setHeader("SignatureMethod", signatureMethod);
        request.setHeader("Signature", signature);
        request.setHeader("TerminalId", terminalId);

        HttpResponse serviceResponse = client.execute(request);

        log.log(Level.INFO, "" + serviceResponse.getStatusLine().getStatusCode());
        String resp = EntityUtils.toString(serviceResponse.getEntity());
        log.log(Level.INFO, resp);
        return resp;

    }
}
