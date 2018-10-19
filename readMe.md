#CommunitySdk集成

#编译要求
    compileSdkVersion >=25  (由于sdk编译版本是25)
     defaultConfig {
            minSdkVersion 17
            targetSdkVersion 25
        }

# sdk集成 #

**项目跟目录文件build.gradle**
 
    allprojects {
    repositories {
        maven {
            url 'https://dl.bintray.com/andylove/maven'
        }
    }
   }

**文件app/build.gradle**

    dependencies {
        implementation 'com.guoan.community.sdk:communitysdk:1.3.2'
     }
     
**可能遇到的冲突**

     国安社区sdk目前引入的jar包如下：（不断更新中。。。）
     1，腾讯x5内核：tbs_sdk_thirdapp_v3.1.0.1034_43100_sharewithdownload_obfs_20170301_182143.jar
     2，okhttp工具包：okhttputils-2_6_2.jar
     3，阿里云图片上传包：aliyun-oss-sdk-android-2.2.0.jar
  
     *解决办法* 宿主项目去掉与国安社区sdk重复的jar包依赖即可。

**相关api**

     sdk初始化：
     
     （目前不需要这个授权）
     清单文件添加 在国安社区授权的appid和appsecret，如下
     <meta-data
           android:name="COMMUNITY_APP_ID"
           android:value="xxxxxxxxxxx" />
     <meta-data
           android:name="COMMUNITY_APP_SECRET"
           android:value="ooooooooooo" />
           
     在宿主项目application基类onCreate()里面：
          CommunityFactory.getInstance()?.initSdkAuth(applicationContext)
          
          (登录国安社区平台，可以不加此初始化)
          CommunityFactory.getInstance()?.initCallBack(object : CommunityCallBack {
                      
                      override fun onGetStoreInfoList(): List<StoreInfo>? {
                                      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                  }
                      
                                  override fun onShare(context: Context?, jsInterface: CommunityJavaScriptInterface?, responseId: String?, shareInfo: ShareInfo?) {
                                      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                  }
                      
                                  override fun onDoBeforFinished(context: Activity?) {
                                      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                  }
                      
                                  override fun onJumpNative(context: Context?, type: String?, param: String?) {
                                      toast("跳到国安社区的具体页面" + type + "----" + param)
                                  }
                      
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
                      
                                  override fun onTryLogin(context: Context?, reqCode: Int) {
                                      (context as Activity).startActivityForResult(Intent(context, LoginActivity::class.java), reqCode)
                                  }
                      
                                  override fun onGetUserInfo(): UserInfo? {
                                      if (phone == null || token == null) {
                                          return null
                                      }
                                      var userInfo = UserInfo(token, "", phone, "")
                                      return userInfo
                                  }
                      
                                  override fun onPay(context: Context?, jsInterface: CommunityJavaScriptInterface?, responseId: String?, payment: CommunityPayment?) {
                                      sJsInterface = jsInterface
                                      sResponseId = responseId
                                      //跳转订单支付页面
                                      var intent: Intent? = Intent(context, PayActivity::class.java)
                                      intent?.putExtra("responseId", responseId)
                                      intent?.putExtra("orderId", payment?.param)
                                      intent?.putExtra("payMoney", payment?.amount)
                                      context?.startActivity(intent)
                                      //支付结束后回调（成功 失败 取消）
                      //                MyApplication.sJsInterface?.callPayed(MyApplication.sResponseId, CommunityConstants.SDK_SUCCESS)
                                  }
                  })
           
           
    1， 跳转到国安社区页面：
     CommunityFactory.getInstance()?.onIntoCommunityHome(this@MainActivity,null,true,null)

    2， 原生登录国安社区平台:
    CommunityFactory.getInstance()?.onLoginCommunity(this@MainActivity)
    登录成功，数据回调接收：
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (CommunityConstants.SDK_COMMUNITY_LOGION_CODE == requestCode && data != null) {
            //取国安社区用户信息
                var loginBack: CommunityLoginBack? = data?.getSerializableExtra(CommunityConstants.SDK_COMMUNITY_LOGION_KEY) as CommunityLoginBack?
                toast("登录成功 token：${loginBack?.customer?.appToken}")
            }
        }
    2， 网页登录国安社区平台url:
    login://com.guoan.app.sdk

     销毁sdk
     在宿主app的退出app方法体内执行：
     CommunityFactory.onDestory()
     
     
     
