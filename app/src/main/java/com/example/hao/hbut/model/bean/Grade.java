package com.example.hao.hbut.model.bean;

import java.util.List;

/**
 * Created by hao on 2016/10/25.
 */

public class Grade {

    public String AverageGradePoint;
    public String TotalGradePoint;
    public String Title;
    public String Name;
    public String IsViewGradeAfterEvaluating;
    public List<StuGradeList> StuGradeList;

    public class StuGradeList{
        public String CourseName;
        public String CourseType;
        public String CourseCredit;
        public String Grade;
        public String GradePoint;
    }

    /*
    "AverageGradePoint": 2.1062,
  "TotalGradePoint": 153.2,
  "Title": "13电信1 於浩远(1310200128) 2016学年 第一学期 ",
  "Name": "於浩远",
  "IsViewGradeAfterEvaluating": false,
  "StuGradeList": [
    {
      "TaskNO": "201617036",
      "CourseName": "2016夏季短学期实践答辩",
      "CourseType": "实践环节",
      "CourseCredit": 0.5,
      "Grade": 95,
      "GradePoint": 4.5,
      "CanModifyScore": 0,
      "IsShowScore": true,
      "HasEvaludated": true,
      "IsCurrentSemester": true
    },
    {
      "TaskNO": "201614068",
      "CourseName": "Web前端开发",
      "CourseType": "公选课",
      "CourseCredit": 2,
      "Grade": 0,
      "GradePoint": 0,
      "CanModifyScore": 1,
      "IsShowScore": false,
      "HasEvaludated": true,
      "IsCurrentSemester": true
    }
  ]*/
}
