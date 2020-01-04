package com.shop.arram.payment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.activity.ThankYouActivity;
import com.shop.arram.model.Customer;
import com.shop.arram.payment.api.Billing_address;
import com.shop.arram.payment.api.Billing_order;
import com.shop.arram.payment.api.Klarnaservice;
import com.shop.arram.payment.api.Lines_items;
import com.shop.arram.payment.api.Sendorder;
import com.shop.arram.payment.api.Shipping_address;
import com.shop.arram.payment.api.Shipping_lines;
import com.shop.arram.payment.api.Shipping_order;
import com.shop.arram.utils.BaseActivity;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.ShippingAddress;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.ServiceGenerator_other;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * THIS FILE IS OVERWRITTEN BY `androidSDK/src/<general|partner>sampleAppJava.
 * ANY UPDATES TO THIS FILE WILL BE REMOVED IN RELEASES.
 * <p>
 * Basic sample using the SDK to make a payment or consent to future payments.
 * <p>
 * For sample mobile backend interactions, see
 * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
 */

public class PaymentPaypalActivity extends BaseActivity {
    private static final String TAG = "paymentExample";
    /**
     * - Set to PayPalConfiguration.ENVIRONMENT_PRODUCTION to move real money.
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_SANDBOX to use your test credentials
     * from https://developer.paypal.com
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires
     * without communicating to PayPal's servers.
     */
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private static final String CONFIG_ENVIRONMENT_LIVE = PayPalConfiguration.ENVIRONMENT_PRODUCTION;

    // note that these credentials will differ between live & sandbox environments.
    //Developer TEST
    private static final String CONFIG_CLIENT_ID = "AZAuLB4eU43EaGETj8SsOY2rLz8maP4CH4bwEF5Bg6yR51D_9HANYiCjbCIdJbEWObs_eZ1Go-RsJAUz";

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT_LIVE)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    private String payment_request = "", payment_response = "";

    private Customer customer = new Customer();
    double orderamount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_payment);

        setToolbarTheme();
        settvTitle(getResources().getString(R.string.payment));
        showBackButton();
        hideSearchNotification();

        payment_request = getIntent().getExtras().getString(RequestParamUtils.payment_request, "");
        payment_response = getIntent().getExtras().getString(RequestParamUtils.payment_response, "");

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);


        /*
         * PAYMENT_INTENT_SALE will cause the payment to complete immediately.
         * Change PAYMENT_INTENT_SALE to
         *   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
         *   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
         *     later via calls from your server.
         *
         * Also, to include additional payment details and an item list, see getStuffToBuy() below.
         */
        //PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        // PayPalPayment thingToBuy = getStuffToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        PayPalPayment thingToBuy = getPaymentOrderList(PayPalPayment.PAYMENT_INTENT_SALE);

        /*
         * See getStuffToBuy(..) for examples of some available payment options.
         */

        Intent intent_2 = new Intent(PaymentPaypalActivity.this, PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent_2.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent_2.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent_2, REQUEST_CODE_PAYMENT);
    }

    public void onBuyPressed(View pressed) {
        /*
         * PAYMENT_INTENT_SALE will cause the payment to complete immediately.
         * Change PAYMENT_INTENT_SALE to
         *   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
         *   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
         *     later via calls from your server.
         *
         * Also, to include additional payment details and an item list, see getStuffToBuy() below.
         */
        //PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        // PayPalPayment thingToBuy = getStuffToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
        PayPalPayment thingToBuy = getPaymentOrderList(PayPalPayment.PAYMENT_INTENT_SALE);

        /*
         * See getStuffToBuy(..) for examples of some available payment options.
         */

        Intent intent = new Intent(PaymentPaypalActivity.this, PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    /*
     * This method shows use of optional payment details and item list.
     */
    private PayPalPayment getPaymentOrderList(String paymentIntent) {
        //--- include an item list, payment amount details
        try {
            JSONObject jsonObject = new JSONObject(payment_request);
            String customer_id = jsonObject.optString(RequestParamUtils.user_id, "");
            String currencySymbol = "EUR";
            JSONArray arrCartItems = jsonObject.optJSONArray(RequestParamUtils.cartItems);
            PayPalItem[] items = null;
            if (arrCartItems != null && arrCartItems.length() > 0) {
                items = new PayPalItem[arrCartItems.length()];

                for (int i = 0; i < arrCartItems.length(); i++) {
                    JSONObject cartItem = new JSONObject();
                    cartItem = arrCartItems.optJSONObject(i);

                    items[i] = new PayPalItem(
                            cartItem.optString(RequestParamUtils.name, "Sample Item - " + i),
                            cartItem.optInt(RequestParamUtils.quantity, 1),
                            new BigDecimal(cartItem.optString(RequestParamUtils.price, "0.0")),
                            currencySymbol,
                            "sku-" + customer_id + "-" + cartItem.optString(RequestParamUtils.PRODUCT_ID,
                                    "PRODUCT_ID-" + i));
                }
            }


            BigDecimal subtotal = PayPalItem.getItemTotal(items); // Sub total order checkout data item price

            orderamount = subtotal.doubleValue();
            if (subtotal.doubleValue() < 29.99) {
                BigDecimal shipping = new BigDecimal("6.00"); // Use for shipping code
//            BigDecimal tax = new BigDecimal("4.67"); // use for tax amount calculation

//            PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
//            PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(subtotal);
                BigDecimal amount = subtotal.add(shipping);

                PayPalPayment payment = new PayPalPayment(amount, currencySymbol, "Products + Shipping Charge", paymentIntent);


//            payment.items(items).paymentDetails(paymentDetails);

                //--- set other optional fields like invoice_number, custom field, and soft_descriptor
                payment.custom("This is text that will be associated with the payment that the app can use.");

//            addAppProvidedShippingAddress(payment);
//            enableShippingAddressRetrieval(payment, true);

                return payment;
            } else {
                PayPalPayment payment = new PayPalPayment(subtotal, currencySymbol, "Products", paymentIntent);


//            payment.items(items).paymentDetails(paymentDetails);

                //--- set other optional fields like invoice_number, custom field, and soft_descriptor
                payment.custom("This is text that will be associated with the payment that the app can use.");

//            addAppProvidedShippingAddress(payment);
//            enableShippingAddressRetrieval(payment, true);

                return payment;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private PayPalPayment getThingToBuy(String paymentIntent) {
        return new PayPalPayment(new BigDecimal("0.01"), "USD", "Products",
                paymentIntent);
    }

    /*
     * This method shows use of optional payment details and item list.
     */
    private PayPalPayment getStuffToBuy(String paymentIntent) {
        //--- include an item list, payment amount details
        PayPalItem[] items =
                {
                        new PayPalItem("sample item #1", 2, new BigDecimal("87.50"), "USD",
                                "sku-12345678"),
                        new PayPalItem("free sample item #2", 1, new BigDecimal("0.00"),
                                "USD", "sku-zero-price"),
                        new PayPalItem("sample item #3 with a longer name", 6, new BigDecimal("37.99"),
                                "USD", "sku-33333")
                };
        BigDecimal subtotal = PayPalItem.getItemTotal(items);
        BigDecimal shipping = new BigDecimal("7.21");
        BigDecimal tax = new BigDecimal("4.67");
        PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
        BigDecimal amount = subtotal.add(shipping).add(tax);
        PayPalPayment payment = new PayPalPayment(amount, "USD", "sample item", paymentIntent);
        payment.items(items).paymentDetails(paymentDetails);

        //--- set other optional fields like invoice_number, custom field, and soft_descriptor
        payment.custom("This is text that will be associated with the payment that the app can use.");

        addAppProvidedShippingAddress(payment);

        return payment;
    }

    /*
     * Add app-provided shipping address to payment
     */
    private void addAppProvidedShippingAddress(PayPalPayment paypalPayment) {
        ShippingAddress shippingAddress =
                new ShippingAddress().recipientName("Ethen Hunt").line1("Dahlienweg 9")
                        .city("Liebefeld").state("Köniz").postalCode("3097").countryCode("CH");
        // WCH5+V4 Köniz, Switzerland
        /* ShippingAddress shippingAddress =
                new ShippingAddress().recipientName("Mom Parker").line1("52 North Main St.")
                        .city("Austin").state("TX").postalCode("78729").countryCode("US");

        */
        paypalPayment.providedShippingAddress(shippingAddress);
    }

    /*
     * Enable retrieval of shipping addresses from buyer's PayPal account
     */
    private void enableShippingAddressRetrieval(PayPalPayment paypalPayment, boolean enable) {
        paypalPayment.enablePayPalShippingAddressesRetrieval(enable);
    }

    public void onFuturePaymentPressed(View pressed) {
        Intent intent = new Intent(PaymentPaypalActivity.this, PayPalFuturePaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startActivityForResult(intent, REQUEST_CODE_FUTURE_PAYMENT);
    }

    public void onProfileSharingPressed(View pressed) {
        Intent intent = new Intent(PaymentPaypalActivity.this, PayPalProfileSharingActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PayPalProfileSharingActivity.EXTRA_REQUESTED_SCOPES, getOauthScopes());

        startActivityForResult(intent, REQUEST_CODE_PROFILE_SHARING);
    }

    private PayPalOAuthScopes getOauthScopes() {
        /* create the set of required scopes
         * Note: see https://developer.paypal.com/docs/integration/direct/identity/attributes/ for mapping between the
         * attributes you select for this app in the PayPal developer portal and the scopes required here.
         */
        Set<String> scopes = new HashSet<String>(
                Arrays.asList(PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS));
        return new PayPalOAuthScopes(scopes);
    }

    protected void displayResultText(String result) {
//        ((TextView) findViewById(R.id.txtResult)).setText("Result : " + result);
        Toast.makeText(
                getApplicationContext(),
                result, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
//                        Log.i(TAG, confirm.toJSONObject().toString(4));
//                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
                        /**
                         *  TODO: send 'confirm' (and possibly confirm.getPayment() to your server for verification
                         * or consent completion.
                         * See https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                         * for more details.
                         *
                         * For sample mobile backend interactions, see
                         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
                         */
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        try {
                            JSONObject jsonDetails = new JSONObject(paymentDetails);

                            //Displaying payment details
                            JSONObject resposnce = jsonDetails.getJSONObject("response");

                            String payment_id = resposnce.getString("id");

                            order_complete(payment_id);

                        } catch (JSONException e) {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        displayResultText("PaymentConfirmation info received from PayPal");


                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.");
                finish();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(TAG, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
                Toast.makeText(this, "Some error occured", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth =
                        data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("FuturePaymentExample", auth.toJSONObject().toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("FuturePaymentExample", authorization_code);

                        sendAuthorizationToServer(auth);
                        displayResultText("Future Payment code received from PayPal");

                    } catch (JSONException e) {
                        Log.e("FuturePaymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("FuturePaymentExample", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        "FuturePaymentExample",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }
        } else if (requestCode == REQUEST_CODE_PROFILE_SHARING) {
            if (resultCode == Activity.RESULT_OK) {
                PayPalAuthorization auth =
                        data.getParcelableExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION);
                if (auth != null) {
                    try {
                        Log.i("ProfileSharingExample", auth.toJSONObject().toString(4));

                        String authorization_code = auth.getAuthorizationCode();
                        Log.i("ProfileSharingExample", authorization_code);

                        sendAuthorizationToServer(auth);
                        displayResultText("Profile Sharing code received from PayPal");

                    } catch (JSONException e) {
                        Log.e("ProfileSharingExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("ProfileSharingExample", "The user canceled.");
            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        "ProfileSharingExample",
                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
            }
        }
    }

    private void sendAuthorizationToServer(PayPalAuthorization authorization) {

        /**
         * TODO: Send the authorization response to your server, where it can
         * exchange the authorization code for OAuth access and refresh tokens.
         *
         * Your server must then store these tokens, so that your server code
         * can execute payments for this user in the future.
         *
         * A more complete example that includes the required app-server to
         * PayPal-server integration is available from
         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
         */

    }

    public void onFuturePaymentPurchasePressed(View pressed) {
        // Get the Client Metadata ID from the SDK
        String metadataId = PayPalConfiguration.getClientMetadataId(this);

        Log.i("FuturePaymentExample", "Client Metadata ID: " + metadataId);

        // TODO: Send metadataId and transaction details to your server for processing with
        // PayPal...
        displayResultText("Client Metadata Id received from SDK");
    }

    @Override
    public void onDestroy() {
        // Stop service when done
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    public void order_complete(String tra_id) {
        showProgress("");
        Klarnaservice order_complete =
                ServiceGenerator_other.createService(Klarnaservice.class, "ck_dc6e46c6f25a5c737ee313930e2c3e6ab4dd8ef1",
                        "cs_1ae4ddbe59aa4db5427bce36d20ef842f35df7f5");
        // prepare call in Retrofit 2.0
        try {

            JSONObject jsonObject = new JSONObject(payment_request);
            String customer_id = jsonObject.optString(RequestParamUtils.user_id, "1");

            JSONArray arrCartItems = jsonObject.optJSONArray(RequestParamUtils.cartItems);

            ArrayList<Lines_items> am = new ArrayList<>();
            if (arrCartItems != null && arrCartItems.length() > 0) {


                for (int i = 0; i < arrCartItems.length(); i++) {
                    JSONObject cartItem = new JSONObject();
                    cartItem = arrCartItems.optJSONObject(i);
                    am.add(new Lines_items(cartItem.optInt(RequestParamUtils.PRODUCT_ID, 1),
                            cartItem.optInt(RequestParamUtils.quantity, 1)));

                }

                String cust = getPreferences().getString(RequestParamUtils.CUSTOMER, "");

                customer = new Gson().fromJson(
                        cust, new TypeToken<Customer>() {
                        }.getType());
                Billing_order ba;
                Shipping_order so;

                if (customer != null) {
                    if (customer.billing.phone.equals("") && customer.billing.firstName.equals("")
                            && customer.billing.lastName.equals("") && customer.billing.address1.equals("") &&
                            customer.billing.address2.equals("") && customer.billing.company.equals("") &&
                            customer.billing.city.equals("") && customer.billing.state.equals("") &&
                            customer.billing.postcode.equals("")) {
                        ba = new Billing_order("",
                                "", "", "", "",
                                "", "", "", "",
                                "");
                    } else {


                        ba = new Billing_order(customer.billing.firstName,
                                customer.billing.lastName, customer.billing.address2, customer.billing.address1,
                                customer.billing.city, customer.billing.state, customer.billing.postcode, "Germany",
                                customer.email, customer.billing.phone);
                    }

                    if (customer.shipping.firstName.equals("") &&
                            customer.shipping.lastName.equals("") && customer.shipping.address1.equals("")
                            && customer.shipping.address2.equals("") && customer.shipping.company.equals("")
                            && customer.shipping.city.equals("") && customer.shipping.state.equals("")
                            && customer.shipping.postcode.equals("")) {

                        so = new Shipping_order(customer.billing.firstName,
                                customer.billing.lastName, customer.billing.address2, customer.billing.address1,
                                customer.billing.city, customer.billing.state, customer.billing.postcode, "Germany");

                    } else {


                        so = new Shipping_order(customer.shipping.firstName,
                                customer.shipping.lastName, customer.shipping.address1, customer.shipping.address2,
                                customer.shipping.city, customer.shipping.state,
                                customer.shipping.postcode, "Germany");


                    }

                    ArrayList<Shipping_lines> sl = new ArrayList<>();
                    if (orderamount < 29.99){
                        sl.add(new Shipping_lines("pr_dhl_paket", "DHL Paket", "6"));
                    }else{
                        sl.add(new Shipping_lines("pr_dhl_paket", "DHL Paket", "0"));
                    }

                    Sendorder sc = new Sendorder("paypal", "PayPal",
                            tra_id, true, Integer.parseInt(customer_id), am,
                            ba, so, sl);


                    Call<Sendorder> call = order_complete.order_com(sc);
                    call.enqueue(new Callback<Sendorder>() {
                        @Override
                        public void onResponse(Call<Sendorder> call, Response<Sendorder> response) {
                            dismissProgress();
                            if (response.isSuccessful()) {
                                // user object available
//                            Log.d("Paymentwebactiivty", "iss>body" + response.body().toString());
                                Intent intent = new Intent(PaymentPaypalActivity.this, ThankYouActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
//                            Log.d("Paymentwebactiivty", "iss>bodyda" + response);
                                Toast.makeText(PaymentPaypalActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                                Log.w("Paymentwebactiivty.2.0 getFeed > Full json res wrapped in gson => ",new Gson().toJson(response));
                                // error response, no access to resource?
                            }
                        }


                        @Override
                        public void onFailure(Call<Sendorder> call, Throwable t) {
                            // something went completely south (like no internet connection)
//                            Log.d("Paymentwebactiivty", "Erro" + t.getMessage());

                            dismissProgress();
                            Toast.makeText(PaymentPaypalActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    dismissProgress();
                    Toast.makeText(this, "Address not found.", Toast.LENGTH_SHORT).show();
                }


            } else {
                dismissProgress();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
