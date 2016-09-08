# 底部导航栏实现页面的切换（三）：Fragment + RadioGroup + ViewPager #
## 知识点 ##
### 效果图： ###
![这里写图片描述](http://img.blog.csdn.net/20160908111600269)
### 逻辑 ###
> 布局：顶部是LinearLayout，里面放了TextView；底部是RadioGroup，其中里面水平放置4个RadioButton,比例是1:1:1:1；其余是ViewPager填充满剩余空间.
> 代码逻辑：ViewPager可以滑动实现页面的切换 + RadioGroup点击可以实现页面的切换；实现页面切换的时候viewpager与RadioGroup要同步。

###相关方法###
|类|方法|子方法|含义|
|---|---|---|
|ViewPager|setCurrentItem(int item)|子方法|含义|
|--|setOnPageChangeListener(ViewPager.OnPageChangeListener listener)|--|过时方法，现在用addOnPageChangeListener（...）|
|--|addOnPageChangeListener(ViewPager.OnPageChangeListener listener)|--|页面切换的监听|
|--|--| onPageScrolled(int position,float positionOffset,int positionOffsetPixels)|正在滑动时|
|--|--| onPageSelected(int position)|滑动完成|
|--|--|onPageScrollStateChanged(int state)|//滑动状态改变，默认值有0/1,2，也可以在这里设置tab为true（先要判断state == 2）；0:没有滑动，静止状态；1:正在滑动；2:滑动完成|
|RadioGroup| setOnCheckedChangeListener (RadioGroup.OnCheckedChangeListener listener)|--|check监听|
|--|---|onCheckedChanged(RadioGroup radioGroup, int checkId)|checkId选中的radioButton的id|
|RadioButton|setChecked (boolean checked)|--|RadioButton是否选中|


## 底部文字颜色 + 背景色 + 图片 的切换与上一篇一样。如下： ##
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
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
    findViews();
    initData();
    initViews();
    //默认点击第一个
    viewPager.setCurrentItem(0);
    tab1.setChecked(true);
}
```

### Step 1 findViews()：通过id找到所有的view ###
```
private void findViews() {
    radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    viewPager = (ViewPager) findViewById(R.id.viewPager);
    tab1 = (RadioButton) findViewById(R.id.tab1);
    tab2 = (RadioButton) findViewById(R.id.tab2);
    tab3 = (RadioButton) findViewById(R.id.tab3);
    tab4 = (RadioButton) findViewById(R.id.tab4);
}
```
### Step 2 initData():初始化数据 ###
```
private void initData() {
    list.clear();
    frag1 = new Frag1();
    frag2 = new Frag2();
    frag3 = new Frag3();
    frag4 = new Frag4();

    list.add(frag1);
    list.add(frag2);
    list.add(frag3);
    list.add(frag4);
}
```
### Step 3 initViews()：设置radioGroup + viewPager的监听 ###
```
private void initViews() {
    //RadioGroup点击的时实现viewpager的切换
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            switch (checkId) {
                case R.id.tab1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.tab2:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.tab3:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.tab4:
                    viewPager.setCurrentItem(3);
                    break;
            }
        }
    });

    MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(), list);
    viewPager.setAdapter(adapter);

    //viewpager滑动的时候让RadioGroup切换
//        viewPager.setOnPageChangeListener(OnPageChangeListener mOnPageChangeListener);//过时方法
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //正在滑动时
            Log.d("tag", "position=" + position + "---positionOffset" + positionOffset + "---positionOffsetPixels" + positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            //滑动完成
            switch (position) {
                case 0:
                    tab1.setChecked(true);
                    break;
                case 1:
                    tab2.setChecked(true);
                    break;
                case 2:
                    tab3.setChecked(true);
                    break;
                case 3:
                    tab4.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //滑动状态改变，默认值有0/1,2，也可以在这里设置tab为true（先要判断state == 2）
            //0:没有滑动，静止状态
            //1:正在滑动
            //2:滑动完成
        }
    });
}
```
### Step 4 默认设置第一页 ###
```
//默认点击第一个
viewPager.setCurrentItem(0);
tab1.setChecked(true);
```

## 禁止ViewPager的滑动 ##
> app通常导航栏可以点击，但viewpager是不可以滑动的，这是怎么实现的呢？

## 解决方法： ##

> 创建类NoScrollViewPager继承ViewPager，通过重写onInterceptTouchEvent(MotionEvent ev) 和 onTouchEvent(MotionEvent ev) 实现不可滑动。

### 需要修改哪些 ###
1. 创建类NoScrollViewPager继承ViewPager，通过重写onInterceptTouchEvent(MotionEvent ev) 和 onTouchEvent(MotionEvent ev) 实现不可滑动
2. 将布局和代码中的控件ViewPager改成NoScrollViewPager
3. 注释掉viewpager的viewPager.setCurrentItem(0) + viewPager.addOnPageChangeListener(...}

### 代码： ###
```
/**
 * Created by ${cqc} on 2016/9/8.
 * 禁止viewpager的滑动
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //是否拦截事件向内部传递，true:拦截; false：不拦截.
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //viewpager本身是否处理事件，true:处理; false：不处理.
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
```