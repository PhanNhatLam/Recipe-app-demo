package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemSpecificationAdapter extends RecyclerView.Adapter<ItemSpecificationAdapter.ViewHolder> {

    private List<ItemSpecificationModel> itemSpecificationModelList;

    public ItemSpecificationAdapter(List<ItemSpecificationModel> itemSpecificationModelList) {
        this.itemSpecificationModelList = itemSpecificationModelList;
    }

    @NonNull
    @Override
    public ItemSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_specification_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSpecificationAdapter.ViewHolder viewHolder, int position) {
        String featureTitle = itemSpecificationModelList.get(position).getFeatureName();
        String featureDetail = itemSpecificationModelList.get(position).getFeatureValue();
        viewHolder.setFeatures(featureTitle, featureDetail);
    }

    @Override
    public int getItemCount() {
        return itemSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView featureName;
        private TextView featureValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
        }

        private void setFeatures(String featureTitle, String featuredetail) {
            featureName.setText(featureTitle);
            featureValue.setText(featuredetail);
        }
    }

    private int setDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
