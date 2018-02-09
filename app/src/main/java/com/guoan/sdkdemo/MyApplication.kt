package com.guoan.sdkdemo

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.business.CommunityCallBack
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
            override fun onPay(context: Context?, orderId: String?, payMoney: BigDecimal?) {
                // todo 调起支付
            }
        })

    }

}