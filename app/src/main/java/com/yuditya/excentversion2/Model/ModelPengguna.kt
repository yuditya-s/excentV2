package com.yuditya.excentversion2.Model

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

class ModelPengguna {
    @SerializedName("id_user")
    private var id_user = 0

    @SerializedName("email")
    private var email: String = ""

    @SerializedName("password")
    private var password: String = ""

    @SerializedName("nama_lengkap")
    private var nama_lengkap: String = ""

    @SerializedName("status")
    private var status: String = ""

    @SerializedName("nip_nis")
    private var nip_nis: BigInteger? = null

    @SerializedName("kelas")
    private var kelas: String = ""

    @SerializedName("gender")
    private var gender: String = ""

    @SerializedName("no_hp")
    private var no_hp: String = ""

    @SerializedName("img_user")
    private var img_user: String = ""

    private var date_join: String? = null


    @SerializedName("value")
    private var values: String? = null

    @SerializedName("success")
    private var success: Boolean = false

    @SerializedName("message")
    private var message: String = ""

//    -----------------------------------------
    fun getSuccess() : Boolean{
        return success
    }
    fun setSuccess(success : Boolean){
        this.success = success
    }
    //    -----------------------------------------
    fun getMessage() : String{
        return message
    }
    fun setMessage(message : String){
        this.message = message
    }
    //    -----------------------------------------
    //USER RELATED
    fun getId_user() : Int{
        return id_user
    }
    fun setId_user(id_user : Int){
        this.id_user = id_user
    }
    //    -----------------------------------------
    fun getNama_lengkap() : String{
        return nama_lengkap
    }
    fun setNama_lengkap(namaLengkap : String){
        this.nama_lengkap = nama_lengkap
    }
    //    -----------------------------------------
    fun getStatus() : String{
        return status
    }
    fun setStatus(status : String){
        this.status = status
    }
    //    -----------------------------------------
    fun getNip_nis() : BigInteger? {
        return nip_nis
    }
    fun setNip_nis(nip_nis: BigInteger?){
        this.nip_nis = nip_nis
    }
    //    -----------------------------------------
    fun getKelas() : String{
        return kelas
    }
    fun setKelas(kelas: String){
        this.kelas = kelas
    }
    //    -----------------------------------------
    fun getImg_user() : String{
        return img_user
    }
    fun setImg_user(img_user: String){
        this.img_user = img_user
    }
    //EMAIL&PASSWORD GETTER SETTER
    fun getEmail() : String{
        return email
    }
    fun setEmail(email: String){
        this.email = email
    }
    fun getPassword() : String{
        return password
    }
    fun setPassword(password: String){
        this.password = password
    }
}