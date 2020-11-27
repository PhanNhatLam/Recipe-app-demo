package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteModel> favoriteModelList;
    private Boolean favorite;

    public FavoriteAdapter(List<FavoriteModel> favoriteModelList, Boolean favorite) {
        this.favoriteModelList = favoriteModelList;
        this.favorite = favorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String resource = favoriteModelList.get(position).getItemImage();
        String title = favoriteModelList.get(position).getItemTitle();
        String rating = favoriteModelList.get(position).getRating();
        long totalRatings = favoriteModelList.get(position).getTotalRatings();
        String itemMadeBy = favoriteModelList.get(position).getItemMadeBy();
        String itemTime = favoriteModelList.get(position).getItemTime();

        viewHolder.setData(resource, title, rating, totalRatings, itemMadeBy, itemTime);
    }

    @Override
    public int getItemCount() {
        return favoriteModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView rating;
        private TextView totalRatings;
        private TextView itemMadeBy;
        private TextView itemTime;
        private ImageView deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            rating = itemView.findViewById(R.id.tv_item_rating_miniview);
            totalRatings = itemView.findViewById(R.id.total_ratings);
            itemMadeBy = itemView.findViewById(R.id.item_made_by);
            itemTime = itemView.findViewById(R.id.item_time);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }

        private void setData(String resource, String title, String averageRate, long totalRatingsNumber, String itemMadeIt, String time) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.baseline_home_24)).into(itemImage);
//            itemImage.setImageResource(resource);
            itemTitle.setText(title);
            rating.setText(averageRate);
            totalRatings.setText("(" + totalRatingsNumber + ") đánh giá");
            itemMadeBy.setText("bởi " + itemMadeIt);
            itemTime.setText("Thời gian: " + time);

            if (favorite) {
                deleteBtn.setVisibility(View.VISIBLE);
            } else {
                deleteBtn.setVisibility(View.GONE);
            }
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Xoá", Toast.LENGTH_SHORT).show();

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itemDetailsIntent = new Intent(itemView.getContext(), ItemDetailsActivity.class);
                    itemView.getContext().startActivity(itemDetailsIntent);
                }
            });
        }
    }
}
