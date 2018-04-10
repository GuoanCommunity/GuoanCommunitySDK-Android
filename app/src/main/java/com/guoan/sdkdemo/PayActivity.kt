package com.guoan.sdkdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guoan.community.sdk.CommunityConstants
import kotlinx.android.synthetic.main.activity_pay.*
import org.jetbrains.anko.onClick
import java.math.BigDecimal

/**
 * @author andylove
 * demo pager
 */
class PayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        //支付
        pay_success?.onClick {
            orderid?.text = intent?.getStringExtra("orderId")
            paymoney?.text = (intent?.getSerializableExtra("payMoney") as BigDecimal)?.toString()
            //支付结束后回调
            MyApplication.sJsInterface?.callPayed(intent?.getStringExtra("responseId"), CommunityConstants.SDK_SUCCESS)
            finish()
        }

    }
}
