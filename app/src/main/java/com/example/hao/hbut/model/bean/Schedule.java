package com.example.hao.hbut.model.bean;

import java.util.List;

/**
 * Created by hao on 2016-11-25.
 */

public class Schedule {


    /**
     * Title : 16电气_师资1 2016学年 第二学期 课程表安排
     * Content : 16电气_师资1
     * IsTeacher : false
     * TimeScheduleList : [{"Classes":null,"CurName":"中国近现代史纲要","Day":4,"DayStr":null,"DayTime":2,"DayTimeStr":null,"Place":"2-003","TaskTimeType":1,"Teacher":"张艳丽-主讲","Week":"第1-12周 "},{"Classes":null,"CurName":"大学英语读写译-2","Day":1,"DayStr":null,"DayTime":2,"DayTimeStr":null,"Place":"3-101","TaskTimeType":1,"Teacher":"陈玲-主讲","Week":"第1-14周 "},{"Classes":null,"CurName":"大学英语读写译-2","Day":3,"DayStr":null,"DayTime":2,"DayTimeStr":null,"Place":"3-101","TaskTimeType":1,"Teacher":"陈玲-主讲","Week":"第1-14周 "},{"Classes":null,"CurName":"线性代数","Day":2,"DayStr":null,"DayTime":4,"DayTimeStr":null,"Place":"工1-A203","TaskTimeType":1,"Teacher":"王刚LXY-主讲","Week":"第1-10周 "},{"Classes":null,"CurName":"线性代数","Day":4,"DayStr":null,"DayTime":3,"DayTimeStr":null,"Place":"工1-A203","TaskTimeType":1,"Teacher":"王刚LXY-主讲","Week":"第1-10周 "},{"Classes":null,"CurName":"大学物理(一)-1","Day":1,"DayStr":null,"DayTime":4,"DayTimeStr":null,"Place":"2-502","TaskTimeType":1,"Teacher":"欧艺文-主讲","Week":"第1-14周 "},{"Classes":null,"CurName":"大学物理(一)-1","Day":5,"DayStr":null,"DayTime":4,"DayTimeStr":null,"Place":"2-502","TaskTimeType":1,"Teacher":"欧艺文-主讲","Week":"第1-14周 "},{"Classes":null,"CurName":"电路理论(一)-1","Day":2,"DayStr":null,"DayTime":3,"DayTimeStr":null,"Place":"2-301","TaskTimeType":1,"Teacher":"凃玲英-主讲","Week":"第10-14周 "},{"Classes":null,"CurName":"电路理论(一)-1","Day":4,"DayStr":null,"DayTime":5,"DayTimeStr":null,"Place":"2-301","TaskTimeType":1,"Teacher":"凃玲英-主讲","Week":"第2-14周 "},{"Classes":null,"CurName":"金工实习(一)","Day":0,"DayStr":"星期一~星期六","DayTime":0,"DayTimeStr":"第1~8节","Place":"ZS","TaskTimeType":2,"Teacher":"陶世钊-实习指导","Week":"第15~16周"},{"Classes":null,"CurName":"职业教育学","Day":2,"DayStr":null,"DayTime":3,"DayTimeStr":null,"Place":"5B-210","TaskTimeType":1,"Teacher":"汪曦-主讲","Week":"第1-8周 "},{"Classes":null,"CurName":"职业教育学","Day":3,"DayStr":null,"DayTime":4,"DayTimeStr":null,"Place":"5B-210","TaskTimeType":1,"Teacher":"汪曦-主讲","Week":"第1-8周 "},{"Classes":null,"CurName":"ET班上课时间段","Day":1,"DayStr":null,"DayTime":1,"DayTimeStr":null,"Place":"教室8","TaskTimeType":1,"Teacher":"具体安排另行通知","Week":"第1-14周 "},{"Classes":null,"CurName":"ET班上课时间段","Day":3,"DayStr":null,"DayTime":1,"DayTimeStr":null,"Place":"教室8","TaskTimeType":1,"Teacher":"具体安排另行通知","Week":"第1-14周 "},{"Classes":null,"CurName":"工程图学(三)","Day":3,"DayStr":null,"DayTime":3,"DayTimeStr":null,"Place":"6B6-09","TaskTimeType":1,"Teacher":"尹杰-主讲","Week":"第5-12周 "},{"Classes":null,"CurName":"工程图学(三)","Day":5,"DayStr":null,"DayTime":1,"DayTimeStr":null,"Place":"6B6-09","TaskTimeType":1,"Teacher":"尹杰-主讲","Week":"第5-12周 "},{"Classes":null,"CurName":"高等数学(一)-2","Day":1,"DayStr":null,"DayTime":3,"DayTimeStr":null,"Place":"2-003","TaskTimeType":1,"Teacher":"耿亮-主讲","Week":"第1-8周 第11-13周 "},{"Classes":null,"CurName":"高等数学(一)-2","Day":5,"DayStr":null,"DayTime":3,"DayTimeStr":null,"Place":"2-003","TaskTimeType":1,"Teacher":"耿亮-主讲","Week":"第1-8周 第11-13周 "},{"Classes":null,"CurName":"高等数学(一)-2","Day":2,"DayStr":null,"DayTime":1,"DayTimeStr":null,"Place":"2-006","TaskTimeType":1,"Teacher":"耿亮-主讲","Week":"第1-8周 第11-13周 "},{"Classes":null,"CurName":"高等数学(一)-2","Day":4,"DayStr":null,"DayTime":4,"DayTimeStr":null,"Place":"2-006","TaskTimeType":1,"Teacher":"耿亮-主讲","Week":"第1-8周 第11-13周 "},{"Classes":null,"CurName":"大学英语听说-2","Day":5,"DayStr":null,"DayTime":2,"DayTimeStr":null,"Place":"5B-206","TaskTimeType":1,"Teacher":"方菲-主讲","Week":"第1-12周 "},{"Classes":null,"CurName":"电子实训课外拓展-1","Day":2,"DayStr":null,"DayTime":5,"DayTimeStr":null,"Place":"6A-603","TaskTimeType":1,"Teacher":"王超DQ-主讲","Week":"第2-9周 "},{"Classes":null,"CurName":"ET班上课时间段","Day":5,"DayStr":null,"DayTime":2,"DayTimeStr":null,"Place":"教室8","TaskTimeType":1,"Teacher":"具体安排另行通知","Week":"第1-12周 "}]
     */

