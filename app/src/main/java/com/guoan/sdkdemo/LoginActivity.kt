package com.guoan.sdkdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.onClick

/**
 * @author andylove
 * demo pager
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //登录
        login_success?.onClick {
            var token: String? = "customer_app_2f38ee74f538e56ee1e31afd856268aa"
            MyApplication.phone = accout?.text?.toString()
            MyApplication.token = token
            finish()
        }

    }
}
