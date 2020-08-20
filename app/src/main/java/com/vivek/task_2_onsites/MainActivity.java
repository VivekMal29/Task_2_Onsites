package com.vivek.task_2_onsites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements fragment_listener{
    private First_Fragment fragment1;
    private second_Frangment fragment2;
    private myCanvas myCanvas1;
    private myCanvas2 myCanvas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new First_Fragment();
        fragment2 = new second_Frangment();
        myCanvas2 = new myCanvas2(this);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout2,fragment1)
                .replace(R.id.frameLayout,fragment2)
                .commit();



    }

    @Override
    public void setPath(int x, int y, int action) {
            fragment2.setPath(x,y,action);
    }


    //    @Override
//    public void onInputASent(myCanvas canvas) {
//        myCanvas2.updatePath(canvas);
//    }
}
