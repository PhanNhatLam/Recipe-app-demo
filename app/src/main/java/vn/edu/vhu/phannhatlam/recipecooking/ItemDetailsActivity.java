package vn.edu.vhu.phannhatlam.recipecooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static vn.edu.vhu.phannhatlam.recipecooking.RegisterActivity.setSignUpFragment;

public class ItemDetailsActivity extends AppCompatActivity {

    private ViewPager itemImagesViewPager;
    private TextView itemTitle;
    private TextView averageRatingMiniView;
    private TextView totalRatingMiniView;
    private TextView itemTime;
    private TabLayout viewpagerIndicator;


    ////// recipe description
    private ConstraintLayout itemDetailsOnlyContainer;
    private ConstraintLayout itemDetailsTabsContainer;
    private ViewPager itemDetailsViewpager;
    private TabLayout itemDetailsTablayout;
    private TextView itemOnlyDescriptionBody;

    private List<ItemSpecificationModel> itemSpecificationModelList  = new ArrayList<>();
    private String itemDescription;
    private String itemOtherDetails;
    ////// recipe description


    ////// ratings layout
    private LinearLayout rateNowContainer;
    private TextView totalRatings;
    private LinearLayout ratingsNumbersContainer;
    private TextView totalRatingsFigure;
    private LinearLayout ratingsProgressBarContainer;
    private TextView averageRating;
    ////// ratings layout

    private static boolean ALREADY_ADDED_TO_FAVORITE = false;
    private FloatingActionButton addToFavoriteBtn;

    private FirebaseFirestore firebaseFirestore;

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
        itemTitle = findViewById(R.id.item_title);
        averageRatingMiniView = findViewById(R.id.tv_item_rating_miniview);
        totalRatingMiniView = findViewById(R.id.total_ratings_miniview);
        itemTime = findViewById(R.id.item_time);
        itemDetailsTabsContainer = findViewById(R.id.item_details_tabs_container);
        itemDetailsOnlyContainer = findViewById(R.id.item_details_container);
        itemOnlyDescriptionBody = findViewById(R.id.item_details_body);
        totalRatings = findViewById(R.id.total_ratings);
        ratingsNumbersContainer = findViewById(R.id.ratings_numbers_container);
        totalRatingsFigure = findViewById(R.id.total_ratings_figure);
        ratingsProgressBarContainer = findViewById(R.id.ratings_progressbar_container);
        averageRating = findViewById(R.id.average_rating);

        firebaseFirestore = FirebaseFirestore.getInstance();

        final List<String> itemImages = new ArrayList<>();

        firebaseFirestore.collection("RECIPES").document("5xCuwQti2eSKh6VXh2sC")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    for (long x = 1; x < (long) documentSnapshot.get("number_of_recipe_images") + 1; x++) {
                        itemImages.add(String.valueOf(documentSnapshot.get("recipe_image_" + x)));
                    }
                    ItemImagesAdapter itemImagesAdapter = new ItemImagesAdapter(itemImages);
                    itemImagesViewPager.setAdapter(itemImagesAdapter);

                    itemTitle.setText(documentSnapshot.get("recipe_title").toString());
                    averageRatingMiniView.setText(documentSnapshot.get("average_rating").toString());
                    totalRatingMiniView.setText("(" + (long) documentSnapshot.get("total_ratings") + ") đánh giá");
                    itemTime.setText("Thời gian: " + String.valueOf(documentSnapshot.get("recipe_time")) + " phút");

                    if ((boolean) documentSnapshot.get("use_tab_layout")) {
                        itemDetailsTabsContainer.setVisibility(View.VISIBLE);
                        itemDetailsOnlyContainer.setVisibility(View.GONE);
                        itemDescription = documentSnapshot.get("recipe_description").toString();

                        itemOtherDetails = documentSnapshot.get("recipe_other_details").toString();

                        for (long x = 1; x < (long) documentSnapshot.get("total_spec_titles") + 1; x++) {
                            itemSpecificationModelList.add(new ItemSpecificationModel(0, String.valueOf(documentSnapshot.get("spec_title_" + x))));
                            for (long y = 1; y < ((long) documentSnapshot.get("spec_title_" + x + "_total_fields")) + 1; y++) {
                                itemSpecificationModelList.add(new ItemSpecificationModel(1, String.valueOf(documentSnapshot.get("spec_title_" + x + "_field_" + y +"_name")), String.valueOf(documentSnapshot.get("spec_title_" + x + "_field_" + y +"_value"))));
                            }
                        }

                    } else {
                        itemDetailsTabsContainer.setVisibility(View.GONE);
                        itemDetailsOnlyContainer.setVisibility(View.VISIBLE);
                        itemOnlyDescriptionBody.setText(String.valueOf(documentSnapshot.get("recipe_description")));
                    }

                    totalRatings.setText((long) documentSnapshot.get("total_ratings") + " đánh giá");

                    for (int x = 0; x < 5; x++) {
                        TextView rating = (TextView) ratingsNumbersContainer.getChildAt(x);
                        rating.setText(String.valueOf((long) documentSnapshot.get((5 - x) + "_star")));

                        ProgressBar progressBar = (ProgressBar) ratingsProgressBarContainer.getChildAt(x);
                        int maxProgress = Integer.parseInt(String.valueOf((long) documentSnapshot.get("total_ratings")));
                        progressBar.setMax(maxProgress);
                        progressBar.setProgress(Integer.parseInt(String.valueOf((long) documentSnapshot.get((5 - x) + "_star"))));
                    }

                    totalRatingsFigure.setText(String.valueOf((long) documentSnapshot.get("total_ratings")));
                    averageRating.setText(String.valueOf(documentSnapshot.get("average_rating")));
                    itemDetailsViewpager.setAdapter(new ItemDetailsAdapter(getSupportFragmentManager(), itemDetailsTablayout.getTabCount(), itemDescription, itemOtherDetails, itemSpecificationModelList));

                } else {
                    String error = task.getException().getLocalizedMessage();
                    Toast.makeText(ItemDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });

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