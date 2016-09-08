package com.cqc.fragmentab1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tab1;
    private TextView tab2;
    private TextView tab3;
    private TextView tab4;
    private FrameLayout framgLayout;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;

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

    private void initView() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
    }

    private void findViews() {
        tab1 = (TextView) findViewById(R.id.tab1);
        tab2 = (TextView) findViewById(R.id.tab2);
        tab3 = (TextView) findViewById(R.id.tab3);
        tab4 = (TextView) findViewById(R.id.tab4);

        framgLayout = (FrameLayout) findViewById(R.id.framgLayout);
    }


    @Override
    public void onClick(View view) {
        transaction = fm.beginTransaction();
        hideAllFragment();
        switch (view.getId()) {
            case R.id.tab1:
                setSelectorAllFalse();//这个最好放在4个tab的每个case中，因为如果还有其他的点击，就会导致tab全变成灰色
                tab1.setSelected(true);
                if (frag1 == null) {
                    frag1 = new Frag1();
                    transaction.add(R.id.framgLayout, frag1, "Frag1");
                }
                transaction.show(frag1);
                break;
            case R.id.tab2:
                setSelectorAllFalse();
                tab2.setSelected(true);
                if (frag2 == null) {
                    frag2 = new Frag2();
                    transaction.add(R.id.framgLayout, frag2, "Frag2");
                }
                transaction.show(frag2);
                break;
            case R.id.tab3:
                setSelectorAllFalse();
                tab3.setSelected(true);
                if (frag3 == null) {
                    frag3 = new Frag3();
                    transaction.add(R.id.framgLayout, frag3, "Frag3");
                }
                transaction.show(frag3);
                break;
            case R.id.tab4:
                setSelectorAllFalse();
                tab4.setSelected(true);
                if (frag4 == null) {
                    frag4 = new Frag4();
                    transaction.add(R.id.framgLayout, frag4, "Frag4");
                }
                transaction.show(frag4);
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
