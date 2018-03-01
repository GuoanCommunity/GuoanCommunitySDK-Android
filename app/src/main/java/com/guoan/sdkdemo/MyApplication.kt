package com.guoan.sdkdemo

import android.app.Application
import android.content.Context
import android.content.Intent
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.business.CommunityCallBack
import com.guoan.community.sdk.store.CommunityLocation
import com.guoan.community.sdk.userinfo.CommunityUserInfo
import org.jetbrains.anko.toast
import java.math.BigDecimal

/**
 * Created by andylove on 2018/2/8.
 * demo application
 */
class MyApplication : Application() {

    companion object {
        var phone: String? = null
        var token: String? = null

        var lat: String? = null
        var lon: String? = null
        var cityCode: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        //集成国安社区
        CommunityFactory.getInstance()?.initSdkAuth(applicationContext, null, null)
        CommunityFactory.getInstance()?.initCallBack(object : CommunityCallBack {
            override fun onTryLocation(context: Context?) {
                toast("无法获取位置信息,待宿主定位")
            }

            override fun onGetLocation(): CommunityLocation? {
                return CommunityLocation(lat, lon, "xxx", cityCode, "xxx", "xxx")
            }

            override fun onTryLogin(context: Context?) {
                context?.startActivity(Intent(context, LoginActivity::class.java))
            }

            override fun onGetUserInfo(): CommunityUserInfo? {
                if (phone == null || token == null) {
                    return null
                }
                var userInfo = CommunityUserInfo(token, phone)
                return userInfo
            }

            override fun onShare(context: Context?, json: String?) {
                toast("调起宿主分享"+json)
            }

            override fun onPay(context: Context?, orderId: String?, payMoney: BigDecimal?) {
                toast("调起宿主支付" + orderId + payMoney)
            }
        })

    }

}