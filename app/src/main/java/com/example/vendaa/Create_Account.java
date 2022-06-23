package com.example.vendaa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public class Create_Account extends AppCompatActivity {
    Button buttonReg ;
    EditText editEmail;
    private SSLContext ClientSSLSocketFactory;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);

       buttonReg = (Button) findViewById(R.id.butttonReg);
       editEmail = (EditText) findViewById(R.id.edittEmail);

       buttonReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               final RequestQueue queue = Volley.newRequestQueue(Create_Account.this);

               String url = "https://sandbox.interswitchng.com/passport/oauth/token";
               HurlStack hurlStack = new HurlStack() {
                   @Override
                   protected HttpURLConnection createConnection(URL url) throws IOException {
                       HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
                       try {
                           httpsURLConnection.setSSLSocketFactory(getSSLSocketFactory());
                           httpsURLConnection.setHostnameVerifier(getHostnameVerifier());
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       return httpsURLConnection;
                   }
               };

               StringRequest postRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {

                   @Override
                   public void onResponse(String response) {

                       Toast.makeText(Create_Account.this,response,Toast.LENGTH_SHORT);
                       queue.stop();
                   }
               },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError volleyError) {
                               Toast.makeText(Create_Account.this,volleyError.toString(),Toast.LENGTH_SHORT);
                               volleyError.printStackTrace();
                               queue.stop();
                           }
                       })
               {

                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       Map<String,String> params=new HashMap<String,String>();
                       params.put("grant_type","client_credentials");

                       return params;

                   }

                   @Override
                   public Map<String, String> getHeaders() throws AuthFailureError {
                       Map<String, String> headers = new HashMap<>();
                       String auth = "Basic SUtJQTE4RjcxMEJEMzAyRjY3N0UwQzkzRjVGMTIxNDU4MDI4N0Y1NDRGODg6NUVEQzRBQjQyMDUzMzMzNkFBMEZFNjgwODRBQTc2QkVFNkI4RTRDMg==";
                       headers.put("Content-Type", "application/x-www-form-urlencoded");
                       headers.put("Authorization", auth);
                       return headers;


                   }

               };
               postRequest.setRetryPolicy(new DefaultRetryPolicy(
                       DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5,
                       DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                       DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
               queue.add(postRequest);
           }
       });


    }
    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //return true; // verify always returns true, which could cause insecure network traffic due to trusting TLS/SSL server certificates for wrong hostnames
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify("localhost", session);
            }
        };
    }
    private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
        return new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return originalTrustManager.getAcceptedIssuers();
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0){
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkClientTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
                            Log.w("checkClientTrusted", e.toString());
                        }
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        try {
                            if (certs != null && certs.length > 0){
                                certs[0].checkValidity();
                            } else {
                                originalTrustManager.checkServerTrusted(certs, authType);
                            }
                        } catch (CertificateException e) {
                            Log.w("checkServerTrusted", e.toString());
                        }
                    }
                }
        };
    }
    private SSLSocketFactory getSSLSocketFactory()
            throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = getResources().openRawResource(R.raw.venda); // this cert file stored in \app\src\main\res\raw folder path

        Certificate ca = cf.generateCertificate(caInput);
        caInput.close();
        String keyStoreType = KeyStore.getDefaultType();
        // start up a store with a key
        char[] keyStorePassword = "12345678".toCharArray();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(caInput, keyStorePassword);
        keyStore.setCertificateEntry("Venda", ca);


        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, wrappedTrustManagers, null);

        return sslContext.getSocketFactory();
    }
}

