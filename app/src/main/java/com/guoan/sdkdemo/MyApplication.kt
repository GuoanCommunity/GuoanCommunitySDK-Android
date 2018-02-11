package com.guoan.sdkdemo

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.business.CommunityCallBack
import com.guoan.community.sdk.userinfo.CommunityUserInfo
import org.jetbrains.anko.toast
import java.math.BigDecimal

/**
 * Created by andylove on 2018/2/8.
 * demo application
 */
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        //集成国安社区
        CommunityFactory.getInstance()?.initSdkAuth(applicationContext, null, null)
        CommunityFactory.getInstance()?.initCallBack(object : CommunityCallBack {
            override fun onTryLogin(context: Context?) {
                toast("去登录")
            }

            override fun onGetUserInfo(): CommunityUserInfo? {
                return CommunityUserInfo("e12sdfwefcdzfsd", "13555865965")
            }

            override fun onSelectPicture(context: Context?) {
                toast("去打开照片选择")
            }

            override fun onShare(context: Context?, json: String?) {
                toast("去打开分享")
            }

            override fun onPay(context: Context?, orderId: String?, payMoney: BigDecimal?) {
                toast("调起支付")
            }
        })

    }

}