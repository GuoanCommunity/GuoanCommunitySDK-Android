#CommunitySdk集成

#编译要求
    compileSdkVersion >=25  (由于sdk编译版本是25)
     defaultConfig {
            minSdkVersion 17
            targetSdkVersion 25
        }

# sdk集成 #
**文件build.gradle**
 
     buildscript {
        ext.kotlin_version = '1.2.0'
        ext.anko_version = '0.8.3'
     }
     
    allprojects {
    repositories {
        maven {
            url 'https://dl.bintray.com/andylove/maven'
        }
    }
   }

**文件app/build.gradle**

依赖(如果项目已经有以下依赖，可忽略)

    dependencies {
    compile 'com.guoan.community.sdk:communitysdk:1.0.1'
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation "org.jetbrains.anko:anko-sdk15:$anko_version"
    implementation 'com.android.support:appcompat-v7:25.4.0'
     }
**可能遇到的冲突**

     国安社区sdk目前引入的jar包如下：（不断更新中。。。）
     1，腾讯x5内核：tbs_sdk_thirdapp_v3.1.0.1034_43100_sharewithdownload_obfs_20170301_182143.jar
  
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
                 override fun onGetUserInfo(): CommunityUserInfo? {
                     //构造用户信息
                     return CommunityUserInfo("e12sdfwefcdzfsd", "13555865965")
                 }
     
                 override fun onSelectPicture(context: Context?) {
                     
                 }
     
                 override fun onShare(context: Context?, json: String?) {
                     
                 }
     
                 override fun onPay(context: Context?, orderId: String?, payMoney: BigDecimal?) {
                     
                 }
             })
            
     宿主app如果有登录功能，登录成功后执行：
     CommunityFactory.getInstance()?.onSaveUserInfo(userInfo)
             
     跳转到国安社区页面
     CommunityFactory.getInstance()?.onIntoCommunityHome(this)
     
     销毁sdk
     在宿主app的退出app方法体内执行：
     CommunityFactory.onDestory()
     
     
