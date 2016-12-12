package com.example.guillaume_grand_clement.herowars.network;

import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IWebService {

    @GET("/profile")
    Observable<SamplePojo> sampleServiceWithParam(@Query("param1") int currentPage, @Query("param2") int pageSize);

    @GET("profile/")
    Observable<SamplePojo> sampleService();
}
