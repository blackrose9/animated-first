package com.blackrose9.myjournal.util;

public interface ItemTouchHelperAdapter {
    void onItemDismiss(int position);

    boolean onItemMove(int fromPosition, int toPosition);
}
