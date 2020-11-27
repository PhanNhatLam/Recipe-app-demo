package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.HORIZONTAL_ITEM_VIEW;
            case 2:
                return HomePageModel.GRID_ITEM_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout, parent, false);
                return new BannerSliderViewholder(bannerSliderView);
            case HomePageModel.HORIZONTAL_ITEM_VIEW:
                View horizontalItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalItemViewholder(horizontalItemView);
            case HomePageModel.GRID_ITEM_VIEW:
                View gridItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, parent, false);
                return new GridItemViewholder(gridItemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewholder) holder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.HORIZONTAL_ITEM_VIEW:
                String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
                List<FavoriteModel> favoriteModelList = homePageModelList.get(position).getFavoriteModelList();
                List<HorizontalItemScrollModel> horizontalItemScrollModelList = homePageModelList.get(position).getHorizontalItemScrollModelList();
                ((HorizontalItemViewholder) holder).setHorizontalItemLayout(horizontalItemScrollModelList, horizontalLayoutTitle, favoriteModelList);
                break;
            case HomePageModel.GRID_ITEM_VIEW:
                String gridLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalItemScrollModel> gridItemScrollModelList = homePageModelList.get(position).getHorizontalItemScrollModelList();
                ((GridItemViewholder) holder).setGridItemLayout(gridItemScrollModelList, gridLayoutTitle);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewholder extends RecyclerView.ViewHolder {

        private ViewPager bannerSliderViewPager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;

        public BannerSliderViewholder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            currentPage = 2;
            if (timer != null) {
                timer.cancel();
            }
            arrangedList = new ArrayList<>();
            for (int x = 0; x < sliderModelList.size(); x++) {
                arrangedList.add(x, sliderModelList.get(x));
            }
            arrangedList.add(0, sliderModelList.get(sliderModelList.size() - 2));
            arrangedList.add(1, sliderModelList.get(sliderModelList.size() - 1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            SliderAdapter sliderAdapter = new SliderAdapter(arrangedList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);

            bannerSliderViewPager.setCurrentItem(currentPage);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int position) {
                    if (position == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangedList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopBannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrangedList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSlideShow() {
            timer.cancel();
        }
    }

    public class HorizontalItemViewholder extends RecyclerView.ViewHolder {

        private TextView horizontalLayoutTitle;
        private TextView horizontalLayoutViewAll;
        private RecyclerView horizontalRecyclerView;

        public HorizontalItemViewholder(@NonNull View itemView) {
            super(itemView);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalLayoutViewAll = itemView.findViewById(R.id.horizontal_scroll_layout_view_all);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalItemLayout(List<HorizontalItemScrollModel> horizontalItemScrollModelList, final String title, final List<FavoriteModel> favoriteModelList) {

            horizontalLayoutTitle.setText(title);
            if (horizontalItemScrollModelList.size() > 8) {
                horizontalLayoutViewAll.setVisibility(View.VISIBLE);
                horizontalLayoutViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        ViewAllActivity.favoriteModelList = favoriteModelList;
                        viewAllIntent.putExtra("title", title);
                        viewAllIntent.putExtra("layout_code", 0);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                horizontalLayoutViewAll.setVisibility(View.INVISIBLE);
            }

            HorizontalItemScrollAdapter horizontalItemScrollAdapter = new HorizontalItemScrollAdapter(horizontalItemScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(horizontalItemScrollAdapter);
            horizontalItemScrollAdapter.notifyDataSetChanged();
        }
    }

    public class GridItemViewholder extends RecyclerView.ViewHolder {

        private TextView gridLayoutTitle;
        private TextView gridLayoutViewAll;
        private GridLayout gridItemLayout;

        public GridItemViewholder(@NonNull View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.grid_item_layout_title);
            gridLayoutViewAll = itemView.findViewById(R.id.grid_item_layout_view_all);
            gridItemLayout = itemView.findViewById(R.id.grid_layout);
        }

        private void setGridItemLayout(final List<HorizontalItemScrollModel> horizontalItemScrollModelList, final String title) {
            gridLayoutTitle.setText(title);


            for (int x = 0; x < 4; x++) {
                ImageView itemImage = gridItemLayout.getChildAt(x).findViewById(R.id.h_s_item_image);
                TextView itemTitle = gridItemLayout.getChildAt(x).findViewById(R.id.h_s_item_title);
                TextView itemDescription = gridItemLayout.getChildAt(x).findViewById(R.id.h_s_item_description);
                TextView itemTime = gridItemLayout.getChildAt(x).findViewById(R.id.h_s_item_time);

                Glide.with(itemView.getContext()).load(horizontalItemScrollModelList.get(x).getItemImage()).apply(new RequestOptions().placeholder(R.drawable.baseline_home_24)).into(itemImage);
                itemTitle.setText(horizontalItemScrollModelList.get(x).getItemTitle());
                itemDescription.setText("bởi " + horizontalItemScrollModelList.get(x).getItemDescription());
                itemTime.setText("Thời gian: " + horizontalItemScrollModelList.get(x).getItemTime() + " phút");
                gridItemLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));
                gridItemLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent itemDetailsIntent = new Intent(itemView.getContext(), ItemDetailsActivity.class);
                        itemView.getContext().startActivity(itemDetailsIntent);

                    }
                });
            }


            gridLayoutViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewAllActivity.horizontalItemScrollModelList = horizontalItemScrollModelList;
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code", 1);
                    viewAllIntent.putExtra("title", title);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }
}
