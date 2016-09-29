package com.example.aaron.fragmentscommunicationhomework;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentsCommunication{

    Fragment1 f1;
    Fragment2 f2;
    boolean isTablet = false;

    LinearLayout bigContainer;

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bigContainer = ((LinearLayout) findViewById(R.id.bigContainer));
        f1 = new Fragment1();
        f2 = new Fragment2();

        if(findViewById(R.id.bigContainer) != null){
            if (savedInstanceState != null) {
                return;
            }

            //Ask for the size of the screen
            isTablet = isTablet();
            if( isTablet ){
                //ON A LARGE DEVICE
                //add new container
                LinearLayout container1 = new LinearLayout(this);
                container1.setId(R.id.ID_CONTAINER_ONE);
                LinearLayout container2 = new LinearLayout(this);
                container2.setId(R.id.ID_CONTAINER_TWO);

                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.add(container1.getId(), f1);
                ft.add(container2.getId(), f2);
                ft.commit();

                bigContainer.addView(container1);
                bigContainer.addView(container2);

                Toast.makeText(this, "You are in a tablet", Toast.LENGTH_LONG);
            }
            else{
                //ON A SMALL DEVICE

                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.add(bigContainer.getId(), f1);
                ft.add(bigContainer.getId(), f2);
                ft.hide(f2);
                ft.commit();

                Toast.makeText(this, "You are in a cellphone", Toast.LENGTH_LONG);
            }
        }

    }

    @Override
    public void sendMessage(String message, int idFragment) {

        if(idFragment == R.id.fragment1){
            f2.reciveMessage(message);
            if(!isTablet){
                ft = fm.beginTransaction();
                ft.hide(f1);
                ft.show(f2);
                //ft.replace(bigContainer.getId(), f2);
                ft.addToBackStack(null);
                ft.commit();
            }

        }
        else if(idFragment == R.id.fragment2){
            f1.reciveMessage(message);
            if(!isTablet){
                ft = fm.beginTransaction();
                ft.hide(f2);
                ft.show(f1);
                //ft.replace(bigContainer.getId(), f1);
                ft.addToBackStack(null);
                ft.commit();
            }
        }

    }

    public boolean isTablet(){
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == (Configuration.SCREENLAYOUT_SIZE_LARGE)){
            return true;
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == (Configuration.SCREENLAYOUT_SIZE_XLARGE)){
            return true;
        }
        else{
            return false;
        }
    }
}
