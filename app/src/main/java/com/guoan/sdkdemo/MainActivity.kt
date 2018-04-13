package com.guoan.sdkdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guoan.community.sdk.CommunityFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick

/**
 * @author andylove
 * demo pager
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ck_enter_sdk?.onClick {
            CommunityFactory.getInstance()?.onIntoCommunityHome(this@MainActivity,null,true,null)
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
    }

}
