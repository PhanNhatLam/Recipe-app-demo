package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.categoryModelList;
import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.firebaseFirestore;
import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.homePageModelList;
import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.loadCategories;
import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.loadFragmentData;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

//    ////// Banner Slider
//    private ViewPager bannerSliderViewPager;
//    private List<SliderModel> sliderModelList;
//    private int currentPage = 2;
//    private Timer timer;
//    final private long DELAY_TIME = 3000;
//    final private long PERIOD_TIME = 3000;
//    ////// Banner Slider



    ////// Category
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    ////// Category



//    ////// Horizontal Item Layout
//    private TextView horizontalLayoutTitle;
//    private TextView horizontalLayoutViewAll;
//    private RecyclerView horizontalRecyclerView;
//    ////// Horizontal Item Layout



    ////// Home Page Recyclerview
    private RecyclerView homePageRecyclerView;
    private HomePageAdapter adapter;
    private ImageView noInternetConnection;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            noInternetConnection.setVisibility(View.GONE);
            categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            categoryRecyclerView.setLayoutManager(layoutManager);
            categoryAdapter = new CategoryAdapter(categoryModelList);
            categoryRecyclerView.setAdapter(categoryAdapter);

            if (categoryModelList.size() == 0) {
                loadCategories(categoryAdapter, getContext());
            } else {
                categoryAdapter.notifyDataSetChanged();
            }

            homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            homePageRecyclerView.setLayoutManager(testingLayoutManager);
            adapter = new HomePageAdapter(homePageModelList);
            homePageRecyclerView.setAdapter(adapter);

            if (homePageModelList.size() == 0) {
                loadFragmentData(adapter, getContext());
            } else {
                categoryAdapter.notifyDataSetChanged();
            }

        } else {
            Glide.with(this).load(R.drawable.loading).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
        }

        ////// Category



        ////// Category


        ////// Banner Slider
//        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
//        sliderModelList.add(new SliderModel(R.drawable.banner, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner3, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner4, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner5, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner6, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner7, "#F0F0F0FA"));
//        sliderModelList.add(new SliderModel(R.drawable.banner8, "#F0F0F0FA"));

//        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
//        bannerSliderViewPager.setAdapter(sliderAdapter);
//        bannerSliderViewPager.setClipToPadding(false);
//        bannerSliderViewPager.setPageMargin(20);
//
//        bannerSliderViewPager.setCurrentItem(currentPage);
//
//        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentPage = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int position) {
//                if (position == ViewPager.SCROLL_STATE_IDLE) {
//                    pageLooper();
//                }
//            }
//        };
//        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
//
//        startBannerSlideShow();
//
//        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                pageLooper();
//                stopBannerSlideShow();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    startBannerSlideShow();
//                }
//                return false;
//            }
//        });
        ////// Banner Slider




        ////// Horizontal Item Layout
//        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
//        horizontalLayoutViewAll = view.findViewById(R.id.horizontal_scroll_layout_view_all);
//        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

//        List<HorizontalItemScrollModel> horizontalItemScrollModelList = new ArrayList<>();
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner2, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner3, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner4, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner5, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner6, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner7, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner8, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner2, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner3, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner4, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner5, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner6, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner7, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));
//        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(R.drawable.banner8, "Salab đậu hũ chiên", "bởi ABC Cook", "Thời gian: 30 phút"));

//        HorizontalItemScrollAdapter horizontalItemScrollAdapter = new HorizontalItemScrollAdapter(horizontalItemScrollModelList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
//
//        horizontalRecyclerView.setAdapter(horizontalItemScrollAdapter);
//        horizontalItemScrollAdapter.notifyDataSetChanged();
        ////// Horizontal Item Layout



        //// Grid Item Layout
//        TextView gridLayoutTitle = view.findViewById(R.id.grid_layout_title);
//        TextView gridLayoutViewAll = view.findViewById(R.id.grid_layout_view_all);
//        GridView gridView = view.findViewById(R.id.grid_layout_gridview);
//
//        gridView.setAdapter(new GridItemLayoutAdapter(horizontalItemScrollModelList));
        //// Grid Item Layout



        ////// Testing


        ////// Testing

        return view;
    }

//    ////// Banner Slider
//    private void pageLooper() {
//        if (currentPage == sliderModelList.size() - 2) {
//            currentPage = 2;
//            bannerSliderViewPager.setCurrentItem(currentPage, false);
//        }
//        if (currentPage == 1) {
//            currentPage = sliderModelList.size() - 3;
//            bannerSliderViewPager.setCurrentItem(currentPage, false);
//        }
//    }
//
//    private void startBannerSlideShow() {
//        final Handler handler = new Handler();
//        final Runnable update = new Runnable() {
//            @Override
//            public void run() {
//                if (currentPage >= sliderModelList.size()) {
//                    currentPage = 1;
//                }
//                bannerSliderViewPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(update);
//            }
//        }, DELAY_TIME, PERIOD_TIME);
//    }
//
//    private void stopBannerSlideShow() {
//        timer.cancel();
//    }
//    ////// Banner Slider
}
