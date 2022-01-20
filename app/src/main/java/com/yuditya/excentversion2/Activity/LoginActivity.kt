package com.yuditya.excentversion2.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.yuditya.excentversion2.R
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.getSystemService
import com.yuditya.excentversion2.Api.ApiClient
import com.yuditya.excentversion2.Api.ApiInterface
import com.yuditya.excentversion2.Model.ModelPengguna
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class LoginActivity : AppCompatActivity() {

    val SHARED_PREF_NAME = "user_pref"
    val KEY_ID = "pref_id_user"
    val KEY_NAME = "pref_nama_lengkap"
    val KEY_STATUS = "pref_status"
    val KEY_IMG_USER = "pref_img_user"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val log_email = findViewById<EditText>(R.id.input_login_email)
        val log_password = findViewById<EditText>(R.id.input_login_password)

        val buttonRegister = findViewById<Button>(R.id.button_register)
        buttonRegister.setOnClickListener(View.OnClickListener {
            openActivityReg()
        })

        val buttonLogin = findViewById<Button>(R.id.button_login)
        buttonLogin.setOnClickListener {
            //JAVA => InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(buttonLogin.windowToken, 0)
            if(log_email.text.isEmpty()){
                log_email.requestFocus()
                log_email.error = "Please fill the email"
            } else if (log_password.text.isEmpty()){
                log_password.requestFocus()
                log_password.error = "Please fill the password"
            } else {
                loginUser(log_email.text.toString(), log_password.text.toString())
            }
//            val imm = InputMethodManager(getSystemService())
        }

        val buttonSkip = findViewById<ImageButton>(R.id.button_skip)
        buttonSkip.setOnClickListener{
            buttonSkip.setBackgroundResource(R.drawable.arrow_skip)
            openDashboard()
        }
        val sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        val id_user: Int = sharedPreference.getInt(KEY_ID, 0)
        if(id_user != 0){
            Toast.makeText(this,
                "Anda sudah login sebagai: ${sharedPreference.getString(KEY_NAME, "No Name")}",
                Toast.LENGTH_LONG).show()
            openDashboard()
            finish()
        }

    }

    private fun openDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }

    private fun loginUser(logEmail:String, logPassword:String) {
        val base_url_local = resources.getString(R.string.base_url_local)
        val apiInterface : ApiInterface = ApiClient().getApiClient(base_url_local).create(ApiInterface::class.java)
        val call : Call<ModelPengguna> = apiInterface.loginUser(
                                            logEmail.trim(), logPassword.trim())
        call.enqueue(object : Callback<ModelPengguna>{
            override fun onResponse(call: Call<ModelPengguna>, response: Response<ModelPengguna>) {
                if(response.body() != null){
                    val modelPengguna = response.body()
                    if (modelPengguna != null) {
                        if(modelPengguna.getSuccess()){
                            val stringToast = "${modelPengguna.getMessage()}, Selamat Datang: ${modelPengguna.getNama_lengkap()}"
                            Toast.makeText(this@LoginActivity,stringToast,Toast.LENGTH_LONG).show()
                            addSharedPreference(
                                modelPengguna.getId_user(),
                                modelPengguna.getNama_lengkap(),
                                modelPengguna.getStatus(),
                                modelPengguna.getImg_user())
                                openDashboard()
                                this@LoginActivity.finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Pengguna tidak ditemukan atau password salah"
                                , Toast.LENGTH_LONG).show()
//                            log_email.clearFocus()
//                            log_email.setError("Email must be correct")
//                            log_password.clearFocus()
//                            log_password.setError("Password must be correct")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ModelPengguna>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Tidak bisa terkoneksi, cek internet", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun addSharedPreference(idUser: Int, namaLengkap: String, status: String, imgUser: String) {
        val sharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putInt(KEY_ID, idUser)
        editor.putString(KEY_NAME, namaLengkap)
        editor.putString(KEY_STATUS, status)
        editor.putString(KEY_IMG_USER, imgUser)
        editor.apply()
    }

    private fun openActivityReg() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}


