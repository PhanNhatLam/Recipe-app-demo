package vn.edu.vhu.phannhatlam.recipecooking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ItemDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;
    private String itemDescription;
    private String itemOtherDetails;
    private List<ItemSpecificationModel> itemSpecificationModelList;

    public ItemDetailsAdapter(FragmentManager fm, int totalTabs, String itemDescription, String itemOtherDetails, List<ItemSpecificationModel> itemSpecificationModelList) {
        super(fm);
        this.totalTabs = totalTabs;
        this.itemDescription = itemDescription;
        this.itemOtherDetails = itemOtherDetails;
        this.itemSpecificationModelList = itemSpecificationModelList;

    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                ItemDescriptionFragment itemDescriptionFragment1 = new ItemDescriptionFragment();
                itemDescriptionFragment1.body = itemDescription;
                return itemDescriptionFragment1;
            case 1:
                ItemSpecificationFragment itemSpecificationFragment = new ItemSpecificationFragment();
                itemSpecificationFragment.itemSpecificationModelList = itemSpecificationModelList;
                return itemSpecificationFragment;
            case 2:
                ItemDescriptionFragment itemDescriptionFragment2 = new ItemDescriptionFragment();
                itemDescriptionFragment2.body = itemOtherDetails;
                return itemDescriptionFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
