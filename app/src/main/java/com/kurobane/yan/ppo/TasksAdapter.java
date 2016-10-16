package com.kurobane.yan.ppo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class TasksAdapter extends ArrayAdapter<Task> {
    TasksAdapter(Context context, List<Task> objects) {
        super(context, 0, objects);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView taskName = (TextView) convertView.findViewById(R.id.item_task_name);
        if (task != null) {
            taskName.setText(task.getName());
        }

        return convertView;
    }
}
