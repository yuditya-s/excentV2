package com.yuditya.excentversion2.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.navigation.NavigationView
import com.yuditya.excentversion2.R
import de.hdodenhof.circleimageview.CircleImageView


class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val SHARED_PREF_NAME : String = "user_pref"
    private val KEY_ID : String = "pref_id_user"
    private val KEY_NAME : String = "pref_nama_lengkap"
    private val KEY_STATUS : String = "pref_status"
    private val KEY_IMG_USER : String = "pref_img_user"

    private var isLogin : Boolean = false

    val base_url_local : String = R.string.base_url_local.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //SHARED PREFERENCE
        val sharedPreference = getSharedPreferences("user_pref", MODE_PRIVATE)
        val id_user: Int = sharedPreference.getInt(KEY_ID, 0)
        val nama_lengkap : String = sharedPreference.getString("pref_nama_lengkap", "Guest Name").toString()
        val status : String = sharedPreference.getString("pref_status", "Guest Status").toString()
        val img_user : String? = sharedPreference.getString("pref_img_user", null)

        //MAIN MENU
        val feature_extracurricular : LinearLayout = findViewById(R.id.feature_extracurricular)
        feature_extracurricular.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val feature_administrasi : LinearLayout = findViewById(R.id.feature_administrasi)
        feature_administrasi.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val feature_event : LinearLayout = findViewById(R.id.feature_events)
        feature_event.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //COMPONEN DRAWER
        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val drawerButton : ImageButton = findViewById(R.id.button_open_drawer)
        val navigationView : NavigationView = findViewById(R.id.navigation_view)
        val navMenu : Menu = navigationView.menu
        drawerButton.setOnClickListener{
            drawerLayout.openDrawer(navigationView)
            navigationView.bringToFront()
        }
        navigationView.setNavigationItemSelectedListener(this)

        gettingSharedPrefData(id_user, nama_lengkap, status, img_user)
        drawerState(isLogin, navMenu)
    }

//    fun isLogin(idUser: Int): Boolean{
//        return !idUser.equals(0)
//    }
    fun drawerState(isLogin : Boolean, navMenu : Menu){
        if(isLogin){
            navMenu.findItem(R.id.mn_dwr_data_user).setVisible(true)
            navMenu.findItem(R.id.mn_dwr_my_ec).setVisible(true)
            navMenu.findItem(R.id.mn_dwr_notif).setVisible(true)
            navMenu.findItem(R.id.mn_dwr_event).setVisible(true)
            navMenu.findItem(R.id.mn_dwr_logout).setVisible(true)

            navMenu.findItem(R.id.mn_dwr_register).setVisible(false)
            navMenu.findItem(R.id.mn_dwr_login).setVisible(false)
        } else {
            navMenu.findItem(R.id.mn_dwr_data_user).setVisible(false)
            navMenu.findItem(R.id.mn_dwr_my_ec).setVisible(false)
            navMenu.findItem(R.id.mn_dwr_notif).setVisible(false)
            navMenu.findItem(R.id.mn_dwr_event).setVisible(false)
            navMenu.findItem(R.id.mn_dwr_logout).setVisible(false)

            navMenu.findItem(R.id.mn_dwr_register).setVisible(true)
            navMenu.findItem(R.id.mn_dwr_login).setVisible(true)
        }
    }
    fun gettingSharedPrefData(idUser: Int,
                              nama_lengkap : String,
                              status : String,
                              img_user : String?) {

        //COMPONEN DASHBOARD
        val navigationView : NavigationView = findViewById(R.id.navigation_view)
        val header : View = navigationView.getHeaderView(0)
        val sapaan_user : TextView = findViewById(R.id.tv_sapaan_pengguna)
        val headerNamaLengkap : TextView = header.findViewById(R.id.tv_nama_user_lengkap)
        val headerStatus : TextView = header.findViewById(R.id.tv_status_user)
        val headerUserImage : CircleImageView = header.findViewById(R.id.user_img_drawer)

        if(idUser != 0){
            sapaan_user.setText("Halo! $nama_lengkap")
            headerNamaLengkap.setText(nama_lengkap)
            headerStatus.setText(status)
            if(img_user != null){
                loadImgUser(img_user, headerUserImage)
            }
            isLogin = true

        } else {
            sapaan_user.setText("Halo Pengguna EXCENT. Silakan Login")
            isLogin = false
        }
    }

    private fun loadImgUser(imgUser: String, headerUserImage: CircleImageView) {
        val base_url_local = resources.getString(R.string.base_url_local)
        Glide
            .with(this)
            .load(base_url_local + imgUser)
            .apply(RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.img_user_error)
                .placeholder(R.drawable.img_placeholder))
            .into(headerUserImage)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val sharedPreference = getSharedPreferences("user_pref", MODE_PRIVATE)
        val id_user: Int = sharedPreference.getInt(KEY_ID, 0)
        when(item.itemId){
            R.id.mn_dwr_data_user -> {
                openRegister(id_user)
                Toast.makeText(this, "Drawer Menu Data Pengguna Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_my_ec -> {
                startActivity(Intent(this, MyEcActivity::class.java))
                Toast.makeText(this, "Drawer Menu Extracurricular Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_notif -> {
                Toast.makeText(this, "Drawer Menu Notifikasi Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_event -> {
                Toast.makeText(this, "Drawer Menu Event Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_about -> {
                Toast.makeText(this, "Drawer Menu About Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_logout -> {
                LogOut(sharedPreference)
                Toast.makeText(this, "Drawer Menu Logout Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_register -> {
                openRegister(id_user)
                Toast.makeText(this, "Drawer Menu Register Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.mn_dwr_login -> {
                openLogin()
                Toast.makeText(this, "Drawer Menu Login Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.END)
        return true
    }
    private fun LogOut(sharedPreferences: SharedPreferences) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
        Toast.makeText(this, "Log Out Successfull", Toast.LENGTH_LONG).show()
        val sapaan_user : TextView = findViewById(R.id.tv_sapaan_pengguna)
        sapaan_user.setText("Halo Pengguna EXCENT. Silakan Login")
        openLogin()
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun openRegister(idUser: Int) {
        val intent = Intent(this, RegisterActivity::class.java)
        if(idUser != 0){
            intent.putExtra("id_user_login", idUser)
        }
        startActivity(intent)
    }

}