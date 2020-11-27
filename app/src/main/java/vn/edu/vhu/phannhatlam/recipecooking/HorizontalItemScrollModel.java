package vn.edu.vhu.phannhatlam.recipecooking;

import android.widget.TextView;

public class HorizontalItemScrollModel {

    private String itemID;
    private String itemImage;
    private String itemTitle;
    private String itemDescription;
    private String itemTime;

    public HorizontalItemScrollModel(String itemID, String itemImage, String itemTitle, String itemDescription, String itemTime) {
        this.itemID = itemID;
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemTime = itemTime;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }
}
