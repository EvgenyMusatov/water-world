package com.musatov.waterworld;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity{
    Field field;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field = new Field(this);
        setContentView(field);
    }

}
