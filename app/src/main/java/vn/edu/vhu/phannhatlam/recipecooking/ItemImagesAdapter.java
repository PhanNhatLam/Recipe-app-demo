package vn.edu.vhu.phannhatlam.recipecooking;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ItemImagesAdapter extends PagerAdapter {

    private List<Integer> itemImages;

    public ItemImagesAdapter(List<Integer> itemImages) {
        this.itemImages = itemImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView itemImage =  new ImageView(container.getContext());
        itemImage.setImageResource(itemImages.get(position));
        container.addView(itemImage, 0);
        return itemImage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return itemImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
