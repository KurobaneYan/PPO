package com.kurobane.yan.ppo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

        dbHelper = DBHelper.getInstance(getApplicationContext());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void addTask(View view) {
        EditText taskName = (EditText) findViewById(R.id.task_name);
        if (!taskName.getText().toString().equals("")) {
            CheckBox isImportant = (CheckBox) findViewById(R.id.is_important);
            CheckBox isDaily = (CheckBox) findViewById(R.id.is_daily);

            Task task = new Task();
            task.setName(taskName.getText().toString());
            task.setImportant(isImportant.isChecked());
            task.setDaily(isDaily.isChecked());
            dbHelper.addTask(task);
        }

        // send notification to arrayadapter

        Log.d("Buttons", "Add task pressed");
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return TasksFragment.newInstance(position);
            }
            if (position == 1) {
                return TodayFragment.newInstance(position);
            }
            if (position == 2) {
                return AddTaskFragment.newInstance(position);
            }
            return AddTaskFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
