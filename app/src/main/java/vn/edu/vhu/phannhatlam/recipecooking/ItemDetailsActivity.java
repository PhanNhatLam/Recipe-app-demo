package vn.edu.vhu.phannhatlam.recipecooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static vn.edu.vhu.phannhatlam.recipecooking.RegisterActivity.setSignUpFragment;

public class ItemDetailsActivity extends AppCompatActivity {

    private ViewPager itemImagesViewPager;
    private TabLayout viewpagerIndicator;

    private ViewPager itemDetailsViewpager;
    private TabLayout itemDetailsTablayout;

    ////// ratings layout
    private LinearLayout rateNowContainer;
    ////// ratings layout

    private static boolean ALREADY_ADDED_TO_FAVORITE = false;
    private FloatingActionButton addToFavoriteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemImagesViewPager = findViewById(R.id.item_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToFavoriteBtn = findViewById(R.id.add_to_favorite_btn);
        itemDetailsViewpager = findViewById(R.id.item_details_viewpager);
        itemDetailsTablayout = findViewById(R.id.item_details_tablayout);

        List<Integer> itemImages = new ArrayList<>();
        itemImages.add(R.drawable.banner);
        itemImages.add(R.drawable.banner6);
        itemImages.add(R.drawable.banner3);
        itemImages.add(R.drawable.banner2);

        ItemImagesAdapter itemImagesAdapter = new ItemImagesAdapter(itemImages);
        itemImagesViewPager.setAdapter(itemImagesAdapter);

        viewpagerIndicator.setupWithViewPager(itemImagesViewPager, true);

        addToFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Dialog box when clicking on an activity but not logged in
//                final Dialog signInDialog = new Dialog(ItemDetailsActivity.this);
//                signInDialog.setContentView(R.layout.sign_in_dialog);
//                signInDialog.setCancelable(true);
//
//                signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
//                Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);
//                final Intent registerIntent = new Intent(ItemDetailsActivity.this, RegisterActivity.class);
//
//                dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        signInDialog.dismiss();
//                        setSignUpFragment = false;
//                        startActivity(registerIntent);
//                    }
//                });
//
//                dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        signInDialog.dismiss();
//                        setSignUpFragment = true;
//                        startActivity(registerIntent);
//                    }
//                });
//                signInDialog.show();
*/
                if (ALREADY_ADDED_TO_FAVORITE) {
                    ALREADY_ADDED_TO_FAVORITE = false;
                    addToFavoriteBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    ALREADY_ADDED_TO_FAVORITE = true;
                    addToFavoriteBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                }
            }
        });

        itemDetailsViewpager.setAdapter(new ItemDetailsAdapter(getSupportFragmentManager(), itemDetailsTablayout.getTabCount()));

        itemDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(itemDetailsTablayout));
        itemDetailsTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                itemDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ////// ratings layout
        rateNowContainer = findViewById(R.id.rate_now_container);
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
        ////// ratings layout


    }

    private void setRating(int starPosition) {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starBtn = ((ImageView) rateNowContainer.getChildAt(x));
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#b4b4b4")));
            if (x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_save_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}