package com.example.triviaapp.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.triviaapp.R;
import com.example.triviaapp.fragments.HistoryFragment;
import com.example.triviaapp.fragments.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.history:
                addFragment(new HistoryFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (getSupportActionBar() != null && fragmentManager.getBackStackEntryCount() == 0) {
            setActionBar(false, getString(R.string.app_name));
        } else {
            if (getCurrentFragment().equals("SecondFragment")) {
                setActionBar(true, getString(R.string.question_2));
            } else if (getCurrentFragment().equals("ThirdFragment")) {
                setActionBar(true, getString(R.string.question_3));
            } else if (getCurrentFragment().equals("SummayFragment")) {
                setActionBar(true, getString(R.string.summary));
            }
        }
    }
}
