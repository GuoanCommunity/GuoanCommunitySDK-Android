package guoan.app2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.guoan.community.sdk.CommunityFactory;
import com.guoan.community.sdk.business.CommunityCallBack;
import com.guoan.community.sdk.business.CommunityJavaScriptInterface;
import com.guoan.community.sdk.business.LocationInfo;
import com.guoan.community.sdk.business.ShareInfo;
import com.guoan.community.sdk.business.StoreInfo;
import com.guoan.community.sdk.business.UserInfo;
import com.guoan.community.sdk.business.pay.CommunityPayment;

import java.util.List;

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
            public void onDoBeforFinished(@Nullable Activity context) {

            }

            @Override
            public void onShare(@Nullable Context context, @Nullable CommunityJavaScriptInterface jsInterface, @Nullable String responseId, @Nullable ShareInfo shareInfo) {

            }

            @Nullable
            @Override
            public List<StoreInfo> onGetStoreInfoList() {
                return null;
            }

            @Override
            public void onPay(@Nullable Context context, @Nullable CommunityJavaScriptInterface jsInterface, @Nullable String responseId, @Nullable CommunityPayment payment) {
                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onJumpNative(Context context, String type, String param) {
                Toast.makeText(getApplicationContext(), "跳到国安社区的具体页面" + type + "----" + param, Toast.LENGTH_SHORT).show();
            }

            @Nullable
            @Override
            public StoreInfo onGetStoreInfo() {
                Toast.makeText(getApplicationContext(), "获取门店信息", Toast.LENGTH_SHORT).show();
                return null;
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
            public void onTryLogin(Context context, int reqCode) {
                Toast.makeText(context, "去登录", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
