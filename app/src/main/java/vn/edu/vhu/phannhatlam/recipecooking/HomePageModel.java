package vn.edu.vhu.phannhatlam.recipecooking;

import java.util.List;

public class HomePageModel {

    public static final int BANNER_SLIDER = 0;
    public static final int HORIZONTAL_ITEM_VIEW = 1;
    public static final int GRID_ITEM_VIEW = 2;

    private int type;


    ////// Banner Slider
    private List<SliderModel> sliderModelList;

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }
    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    ////// Banner Slider




    private String title;
    private List<HorizontalItemScrollModel> horizontalItemScrollModelList;

    ////// Grid Item Layout
    public HomePageModel(int type, String title, List<HorizontalItemScrollModel> horizontalItemScrollModelList) {
        this.type = type;
        this.title = title;
        this.horizontalItemScrollModelList = horizontalItemScrollModelList;
    }
    ////// Grid Item Layout



    ////// Horizontal Item Layout
    private List<FavoriteModel> favoriteModelList;

    public HomePageModel(int type, String title, List<HorizontalItemScrollModel> horizontalItemScrollModelList, List<FavoriteModel> favoriteModelList) {
        this.type = type;
        this.title = title;
        this.horizontalItemScrollModelList = horizontalItemScrollModelList;
        this.favoriteModelList = favoriteModelList;
    }

    public List<FavoriteModel> getFavoriteModelList() {
        return favoriteModelList;
    }

    public void setFavoriteModelList(List<FavoriteModel> favoriteModelList) {
        this.favoriteModelList = favoriteModelList;
    }

    ////// Horizontal Item Layout

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<HorizontalItemScrollModel> getHorizontalItemScrollModelList() {
        return horizontalItemScrollModelList;
    }

    public void setHorizontalItemScrollModelList(List<HorizontalItemScrollModel> horizontalItemScrollModelList) {
        this.horizontalItemScrollModelList = horizontalItemScrollModelList;
    }


}
