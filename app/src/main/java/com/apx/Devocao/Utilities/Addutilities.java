package com.apx.Devocao.Utilities;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.apx.Devocao.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;

public class Addutilities {
    public static void loadMobileAds (Activity activity) {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
    }
    public static void loadBannerAd(Activity activity, LinearLayout linearLayout){
        AdView adView = new AdView(activity);
        adView.setAdUnitId(activity.getResources().getString(R.string.admob_banner_id));
        adView.setAdSize(AdSize.BANNER);
        AdRequest.Builder builder = new AdRequest.Builder();
        adView.loadAd(builder.build());
        linearLayout.addView(adView);
    }
    public static InterstitialAd mInterstitialAd;
    public static void loadInterAds (Activity activity){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity,
                activity.getResources().getString(R.string.admob_interstial_id),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        mInterstitialAd = mInterstitialAd;
                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        mInterstitialAd = null;
                    }
                });
    }

    static RewardedAd rewardedAd;
    public static void loadRewardAds(Activity activity){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(activity, "ca-app-pub-8411565582267704/3217017597",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        rewardedAd = null;
                        ServerSideVerificationOptions options = new ServerSideVerificationOptions
                                .Builder()
                                .setCustomData("SAMPLE_CUSTOM_DATA_STRING")
                                .build();
                        rewardedAd.setServerSideVerificationOptions(options);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        super.onAdLoaded(rewardedAd);
                        rewardedAd = rewardedAd;
                        Log.d("TAG","Ad was loaded.");
                    }
                });
                if (rewardedAd !=null){
                    Activity activityContext = activity;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                        }
                    });
                }
    }
}
