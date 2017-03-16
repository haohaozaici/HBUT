package com.example.hao.hbut.Main.Sch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.bean.Schedule;

import java.util.ArrayList;

/**
 * Created by hao on 2016-10-26.
 */

public class SchAdapter extends RecyclerView.Adapter<SchAdapter.ViewHolder> {

    ArrayList<Schedule.TimeScheduleList> timeScheduleList = new ArrayList<>();
    private Schedule mSchedule;

    public void setItem(Schedule schedule) {
        this.mSchedule = schedule;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mSchedule != null) {
            switch (position) {
                case 0:
                    holder.key.setText("Title");
                    holder.value.setText(mSchedule.Title);
                    holder.key2.setText("");
                    holder.value2.setText("");
                    break;
                case 1:
                    holder.key.setText("Content");
                    holder.value.setText(mSchedule.Content);
                    holder.key2.setText("");
                    holder.value2.setText("");
                    break;
            }
            if (position > 1) {
                holder.key.setText(timeScheduleList.get(position - 2).CurName);
                holder.value.setText(timeScheduleList.get(position - 2).Place);
                holder.key2.setText(timeScheduleList.get(position - 2).Teacher);
                holder.value2.setText(timeScheduleList.get(position - 2).Week);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (mSchedule != null)
            timeScheduleList = (ArrayList<Schedule.TimeScheduleList>) mSchedule.TimeScheduleList;
        return timeScheduleList == null ? 0 : (timeScheduleList.size() + 2);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView key;
        private TextView key2;
        private TextView value;
        private TextView value2;

        public ViewHolder(View itemView) {
            super(itemView);
            key = (TextView) itemView.findViewById(R.id.key);
            key2 = (TextView) itemView.findViewById(R.id.key2);
            value = (TextView) itemView.findViewById(R.id.value);
            value2 = (TextView) itemView.findViewById(R.id.value2);
        }
    }
}
