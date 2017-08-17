package com.ulsu.marat.fuckinggreatadvice.controllers;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ulsu.marat.fuckinggreatadvice.R;
import com.ulsu.marat.fuckinggreatadvice.adapters.ViewPagerAdapter;
import com.ulsu.marat.fuckinggreatadvice.model.FAdvice;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements CallBack{

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.tab_layout)
    TabLayout mTabLayout;

    @ViewById(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @AfterViews
    public void bindViews(){
        setSupportActionBar(toolbar);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new AdviceFragment_(),getString(R.string.advice_tab_title));
        mViewPagerAdapter.addFragment(new FavoritesAdviceFragment_(),getString(R.string.favorites_advice_tab_title));
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void updateList(FAdvice fAdvice) {
        FavoritesAdviceFragment_ fragment = (FavoritesAdviceFragment_) mViewPagerAdapter.getItem(1);
        fragment.UpdateList(fAdvice);
        Log.d("Tag","Update");
    }
}
