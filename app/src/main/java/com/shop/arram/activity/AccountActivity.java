package com.shop.arram.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.adapter.ChangeLanguageItemAdapter;
import com.shop.arram.adapter.InfoPageAdapter;
import com.shop.arram.customview.Switch;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.javaclasses.SyncWishList;
import com.shop.arram.model.Customer;
import com.shop.arram.model.InfoPages;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends BaseActivity implements OnResponseListner, OnItemClickListner {

    @BindView(R.id.tvCustomerName)
    TextViewBold tvCustomerName;

    @BindView(R.id.tvCurrancy)
    TextViewBold tvCurrancy;

    @BindView(R.id.tvLanguage)
    TextViewBold tvLanguage;

    @BindView(R.id.tvCustomerPhone)
    TextViewLight tvCustomerPhone;

    @BindView(R.id.tvCustomerEmail)
    TextViewLight tvCustomerEmail;

    @BindView(R.id.tvLogIn)
    TextViewBold tvLogIn;

    @BindView(R.id.tvCustomerEmailLogin)
    TextViewLight tvCustomerEmailLogin;

    @BindView(R.id.profile_image)
    CircleImageView profile_image;

    @BindView(R.id.rvInfoPages)
    RecyclerView rvInfoPages;

    @BindView(R.id.swNotification)
    Switch swNotification;

    @BindView(R.id.ivEdit)
    ImageView ivEdit;

    @BindView(R.id.tvMyPoint)
    TextViewLight tvMyPoint;

    @BindView(R.id.RewardPointLine)
    TextViewRegular RewardPointLine;

    @BindView(R.id.svHome)
    NestedScrollView svHome;

    @BindView(R.id.llDownload)
    LinearLayout llDownload;

    private InfoPageAdapter infoPageAdapter;
    private Customer customer = new Customer();

    private String customerId;
    public static AlertDialog alert;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        setToolbarTheme();
        showDownload();
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.account));
        hideSearchNotification();
        showBackButton();
        setBottomBar("account", svHome);
        infoPages();

        customerId = getPreferences().getString(RequestParamUtils.ID, "");
        if (getPreferences().getBoolean(RequestParamUtils.NOTIFICATIONSTATUS, true)) {
            swNotification.setChecked(true);
        } else {
            swNotification.setChecked(false);
        }
        setToolBarTheme();
        setTheme();
        if (Constant.IS_CURRENCY_SWITCHER_ACTIVE && Constant.CurrencyList.size() > 1) {
            tvCurrancy.setVisibility(View.VISIBLE);
        } else {
            tvCurrancy.setVisibility(View.GONE);
        }
        if (Constant.IS_WPML_ACTIVE && Constant.LANGUAGELIST.size() > 1) {
            tvLanguage.setVisibility(View.VISIBLE);
        } else {
            tvLanguage.setVisibility(View.GONE);
        }
    }

    public void showDownload() {
        if (Constant.IS_DOWNLOAD_SHOW) {
            llDownload.setVisibility(View.VISIBLE);
        } else {
            llDownload.setVisibility(View.GONE);
        }
    }

    public void setToolBarTheme() {
        try {
            Drawable mDrawable = getResources().getDrawable(R.drawable.account_bg);
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.OVERLAY));
            if (!Constant.IS_REWARD_POINT_ACTIVE) {
                tvMyPoint.setVisibility(View.GONE);
                RewardPointLine.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Log.e("Exception is ", e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setTheme() {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked},
        };

        int[] thumbColors = new int[]{
                Color.GRAY,
                Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)),
        };

        int[] trackColors = new int[]{
                Color.GRAY,
                Color.parseColor(getPreferences().getString(Constant.APP_TRANSPARENT, Constant.PRIMARY_COLOR)),
        };

        DrawableCompat.setTintList(DrawableCompat.wrap(swNotification.getThumbDrawable()), new ColorStateList(states, thumbColors));
        DrawableCompat.setTintList(DrawableCompat.wrap(swNotification.getTrackDrawable()), new ColorStateList(states, trackColors));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("OnResume call", "called OnResume");

    }

    @OnCheckedChanged(R.id.swNotification)
    public void swNotificationOnCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isNotificationSend(1);

        } else {
            isNotificationSend(2);
        }
    }

    public void setCustomerData() {
        if (customer.id == 0) {
            profile_image.setVisibility(View.VISIBLE);
            ivEdit.setVisibility(View.INVISIBLE);
            Picasso.with(AccountActivity.this).load(R.drawable.man)
                    .placeholder(R.drawable.man)
                    .error(R.drawable.man)
                    .into(profile_image);
            tvCustomerName.setText("");
            tvCustomerPhone.setText("");
            tvCustomerEmail.setText("");
            tvLogIn.setText(R.string.login);
//            tvCustomerEmailLogin.setVisibility(View.GONE);
//            tvCustomerEmailLogin.setText("");
        } else {
            if (customer.pgsProfileImage == null) {
                profile_image.setVisibility(View.VISIBLE);
            } else {
                profile_image.setVisibility(View.VISIBLE);
                Picasso.with(this).load(customer.pgsProfileImage).placeholder(R.drawable.man)
                        .error(R.drawable.man).into(profile_image);
            }
            ivEdit.setVisibility(View.VISIBLE);
            tvCustomerName.setText(customer.firstName + " " + customer.lastName);
            tvCustomerPhone.setText(customer.billing.phone + "");

            try {
                JSONObject jsonObject = new JSONObject(getPreferences().getString(RequestParamUtils.CUSTOMER, ""));
                if (jsonObject.equals("")) {
                    tvCustomerPhone.setText("");
                } else {
                    JSONArray jsonArray = jsonObject.getJSONArray(RequestParamUtils.metaData);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jb = jsonArray.getJSONObject(i);
                        if (jb.getString(RequestParamUtils.key).toLowerCase().equals(RequestParamUtils.mobile)) {
                            //mobile
                            tvCustomerPhone.setText(jb.getString(RequestParamUtils.value) + "");
                        }
                    }
                }

            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }

            tvCustomerEmail.setText(customer.email + "");
            tvLogIn.setText(R.string.sign_out);
//            tvCustomerEmailLogin.setVisibility(View.VISIBLE);
//            tvCustomerEmailLogin.setText(customer.email + "");
        }
    }


    @OnClick(R.id.tvDownload)
    public void tvDownloadClick() {
        if (customerId != null && !customerId.equals("")) {
            Intent intent = new Intent(this, DownloadActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }
    }


    @OnClick(R.id.tvRateUs)
    public void tvRateUsClick() {
        Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }

    @OnClick(R.id.tvCurrancy)
    public void tvCurrancyClick() {
        List<String> listItems = new ArrayList<String>();
        for (int i = 0; i < Constant.CurrencyList.size(); i++) {

            String Name = Constant.CurrencyList.get(i);
            try {
                JSONObject obj = new JSONObject(Name);
                String htmlText = "<html><font color='#8E8E8E'>" + " " + obj.get(RequestParamUtils.SYMBOL).toString() + "</font></html>";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    listItems.add(obj.get(RequestParamUtils.NAME).toString() + " (" + (Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)) + " )");
                } else {
                    listItems.add(obj.get(RequestParamUtils.NAME).toString() + " (" + (Html.fromHtml(htmlText)) + " )");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);

        TextViewRegular title = new TextViewRegular(this);
        title.setText(getString(R.string.currency));
        title.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.HEADER_COLOR, Constant.HEAD_COLOR)));
        title.setPadding(10, 25, 10, 25);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(title);
        builder.setItems(charSequenceItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Constant.IS_CURRENCY_SET = true;
                SharedPreferences.Editor pre = getPreferences().edit();

                for (int i = 0; i < Constant.CurrencyList.size(); i++) {
                    if (i == item) {
                        String Name = Constant.CurrencyList.get(i);
                        try {
                            JSONObject obj = new JSONObject(Name);
                            String htmlText = obj.get(RequestParamUtils.NAME).toString();
                            pre.putString(RequestParamUtils.CurrencyText, "/?currency=" + htmlText);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                pre.commit();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @OnClick(R.id.tvLanguage)
    public void tvLanguageClick() {

        showLanguageDialog();

    }

    public void showLanguageDialog() {


        TextViewRegular title = new TextViewRegular(this);
        title.setText(getString(R.string.language));
        title.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.HEADER_COLOR, Constant.HEAD_COLOR)));
        title.setPadding(10, 25, 10, 25);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ChangeLanguageItemAdapter mAdapter;
        builder.setCustomTitle(title);
        final View alertView = getLayoutInflater().inflate(R.layout.language_dialog, null, false);

        RecyclerView rvDisplayitems = (RecyclerView) alertView.findViewById(R.id.rvDisplayitems);
        mAdapter = new ChangeLanguageItemAdapter(AccountActivity.this, Constant.LANGUAGELIST, AccountActivity.this);
        rvDisplayitems.setHasFixedSize(true);
        rvDisplayitems.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDisplayitems.setLayoutManager(mLayoutManager);
        rvDisplayitems.setAdapter(mAdapter);

        builder.setView(alertView);
        alert = builder.create();
        alert.show();
    }


    @OnClick(R.id.profile_image)
    public void profile_imageClick() {
        if (!customerId.equals("")) {
            selectImage();
        }
    }

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.take_photo), getString(R.string.choose_from_library),
                getString(R.string.cancel)};

        TextViewRegular title = new TextViewRegular(this);
        title.setText(R.string.add_photo);
        title.setBackgroundColor(Color.BLACK);
        title.setPadding(10, 25, 10, 25);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);


        AlertDialog.Builder builder = new AlertDialog.Builder(
                AccountActivity.this);

        builder.setCustomTitle(title);

        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.take_photo))) {
                    //Camera
                    if (mayRequestPermission()) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 0);
                    }
                } else if (items[item].equals(getString(R.string.choose_from_library))) {
                    //Gallery
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
                } else if (items[item].equals(getString(R.string.cancel))) {

                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private boolean mayRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1212);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1212) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                bindView();
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code

            } else {
                Snackbar.make(tvCustomerName, R.string.permission_need, Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            @TargetApi(Build.VERSION_CODES.M)
                            public void onClick(View v) {
                                final Intent i = new Intent();
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImage = null;
        switch (requestCode) {
            case 0:
                //Camera
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    uploadUserImage(selectedImage, photo);
//                    imageview.setImageURI(selectedImage);
                }

                break;
            case 1:
                //Gallery
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    if (selectedImage != null) {
                        uploadUserImage(selectedImage, null);
                    }
//                    imageview.setImageURI(selectedImage);
                }
                break;
        }


    }

    @OnClick(R.id.tvAddress)
    public void tvAddressClick() {
        if (customerId.equals("")) {
            setLogin();
        } else {
            Intent intent = new Intent(AccountActivity.this, MyAddressActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.tvMyPoint)
    public void tvMyPointClick() {
        if (customerId.equals("")) {
            setLogin();
        } else {
            Intent intent = new Intent(AccountActivity.this, MyPointActivity.class);
            intent.putExtra(RequestParamUtils.USER_ID, customerId);
            startActivity(intent);
        }
    }


    @OnClick(R.id.tvAccountSetting)
    public void tvAccountSettingClick() {
        if (customerId.equals("")) {
            setLogin();
        } else {
            Intent intent = new Intent(AccountActivity.this, AccountSettingActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.tvLogIn)
    public void tvLogInClick() {
        if (tvLogIn.getText().toString().equals(getResources().getString(R.string.login))) {
            setLogin();
        } else {
            setLogoutDialog();
        }
    }

    public void setLogin() {
        Intent intent = new Intent(AccountActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvClearHistory)
    public void tvClearHistoryClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.this_permentantly_clear_you_history));
        builder.setTitle(getResources().getString(R.string.clear_history));
        builder.setCancelable(false);
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton(getResources().getString(R.string.claer), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AccountActivity.this);
                databaseHelper.clearCart();
                databaseHelper.clearRecentItem();
                databaseHelper.clearWhishlist();
                databaseHelper.clearSearch();
                getPreferences().edit().putString(RequestParamUtils.LANGUAGE, "").commit();
                getPreferences().edit().putBoolean(RequestParamUtils.iSSITELANGUAGECALLED, false).commit();
                getPreferences().edit().putString(RequestParamUtils.DEFAULTLANGUAGE, "").commit();
                getPreferences().edit().putBoolean(Constant.RTL, false).commit();

                Constant.IS_RTL = false;

                Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
    }

    public void setLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.do_you_really_want_to_signout));
        builder.setTitle(getResources().getString(R.string.sign_out));
        builder.setCancelable(false);
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AccountActivity.this, R.string.log_out_success,
                        Toast.LENGTH_LONG).show();
                SharedPreferences.Editor pre = getPreferences().edit();
                pre.putString(RequestParamUtils.CUSTOMER, "");
                pre.putString(RequestParamUtils.ID, "");
                pre.commit();
                LoginManager.getInstance().logOut();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                }
                customer = new Customer();
                checkLoggedin();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(getResources().getColor(R.color.colorPrimary));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @OnClick(R.id.tvMyOrder)
    public void tvMyOrderClick() {
        Intent intent = new Intent(AccountActivity.this, MyOrderActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvAboutUs)
    public void tvAboutUsClick() {
        Intent intent = new Intent(AccountActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvContactUs)
    public void tvContactUsClick() {
        Intent intent = new Intent(AccountActivity.this, ContactUsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvMyRewars)
    public void tvMyRewarsClick() {
        Intent intent = new Intent(AccountActivity.this, RewardsActivity.class);
        startActivity(intent);
    }


    public void isNotificationSend(int status) {

        if (Constant.DEVICE_TOKEN == null || Constant.DEVICE_TOKEN.equals("")) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Constant.DEVICE_TOKEN = refreshedToken;
        }

        if (Utils.isInternetConnected(this)) {
            showProgress("");

            PostApi postApi = new PostApi(this, RequestParamUtils.isNotificationSend, this, getPreferences().getString(RequestParamUtils.LANGUAGE, ""));
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.deviceToken, Constant.DEVICE_TOKEN);
                jsonObject.put(RequestParamUtils.deviceType, "2");
                jsonObject.put(RequestParamUtils.status, "" + status);
                postApi.callPostApi(new URLS().NOTIFICATIONSTATUS, jsonObject.toString());
            } catch (JSONException e) {
                Log.e("error", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }

    public void customerAccount() {

        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.customer, this, getPreferences().getString(RequestParamUtils.LANGUAGE, ""));
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.user_id, customerId);
                postApi.callPostApi(new URLS().CUSTOMER, jsonObject.toString());
            } catch (JSONException e) {
                Log.e("error", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }

    public void infoPages() {
        if (Utils.isInternetConnected(this)) {
            if (Constant.INFO_PAGE_DATA.equals("")) {
                PostApi postApi = new PostApi(this, RequestParamUtils.infoPages, this, getlanuage());
                postApi.callPostApi(new URLS().INFO_PAGES, "");
            } else {
                setInfoPages();
                checkLoggedin();
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }

    public void uploadUserImage(Uri image, Bitmap bitmap) {
        PostApi postApi = new PostApi(this, RequestParamUtils.updateUserImage, this, getlanuage());
        try {

            if (bitmap == null) {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();

            String encoded = Base64.encodeToString(b, Base64.DEFAULT);

            JSONObject jsonObject = new JSONObject();
            JSONObject object = new JSONObject();

            object.put(RequestParamUtils.data, encoded);
            object.put(RequestParamUtils.name, "image.jpg");

            jsonObject.put(RequestParamUtils.userImage, object);
            jsonObject.put(RequestParamUtils.user_id, customerId);

            postApi.callPostApi(new URLS().UPDATE_USER_IMAGE, jsonObject.toString());

        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }


    @Override
    public void onResponse(final String response, String methodName) {

        if (methodName.equals(RequestParamUtils.customer)) {


            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("error")) {
                        String msg = jsonObj.getString("message");
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    customer = new Gson().fromJson(
                            response, new TypeToken<Customer>() {
                            }.getType());

                    SharedPreferences.Editor pre = getPreferences().edit();
                    pre.putString(RequestParamUtils.CUSTOMER, response);
                    pre.commit();
                    setCustomerData();
                }
            }
            dismissProgress();
            new SyncWishList(AccountActivity.this).syncWishList(getPreferences().getString(RequestParamUtils.ID, ""), false);
        } else if (methodName.equals(RequestParamUtils.infoPages)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        //get data and show it
                        Constant.INFO_PAGE_DATA = response;
                        setInfoPages();

                    } else {
                        String msg = jsonObj.getString("message");
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
            checkLoggedin();

        } else if (methodName.equals(RequestParamUtils.updateUserImage)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        customer = new Gson().fromJson(
                                response, new TypeToken<Customer>() {
                                }.getType());

                        SharedPreferences.Editor pre = getPreferences().edit();
                        pre.putString(RequestParamUtils.CUSTOMER, response);
                        pre.commit();
                        setCustomerData();
                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        } else if (methodName.equals(RequestParamUtils.isNotificationSend)) {
            dismissProgress();
            if (response != null && response.length() > 0) {

                Log.e("Response is ", response + "");
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {

                        SharedPreferences.Editor pre = getPreferences().edit();
                        pre.putBoolean(RequestParamUtils.NOTIFICATIONSTATUS, swNotification.isChecked());
                        pre.commit();
                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        }
    }

    public void setInfoPages() {
        final InfoPages infoPageRider = new Gson().fromJson(
                Constant.INFO_PAGE_DATA, new TypeToken<InfoPages>() {
                }.getType());
        infoPageAdapter = new InfoPageAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvInfoPages.setLayoutManager(mLayoutManager);
        rvInfoPages.setAdapter(infoPageAdapter);
        rvInfoPages.setNestedScrollingEnabled(false);
        infoPageAdapter.addAll(infoPageRider.data);

    }

    public void checkLoggedin() {
        customerId = getPreferences().getString(RequestParamUtils.ID, "");
        String cust = getPreferences().getString(RequestParamUtils.CUSTOMER, "");
        if (cust.equals("")) {
            if (!customerId.isEmpty()) {
                customerAccount();
            }
        } else {
            AccessToken.setCurrentAccessToken(null);
            customer = new Gson().fromJson(
                    cust, new TypeToken<Customer>() {
                    }.getType());
        }
        setCustomerData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        checkLoggedin();
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {
        alert.dismiss();

        cleardatabasedata();

        getPreferences().edit().putBoolean(RequestParamUtils.iSSITELANGUAGECALLED, false).commit();
        getPreferences().edit().putString(RequestParamUtils.DEFAULTLANGUAGE, "").commit();

        setLocaleByLanguageChange(getPreferences().getString(RequestParamUtils.LANGUAGE, ""));
    }

    private void cleardatabasedata() {
        DatabaseHelper databaseHelper = new DatabaseHelper(AccountActivity.this);
        databaseHelper.clearCart();
        databaseHelper.clearRecentItem();
        databaseHelper.clearWhishlist();
        databaseHelper.clearSearch();
    }

}