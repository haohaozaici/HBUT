package com.example.hao.hbut.model.bean;

import java.util.List;

/**
 * Created by hao on 2016-11-25.
 */

public class Schedule {
    public String Title;
    public String Content;
    public List<TimeScheduleList> TimeScheduleList;

    public class TimeScheduleList {
        public String CurName;
        public String Place;
        public String Teacher;
        public String Week;
    }

}

       /* "Title": "13电信1 2016学年 第一学期 课程表安排",
        "Content": "13电信1",
        "IsTeacher": false,
        "TimeScheduleList": [
        {
        "Classes": null,
        "CurName": "创业综合实践",
        "Day": 0,
        "DayStr": "星期一~星期六",
        "DayTime": 0,
        "DayTimeStr": "第1~8节",
        "Place": "DQ",
        "TaskTimeType": 2,
        "Teacher": "邹玲-综合指导,王东剑-综合指导,韦琳-综合指导,周冬婉-综合指导,王慧-综合指导,巩朋成-综合指导",
        "Week": "第19~20周"
        },
        {
        "Classes": null,
        "CurName": "数字视频技术",
        "Day": 2,
        "DayStr": null,
        "DayTime": 4,
        "DayTimeStr": null,
        "Place": "文A-507",
        "TaskTimeType": 1,
        "Teacher": "谢红刚-主讲",
        "Week": "第1-8周 第10周"
        },*/