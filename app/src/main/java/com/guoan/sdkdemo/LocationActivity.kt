package com.guoan.sdkdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guoan.community.sdk.CommunityFactory
import com.guoan.community.sdk.business.LocationInfo
import kotlinx.android.synthetic.main.activity_location.*
import org.jetbrains.anko.onClick

/**
 * @author andylove
 * demo pager
 */
class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        //定位
        loc_success?.onClick {
            MyApplication.lat = lat?.text?.toString()
            MyApplication.lon = lon.text?.toString()
            MyApplication.cityCode = city_code.text?.toString()

            var location = LocationInfo(MyApplication.lat, MyApplication.lon, "010")
            CommunityFactory.getInstance()?.onSaveTempAddress(this@LocationActivity, location)

            finish()
        }

    }
}
