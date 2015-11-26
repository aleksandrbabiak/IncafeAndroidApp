package com.example.alex.incafeandroidapp1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.incafeandroidapp1.entity.OrderDish;
import java.util.List;

/**
 * Created by Alex on 23.11.2015.
 */
public class OrderDishFragment extends Fragment implements View.OnClickListener{

    private MainActivity activity;
    private List<OrderDish> orderDishList;
private double orderPrice = 0;
    public static OrderDishFragment newInstance() {
        OrderDishFragment orderDishFragment = new OrderDishFragment();
        Bundle bundle = new Bundle();
        return orderDishFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        orderDishList = activity.orderDishList;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_dish, null, false);

        for (OrderDish orderDish : orderDishList) {
          orderDish.setAllPriceOneKindDish(calculationAllpriceOneKindDish(orderDish.getDishCount(), orderDish.getPriceDish()));
            calculationOrderPrice(calculationAllpriceOneKindDish(orderDish.getDishCount(), orderDish.getPriceDish()));
        }
        TextView orderPriceCalculation = (TextView) view.findViewById(R.id.price_calculation);
        orderPriceCalculation.setText("" + orderPrice);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        //   mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        InfoForOrderDishReciclerAdapter infoForOrderDishReciclerAdapter = new InfoForOrderDishReciclerAdapter(orderDishList, activity);
        mRecyclerView.setAdapter(infoForOrderDishReciclerAdapter);
        return view;

    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.cancel_order_dish:


                break;

            case R.id.make_order_dish:


                break;
        }
    }
    private double calculationAllpriceOneKindDish(int dishCount, double priceOneCountDish){
        double priceAllCountDish = dishCount * priceOneCountDish;

        return priceAllCountDish;
    }
    private void calculationOrderPrice(double allPriceOneKindDish){
        orderPrice = allPriceOneKindDish + orderPrice;
    }
}
