package com.cqc.fragmentab2;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentManager fm;
    private RadioGroup radioGroup;
    private RadioButton tab1;
    private RadioButton tab2;
    private RadioButton tab3;
    private RadioButton tab4;

    private FragmentTransaction transaction;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        findViews();
        radioGroup.setOnCheckedChangeListener(this);
        tab1.setChecked(true);//默认选中第一个tab
    }


    private void hideAllFragment() {
        if (frag1 != null) transaction.hide(frag1);
        if (frag2 != null) transaction.hide(frag2);
        if (frag3 != null) transaction.hide(frag3);
        if (frag4 != null) transaction.hide(frag4);
    }

    private void findViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tab1 = (RadioButton) findViewById(R.id.tab1);
        tab2 = (RadioButton) findViewById(R.id.tab2);
        tab3 = (RadioButton) findViewById(R.id.tab3);
        tab4 = (RadioButton) findViewById(R.id.tab4);
    }

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
}
