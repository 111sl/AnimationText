## day12

### Android手机屏幕参数

为简单起见，Android把所有的屏幕分辨率也分为四种尺寸：小，普通，大，超大(分别对应：small, normal, large, and extra large)。

设备独立像素转换为屏幕像素公式： 
pixels = dips * (densityDPI / 160)。

获取设备dpi方法：

```
DisplayMetrics metric = new DisplayMetrics();     getWindowManager().getDefaultDisplay().getMetrics(metric);
int width = metric.widthPixels;  // 屏幕宽度（像素）
int height = metric.heightPixels;  // 屏幕高度（像素）
//屏幕密度像素比例（0.75 / 1.0 / 1.5）
float density = metric.density;  
// 屏幕密度DPI（120 / 160 / 240）
int densityDpi = metric.densityDpi;  
```

### Android 适配不同屏幕

#### layout

layout(放一些通用布局xml文件，比如界面顶部和底部的布局，不会随着屏幕大小变化，类似windos窗口的title bar) 
layout-small(屏幕尺寸小于3英寸左右的布局） 
layout-normal(屏幕尺寸小于4.5英寸左右） 
layout-large(4英寸-7英寸之间） 
layout-xlarge(7-10英寸之间） 多种dimens支持

#### 使用简单布局，能自适应布局优先

比如相对布局，百分比布局，**不要使用绝对布局**

#### 图片资源

和layout基本类似

drawable：主要放置xml配置文件或者对分辨率要求较低的图片 
drawalbe-ldpi:低分辨率的图片,如QVGA (240x320)  
drawable-mdpi:中等分辨率的图片,如HVGA (320x480) 
drawable-hdpi:高分辨率的图片,如WVGA (480x800),FWVGA (480x854)  
drawable-xhdpi：超高分辨率图片，至少960x540   

#### 根据不同Android版本切换API

根据谷歌官方给出的适配新版本的方式即可。

-----------------------------------------------------------------

适配各种机型如果失效，可能是没有在配置文件中加入

```
<supports-screens 
android:largeScreens="true" 
android:normalScreens="true" 
android:smallScreens="true" 
android:anyDensity="true"/> 
```

获得手机型号,系统版本

```
private String getHandSetInfo()
{ 
String handSetInfo=
 "手机型号:" + android.os.Build.MODEL + 
",SDK版本:" + android.os.Build.VERSION.SDK +
 ",系统版本:" + android.os.Build.VERSION.RELEASE+
 ",软件版本:"+getAppVersionName(MainActivity.this);
 return handSetInfo; 
}
```

### 安卓源码下载以及编译

7.0参考：https://blog.csdn.net/w1143408997/article/details/80993464

8.0参考：https://blog.csdn.net/xx326664162/article/details/86354616?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase

Ubuntu18.04安卓11参考：https://blog.csdn.net/w690333243/article/details/104779330

Ubuntu18.04安卓10参考：https://blog.csdn.net/weixin_43250455/article/details/105887771

编译完成后可以先看看简单的系统应用，比如录音、音乐播放等软件的源码，改动实验一下。还可以看看framework层的代码，改动编译后运行看看效果。