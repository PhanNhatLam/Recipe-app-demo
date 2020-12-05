package vn.edu.vhu.phannhatlam.recipecooking;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ItemImagesAdapter extends PagerAdapter {

    private List<String> itemImages;

    public ItemImagesAdapter(List<String> itemImages) {
        this.itemImages = itemImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView itemImage =  new ImageView(container.getContext());
        Glide.with(container.getContext()).load(itemImages.get(position)).apply(new RequestOptions().placeholder(R.drawable.baseline_home_24)).into(itemImage);
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
