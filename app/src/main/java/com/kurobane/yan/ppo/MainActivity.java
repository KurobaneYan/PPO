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
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static DBHelper dbHelper;
    private TasksFragment tasksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                tasksFragment = TasksFragment.newInstance(position);
                return tasksFragment;
            }
            return AddTaskFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
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
            Log.d("Buttons", "Add task " + task.toString());
        }

        tasksFragment.updateTaskList();
    }
}
