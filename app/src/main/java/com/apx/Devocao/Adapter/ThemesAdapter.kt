package com.apx.Devocao.Adapter

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.Model.ThemesModel
import com.apx.Devocao.PurchaseActivity
import com.apx.Devocao.R
import com.apx.Devocao.Utilities.Addutilities.loadMobileAds
import com.apx.Devocao.Utilities.Constant.Theme_Image
import com.apx.Devocao.Utilities.Constant.Theme_color
import com.apx.Devocao.Utilities.Constant.Theme_font
import com.apx.Devocao.Utilities.Constant.isPremium
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions

class ThemesAdapter(private val data:List<ThemesModel>):
RecyclerView.Adapter<ThemesAdapter.ViewHolder>(){
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        private var rewardedAd: RewardedAd? = null
        fun bind (list: ThemesModel){
            val image = itemView.findViewById<ImageView>(R.id.image)
            val check = itemView.findViewById<ImageView>(R.id.check)
            val  text = itemView.findViewById<TextView>(R.id.text)
            val lock = itemView.findViewById<ImageView>(R.id.lock)
            val Ads = itemView.findViewById<ImageView>(R.id.Ads)
            image.setImageResource(list.image)

            text.setTextColor(Color.parseColor(list.color))

            val typeface = Typeface.createFromAsset(itemView.context.assets, "font/" + list.font)
            text.typeface = typeface

            if (list.isAds){
                Ads.visibility = View.VISIBLE
            }else {
                Ads.visibility = View.GONE
            }

            if (list.isLocked){
                lock.visibility = View.VISIBLE
            }else{
                lock.visibility = View.GONE
            }


            var sharedPreferences = itemView.context.getSharedPreferences(itemView.context.packageName,MODE_PRIVATE)
            var edit = sharedPreferences.edit()

            itemView.setOnClickListener {
                if (list.isLocked) {
                    if (list.isAds) {
                        loadMobileAds(itemView.context as Activity)

                        val adRequest = AdRequest.Builder().build()
                        RewardedAd.load(itemView.context as Activity, "ca-app-pub-8411565582267704/3217017597",
                            adRequest, object : RewardedAdLoadCallback() {
                                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                    super.onAdFailedToLoad(loadAdError)

                                    val options = ServerSideVerificationOptions.Builder()
                                        .setCustomData("SAMPLE_CUSTOM_DATA_STRING")
                                        .build()
                                    Toast.makeText(
                                        itemView.context,
                                        "Tema alterado Com Sucesso",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    rewardedAd?.setServerSideVerificationOptions(options)
                                }

                                override fun onAdLoaded(rewardedAd: RewardedAd) {
                                    var rewardedAd = rewardedAd
                                    super.onAdLoaded(rewardedAd)
                                    rewardedAd = rewardedAd
                                    Log.d("TAG", "Ad was loaded.")

                                }
                            })
                        if (rewardedAd != null) {
                            val activityContext: Activity = itemView.context as Activity
                            rewardedAd?.show(
                                activityContext
                            ) {
                                edit.putInt("USER_THEME", list.image)
                                edit.putString("USER_COLOR", list.color)
                                edit.putString("USER_FONT", list.font)
                                edit.apply()

                                Theme_Image = list.image
                                Theme_color = list.color
                                Theme_font = list.font
                            }
                        }
                    } else {
                        if (isPremium) {
                            edit.putInt("USER_THEME", list.image)
                            edit.putString("USER_COLOR", list.color)
                            edit.putString("USER_FONT", list.font)
                            edit.apply()

                            Theme_Image = list.image
                            Theme_color = list.color
                            Theme_font = list.font
                        } else {
                            itemView.context.startActivity(Intent(itemView.context, PurchaseActivity::class.java))
                        }
                    }
                } else {
                    if (list.isAds) {
                        loadMobileAds(itemView.context as Activity)

                        var adRequest = AdRequest.Builder().build()
                        RewardedAd.load(itemView.context,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
                            override fun onAdFailedToLoad(adError: LoadAdError) {
                                rewardedAd = null
                            }

                            override fun onAdLoaded(ad: RewardedAd) {
                                rewardedAd = ad
                                val options = ServerSideVerificationOptions.Builder()
                                    .setCustomData("SAMPLE_CUSTOM_DATA_STRING")
                                    .build()
                                rewardedAd!!.setServerSideVerificationOptions(options)
                            }
                        })

                        rewardedAd?.let { ad ->
                            ad.show(itemView.context as Activity, OnUserEarnedRewardListener { rewardItem ->
                                // Handle the reward.
                                val rewardAmount = rewardItem.amount
                                val rewardType = rewardItem.type

                                edit.putInt("USER_THEME", list.image)
                                edit.putString("USER_COLOR", list.color)
                                edit.putString("USER_FONT", list.font)
                                edit.apply()

                                Theme_Image = list.image
                                Theme_color = list.color
                                Theme_font = list.font
                            })
                        } ?: run {
                        }




                    } else {
                        edit.putInt("USER_THEME", list.image)
                        edit.putString("USER_COLOR", list.color)
                        edit.putString("USER_FONT", list.font)
                        edit.apply()

                        Theme_Image = list.image
                        Theme_color = list.color
                        Theme_font = list.font
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemthemes, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])

    }

    override fun getItemCount(): Int {
    return data.size

    }

}