package com.jivahealth.test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface UserApi {
    // GET request to retrieve all users
    @GET("/api/users")
    Call<List<User>> getAllUsers();

    // POST request to add a new user
    @POST("/api/users")
    Call<User> createUser(@Body User user);
}

