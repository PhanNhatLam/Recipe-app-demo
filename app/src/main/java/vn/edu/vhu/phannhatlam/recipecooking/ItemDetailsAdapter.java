package vn.edu.vhu.phannhatlam.recipecooking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ItemDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public ItemDetailsAdapter(@NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                ItemDescriptionFragment itemDescriptionFragment1 = new ItemDescriptionFragment();
                return itemDescriptionFragment1;
            case 1:
                ItemSpecificationFragment itemSpecificationFragment = new ItemSpecificationFragment();
                return itemSpecificationFragment;
            case 2:
                ItemDescriptionFragment itemDescriptionFragment2 = new ItemDescriptionFragment();
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
