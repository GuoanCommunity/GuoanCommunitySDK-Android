package com.guoan.sdkdemo

import android.app.Application
import android.content.Context
import android.content.Intent
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.business.*
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
        var sJsInterface: CommunityJavaScriptInterface? = null
    }

    override fun onCreate() {
        super.onCreate()
        //集成国安社区
        CommunityFactory.getInstance()?.initSdkAuth(applicationContext)
        CommunityFactory.getInstance()?.initCallBack(object : CommunityCallBack {
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

            override fun onTryLogin(context: Context?) {
                context?.startActivity(Intent(context, LoginActivity::class.java))
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

            override fun onPay(context: Context?, jsInterface: CommunityJavaScriptInterface?, responseId: String?, orderId: String?, payMoney: BigDecimal?) {
                sJsInterface = jsInterface
                //跳转支付页面
                var intent: Intent? = Intent(context, PayActivity::class.java)
                intent?.putExtra("responseId", responseId)
                intent?.putExtra("orderId", orderId)
                intent?.putExtra("payMoney", payMoney)
                context?.startActivity(intent)
                //支付结束后回调（成功 失败 取消）
                //jsInterface?.callPayed(responseId, CommunityConstants.SDK_SUCCESS)
            }
        })

    }

}