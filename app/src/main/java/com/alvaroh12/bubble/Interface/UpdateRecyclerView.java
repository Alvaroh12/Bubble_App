package com.alvaroh12.bubble.Interface;

import com.alvaroh12.bubble.DynamicRVModel;

import java.util.ArrayList;

public interface UpdateRecyclerView {

    public void callback(int position, ArrayList<DynamicRVModel> items);

}
