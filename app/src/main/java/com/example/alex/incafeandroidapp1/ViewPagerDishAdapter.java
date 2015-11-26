package com.example.alex.incafeandroidapp1;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alex.incafeandroidapp1.database.model.DishItem;

import java.util.List;

/**
 * Created by Alex on 16.11.2015.
 */
public class ViewPagerDishAdapter extends FragmentPagerAdapter {
    private static final String LOG_TAG = ViewPagerDishAdapter.class.getSimpleName();
    private int indicatorCount = 8;
    private int clickCountOrderDish = 0;
    private List<DishItem> dishItemList;
    private Activity activity;

    public ViewPagerDishAdapter(Activity activity, FragmentManager fm, List<DishItem> dishItemList) {
        super(fm);
        this.dishItemList = dishItemList;
        this.activity = activity;
    }


    @Override
    public int getCount() {

        //   Log.d(LOG_TAG, "Size " + dishItemList.size());
        return indicatorCount;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < 7) {
            fragment = DishInfoFragment.newInstance(dishItemList.get(position));
        } else {

            fragment = OrderDishFragment.newInstance();
        }

        return fragment;
    }


}
