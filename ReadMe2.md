# 底部导航栏的切换（二）：Fragment + RadioGroup #
> 素材用的上一篇：http://blog.csdn.net/ss1168805219/article/details/52463375；
> 参考：http://blog.csdn.net/coder_pig/article/details/48086729#t7

## 知识点 ##
### 效果图： ###

### 逻辑 ###
> 顶部是LinearLayout，里面放了TextView；底部是RadioGroup，其中里面水平放置4个RadioButton,比例是1:1:1:1；其余是FrameLayout填充满剩余空间。点击RadioButton，用4个Fragement替换FrameLayout，同时tab的文字+图片+背景色改变。

### 步骤： ###
1. 找到所有view，获取FragmentManager
2. 给RadioGroup设置监听，在监听中先创建FragmentTransaction，hide（）所有的fragment
3. 在每个case中判断对应的Fragment==null，是:创建并add();并显示show（）
4. switch后记得commit()

### 注意： ###
1. 在RadioGroup监听中隐藏所有的fragment，然后根据id显示该id对应的fragment。
2. 文字的颜色、图片、tab的背景色，都不需要在写setSelector(true)来做改变了。属性值是checked

###文字颜色： text_bottom_selector.xml ### 
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:color="@color/colorAccent"/>
    <item android:color="@color/colorBlack"/>
</selector>
```
###图片切换： tab1_icon_selector.xml ### 
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@mipmap/chats_light" android:state_checked="true"/>
    <item android:drawable="@mipmap/chats"/>
</selector>
```
###背景色切换： tab_bottom_bg_selector.xml ### 
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
	<item android:drawable="@color/tabBottomBg" android:state_checked="true"/>
	<item android:drawable="@color/transparent"/>
</selector>
```

## 下面通过代码学习 ##
### 主要逻辑 ###
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fm = getSupportFragmentManager();

    findViews();
    radioGroup.setOnCheckedChangeListener(this);
    tab1.setChecked(true);//默认选中第一个tab
}
```
### findViews() ###
```
private void findViews() {
    radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    tab1 = (RadioButton) findViewById(R.id.tab1);
    tab2 = (RadioButton) findViewById(R.id.tab2);
    tab3 = (RadioButton) findViewById(R.id.tab3);
    tab4 = (RadioButton) findViewById(R.id.tab4);
}
```
### RadioGroup的监听 ###
```
//i 是checkedId，不是position:0 1 2 3
@Override
public void onCheckedChanged(RadioGroup radioGroup, int i) {
    transaction = fm.beginTransaction();
    hideAllFragment();
    switch (i) {
        case R.id.tab1:
            if (frag1 == null) {
                frag1 = new Frag1();
                transaction.add(R.id.frameLayout, frag1);
            }
            transaction.show(frag1);
            break;
        case R.id.tab2:
            if (frag2 == null) {
                frag2 = new Frag2();
                transaction.add(R.id.frameLayout, frag2);
            }
            transaction.show(frag2);
            break;
        case R.id.tab3:
            if (frag3 == null) {
                frag3 = new Frag3();
                transaction.add(R.id.frameLayout, frag3);
            }
            transaction.show(frag3);
            break;
        case R.id.tab4:
            if (frag4 == null) {
                frag4 = new Frag4();
                transaction.add(R.id.frameLayout, frag4);
                Log.d("tag", "frag4== null");
            }
            transaction.show(frag4);
            break;
    }
    transaction.commit();
}
```

### 隐藏所有的Fragment；hideAllFragment() ###
```
private void hideAllFragment() {
    if (frag1 != null) transaction.hide(frag1);
    if (frag2 != null) transaction.hide(frag2);
    if (frag3 != null) transaction.hide(frag3);
    if (frag4 != null) transaction.hide(frag4);
}
```
### 注意： ###
> RadioGroup的监听事件方法中：onCheckedChanged(RadioGroup radioGroup, int i) {}；i就是checkId，不知道问什么代码补全是i，而不是checkId（API=24）.
> 源码：https://github.com/s1168805219/FragmentTab；对应的Module是app2.