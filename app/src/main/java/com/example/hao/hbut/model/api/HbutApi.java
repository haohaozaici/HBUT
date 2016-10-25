package com.example.hao.hbut.model.api;

import com.example.hao.hbut.model.data.Grade;
import com.example.hao.hbut.model.data.Student;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hao on 2016/10/25.
 */

public interface HbutApi {

    String Account_HOST = "http://run.hbut.edu.cn/Account/";

    //http://run.hbut.edu.cn/Account/LogOnForJson?Mobile=1&UserName=1310200128&Password=1310200128&Role=Student
    @GET("LogOnForJson?Mobile=1")
    Observable<Student> logOn(@Query("UserName") String UserName,
                              @Query("Password") String Password,
                              @Query("Role") String Role);

    String StuGrade_HOST = "http://run.hbut.edu.cn//StuGrade/IndexRecentSemesterForJson/";

    //http://run.hbut.edu.cn//StuGrade/IndexRecentSemesterForJson/?id=1110321229&Mobile=1
    @GET("?")
    Observable<ResponseBody> getRecent(@Query("id") String id,
                                @Query("Mobile") String m);


}
