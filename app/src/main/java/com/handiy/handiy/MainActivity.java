package com.handiy.handiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.handiy.handiy.bookmark.BookmarkActivity;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Handiy");

        //inisialisasi tab dan pager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tabs);

        //set object adapter ke dalam ViewPager
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tabs.setTabTextColors(getResources().getColor(R.color.colorWhite),
                getResources().getColor(R.color.colorAccent));

        //set tab ke ViewPager
        tabs.setupWithViewPager(viewPager);

        //konfigurasi gravity fill untuk Tab berada di posisi yang proporsional
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                // Not implemented here
                return false;
            case R.id.action_marked:
                Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
                startActivity(intent);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
