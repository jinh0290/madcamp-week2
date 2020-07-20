package com.example.facebooklogin.taps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.facebooklogin.R;
import com.example.facebooklogin.fragmentClasses.Fragment_First;
import com.example.facebooklogin.fragmentClasses.Fragment_Second;
import com.example.facebooklogin.fragmentClasses.Fragment_Third;
import com.google.android.material.tabs.TabLayout;


public class MainActivity2 extends AppCompatActivity {

    private ViewPager mViewPager;
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new Fragment_First(), "Addressbook");
        adapter.addFragment(new Fragment_Second(), "Gallery");
        adapter.addFragment(new Fragment_Third(), "Do not Try");
        viewPager.setAdapter(adapter);
    }
}