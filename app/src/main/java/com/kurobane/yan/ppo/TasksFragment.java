package com.kurobane.yan.ppo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TasksFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public TasksAdapter tasksAdapter;

    public TasksFragment() {
    }

    public static TasksFragment newInstance(int sectionNumber) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);

        final ArrayList<Task> tasks = MainActivity.dbHelper.getAllTasks();

        Log.d("Tasks list", tasks.toString());
        tasksAdapter = new TasksAdapter(getContext(), tasks);

        ListView listView = (ListView) rootView.findViewById(R.id.tasks_list);
        if (listView != null) {
            listView.setAdapter(tasksAdapter);
        }

        if (listView != null) {
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                               int arg2, long arg3) {

                    MainActivity.dbHelper.deleteTask(tasks.get(arg2));
                    tasks.remove(arg2);
                    tasksAdapter.notifyDataSetChanged();

                    return false;
                }
            });
        }

        return rootView;
    }


}
