package com.rob.shopcenter.interfaces;

import android.widget.TextView;

import com.rob.shopcenter.clases.Product;

public interface OnItemClickListener {
    void onItemClick(Product item, TextView numStock);
}
