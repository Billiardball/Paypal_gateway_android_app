package com.shop.arram.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.customview.edittext.EditTextMedium;
import com.shop.arram.customview.edittext.EditTextRegular;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.model.LogIn;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener, OnResponseListner {

    private static final String TAG = LogInActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @BindView(R.id.tvNewUser)
    TextView tvNewUser;
    @BindView(R.id.google_login_button)
    SignInButton google_login_button;
    @BindView(R.id.fb_login_button)
    LoginButton fb_login_button;
    CallbackManager callbackManager;
    @BindView(R.id.etEmail)
    EditTextRegular etEmail;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.etPass)
    EditTextRegular etPass;
    @BindView(R.id.tvSignIn)
    TextViewLight tvSignIn;
    @BindView(R.id.tvForgetPass)
    TextViewLight tvForgetPass;
    @BindView(R.id.ivBlackBackButton)
    ImageView ivBlackBackButton;

    AlertDialog alertDialog;
    AlertDialog alertDialog1;
    private GoogleApiClient mGoogleApiClient;
    private String pin, email, password;
    private String facbookImageUrl;
    private JSONObject fbjsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        setScreenLayoutDirection();

        loginWithFB();
        setColor();

        if (Constant.APPLOGO != null && !Constant.APPLOGO.equals("")) {
            Picasso.with(this).load(Constant.APPLOGO).error(R.drawable.logo).into(ivLogo);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        google_login_button.setSize(SignInButton.SIZE_STANDARD);
        google_login_button.setScopes(gso.getScopeArray());

        ivBlackBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setColor() {
        tvNewUser.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvSignIn.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        tvForgetPass.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        Drawable mDrawable = getResources().getDrawable(R.drawable.login);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.OVERLAY));

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("hi", "onConnectionFailed:" + connectionResult);
    }

    @OnClick(R.id.tvSignIn)
    public void tvSignInClick() {
        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_email_address, Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isValidEmail(etEmail.getText().toString())) {
                if (etPass.getText().toString().isEmpty()) {
                    Toast.makeText(this, R.string.enter_password, Toast.LENGTH_SHORT).show();
                } else {
                    userlogin();
                }
            } else {
                Toast.makeText(this, R.string.enter_valid_email_address, Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void userlogin() {

        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(LogInActivity.this, RequestParamUtils.login, this, getlanuage());
            JSONObject object = new JSONObject();
            try {
                object.put(RequestParamUtils.email, etEmail.getText().toString());
                object.put(RequestParamUtils.PASSWORD, etPass.getText().toString());
                object.put(RequestParamUtils.deviceType, "2");
                String token = getPreferences().getString(RequestParamUtils.NOTIFICATION_TOKEN, "");
                object.put(RequestParamUtils.deviceToken, token);
                postApi.callPostApi(new URLS().LOGIN, object.toString());

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.tvSignInWithGoogle)
    public void tvSignInWithGoogleClick() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        signIn();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.

            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("hi", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String email = acct.getEmail();

            Log.e("hi", "Name: " + personName + ", email: " + email);

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.socialId, acct.getId());
                jsonObject.put(RequestParamUtils.email, acct.getEmail());
                jsonObject.put(RequestParamUtils.firstName, acct.getGivenName());
                jsonObject.put(RequestParamUtils.lastName, acct.getFamilyName());

                socialLogin(jsonObject);
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
        }
    }

    @OnClick(R.id.tvSignInwithFB)
    public void tvSignInwithFBClick() {

        LoginManager.getInstance().logOut();

        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList(RequestParamUtils.email, RequestParamUtils.publicProfile)
        );
    }

    public void loginWithFB() {
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                String accessToken = loginResult.getAccessToken()
                        .getToken();
                Log.e("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.e("LoginActivity", response.toString());
                                try {
                                    String id = object.getString("id");
                                    facbookImageUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
                                    // facbookImageUrl = "http://graph.facebook.com/" + id + "/picture?type=large";
                                    fbjsonObject = object;
                                    // if (fbjsonObject != null) {
                                    new getBitmap().execute();
//                                    }else {
//                                        socialLogin(fbjsonObject);
//                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender, first_name, last_name, picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e(TAG, "onError: " + exception.toString());
                // App code
            }
        });
    }

    public String getBitmap() {
        try {
            URL url = new URL(facbookImageUrl);
            try {


                Bitmap mIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mIcon.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();

                String encoded = Base64.encodeToString(b, Base64.DEFAULT);

                return encoded;
            } catch (IOException e) {
                Log.e("IOException ", e.getMessage());

            } catch (Exception e) {
                Log.e("Exception ", e.getMessage());

            }
            Log.e("Done", "Done");
        } catch (IOException e) {
            Log.e("Exception Url ", e.getMessage());
        }
        return null;
    }

    public void socialLogin(final JSONObject object) {
        if (Utils.isInternetConnected(LogInActivity.this)) {
            showProgress("");
            final PostApi postApi = new PostApi(LogInActivity.this, RequestParamUtils.socialLogin, LogInActivity.this, getlanuage());

            try {
                object.put(RequestParamUtils.deviceType, Constant.DEVICE_TYPE);

                String token = getPreferences().getString(RequestParamUtils.NOTIFICATION_TOKEN, "");
                object.put(RequestParamUtils.deviceToken, token);
                postApi.callPostApi(new URLS().SOCIAL_LOGIN, object.toString());

            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
        } else {
            Toast.makeText(LogInActivity.this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    // this part was missing thanks to wesely
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @OnClick(R.id.tvNewUser)
    public void tvNewUserClick() {
        Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tvForgetPass)
    public void tvForgetPassClick() {
        showForgetPassDialog();
    }

    public void showForgetPassDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_forget_password, null);
        dialogBuilder.setView(dialogView);

        TextViewRegular tvRequestPasswordReset = (TextViewRegular) dialogView.findViewById(R.id.tvRequestPasswordReset);
        tvRequestPasswordReset.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        final EditTextRegular etForgetPassEmail = (EditTextRegular) dialogView.findViewById(R.id.etForgetPassEmail);

        alertDialog1 = dialogBuilder.create();
        alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = alertDialog1.getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        alertDialog1.getWindow().setAttributes(lp);
        alertDialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        alertDialog1.show();

        tvRequestPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etForgetPassEmail.getText().toString().isEmpty()) {
                    Toast.makeText(LogInActivity.this, R.string.enter_email_address, Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.isValidEmail(etForgetPassEmail.getText().toString())) {
                        email = etForgetPassEmail.getText().toString();
                        forgetPassword();
                    } else {
                        Toast.makeText(LogInActivity.this, R.string.enter_valid_email_address, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void forgetPassword() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.forgotPassword, this, getlanuage());

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.email, email);
                postApi.callPostApi(new URLS().FORGET_PASSWORD, jsonObject.toString());

            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    public void showSetPassDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_forget_password_pin, null);
        dialogBuilder.setView(dialogView);

        final TextViewRegular tvSetNewPass = (TextViewRegular) dialogView.findViewById(R.id.tvSetNewPass);
        tvSetNewPass.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        final TextViewLight tvNowEnterPass = (TextViewLight) dialogView.findViewById(R.id.tvNowEnterPass);
        final EditTextMedium etPin = (EditTextMedium) dialogView.findViewById(R.id.etPin);
        final EditTextMedium etNewPassword = (EditTextMedium) dialogView.findViewById(R.id.etNewPassword);
        final EditTextMedium etConfirrmNewPassword = (EditTextMedium) dialogView.findViewById(R.id.etConfirrmNewPassword);

        alertDialog = dialogBuilder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        alertDialog.show();

        etPin.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPin.getText().toString().equals(pin)) {
                    tvNowEnterPass.setVisibility(View.VISIBLE);
                    etNewPassword.setVisibility(View.VISIBLE);
                    etConfirrmNewPassword.setVisibility(View.VISIBLE);
                }
            }
        });


        tvSetNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etPin.getText().toString().isEmpty()) {
                    Toast.makeText(LogInActivity.this, R.string.enter_pin, Toast.LENGTH_SHORT).show();
                } else {
                    if (etPin.getText().toString().equals(pin)) {
                        if (etNewPassword.getText().toString().isEmpty()) {
                            Toast.makeText(LogInActivity.this, R.string.enter_new_password, Toast.LENGTH_SHORT).show();
                        } else {
                            if (etConfirrmNewPassword.getText().toString().isEmpty()) {
                                Toast.makeText(LogInActivity.this, R.string.enter_confirm_password, Toast.LENGTH_SHORT).show();
                            } else {
                                if (etNewPassword.getText().toString().equals(etConfirrmNewPassword.getText().toString())) {
                                    //apicalls
                                    password = etNewPassword.getText().toString();
                                    updatePassword();

                                } else {
                                    Toast.makeText(LogInActivity.this, R.string.password_and_confirm_password_not_matched, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } else {
                        Toast.makeText(LogInActivity.this, R.string.enter_proper_detail, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void updatePassword() {

        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.updatePassword, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put(RequestParamUtils.email, email);
                jsonObject.put(RequestParamUtils.PASSWORD, password);
                jsonObject.put(RequestParamUtils.key, pin);

                postApi.callPostApi(new URLS().UPDATE_PASSWORD, jsonObject.toString());
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }

        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onResponse(final String response, final String methodName) {

        if (methodName.equals(RequestParamUtils.login) || methodName.equals(RequestParamUtils.socialLogin)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        final LogIn loginRider = new Gson().fromJson(
                                response, new TypeToken<LogIn>() {
                                }.getType());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //set call here
                                if (loginRider.status.equals("success")) {

                                    SharedPreferences.Editor pre = getPreferences().edit();
                                    pre.putString(RequestParamUtils.CUSTOMER, "");
                                    pre.putString(RequestParamUtils.ID, loginRider.user.id + "");
                                    pre.putString(RequestParamUtils.PASSWORD, etPass.getText().toString());
                                    if (methodName.equals(RequestParamUtils.socialLogin)) {
                                        pre.putString(RequestParamUtils.SOCIAL_SIGNIN, "1");
                                    }
                                    pre.commit();

//                                    Intent intent = new Intent(LogInActivity.this, AccountActivity.class);
//                                    startActivity(intent);

                                    dismissProgress();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), R.string.enter_proper_detail, Toast.LENGTH_SHORT).show(); //display in long period of time
                                }
                            }
                        });
                    } else {
                        String msg = jsonObj.getString("message");
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show(); //display in long period of time
                }
            }
        } else if (methodName.equals(RequestParamUtils.forgotPassword)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        alertDialog1.dismiss();
                        pin = jsonObj.getString("key");
                        showSetPassDialog();
                    } else {
                        Toast.makeText(this, jsonObj.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show(); //display in long period of time
                }
            }
        } else if (methodName.equals(RequestParamUtils.updatePassword)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        alertDialog.dismiss();
                        Toast.makeText(this, jsonObj.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, jsonObj.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show(); //display in long period of time
                }
            }
        }


    }

    class getBitmap extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return getBitmap();
        }

        @Override
        protected void onPostExecute(String encoded) {
            super.onPostExecute(encoded);
            try {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put(RequestParamUtils.data, encoded);
                jsonObject.put(RequestParamUtils.name, "image.jpg");

              /*  Log.e("name", fbjsonObject.getString("name"));
                Log.e("email", fbjsonObject.getString("email"));*/
                if (fbjsonObject.has(RequestParamUtils.gender)) {
                    Log.e("gender", fbjsonObject.getString(RequestParamUtils.gender));
                }

                fbjsonObject.put(RequestParamUtils.userImage, jsonObject);
                fbjsonObject.put(RequestParamUtils.socialId, fbjsonObject.getString("id"));
                socialLogin(fbjsonObject);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}