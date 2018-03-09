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
        compile 'com.guoan.community.sdk:communitysdk:1.0.5'
     }
     
**可能遇到的冲突**

     国安社区sdk目前引入的jar包如下：（不断更新中。。。）
     1，腾讯x5内核：tbs_sdk_thirdapp_v3.1.0.1034_43100_sharewithdownload_obfs_20170301_182143.jar
     2，okhttp工具包：okhttputils-2_6_2.jar
     3，阿里云图片上传包：aliyun-oss-sdk-android-2.2.0.jar
  
     *解决办法* 宿主项目去掉与国安社区sdk重复的jar包依赖即可。

**相关api**

     sdk初始化：
     
     清单文件添加 在国安社区授权的appid和appsecret，如下
     <meta-data
           android:name="COMMUNITY_APP_ID"
           android:value="xxxxxxxxxxx" />
     <meta-data
           android:name="COMMUNITY_APP_SECRET"
           android:value="ooooooooooo" />
           
     在宿主项目application基类onCreate()里面：
             CommunityFactory.getInstance()?.initSdkAuth(applicationContext, null, null)
             CommunityFactory.getInstance()?.initCallBack(object : CommunityCallBack {
                 override fun onTryLocation(context: Context?) {
                     toast("无法获取位置信息,待宿主定位")
                 }
     
                 override fun onGetLocation(): CommunityLocation? {
                     return CommunityLocation(lat, lon, "xxx", cityCode, "xxx", "xxx")
                 }
     
                 override fun onTryLogin(context: Context?) {
                     context?.startActivity(Intent(context, LoginActivity::class.java))
                 }
     
                 override fun onGetUserInfo(): CommunityUserInfo? {
                     if (phone == null || token == null) {
                         return null
                     }
                     var userInfo = CommunityUserInfo(token, phone)
                     return userInfo
                 }
     
                 override fun onShare(context: Context?, json: String?) {
                     toast("调起宿主分享"+json)
                 }
     
                 override fun onPay(context: Context?, orderId: String?, payMoney: BigDecimal?) {
                     toast("调起宿主支付" + orderId + payMoney)
                 }
             })
            
     宿主app如果有登录功能，登录成功后执行：
     CommunityFactory.getInstance()?.onSaveUserInfo(userInfo)
     
     宿主app如果有LBS功能，定位成功后执行：
     CommunityFactory.getInstance()?.onSaveLocation(this@LocationActivity, location)
     
     跳转到国安社区页面
     CommunityFactory.getInstance()?.onIntoCommunityHome(this)
     
     销毁sdk
     在宿主app的退出app方法体内执行：
     CommunityFactory.onDestory()
     
     
