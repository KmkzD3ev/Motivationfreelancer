package com.apx.Devocao;

import static com.android.billingclient.api.BillingClient.ProductType.SUBS;
import static com.apx.Devocao.Utilities.Constant.isPremium;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryPurchasesParams;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import com.apx.Devocao.Utilities.Security;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    private BillingClient billingClient;
    public static final String PREF_FILE = "MyPref";
    public static final String SUBSCRIBE_KEY = "subscribe";
    public static String ITEM_SKU_SUBSCRIBE = "motivation_lite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_down);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button buy = findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
        }

        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    billingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(SUBS).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    if (list.size() > 0) {
                                        isPremium = true;
                                        getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().putBoolean("isPremium", true).apply();
                                        int i = 0;
                                        for (Purchase purchase : list) {
                                            //Here you can manage each product, if you have multiple subscription
                                            i++;
                                        }
                                    } else {
                                        isPremium = false;
                                        getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().putBoolean("isPremium", false).apply();
                                    }
                                }
                            });

                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                DynamicToast.makeError(getApplicationContext(), "Something went wrong, Please try again!").show();
            }
        });
        //item subscribed
        if (getSubscribeValueFromPref()) {

        }
        //item not subscribed
        else {

        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse);
    }

    private SharedPreferences getPrefernceObject() {
        return getApplicationContext().getSharedPreferences(PREF_FILE, 0);

    }

    private SharedPreferences.Editor getPreferenceEditObject() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_FILE, 0);
        return pref.edit();
    }

    private boolean getSubscribeValueFromPref() {
        return getPrefernceObject().getBoolean(SUBSCRIBE_KEY, false);

    }

    private void saveSubscribeValueToPref(boolean value) {
        getPreferenceEditObject().putBoolean(SUBSCRIBE_KEY, value).commit();
    }

    //initiate purchase on button click
    public void subscribe() {
        //check if service is already connected
        if (billingClient.isReady()) {
            initiatePurchase();
        }
        //else reconnect service
        else {
            billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        initiatePurchase();
                    } else {
                        DynamicToast.makeError(getApplicationContext(), "Error " + billingResult.getDebugMessage()).show();
                    }
                }

                @Override
                public void onBillingServiceDisconnected() {
                    DynamicToast.makeError(getApplicationContext(), "Service Disconnected ").show();
                }
            });
        }
    }

    private void initiatePurchase() {
        List<String> skuList = new ArrayList<>();
        skuList.add(ITEM_SKU_SUBSCRIBE);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(SUBS);
        BillingResult billingResult = billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS);
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            billingClient.querySkuDetailsAsync(params.build(),
                    new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                if (skuDetailsList != null && skuDetailsList.size() > 0) {
                                    BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(skuDetailsList.get(0))
                                            .build();
                                    billingClient.launchBillingFlow(PurchaseActivity.this, flowParams);
                                } else {
                                    //try to add subscription item "sub_example" in google play console
                                    DynamicToast.makeError(getApplicationContext(), "Item not Found").show();
                                }
                            } else {
                                DynamicToast.makeError(getApplicationContext(),
                                        " Error " + billingResult.getDebugMessage()).show();
                            }
                        }
                    });
        } else {
            DynamicToast.makeError(getApplicationContext(),
                    "Sorry Subscription not Supported. Please Update Play Store").show();
        }
    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        //if item subscribed
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            handlePurchases(purchases);
        }
        //if item already subscribed then check and reflect changes
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {

            billingClient.queryPurchasesAsync(
                    QueryPurchasesParams.newBuilder().setProductType(SUBS).build(), (billingResult1, list) -> {
                        if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            if (list.size() > 0) {
                                isPremium = true;
                                getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().putBoolean("isPremium", true).apply();
                                int i = 0;
                                for (Purchase purchase : list) {
                                    //Here you can manage each product, if you have multiple subscription
                                    i++;
                                }
                            } else {
                                isPremium = false;
                                getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().putBoolean("isPremium", false).apply();
                            }
                        }
                    });

        }
        //if Purchase canceled
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            DynamicToast.makeError(getApplicationContext(), "Purchase Canceled").show();
        }
        // Handle any other error msgs
        else {
            DynamicToast.makeError(getApplicationContext(), "Error " + billingResult.getDebugMessage()).show();
        }
    }

    void handlePurchases(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            //if item is purchased
            if (ITEM_SKU_SUBSCRIBE.equals(purchase.getSkus()) && purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
                    // Invalid purchase
                    // show error to user
                    DynamicToast.makeError(getApplicationContext(), "Error : invalid Purchase").show();
                    return;
                }
                // else purchase is valid
                //if item is purchased and not acknowledged
                if (!purchase.isAcknowledged()) {
                    AcknowledgePurchaseParams acknowledgePurchaseParams =
                            AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, ackPurchase);
                }
                //else item is purchased and also acknowledged
                else {
                    // Grant entitlement to the user on item purchase
                    // restart activity
                    if (!getSubscribeValueFromPref()) {
                        saveSubscribeValueToPref(true);
                        DynamicToast.makeError(getApplicationContext(), "Thanks for buying annual Subscription").show();
                        this.recreate();
                    }
                }
            }
            //if purchase is pending
            else if (ITEM_SKU_SUBSCRIBE.equals(purchase.getSkus()) && purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
                DynamicToast.makeError(getApplicationContext(),
                        "Purchase is Pending. Please complete Transaction").show();
            }
            //if purchase is unknown mark false
            else if (ITEM_SKU_SUBSCRIBE.equals(purchase.getSkus()) && purchase.getPurchaseState() == Purchase.PurchaseState.UNSPECIFIED_STATE) {
                saveSubscribeValueToPref(false);
                //premiumContent.setVisibility(View.GONE);
                //subscribe.setVisibility(View.VISIBLE);
                //subscriptionStatus.setText("Subscription Status : Not Subscribed");
                DynamicToast.makeError(getApplicationContext(), "Purchase Status Unknown").show();
            }
        }
    }

    AcknowledgePurchaseResponseListener ackPurchase = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                //if purchase is acknowledged
                // Grant entitlement to the user. and restart activity
                saveSubscribeValueToPref(true);
                PurchaseActivity.this.recreate();
            }
        }
    };

    /**
     * Verifies that the purchase was signed correctly for this developer's public key.
     * <p>Note: It's strongly recommended to perform such check on your backend since hackers can
     * replace this method with "constant true" if they decompile/rebuild your app.
     * </p>
     */
    private boolean verifyValidSignature(String signedData, String signature) {
        try {
            // To get key go to Developer Console > Select your app > Development Tools > Services & APIs.
            String base64Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAllVvk+H3ax9CV6z8ZWRdpy9wSFAQ7W+iS9y+ghwI5Wx4KSVjDivTLTIyZ8kH5v4yDFxATHZITgl6ejKmhmFNcufVwt0SDQeNEW2VaYXCmH9cZQFTognrDha0sbz7iYdnBCletcSY5S6BIJZY/A5ASSMJbhyWqrc41d2wfjwj2Otl4wiC78P2ptdmWqd1RArK89FqNQNtFBB8UIyD/9a/3rp7QcnyZlJiG2dxWTvB7xmIBHH2u25CTH9IoDLYcfomIrFBqt+Gs1Iz3/7kGfnEygQhuOorocf6aGt0ThYCxVxJYdHaf4yQGrAy78zVc0048KqA9xkV2SK34I2++hz1GQIDAQAB";
            return Security.verifyPurchase(base64Key, signedData, signature);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (billingClient != null) {
            billingClient.endConnection();
        }
    }
}