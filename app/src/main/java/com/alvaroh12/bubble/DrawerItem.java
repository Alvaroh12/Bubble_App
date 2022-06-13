package com.alvaroh12.bubble;

import android.view.ViewGroup;

public abstract class DrawerItem <T extends  DrawerAdapter.ViewHolder>{
    protected boolean isCheked;
    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

    public DrawerItem<T>setCheked(boolean isCheked) {
        this.isCheked = isCheked;
        return this;
    }

    public boolean isCheked(){
        return isCheked;
    }

    public boolean isSelectable(){
        return true;
    }
}
