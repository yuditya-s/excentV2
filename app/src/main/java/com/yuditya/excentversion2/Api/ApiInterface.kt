package com.yuditya.excentversion2.Api

import com.yuditya.excentversion2.Model.ModelPengguna
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    //LOGIN
    @FormUrlEncoded
    @POST("data-pengguna/login_pengguna.php")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ModelPengguna>

    //REGISTER
    @FormUrlEncoded
    @POST("data-pengguna/retrieve_single_user.php")
    fun retriveSingleUser(@Field("id_user") id_user: Int) : Call<ModelPengguna>
}
