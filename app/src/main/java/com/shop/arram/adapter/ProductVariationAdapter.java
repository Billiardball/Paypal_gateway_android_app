package com.shop.arram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.arram.R;
import com.shop.arram.activity.ProductDetailActivity;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.javaclasses.CheckIsVariationAvailable;
import com.shop.arram.model.CategoryList;
import com.shop.arram.model.Variation;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.CustomToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class ProductVariationAdapter extends RecyclerView.Adapter<ProductVariationAdapter.ProductVariationViewHolder> implements OnItemClickListner {

    ProductVariationInnerAdapter productVariationInnerAdapter;
    HashMap<Integer, String> combination = new HashMap<>();
    private List<CategoryList.Attribute> list = new ArrayList<>();
    private List<CategoryList.Attribute> tempVariationList = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private List<Variation> variationList = new ArrayList<>();
    private int tempPosition = -1;
    private int isFirstLoad;
    private CustomToast toast;
    private int previousOuterPosition;

    public ProductVariationAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
        toast = new CustomToast(activity);
    }

    public void addAll(List<CategoryList.Attribute> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addAllVariationList(List<Variation> variationList) {
        this.variationList = variationList;
        isFirstLoad = 0;
        onItemClick(0, list.get(0).name + "->" + list.get(0).options.get(0), 0);

    }


    @Override
    public ProductVariationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_detail_variation, parent, false);

        return new ProductVariationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductVariationViewHolder holder, final int position) {
        setVariationAdapter(holder.rvProductVariation, position);
        holder.tvTitle.setText(list.get(position).name);
        holder.tvTitle.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT, Constant.PRIMARY_COLOR)));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position, String value, int outerpos) {
        if (outerpos == 0) {
            ProductColorAdapter.selectedpos = position;
        }
        isFirstLoad = isFirstLoad + 1;
        String comb = "";
        previousOuterPosition = outerpos;
        combination.put(outerpos, value);
        ProductDetailActivity.combination = combination;
        Map<Integer, String> tempCombinationList = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (i > outerpos) {
                tempCombinationList.put(i, "");
            } else if (i == outerpos) {
                tempCombinationList.put(i, combination.get(i));
            } else {
                tempCombinationList.put(i, combination.get(i));
            }
        }
        list.get(outerpos).position = position;
        for (int i = 0; i < tempCombinationList.size(); i++) {
            if (comb.equals("")) {
                comb = comb + tempCombinationList.get(i);
            } else {
                if (tempCombinationList.get(i) != null) {
                    if (!tempCombinationList.get(i).equals(""))
                        comb = comb + "!" + tempCombinationList.get(i);
                }
            }
        }

        if (outerpos > 0) {
            List<CategoryList.Attribute> temp = new CheckIsVariationAvailable().getVariationList(variationList, comb, list);
            if (temp.size() > 0) {
                List<CategoryList.Attribute> oldList = tempVariationList;
                tempVariationList = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    if (j > outerpos && temp.size() > 0) {
                        try {
                            tempVariationList.add(temp.get(j));
                        } catch (IndexOutOfBoundsException e) {
                            tempVariationList.add(list.get(j));
                        }
                    } else {
                        tempVariationList.add(oldList.get(j));
                    }
                }
            } else {
                for (int j = 0; j < list.size(); j++) {
                    if (j > outerpos) {
                        tempVariationList.set(j, new CategoryList().getAttributeInstance());
                    }
                }
            }
            notifyDataSetChanged();
        } else {
            if (new CheckIsVariationAvailable().getVariationList(variationList, comb, list).size() > 0) {
                tempVariationList = new ArrayList<>();
                tempVariationList.addAll(new CheckIsVariationAvailable().getVariationList(variationList, comb, list));
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (i > outerpos) {
                        if (tempCombinationList.size() > i && tempVariationList.size()>=i) {
                            tempVariationList.set(i, new CategoryList().getAttributeInstance());
                        }

                    }
                }
            }
            notifyDataSetChanged();
        }

        if (isFirstLoad != 1) {
            if (!new CheckIsVariationAvailable().isVariationAvailbale(combination, variationList, list)) {
                toast.showToast(activity.getResources().getString(R.string.combition));
            } else {
                toast.cancelToast();
            }
        } else if (isFirstLoad == 1) {
            setCombination();
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setVariationAdapter(RecyclerView recyclerView, int pos) {
        productVariationInnerAdapter = new ProductVariationInnerAdapter(activity, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(productVariationInnerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        productVariationInnerAdapter.setOutListId(list.get(pos).name);
        productVariationInnerAdapter.setOuterPosition(pos);
        if (tempVariationList.size() > 0) {
            try {
                if (tempVariationList.get(pos) != null) {
                    productVariationInnerAdapter.addAllVariationList(tempVariationList.get(pos).options);

                }

//                if (isFirstLoad == 1) {
//
////                    setCombination(pos, tempVariationList.get(pos).name + "->" + tempVariationList.get(pos).options.get(0));
//                } else {
//
//                }

                productVariationInnerAdapter.previousSelectionPosition = list.get(pos).position;

            } catch (IndexOutOfBoundsException e) {
                productVariationInnerAdapter.addAllVariationList(list.get(pos).options);
                productVariationInnerAdapter.previousSelectionPosition = list.get(pos).position;
            }

        } else {
            productVariationInnerAdapter.previousSelectionPosition = list.get(pos).position;
        }
        productVariationInnerAdapter.addAll(list.get(pos).options);
        productVariationInnerAdapter.getSizePosition(list.size(), previousOuterPosition);


    }

    public void setCombination() {
        if (list.size() == tempVariationList.size()) {
            for (int i = 0; i < list.size(); i++) {

                String value = tempVariationList.get(i).name + "->" + tempVariationList.get(i).options.get(0);
                combination.put(i, value);
                list.get(i).position = list.get(i).options.indexOf(tempVariationList.get(i).options.get(0));
            }
        }


    }

    public class ProductVariationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rvProductVariation)
        RecyclerView rvProductVariation;

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;


        public ProductVariationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }


    }


}