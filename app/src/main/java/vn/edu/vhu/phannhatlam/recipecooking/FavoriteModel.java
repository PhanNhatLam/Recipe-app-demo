package vn.edu.vhu.phannhatlam.recipecooking;

public class FavoriteModel {

    private String itemImage;
    private String itemTitle;
    private String rating;
    private int totalRatings;
    private String itemMadeBy;
    private String itemTime;

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getItemMadeBy() {
        return itemMadeBy;
    }

    public void setItemMadeBy(String itemMadeBy) {
        this.itemMadeBy = itemMadeBy;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }

    public FavoriteModel(String itemImage, String itemTitle, String rating, int totalRatings, String itemMadeBy, String itemTime) {
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.rating = rating;
        this.totalRatings = totalRatings;
        this.itemMadeBy = itemMadeBy;
        this.itemTime = itemTime;
    }
}
