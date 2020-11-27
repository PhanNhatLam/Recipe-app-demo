package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HorizontalItemScrollAdapter extends RecyclerView.Adapter<HorizontalItemScrollAdapter.ViewHolder> {

    private List<HorizontalItemScrollModel> horizontalItemScrollModelList;

    public HorizontalItemScrollAdapter(List<HorizontalItemScrollModel> horizontalItemScrollModelList) {
        this.horizontalItemScrollModelList = horizontalItemScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalItemScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalItemScrollAdapter.ViewHolder viewHolder, int position) {
        String resource = horizontalItemScrollModelList.get(position).getItemImage();
        String title = horizontalItemScrollModelList.get(position).getItemTitle();
        String description = horizontalItemScrollModelList.get(position).getItemDescription();
        String time = horizontalItemScrollModelList.get(position).getItemTime();

        viewHolder.setItemImage(resource);
        viewHolder.setItemTitle(title);
        viewHolder.setItemDescription(description);
        viewHolder.setItemTime(time);

    }

    @Override
    public int getItemCount() {
        if (horizontalItemScrollModelList.size() > 8) {
            return 8;
        } else {
            return horizontalItemScrollModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDescription;
        private TextView itemTime;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.h_s_item_image);
            itemTitle = itemView.findViewById(R.id.h_s_item_title);
            itemDescription = itemView.findViewById(R.id.h_s_item_description);
            itemTime = itemView.findViewById(R.id.h_s_item_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itemDetailsIntent = new Intent(itemView.getContext(), ItemDetailsActivity.class);
                    itemView.getContext().startActivity(itemDetailsIntent);
                }
            });
        }

        private void setItemImage(String resource) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.baseline_home_24)).into(itemImage);
        }

        private void setItemTitle(String title) {
            itemTitle.setText(title);
        }

        private void setItemDescription(String description) {
            itemDescription.setText("bởi " + description);
        }

        private void setItemTime(String time) {
            itemTime.setText("Thời gian: " + time + " phút");
        }
    }
}
