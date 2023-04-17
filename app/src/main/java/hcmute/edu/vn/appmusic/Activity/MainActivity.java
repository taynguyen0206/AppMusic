package hcmute.edu.vn.appmusic.Activity;

import static hcmute.edu.vn.appmusic.Activity.LoginActivity.IDACCOUNT;
import static hcmute.edu.vn.appmusic.Fragment.Account.AccountFragment.KEY_ISNIGHTMODE;
import static hcmute.edu.vn.appmusic.Fragment.Account.AccountFragment.MyPREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import hcmute.edu.vn.appmusic.R;
import hcmute.edu.vn.appmusic.Model.UserInfor;
import hcmute.edu.vn.appmusic.Database.Services.CallBack.UserCallBack;
import hcmute.edu.vn.appmusic.Database.DAO.UserDAO;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static SlidingUpPanelLayout slidingUpPanelLayout;
    public static BottomNavigationView bottomNavigationView ;
    SharedPreferences sharedPreferences;
    public static UserInfor userInfor = UserInfor.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.slidingUpPanel);
        // setBottom
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        getUser();
        checkDarkMode();

    }

   // slidingUpPanelLayout
    public static void slidingUpPanelLayout() {
        // COLLAPSED: xup do
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                slideDown(bottomNavigationView,slideOffset);
            }
            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        }) ;
    }

    // slideDown bottom
    public static void slideDown(BottomNavigationView child, float slideOffset) {
        float heigh_to_animate = slideOffset * child.getHeight();
        ViewPropertyAnimator animator = child.animate();
        animator
                .translationY(heigh_to_animate)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(0)
                .start();
    }

    // check Darkmode
    private void checkDarkMode() {
        sharedPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(KEY_ISNIGHTMODE, true)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // onBackPressed
    private long Timeback;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - Timeback > 1000) {
            Timeback = System.currentTimeMillis();
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            return;
        } else {
            finish();
        }
        super.onBackPressed();
    }

    // check user
    private void getUser() {
        final String userID ;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userInfor.setID(sharedPreferences.getString(IDACCOUNT,"123"));
        userID  = userInfor.getID() ;
        final UserDAO userDAO = new UserDAO(getApplicationContext());
        userDAO.getUser(userID, new UserCallBack() {
            @Override
            public void getCallback(ArrayList<UserInfor> userInfors) {
//                Log.d("test", userInfors.get(0).toString());
                if(userInfors.size()>0){
                    userInfor.setUsername(userInfors.get(0).getUsername());
                    Log.d("test", userInfors.get(0).getUsername());
                    userInfor.setEmail(userInfors.get(0).getEmail());
                    userInfor.setFavorites(userInfors.get(0).getFavorites());
                    userInfor.setLinkFaceBook(userInfors.get(0).getLinkFaceBook());
                    userInfor.setLinkGmail(userInfors.get(0).getLinkGmail());
                }
            }

        });
    }

}

