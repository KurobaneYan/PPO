package com.kurobane.yan.ppo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TasksFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public TasksAdapter tasksAdapter;
    private MyOnItemLongClickListener myOnItemLongClickListener;
    private ArrayList<Task> tasks;

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
        myOnItemLongClickListener = new MyOnItemLongClickListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);

        tasks = MainActivity.dbHelper.getAllTasks();

        Log.d("Tasks list", tasks.toString());
        tasksAdapter = new TasksAdapter(getContext(), tasks);

        ListView listView = (ListView) rootView.findViewById(R.id.tasks_list);
        if (listView != null) {
            listView.setAdapter(tasksAdapter);
            listView.setOnItemLongClickListener(myOnItemLongClickListener);
        }

        return rootView;
    }

    public void updateTaskList() {
        tasks = MainActivity.dbHelper.getAllTasks();
        tasksAdapter.notifyDataSetChanged();
    }

    public class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       int index, long arg3) {

            Log.d("Deleting", "task: " + tasks.get(index));
            MainActivity.dbHelper.deleteTask(tasks.get(index));
            tasks.remove(index);

            tasksAdapter.notifyDataSetChanged();

            return false;
        }
    }
}
