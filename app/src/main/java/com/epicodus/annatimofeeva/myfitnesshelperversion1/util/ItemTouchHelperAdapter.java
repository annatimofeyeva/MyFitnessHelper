package com.epicodus.annatimofeeva.myfitnesshelperversion1.util;



public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
