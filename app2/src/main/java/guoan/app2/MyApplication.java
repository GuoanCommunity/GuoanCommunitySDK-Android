package guoan.app2;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.guoan.community.sdk.CommunityFactory;
import com.guoan.community.sdk.business.CommunityCallBack;
import com.guoan.community.sdk.store.CommunityLocation;
import com.guoan.community.sdk.userinfo.CommunityUserInfo;

import java.math.BigDecimal;

/**
 *
 * @author andylove
 * @date 2018/3/1
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommunityFactory.Companion.getInstance().initSdkAuth(getApplicationContext(), null, null);
        CommunityFactory.Companion.getInstance().initCallBack(new CommunityCallBack() {

            @Override
            public void onTryLocation(Context context) {
                Toast.makeText(context, "无法获取位置信息,待宿主定位", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPay(Context context, String orderId, BigDecimal payMoney) {
                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();

            }

            @Override
            public CommunityUserInfo onGetUserInfo() {
                return null;
            }

            @Override
            public void onShare(Context context, String json) {
                Toast.makeText(context, "去分享", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTryLogin(Context context) {
                Toast.makeText(context, "去登录", Toast.LENGTH_SHORT).show();

            }

            @Override
            public CommunityLocation onGetLocation() {
                return null;
            }
        });
    }
}
