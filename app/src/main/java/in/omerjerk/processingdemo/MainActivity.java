package in.omerjerk.processingdemo;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.oguzdev.circularfloatingactionmenu.library.CircularMenu;

import in.omerjerk.processingdemo.sketch.Particles;
import processing.core.PApplet;

import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private InterstitialAd interstitial;
    private ImageView item_menu;
    private LinearLayout ll_out;
    private int [] lv_img={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.drawable.ic_plusone_medium_off_client};
    private int [] lv_choose_img={R.mipmap.sprite,R.mipmap.sprite_01,R.mipmap.sprite_02,
    R.mipmap.sprite_03,R.mipmap.sprite_04,R.mipmap.sprite_05,R.mipmap.sprite_06,R.mipmap.sprite_07};
    public static String url="sprite_01.png";
    private CircularMenu circularMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        item_menu= (ImageView) findViewById(R.id.item_menu);
        item_menu.setOnClickListener(this);
        ll_out= (LinearLayout) findViewById(R.id.layout_horizon);
        ll_out.setOnClickListener(this);
       // zenSketchView.addOnAttachStateChangeListener((View.OnAttachStateChangeListener) this);
        interstitial = new InterstitialAd(this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId("ca-app-pub-4424400162036997/4762512466");

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new Particles();
        Bundle bundle = new Bundle();
        bundle.putInt("url",height);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();


        AdView adView = (AdView) this.findViewById(R.id.adView);

        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder()

                // Add a test device to show Test Ads
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("CC5F2C72DF2B356BBF0DA198")
                .build();

        // Load ads into Banner Ads
        adView.loadAd(adRequest);

        // Load ads into Interstitial Ads
        interstitial.loadAd(adRequest);

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });


    }
    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
       // circularMenu = CircularMenuCreator.create();

    }
    public CircularMenu getCircularMenu() {
        return circularMenu;
    }
    @Override
    public void onClick(View v) {
        int n=0;
        if(v.getId()==R.id.item_menu){
            createListData(lv_img.length,lv_img);
        }else if(v.getId()==(lv_img.length-lv_img.length)){
            Toast.makeText(MainActivity.this, "Choose imageview", Toast.LENGTH_SHORT).show();
            createListDataChoose(lv_choose_img.length,lv_choose_img);
        }else if(v.getId()==((lv_img.length+3)-lv_img.length-1)){
            Toast.makeText(MainActivity.this, "Screen Shoft", Toast.LENGTH_SHORT).show();
        }else if(v.getId()==((lv_img.length+3)-lv_img.length-2)){
            Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length)){
            Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
            v.invalidate();
            createFragment("sprite.png");
            url="sprite.png";
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length-1)){
            Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
            createFragment("sprite_01.png");
            url="sprite_01.png";
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length-2)){
            Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
            createFragment("sprite_02.png");
            url="sprite_02.png";
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length-3)){
            Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
            createFragment("sprite_03.png");
            url="sprite_03.png";
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length-4)){
            Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
            createFragment("sprite_04.png");
            url="sprite_04.png";
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length-5)){
            Toast.makeText(MainActivity.this, "6", Toast.LENGTH_SHORT).show();
            createFragment("sprite_05.png");
            url="sprite_05.png";
        }else if(v.getId()==(lv_choose_img.length+3)-(lv_choose_img.length-6)){
            Toast.makeText(MainActivity.this, "7", Toast.LENGTH_SHORT).show();
            createFragment("sprite_06.png");
            url="sprite_06.png";
        }
        /*switch (v.getId()){
            case R.id.item_menu:
                createListData(lv_img.length,lv_img);
                break;
            case n:
                Toast.makeText(MainActivity.this, "Choose imageview", Toast.LENGTH_SHORT).show();
            //    ll_out.removeViews(0,3);
                createListData(lv_choose_img.length,lv_choose_img);
                break;
            case 1:
                Toast.makeText(MainActivity.this, "Screen Shoft", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
        }*/
    }
    private void createListData(int size, int[] array){
        ll_out.removeAllViews();
        ll_out.invalidate();
        for(int i=0;i<size;i++){
            ImageView imageView=new ImageView(this);
            imageView.setOnClickListener(this);
            imageView.setPadding(2,2,2,2);
            imageView.setId(i);
            imageView.setImageResource(array[i]);
            ll_out.addView(imageView);
        }
    }
    private void createListDataChoose(int size, int[] array){
        ll_out.removeAllViews();
        ll_out.invalidate();
        for(int i=0;i<size;i++){
            ImageView imageView=new ImageView(this);
            imageView.setOnClickListener(this);
            imageView.setPadding(2,2,2,2);
            imageView.setId(i+3);
            imageView.setImageResource(array[i]);
            ll_out.addView(imageView);
        }
    }
    private void createFragment(String url){
        Fragment fragment = new Particles();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }
    private void CreateListId(ImageView imgId){
        //imgId.setI

    }
}