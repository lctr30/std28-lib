package com.std28.lib.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity
    extends AppCompatActivity
{
    public int color(int color) {
        return ContextCompat.getColor(this, color);
    }
}
