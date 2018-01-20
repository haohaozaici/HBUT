package com.example.hao.hbut.model.network.api;

import com.example.hao.hbut.feature.login.bean.LoginRes;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hao on 2016/10/25.
 */

public interface HbutApi {

    String Account_HOST = "http://run.hbut.edu.cn/Account/";

    //登录
    //http://run.hbut.edu.cn/Account/LogOnForJson?Mobile=1&UserName=1310200128&Password=1310200128&Role=Student
    @GET("LogOnForJson?Mobile=1")
    Flowable<LoginRes> logOn(@Query("UserName") String UserName,
                             @Query("Password") String Password,
                             @Query("Role") String Role);

    //最近学期成绩
    //http://run.hbut.edu.cn/StuGrade/IndexRecentSemesterForJson/?id=1110321229&Mobile=1
    //所有成绩
    //http://run.hbut.edu.cn/StuGrade/IndexAllSemesterForJson?Id=1310200128&Mobile=1
    String StuGrade_HOST = "http://run.hbut.edu.cn/StuGrade/IndexRecentSemesterForJson/";

    @GET("?")
    Observable<ResponseBody> getRecent(@Query("id") String id,
                                       @Query("Mobile") String m);

    //http://run.hbut.edu.cn/StuGrade/ViewBukaoSchedule 补考科目  html


    /*
    * 课表 带cookie
    * @Semester
     */
    //http://run.hbut.edu.cn/ArrangeTask/MyselfScheduleForJson?Semester=20151&Id=1310200128&Role=Student
    String Schedule_Host = "http://run.hbut.edu.cn/ArrangeTask/MyselfScheduleForJson/";

    @GET("?")
    Observable<ResponseBody> getSchedule(@Query("Semester") String semester,
                                         @Query("Id") String id,
                                         @Query("Role") String role);


    // 图书馆
    String LibHost = "http://202.114.181.3:8080/";
    String LIB_ADD_COOKIE = "LIB_ADD_COOKIE";
    String LIB_RECEIVED_COOKIE = "LIB_RECEIVED_COOKIE";

    @GET("reader/captcha.php")
    Observable<ResponseBody> getCaptchaAndCookie();

    // number:1310200128
    // passwd:1xxxx5
    // captcha:2527
    // select:cert_no
    // returnUrl:
    @GET("reader/redr_verify.php")
    Observable<ResponseBody> verifyCookie(@Query("number") String userName,
                                          @Query("passwd") String password,
                                          @Query("captcha") String captcha,
                                          @Query("select") String cert_no);

    @GET("/reader/redr_info.php")
    Observable<ResponseBody> getInfo();

}
