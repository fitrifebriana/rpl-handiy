package com.handiy.handiy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.handiy.handiy.data.TutorialModel;
import com.handiy.handiy.main.category.CategoryFragment;
import com.handiy.handiy.main.timeline.TimelineFragment;

import java.util.List;

/**
 * Created by FitriFebriana on 4/10/2017.
 */

public class MainAdapter extends FragmentPagerAdapter{

    String[] title = new String[]{
            "Timeline", "Category"
    };
    private List<TutorialModel> list;
    private List<TutorialModel> mList;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new TimelineFragment();
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
