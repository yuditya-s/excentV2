package com.yuditya.excentversion2.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.santalu.maskara.widget.MaskEditText
import com.yuditya.excentversion2.Api.ApiClient
import com.yuditya.excentversion2.Api.ApiInterface
import com.yuditya.excentversion2.Model.ModelPengguna
import com.yuditya.excentversion2.R
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger

class RegisterActivity : AppCompatActivity() {
    var base_url_local: String = ""
    var email : String = ""
    var password : String = ""
    var nama_lengkap : String = ""
    var nip_nis : BigInteger? = null
    var kelas : String = ""
    var img_user : String = ""
    var no_hp : String = ""

    lateinit var action: Menu

    var id_user : Int = 0
    var status : String = "Siswa"
    var gender : String = "Laki-laki"

    lateinit var reg_email: EditText
    lateinit var reg_password: EditText
    lateinit var reg_nama_lengkap: EditText
    lateinit var reg_nip_nis: EditText
    lateinit var reg_kelas: EditText
    lateinit var reg_no_hp: MaskEditText

    lateinit var reg_status: RadioGroup
    lateinit var reg_gender: RadioGroup
    lateinit var selected_reg_status: RadioButton
    lateinit var selected_reg_gender: RadioButton
    lateinit var rb_status_siswa: RadioButton
    lateinit var rb_status_guru: RadioButton
    lateinit var rb_gender_laki: RadioButton
    lateinit var rb_gender_perempuan: RadioButton

    lateinit var btnSelect_profile_pic: FloatingActionButton
    lateinit var view_profile_pic: CircleImageView
    lateinit var bitmap: Bitmap
    lateinit var imageUri: Uri

    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        val pick_image : Int = 100
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        base_url_local = resources.getString(R.string.base_url_local)

        reg_email = findViewById(R.id.userEmail)
        reg_password = findViewById(R.id.userPassword)
        reg_nama_lengkap = findViewById(R.id.nama_lengkap)
        reg_status = findViewById(R.id.status)
        reg_nip_nis = findViewById(R.id.user_nip_nis)
        reg_nip_nis.setHint("NIS")
        reg_kelas = findViewById(R.id.user_kelas)
        reg_nip_nis.setHint("Kelas/Jurusan")
        reg_no_hp = findViewById(R.id.user_no_hp)
        reg_gender = findViewById(R.id.user_gender)
        rb_status_siswa = findViewById(R.id.status_siswa)
        rb_status_guru = findViewById(R.id.status_guru)
        rb_gender_laki = findViewById(R.id.gender_laki)
        rb_gender_perempuan = findViewById(R.id.gender_perempuan)

        val btnSelect_profile_pic = findViewById<FloatingActionButton>(R.id.fabChoosePic)
        btnSelect_profile_pic.setOnClickListener{
            chooseFile()
        }
        view_profile_pic = findViewById(R.id.profile_picture)

        val intent = Intent()
        val id_user = intent.getIntExtra("id_user_login", 0)

        retrieveSingleUser(id_user)
    }

    private fun apiInit():ApiInterface{
        val base_url_local = resources.getString(R.string.base_url_local)
        val apiInterface : ApiInterface = ApiClient().getApiClient(base_url_local).create(ApiInterface::class.java)
        return apiInterface
    }

    private fun retrieveSingleUser(idUser: Int) {
        if(idUser != 0){
            readMode()
            val call : Call<ModelPengguna> = apiInit().retriveSingleUser(idUser)
            call.enqueue(object : Callback<ModelPengguna>{
                override fun onResponse(
                    call: Call<ModelPengguna>,
                    response: Response<ModelPengguna>
                ) {
                    if(response.isSuccessful){
                        if(response.body() != null){
                            email = response.body()!!.getEmail()
                            password = response.body()!!.getPassword()
                            nama_lengkap = response.body()!!.getNama_lengkap()
                            status = response.body()!!.getStatus()
                            nip_nis = response.body()!!.getNip_nis()
                            kelas = response.body()!!.getKelas()
                            gender
                            no_hp
                            img_user

                            reg_email.setText(email)
                            reg_password.setText(password)
                            reg_nama_lengkap.setText(nama_lengkap)

                            if(status.equals("Siswa")){
                                rb_status_siswa.isChecked = true
                                rb_status_guru.isChecked = false
                            } else {
                                rb_status_siswa.isChecked = false
                                rb_status_guru.isChecked = true
                            }

                            reg_nip_nis.setText(nip_nis.toString())
                            reg_kelas.setText(kelas)
                            reg_no_hp.setText(no_hp)

                            if(status.equals("Laki-laki")){
                                rb_gender_laki.isChecked = true
                                rb_gender_perempuan.isChecked = false
                            } else {
                                rb_gender_laki.isChecked = false
                                rb_gender_perempuan.isChecked = true
                            }
                            Glide
                                .with(this@RegisterActivity)
                                .load(base_url_local + img_user)
                                .apply(RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .error(R.drawable.img_user_error)
                                    .placeholder(R.drawable.img_placeholder))
                                .into(view_profile_pic)
                        } else {
                            Toast.makeText(this@RegisterActivity, "Tidak ada respon check backend API", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                override fun onFailure(call: Call<ModelPengguna>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Tidak ada koneksi, check internet", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    fun checkButtonStatus(){
//        selected_reg_status
//        selected_reg_gender
        val radioIdStatus : Int = reg_status.checkedRadioButtonId
        when(radioIdStatus){
            R.id.status_siswa -> {this.status = "Siswa"
                                    reg_nip_nis.setHint("NIS")
                                    reg_kelas.setHint("Kelas")
                                    }
            R.id.status_guru -> {this.status = "Guru"
                                    reg_nip_nis.setHint("NIP")
                                    reg_kelas.setHint("Jurusan/Mapel")
            }
        }
    }
    fun checkButtonGender(){
        val radioIdGender : Int = reg_gender.checkedRadioButtonId
        when(radioIdGender){
            R.id.gender_laki -> this.gender = "Laki-laki"
            R.id.gender_perempuan -> this.gender = "Perempuan"
        }
    }

    private fun readMode() {
        TODO("Not yet implemented")
    }

    private fun chooseFile() {
        TODO("Not yet implemented")
    }
}