package com.example.hao.hbut.Main.Sch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hao.hbut.Login.LoginActivity;
import com.example.hao.hbut.Main.MainActivity;
import com.example.hao.hbut.R;
import com.example.hao.hbut.base.BaseFragment;
import com.example.hao.hbut.base.EntryActivity;
import com.example.hao.hbut.model.bean.Setting;
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

    private Button refresh;
    private Network network = new Network();
    private Schedule schedule;

    private ExcelPanel excelPanel;
    private NewSchAdapter adapter;
    private static List<RowTitle> rowTitles;
    private static List<ColTitle> colTitles;
    private static List<List<Cell>> cells = new ArrayList<>();
    public static final int PAGE_SIZE = 5;
    public static final int ROW_SIZE = 7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataDisk();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout2, container, false);

        excelPanel = (ExcelPanel) view.findViewById(R.id.excel_panel);
        adapter = new NewSchAdapter(getActivity());
        excelPanel.setAdapter(adapter);

        adapter.setTopData(rowTitles);
        adapter.setLeftData(colTitles);
        if (!cells.isEmpty()) {
            adapter.setMajorData(cells);
        }

        refresh = (Button) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
//                                mSchAdapter.setItem(schedule);
                                Snackbar.make(excelPanel, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

                                data.saveSchedule(schedule);
                                data.saveCells(cells);
                            } else {
                                Snackbar.make(excelPanel, getString(R.string.cookie_unable), Snackbar.LENGTH_SHORT).show();

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Do something after 5s = 5000ms
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                }, 1000);
                            }


                        } catch (IOException e) {
//                Log.e("11111111", e.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void formatSchedule() {
        //课表内容 ver X day, hor Y daytime
        for (int i = 0; i < PAGE_SIZE; i++) {
            List<Cell> cellListX = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                cellListX.add(j, new Cell("", "", "", ""));
            }

//                                    cells.add(cellList);
            for (int j = 0; j < ROW_SIZE; j++) {

                for (int k = 0; k < schedule.getTimeScheduleList().size(); k++) {
                    Schedule.TimeScheduleListBean item = schedule.getTimeScheduleList().get(k);
                    if (item.getDay() - 1 == i && item.getDayTime() - 1 == j) {
                        Cell cell = new Cell(item.getCurName(), item.getTeacher(), item.getPlace(), item.getWeek());
                        cellListX.add(item.getDayTime() - 1, cell);
                        break;
                    }

                }

            }

            if (cellListX.size() > 5) {
                for (int j = cellListX.size(); j > 5; j--) {
                    cellListX.remove(j - 1);
                }
            }


            cells.add(i, cellListX);
        }
    }

    private void loadDataDisk() {

        if (data.getSchedule() != null) {
            schedule = data.getSchedule();
            if (data.getCells() != null) {
                cells = data.getCells();
            }
            return;
        }
        loadData();

    }
}
