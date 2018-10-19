package com.guoan.sdkdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guoan.community.sdk.CommunityConstants
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.userinfo.CommunityLoginBack
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

/**
 * @author andylove
 * demo pager
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ck_enter_sdk?.onClick {
            CommunityFactory.getInstance()?.onIntoCommunityHome(this@MainActivity, null, true, null)
        }
        login?.onClick {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        loc?.onClick {
            startActivity(Intent(this@MainActivity, LocationActivity::class.java))
        }
        exit?.onClick {
            finish()
            CommunityFactory.onDestory()
        }
        community_login.onClick {
            CommunityFactory.getInstance()?.onLoginCommunity(this@MainActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (CommunityConstants.SDK_COMMUNITY_LOGION_CODE == requestCode && data != null) {
            var loginBack: CommunityLoginBack? = data?.getSerializableExtra(CommunityConstants.SDK_COMMUNITY_LOGION_KEY) as CommunityLoginBack?
            toast("登录成功 token：${loginBack?.customer?.appToken}")
        }
    }

}
