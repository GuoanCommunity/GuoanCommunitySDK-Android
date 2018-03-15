package guoan.app2;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.guoan.community.sdk.CommunityFactory;
import com.guoan.community.sdk.business.CommunityCallBack;
import com.guoan.community.sdk.store.CommunityAddress;
import com.guoan.community.sdk.userinfo.CommunityUserInfo;

import java.math.BigDecimal;
import java.util.List;

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
            public List<CommunityAddress> onGetUserAddressList(Context context) {
                Toast.makeText(context, "获取地址列表", Toast.LENGTH_SHORT).show();
                return null;
            }

            @Override
            public CommunityAddress onGetTempAddress() {
                Toast.makeText(getApplicationContext(), "获取地址", Toast.LENGTH_SHORT).show();
                return null;
            }

            @Override
            public void onPay(Context context, String orderId, BigDecimal payMoney) {
                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();

            }

            @Override
            public CommunityUserInfo onGetUserInfo() {
                Toast.makeText(getApplicationContext(), "获取用户信息", Toast.LENGTH_SHORT).show();

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
        });
    }
}