    private String Title;
    private String Content;
    private boolean IsTeacher;
    private List<TimeScheduleListBean> TimeScheduleList;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public boolean isIsTeacher() {
        return IsTeacher;
    }

    public void setIsTeacher(boolean IsTeacher) {
        this.IsTeacher = IsTeacher;
    }

    public List<TimeScheduleListBean> getTimeScheduleList() {
        return TimeScheduleList;
    }

    public void setTimeScheduleList(List<TimeScheduleListBean> TimeScheduleList) {
        this.TimeScheduleList = TimeScheduleList;
    }

    public static class TimeScheduleListBean {
        /**
         * Classes : null
         * CurName : 中国近现代史纲要
         * Day : 4
         * DayStr : null
         * DayTime : 2
         * DayTimeStr : null
         * Place : 2-003
         * TaskTimeType : 1
         * Teacher : 张艳丽-主讲
         * Week : 第1-12周
         */

        private Object Classes;
        private String CurName;
        private int Day;
        private Object DayStr;
        private int DayTime;
        private Object DayTimeStr;
        private String Place;
        private int TaskTimeType;
        private String Teacher;
        private String Week;

        public Object getClasses() {
            return Classes;
        }

        public void setClasses(Object Classes) {
            this.Classes = Classes;
        }

        public String getCurName() {
            return CurName;
        }

        public void setCurName(String CurName) {
            this.CurName = CurName;
        }

        public int getDay() {
            return Day;
        }

        public void setDay(int Day) {
            this.Day = Day;
        }

        public Object getDayStr() {
            return DayStr;
        }

        public void setDayStr(Object DayStr) {
            this.DayStr = DayStr;
        }

        public int getDayTime() {
            return DayTime;
        }

        public void setDayTime(int DayTime) {
            this.DayTime = DayTime;
        }

        public Object getDayTimeStr() {
            return DayTimeStr;
        }

        public void setDayTimeStr(Object DayTimeStr) {
            this.DayTimeStr = DayTimeStr;
        }

        public String getPlace() {
            return Place;
        }

        public void setPlace(String Place) {
            this.Place = Place;
        }

        public int getTaskTimeType() {
            return TaskTimeType;
        }

        public void setTaskTimeType(int TaskTimeType) {
            this.TaskTimeType = TaskTimeType;
        }

        public String getTeacher() {
            return Teacher;
        }

        public void setTeacher(String Teacher) {
            this.Teacher = Teacher;
        }

        public String getWeek() {
            return Week;
        }

        public void setWeek(String Week) {
            this.Week = Week;
        }
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