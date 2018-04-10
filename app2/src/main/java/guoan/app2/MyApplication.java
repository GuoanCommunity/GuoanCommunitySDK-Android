package guoan.app2;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.guoan.community.sdk.CommunityFactory;
import com.guoan.community.sdk.business.CommunityCallBack;
import com.guoan.community.sdk.business.CommunityJavaScriptInterface;
import com.guoan.community.sdk.business.LocationInfo;
import com.guoan.community.sdk.business.ShareInfo;
import com.guoan.community.sdk.business.UserInfo;

import java.math.BigDecimal;

/**
 * @author andylove
 * @date 2018/3/1
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommunityFactory.Companion.getInstance().initSdkAuth(getApplicationContext());
        CommunityFactory.Companion.getInstance().initCallBack(new CommunityCallBack() {

            @Override
            public void onPay(Context context, CommunityJavaScriptInterface jsInterface, String responseId, String orderId, BigDecimal payMoney) {
                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGetUserAddressList(Context context, CommunityJavaScriptInterface jsInterface, String responseId) {
                Toast.makeText(context, "异步获取地址列表", Toast.LENGTH_SHORT).show();
                jsInterface.callBackAddList(responseId, null);
            }

            @Override
            public LocationInfo onGetTempAddress() {
                Toast.makeText(getApplicationContext(), "获取位置信息", Toast.LENGTH_SHORT).show();
                return null;
            }

            @Override
            public UserInfo onGetUserInfo() {
                Toast.makeText(getApplicationContext(), "获取用户信息", Toast.LENGTH_SHORT).show();

                return null;
            }

            @Override
            public void onShare(Context context, ShareInfo shareInfo) {
                Toast.makeText(context, "去分享", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTryLogin(Context context) {
                Toast.makeText(context, "去登录", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
