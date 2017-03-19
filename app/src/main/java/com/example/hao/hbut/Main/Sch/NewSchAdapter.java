package com.example.hao.hbut.Main.Sch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.TextView;


import com.example.hao.hbut.R;

import cn.zhouchaoyuan.excelpanel.BaseExcelPanelAdapter;

/**
 * Created by haohao on 2017/3/16.
 */

public class NewSchAdapter extends BaseExcelPanelAdapter<RowTitle, ColTitle, Cell> {

    private Context context;
    private View.OnClickListener block;

    public NewSchAdapter(Context context) {
        super(context);
        this.context = context;
    }

    //======================normal cell=====================================
    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_status_normal, parent, false);

        return new CellHolder(view);
    }

    @Override
    public void onBindCellViewHolder(RecyclerView.ViewHolder holder, int verticalPosition, int horizontalPosition) {
        Cell cell = getMajorItem(verticalPosition, horizontalPosition);
        if (null == holder || !(holder instanceof CellHolder) || cell == null) {
            return;
        }
        CellHolder viewHolder = (CellHolder) holder;
        viewHolder.sch_name.setText(cell.getSchName());
        viewHolder.teacher_name.setText(cell.getTeacherName());
        viewHolder.address.setText(cell.getAddress());
        viewHolder.week.setText(cell.getWeek());


    }

    public class CellHolder extends RecyclerView.ViewHolder {
        private TextView sch_name;
        private TextView teacher_name;
        private TextView address;
        private TextView week;

        public CellHolder(View itemView) {
            super(itemView);

            sch_name = (TextView) itemView.findViewById(R.id.sch_name);
            teacher_name = (TextView) itemView.findViewById(R.id.teacher_name);
            address = (TextView) itemView.findViewById(R.id.address);
            week = (TextView) itemView.findViewById(R.id.week);
        }
    }


    //=======================top cell===========================================

    @Override
    public RecyclerView.ViewHolder onCreateTopViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_status_top_header, parent, false);

        return new TopHolder(view);
    }

    @Override
    public void onBindTopViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowTitle rowTitle = getTopItem(position);
        if (null == holder || !(holder instanceof TopHolder) || rowTitle == null) {
            return;
        }
        TopHolder viewHolder = (TopHolder) holder;
        viewHolder.weekLabel.setText(rowTitle.getWeekString());

    }

    public class TopHolder extends RecyclerView.ViewHolder {

        private TextView weekLabel;

        public TopHolder(View itemView) {
            super(itemView);
            weekLabel = (TextView) itemView.findViewById(R.id.week_label);
        }

    }

    //=======================left cell===========================================

    @Override
    public RecyclerView.ViewHolder onCreateLeftViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_status_left_header, parent, false);

        return new LeftHolder(view);
    }

    @Override
    public void onBindLeftViewHolder(RecyclerView.ViewHolder holder, int position) {
        ColTitle colTitle = getLeftItem(position);
        if (null == holder || !(holder instanceof LeftHolder) || colTitle == null) {
            return;
        }
        LeftHolder viewHolder = (LeftHolder) holder;
        viewHolder.timeString.setText(colTitle.getTimeString());
        viewHolder.classString.setText(colTitle.getClassString());

    }

    public class LeftHolder extends RecyclerView.ViewHolder {

        private TextView classString;
        private TextView timeString;


        public LeftHolder(View itemView) {
            super(itemView);
            timeString = (TextView) itemView.findViewById(R.id.room_number_label);
            classString = (TextView) itemView.findViewById(R.id.room_type_label);

        }
    }

    //=======================top left cell=======================================

    @Override
    public View onCreateTopLeftView() {
        return LayoutInflater.from(context).inflate(R.layout.empty_layout, null);
    }
}
