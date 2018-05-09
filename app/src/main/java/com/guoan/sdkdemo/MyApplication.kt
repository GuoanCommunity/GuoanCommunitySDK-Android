package com.guoan.sdkdemo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.business.*
import com.guoan.community.sdk.business.pay.CommunityPayment
import org.jetbrains.anko.toast

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
        var sJsInterface: CommunityJavaScriptInterface? = null
        var sResponseId: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        //集成国安社区
        CommunityFactory.getInstance()?.initSdkAuth(applicationContext)
        CommunityFactory.getInstance()?.initCallBack(object : CommunityCallBack {
            override fun onJumpNative(context: Context?, type: String?, param: String?) {
                toast("跳到国安社区的具体页面" + type + "----" + param)
            }

            override fun onGetStoreInfo(): StoreInfo? {
                toast("获取门店信息")
                return null
            }

            override fun onGetUserAddressList(context: Context?, jsInterface: CommunityJavaScriptInterface?, responseId: String?) {
                toast("异步获取地址列表信息")
                //获取用户地址列表后回调
                //jsInterface?.callBackAddList(responseId, null)
            }

            override fun onGetTempAddress(): LocationInfo? {
                toast("获取位置信息")
                var locationInfo = LocationInfo(lat, lon, cityCode)
                return locationInfo
            }

            override fun onTryLogin(context: Context?, reqCode: Int) {
                (context as Activity).startActivityForResult(Intent(context, LoginActivity::class.java), reqCode)
            }

            override fun onGetUserInfo(): UserInfo? {
                if (phone == null || token == null) {
                    return null
                }
                var userInfo = UserInfo(token, "", phone, "")
                return userInfo
            }

            override fun onShare(context: Context?, shareInfo: ShareInfo?) {
                toast("调起宿主分享")
            }

            override fun onPay(context: Context?, jsInterface: CommunityJavaScriptInterface?, responseId: String?, payment: CommunityPayment?) {
                sJsInterface = jsInterface
                sResponseId = responseId
                //跳转订单支付页面
                var intent: Intent? = Intent(context, PayActivity::class.java)
                intent?.putExtra("responseId", responseId)
                intent?.putExtra("orderId", payment?.param)
                intent?.putExtra("payMoney", payment?.amount)
                context?.startActivity(intent)
                //支付结束后回调（成功 失败 取消）
//                MyApplication.sJsInterface?.callPayed(MyApplication.sResponseId, CommunityConstants.SDK_SUCCESS)
            }
        })

    }

}