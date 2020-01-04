package api;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.ciyashop.library.apicall.OneLeggedApi10;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import java.util.concurrent.TimeUnit;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class PostApi {
    public Activity activity;
    public String paramsName;
    public String language;
    public OnResponseListner onResponseListner;

    public PostApi(Activity activity, String paramsName, OnResponseListner onResponseListner, String language) {
        this.activity = activity;
        this.paramsName = paramsName;
        this.onResponseListner = onResponseListner;
        this.language = language;
    }

    public void callPostApi(String urls, String json) {
        (new PostApi.postAPiCall()).execute(new String[]{urls, json});
    }

    public class postAPiCall extends AsyncTask<String, String, String> {
        Response response;

        public postAPiCall() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            if (params[0].contains("?")) {
                if (!PostApi.this.language.isEmpty() && PostApi.this.language.length() > 0) {
                    params[0] = params[0] + "&lang=" + PostApi.this.language;
                }
            } else if (!PostApi.this.language.isEmpty() && PostApi.this.language.length() > 0) {
                params[0] = params[0] + "?lang=" + PostApi.this.language;
            }

            Log.e(PostApi.this.paramsName, params[0]);
            String responsebody = "";

            try {
                String var10000;
                OAuthService service;
                label49: {
                    var10000 = params[0];
                    new URLS();
                    if (!var10000.contains(URLS.WOO_MAIN_URL)) {
                        var10000 = params[0];
                        new URLS();
                        if (!var10000.contains(URLS.NATIVE_API)) {
                            service = (new ServiceBuilder()).provider(OneLeggedApi10.class).apiKey(URLS.CONSUMERKEY).apiSecret(URLS.CONSUMERSECRET).signatureType(SignatureType.QueryString).debug().build();
                            break label49;
                        }
                    }

                    service = (new ServiceBuilder()).provider(OneLeggedApi10.class).apiKey(URLS.WOOCONSUMERKEY).apiSecret(URLS.WOOCONSUMERSECRET).signatureType(SignatureType.QueryString).debug().build();
                }

                OAuthRequest request;
                String parameters;
                label44: {
                    request = new OAuthRequest(Verb.POST, params[0]);
                    parameters = params[1];
                    Log.e("p", parameters);
                    request.addHeader("Content-Type", "application/json");
                    var10000 = params[0];
                    new URLS();
                    if (!var10000.contains(URLS.WOO_MAIN_URL)) {
                        var10000 = params[0];
                        new URLS();
                        if (!var10000.contains(URLS.NATIVE_API)) {
                            service.signRequest(new Token(URLS.OAUTH_TOKEN, URLS.OAUTH_TOKEN_SECRET), request);
                            break label44;
                        }
                    }

                    service.signRequest(new Token("9682jgjMxSV4fY3mF5i6AwYp", "bPPJSUMyrRrLAtKjRg01yiP3jVMXPpjTCcyr4TTVQjMki28G"), request);
                   // service.signRequest(new Token("", ""), request);
                }

                request.addPayload(parameters);
                request.setConnectTimeout(60, TimeUnit.SECONDS);

                try {
                    this.response = request.send();
                    if (this.response.isSuccessful()) {
                        responsebody = this.response.getBody();
                        Log.e("response", responsebody);
                    }
                } catch (Exception var7) {
                    Log.e("Exception is ", var7.getMessage());
                    return "OAuthConnectionException";
                }
            } catch (Exception var8) {
                Log.e("Exception is ", var8.getMessage());
            }

            return responsebody;
        }

        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.e(PostApi.this.paramsName + "Response is ", response.toString());
            PostApi.this.onResponseListner.onResponse(response, PostApi.this.paramsName);
        }
    }
}
