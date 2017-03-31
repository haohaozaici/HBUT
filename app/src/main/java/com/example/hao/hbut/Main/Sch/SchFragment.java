package com.example.hao.hbut.Main.Sch;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.hbut.Main.RefreshDialogFragment;
import com.example.hao.hbut.Main.Sch.ListDialog.SchDialogFragment;
import com.example.hao.hbut.R;
import com.example.hao.hbut.View.widget.ENRefreshView;
import com.example.hao.hbut.base.BaseFragment;
import com.example.hao.hbut.model.api.HbutApi;
import com.example.hao.hbut.model.api.Network;
import com.example.hao.hbut.model.bean.Schedule;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.zhouchaoyuan.excelpanel.ExcelPanel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by hao on 2016/11/25.
 */

public class SchFragment extends BaseFragment {

    private ENRefreshView refresh;
    private Network network = new Network();
    private Schedule schedule;

    private ExcelPanel excelPanel;
    private SchAdapter adapter;
    private static List<RowTitle> rowTitles;
    private static List<ColTitle> colTitles;
    private static List<List<Cell>> cells = new ArrayList<>();
    public static final int PAGE_SIZE = 5;
    public static final int ROW_SIZE = 7;

    private DialogFragment refreshDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout2, container, false);

        refreshDialog = new RefreshDialogFragment();
        refreshDialog.setCancelable(false);
        excelPanel = (ExcelPanel) view.findViewById(R.id.excel_panel);
        adapter = new SchAdapter(getActivity(), mListener);
        excelPanel.setAdapter(adapter);
        loadDataDisk();
        adapter.setTopData(rowTitles);
        adapter.setLeftData(colTitles);
        if (!cells.isEmpty()) {
            adapter.setMajorData(cells);
        }

        refresh = (ENRefreshView) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh.startRefresh();
                refresh.setClickable(false);
                refreshDialog.show(getFragmentManager(), "missiles");
                loadData();
            }
        });

        return view;
    }

    private void initData() {
        //row
        if (rowTitles == null) {
            rowTitles = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                switch (i) {
                    case 0:
                        rowTitles.add(new RowTitle("星期一"));
                        break;
                    case 1:
                        rowTitles.add(new RowTitle("星期二"));
                        break;
                    case 2:
                        rowTitles.add(new RowTitle("星期三"));
                        break;
                    case 3:
                        rowTitles.add(new RowTitle("星期四"));
                        break;
                    case 4:
                        rowTitles.add(new RowTitle("星期五"));
                        break;
                    case 5:
                        rowTitles.add(new RowTitle("星期六"));
                        break;
                    case 6:
                        rowTitles.add(new RowTitle("星期日"));
                        break;
                    default:
                        break;
                }
            }
        }

        //col
        if (colTitles == null) {
            colTitles = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                switch (i) {
                    case 0:
                        colTitles.add(new ColTitle("8:20-9:55", "第1-2节"));
                        break;
                    case 1:
                        colTitles.add(new ColTitle("10:15-11:50", "第3-4节"));
                        break;
                    case 2:
                        colTitles.add(new ColTitle("14:00-15:35", "第5-6节"));
                        break;
                    case 3:
                        colTitles.add(new ColTitle("15:55-17:30", "第7-8节"));
                        break;
                    case 4:
                        colTitles.add(new ColTitle("18:30-20:55", "第9-10节"));
                        break;
                    default:
                        break;
                }
            }
        }

    }

    private void loadData() {
        clearSubscribe();
        if (cells != null) {
            cells.clear();
        }

        network.getHbutApi(HbutApi.Schedule_Host).getSchedule("20161", setting.userName, "student")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {

                            String s = responseBody.string();
                            s = s.replaceAll("\\\\", "");
                            s = s.replaceFirst("\"", "");
                            s = s.substring(0, s.length() - 1);

                            // TODO: 2017/3/17  cookies失效
                            if (!s.substring(0, 1).equals("<")) {
                                Gson gson = new Gson();
                                schedule = gson.fromJson(s, Schedule.class);

                                formatSchedule();

                                adapter.setMajorData(cells);
                                Snackbar.make(excelPanel, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

                                data.saveSchedule(schedule);
                                data.saveCells(cells);
                            } else {
                                Snackbar.make(excelPanel, getString(R.string.cookie_unable), Snackbar.LENGTH_SHORT).show();
                            }
                            refresh.setClickable(true);
                            refreshDialog.dismiss();

                        } catch (IOException e) {
//                Log.e("11111111", e.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(excelPanel, getString(R.string.error), Snackbar.LENGTH_SHORT).show();
                        refresh.setClickable(true);
                        refreshDialog.dismiss();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void formatSchedule() {

        //new add schedule method
        //创建 5 * 7 数组对象cells
        for (int i = 0; i < PAGE_SIZE; i++) {
            List<Cell> cellList2 = new ArrayList<>();
            for (int j = 0; j < ROW_SIZE; j++) {
                cellList2.add(j, new Cell("", "", "", ""));
            }
            cells.add(i, cellList2);
        }

        //根据时间坐标set item数据
        for (int i = 0; i < schedule.getTimeScheduleList().size(); i++) {
            Schedule.TimeScheduleListBean item = schedule.getTimeScheduleList().get(i);
            Cell cell = new Cell(item.getCurName(), item.getTeacher(), item.getPlace(), item.getWeek());
            if (item.getDay() != 0 && cells.get(item.getDayTime() - 1).get(item.getDay() - 1).getAddress().equals("")) {
                cells.get(item.getDayTime() - 1).set(item.getDay() - 1, cell);
            } else {
            }
        }

        //暂时整理数组为5 * 5
        for (int i = 0; i < 5; i++) {
            for (int j = cells.get(i).size(); j > 5; j--) {
                cells.get(i).remove(j - 1);
            }
        }

    }

    private void loadDataDisk() {

        if (data.getSchedule() != null) {
            schedule = data.getSchedule();
            if (data.getCells() != null) {
                cells = data.getCells();
            }
        }
//        loadData();

    }

    public static void clearCells() {
        cells.clear();
    }

    public View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }

        private void showDialog() {
            DialogFragment dialog = new SchDialogFragment();
            dialog.show(getFragmentManager(), "missiles");
        }
    };
}
