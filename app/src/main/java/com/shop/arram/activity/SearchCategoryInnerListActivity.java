package com.shop.arram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shop.arram.R;
import com.shop.arram.adapter.SearchInnerCategoryAdapter;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Home;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCategoryInnerListActivity extends BaseActivity implements OnItemClickListner {

    @BindView(R.id.rvSearchCategory)
    RecyclerView rvSearchCategory;
    private List<Home.AllCategory> list = new ArrayList<>();
    private Map<Integer, List<Home.AllCategory>> childList = new HashMap<>();
    private int cat_id;
    private Bundle bundle;
    private SearchInnerCategoryAdapter searchInnerCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category_inner_list);
        ButterKnife.bind(this);
        setToolbarTheme();
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.all_category));
        getIntentData();
        getList(cat_id);
        if (list.size() == 0) {
            finish();
            Intent intent = new Intent(this, CategoryListActivity.class);
            intent.putExtra(RequestParamUtils.CATEGORY, cat_id + "");
            intent.putExtra(RequestParamUtils.ORDER_BY, SearchCategoryListActivity.sortBy);
            intent.putExtra(RequestParamUtils.POSITION, SearchCategoryListActivity.sortPosition);
            startActivity(intent);
        }
        showSearch();
        showCart();
        showBackButton();
        setSerachAdapter();
    }


    public void getIntentData() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            cat_id = bundle.getInt(RequestParamUtils.CATEGORY);
        }
    }

    public void setSerachAdapter() {
        searchInnerCategoryAdapter = new SearchInnerCategoryAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSearchCategory.setLayoutManager(mLayoutManager);
        rvSearchCategory.setAdapter(searchInnerCategoryAdapter);
        rvSearchCategory.setNestedScrollingEnabled(false);
        searchInnerCategoryAdapter.addAll(list, childList);
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }


    public void getList(int id) {
        for (int i = 0; i < Constant.MAINCATEGORYLIST.size(); i++) {
            if (Constant.MAINCATEGORYLIST.get(i).parent == id) {

                list.add(Constant.MAINCATEGORYLIST.get(i));
            }
        }

        for (int j = 0; j < list.size(); j++) {
            List<Home.AllCategory> tempList = new ArrayList<>();
            for (int k = 0; k < Constant.MAINCATEGORYLIST.size(); k++) {
                if (list.get(j).id.intValue() == Constant.MAINCATEGORYLIST.get(k).parent.intValue()) {
                    tempList.add(Constant.MAINCATEGORYLIST.get(k));
                }
            }
            childList.put(list.get(j).id, tempList);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        showCart();
    }
}
