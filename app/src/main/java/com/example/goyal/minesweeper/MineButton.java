package com.example.goyal.minesweeper;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class MineButton extends AppCompatButton {
    private int value;
    private int i;
    private int j;

    public MineButton(Context context) {

        super(context);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}




