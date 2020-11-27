package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridItemLayoutAdapter extends BaseAdapter {

    List<HorizontalItemScrollModel> horizontalItemScrollModelList;

    public GridItemLayoutAdapter(List<HorizontalItemScrollModel> horizontalItemScrollModelList) {
        this.horizontalItemScrollModelList = horizontalItemScrollModelList;
    }

    @Override
    public int getCount() {
        return horizontalItemScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
            view.setElevation(1);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itemDetailsIntent = new Intent(parent.getContext(), ItemDetailsActivity.class);
                    parent.getContext().startActivity(itemDetailsIntent);

                }
            });

            ImageView itemImage = view.findViewById(R.id.h_s_item_image);
            TextView itemTitle = view.findViewById(R.id.h_s_item_title);
            TextView itemDescription = view.findViewById(R.id.h_s_item_description);
            TextView itemTime = view.findViewById(R.id.h_s_item_time);

            Glide.with(parent.getContext()).load(horizontalItemScrollModelList.get(position).getItemImage()).apply(new RequestOptions().placeholder(R.drawable.baseline_home_24)).into(itemImage);
            itemTitle.setText(horizontalItemScrollModelList.get(position).getItemTitle());
            itemDescription.setText("bởi " + horizontalItemScrollModelList.get(position).getItemDescription());
            itemTime.setText("Thời gian: " + horizontalItemScrollModelList.get(position).getItemTime() + " phút");
        } else {
            view = convertView;
        }
        return view;
    }
}
