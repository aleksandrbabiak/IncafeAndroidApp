package com.example.alex.incafeandroidapp1;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.incafeandroidapp1.database.model.DishItem;
import com.example.alex.incafeandroidapp1.entity.OrderDish;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by Alex on 04.11.2015.
 */
public class MainActivity extends AppCompatActivity {

    private final String NAME = "name";
    private final String ID = "id";
    private TextView info;
    private ImageView imageView;
    private ViewPager hackyViewPager;
    private CircleIndicator pagerIndicator;
    private ViewPagerDishAdapter viewPagerDishAdapter = null;
    public List<OrderDish> orderDishList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderDishList = new ArrayList<>();
        initAndConfigToolbar();
        initUI();

        ArrayList<DishItem> dishItems = (ArrayList<DishItem>) getIntent().getSerializableExtra("list");

        if (dishItems != null) {
            viewPagerDishAdapter = new ViewPagerDishAdapter(this, getSupportFragmentManager(), dishItems);
        }
        hackyViewPager.setAdapter(viewPagerDishAdapter);

        pagerIndicator.setViewPager(hackyViewPager);
    }

    private void initUI() {
        hackyViewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerIndicator = (CircleIndicator) findViewById(R.id.indicator_custom);
    }

    public void addOrderDish(OrderDish orderDish){
        if(!orderDishList.contains(orderDish)){
            orderDishList.add(orderDish);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initAndConfigToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Меню заведения");
        toolbar.setBackground(getResources().getDrawable(R.drawable.background_splesh));
        setSupportActionBar(toolbar);
    //    toolbar.setLogo(R.drawable.background);
      //  toolbar.setNavigationIcon(getDrawable(R.drawable.background));
//        info = (TextView) findViewById(R.id.info_id);
//        imageView = (ImageView) findViewById(R.id.image);
    }


}
