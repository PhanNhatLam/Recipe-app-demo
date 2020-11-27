package vn.edu.vhu.phannhatlam.recipecooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);


        ////// Banner Slider
//        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

//        sliderModelList.add(new SliderModel(R.drawable.banner7, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner8, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner, "#F0F0F0FA"));
//
//        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner3, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner4, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner5, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner6, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner7, "#F0F0F0FA"));
//
//        sliderModelList.add(new SliderModel(R.drawable.banner8, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F0F0F0FA"));
        ////// Banner Slider


        ////// Horizontal Item Layout
//        List<HorizontalItemScrollModel> horizontalItemScrollModelList = new ArrayList<>();
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner2, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner3, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner4, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner5, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner6, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner7, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner8, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
        ////// Horizontal Item Layout


        //////////////////////////
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
//        homePageModelList.add(new HomePageModel(0, sliderModelList));
//        homePageModelList.add(new HomePageModel(1, "Món ngon mỗi ngày", horizontalItemScrollModelList));
//        homePageModelList.add(new HomePageModel(1, "Món ngon mỗi tuần", horizontalItemScrollModelList));
//        homePageModelList.add(new HomePageModel(2, "Công thức chung", horizontalItemScrollModelList));
//        homePageModelList.add(new HomePageModel(0, sliderModelList));
//        homePageModelList.add(new HomePageModel(2, "Công thức chung", horizontalItemScrollModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}