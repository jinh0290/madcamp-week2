package com.example.facebooklogin.taps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.facebooklogin.MainActivity;
import com.example.facebooklogin.R;
import com.example.facebooklogin.fragmentClasses.Fragment_First;
import com.example.facebooklogin.fragmentClasses.Fragment_Second;
import com.example.facebooklogin.fragmentClasses.Fragment_Third;
import com.google.android.material.tabs.TabLayout;


public class MainActivity2 extends AppCompatActivity {
    private ViewPager mViewPager;

    private String email;

    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mViewPager = (ViewPager) findViewById(R.id.container);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            email= null;
        } else {
            email = extras.getString("email");
        }
        setupViewPager(mViewPager);

        TabLayout tabLayout;
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
    public void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        Fragment_First fragment_first = new Fragment_First();
        fragment_first.setArguments(bundle);
        adapter.addFragment(fragment_first, "Addressbook");
        adapter.addFragment(new Fragment_Second(), "Gallery");
        adapter.addFragment(new Fragment_Third(), "Do not Try");
        viewPager.setAdapter(adapter);
    }
}