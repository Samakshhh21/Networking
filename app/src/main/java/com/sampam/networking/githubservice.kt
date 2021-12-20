package com.sampam.networking
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface githubservice {
    @GET("users")
    suspend fun getusers():Response<List<User>>
    @GET("users/{id}")
    suspend fun getuser(@Path("id")id:String):Response<User>
    @GET("search/users")
    suspend fun searchusers(@Query("q")query:String):Response<Userresponse>
}