## day11

### SharedPreferences

本质是xml存储数据，用法：

```
SharedPreferences.Editor editor = getSharedPreferences("TextInfo",MODE_PRIVATE).edit();
editor.putString("density",density+"");
editor.apply();
```

谷歌推荐用apply，不过用commit效果相同。

### SQLite数据库

参考：https://www.runoob.com/sqlite/sqlite-tutorial.html

### AlphaAnimation

控制动画透明度的变化，其中透明度可以使用0~1之间的float类型的数字指定，0为透明，1为不透明。
    android:fromAlpha="1.0"
    android:toAlpha="0.5" 

### RotateAnimation

控制动画旋转的变化。

​	android:fromDegrees="0"
​    android:toDegrees="360"

### ScaleAnimation

控制动画成比例缩放的变化
    android:pivotX="50%"
    android:pivotY="50%"
    android:fromXScale="0.2"
    android:fromYScale="0.2"
    android:toXScale="2.0"
    android:toYScale="2.0"

### TranslateAnimation

控制动画移动的变化

​	android:fromXDelta="10%p" 
​    android:toXDelta="20%p"
​    android:fromYDelta="10%p"
​    android:toYDelta="20%p"

### AnimationSet：

以上几种变化的组合。通用的属性不介绍了。

### Interpolator

参考：http://www.cnblogs.com/youxilua/archive/2013/05/31/3109563.html

### FrameAnimation

帧动画，参考：https://blog.csdn.net/weixin_37730482/article/details/80606836

### Property Animation

参考：http://www.cnblogs.com/angeldevil/archive/2011/12/02/2271096.html