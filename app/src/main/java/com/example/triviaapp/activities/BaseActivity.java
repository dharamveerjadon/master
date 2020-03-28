package com.example.triviaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.triviaapp.R;

//Create base Activity to use common function
public abstract class BaseActivity extends AppCompatActivity {

    //replace fragment
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    //add fragment and also add to backstack
    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    //its will remove all backstack entry
    public void removeAllFragments(FragmentManager fragmentManager) {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    /*it will return current fragment name*/
    public String getCurrentFragment(){
        return this.getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
    }

    /*set title and back icon on action bar*/
    public void setActionBar(boolean showBack, String title) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            if(showBack) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }

        }

    }
}
