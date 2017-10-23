package com.example.hao.hbut.Main.Grade;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hao.hbut.R;
import com.example.hao.hbut.model.bean.Grade;

import java.util.ArrayList;

/**
 * Created by hao on 2016-10-26.
 */

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.ViewHolder> {

    ArrayList<Grade.StuGradeList> stuGradeLists = new ArrayList<>();
    private Grade mGrade;
    private Context context;

    public GradeAdapter(Context context) {
        this.context = context;
    }

    public void setItem(Grade grade) {
        this.mGrade = grade;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mGrade != null) {
            switch (position) {
                case 0:
                    holder.key.setText("AverageGradePoint");
                    holder.value.setText(mGrade.getAverageGradePoint());
                    return;
                case 1:
                    holder.key.setText("TotalGradePoint");
                    holder.value.setText(mGrade.getTotalGradePoint());
                    return;
                case 2:
                    holder.key.setText("Title");
                    holder.value.setText(mGrade.getTitle());
                    return;
                case 3:
                    holder.key.setText("Name");
                    holder.value.setText(mGrade.getName());
                    return;
            }
            if (position > 3) {
                holder.key.setText(stuGradeLists.get(position - 4).getCourseName());
                int grade = stuGradeLists.get(position - 4).getGrade();
                holder.value.setText(grade + "");
                if (grade < 60) {
                    holder.value.setTextColor(context.getResources().getColor(R.color.momo));
                }
            }

        }


    }

    @Override
    public int getItemCount() {
        if (mGrade != null)
            stuGradeLists = (ArrayList<Grade.StuGradeList>) mGrade.StuGradeList;
        return stuGradeLists == null ? 0 : (stuGradeLists.size() + 4);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView key;
        private TextView value;

        public ViewHolder(View itemView) {
            super(itemView);
            key = (TextView) itemView.findViewById(R.id.key);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }
}
