package com.ulsu.marat.fuckinggreatadvice;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulsu.marat.fuckinggreatadvice.adapters.ViewPagerAdapter;
import com.ulsu.marat.fuckinggreatadvice.controllers.AdviceFragment;
import com.ulsu.marat.fuckinggreatadvice.controllers.AdviceFragment_;
import com.ulsu.marat.fuckinggreatadvice.controllers.FavoritesAdviceFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

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
}
