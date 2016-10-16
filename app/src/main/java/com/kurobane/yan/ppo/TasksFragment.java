package com.kurobane.yan.ppo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TasksFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

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
        ArrayList<Task> tasks = new ArrayList<>();
        final TasksAdapter tasksAdapter = new TasksAdapter(getContext(), tasks);
        tasks.add(new Task("First task"));
        ListView listView = (ListView) rootView.findViewById(R.id.tasks_list);
        if (listView != null) {
            listView.setAdapter(tasksAdapter);
        }
        return rootView;
    }

}
