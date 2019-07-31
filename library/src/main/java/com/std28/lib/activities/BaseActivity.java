package com.std28.lib.activities;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity
    extends AppCompatActivity
{
    public int color(int color) {
        return ContextCompat.getColor(this, color);
    }
}
