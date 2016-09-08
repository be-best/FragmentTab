package com.cqc.fragmentab4;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout tab1;
    private RelativeLayout tab2;
    private RelativeLayout tab3;
    private RelativeLayout tab4;
    private FrameLayout framgLayout;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tips1;
    private TextView tips2;
    private TextView tips3;
    private TextView tips4;
    private boolean isOpen1 = false;
    private boolean isOpen2 = false;
    private boolean isOpen3 = false;
    private boolean isOpen4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        findViews();
        initView();
        tab1.performClick();//Call this view's OnClickListener
    }

    private void findViews() {
        tab1 = (RelativeLayout) findViewById(R.id.tab1);
        tab2 = (RelativeLayout) findViewById(R.id.tab2);
        tab3 = (RelativeLayout) findViewById(R.id.tab3);
        tab4 = (RelativeLayout) findViewById(R.id.tab4);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);

        tips1 = (TextView) findViewById(R.id.tips1);
        tips2 = (TextView) findViewById(R.id.tips2);
        tips3 = (TextView) findViewById(R.id.tips3);
        tips4 = (TextView) findViewById(R.id.tips4);

        framgLayout = (FrameLayout) findViewById(R.id.framgLayout);
    }

    private void initView() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transaction = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.tab1:
                hideAllFragment();
                setSelectorAllFalse();//这个最好放在4个tab的每个case中，因为如果还有其他的点击，就会导致tab全变成灰色
                tab1.setSelected(true);
                tv1.setSelected(true);
                if (frag1 == null) {
                    frag1 = new Frag1();
                    transaction.add(R.id.framgLayout, frag1, "Frag1");
                }
                transaction.show(frag1);
                break;
            case R.id.tab2:
                hideAllFragment();
                setSelectorAllFalse();
                tab2.setSelected(true);
                tv2.setSelected(true);
                if (frag2 == null) {
                    frag2 = new Frag2();
                    transaction.add(R.id.framgLayout, frag2, "Frag2");
                }
                transaction.show(frag2);
                break;
            case R.id.tab3:
                hideAllFragment();
                setSelectorAllFalse();
                tab3.setSelected(true);
                tv3.setSelected(true);
                if (frag3 == null) {
                    frag3 = new Frag3();
                    transaction.add(R.id.framgLayout, frag3, "Frag3");
                }
                transaction.show(frag3);
                break;
            case R.id.tab4:
                hideAllFragment();
                setSelectorAllFalse();
                tab4.setSelected(true);
                tv4.setSelected(true);
                if (frag4 == null) {
                    frag4 = new Frag4();
                    transaction.add(R.id.framgLayout, frag4, "Frag4");
                }
                transaction.show(frag4);
                break;

            case R.id.btn1:
                if (isOpen1) {
                    tips1.setVisibility(View.GONE);
                } else {
                    tips1.setVisibility(View.VISIBLE);
                }
                isOpen1 = !isOpen1;
                break;
            case R.id.btn2:
                if (isOpen2) {
                    tips2.setVisibility(View.GONE);
                } else {
                    tips2.setVisibility(View.VISIBLE);
                }
                isOpen2 = !isOpen2;
                break;
            case R.id.btn3:
                if (isOpen3) {
                    tips3.setVisibility(View.GONE);

                } else {
                    tips3.setVisibility(View.VISIBLE);
                }
                isOpen3 = !isOpen3;
                break;
            case R.id.btn4:
                if (isOpen4) {
                    tips4.setVisibility(View.GONE);
                } else {
                    tips4.setVisibility(View.VISIBLE);
                }
                isOpen4 = !isOpen4;
                break;
        }
        transaction.commit();
    }

    private void setSelectorAllFalse() {
        tab1.setSelected(false);
        tab2.setSelected(false);
        tab3.setSelected(false);
        tab4.setSelected(false);
    }

    private void hideAllFragment() {
        if (frag1 != null) {
            transaction.hide(frag1);
        }
        if (frag2 != null) {
            transaction.hide(frag2);
        }
        if (frag3 != null) {
            transaction.hide(frag3);
        }
        if (frag4 != null) {
            transaction.hide(frag4);
        }
    }
}
