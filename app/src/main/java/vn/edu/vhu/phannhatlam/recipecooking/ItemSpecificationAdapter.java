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

    @Override
    public int getItemViewType(int position) {
        switch (itemSpecificationModelList.get(position).getType()) {
            case 0:
                return ItemSpecificationModel.SPECIFICATION_TITLE;
            case 1:
                return ItemSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ItemSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case ItemSpecificationModel.SPECIFICATION_TITLE:
                TextView title = new TextView(viewGroup.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, viewGroup.getContext())
                        , setDp(16, viewGroup.getContext())
                        , setDp(16, viewGroup.getContext())
                        , setDp(8, viewGroup.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ItemSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_specification_item_layout, viewGroup, false);
                return new ViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ItemSpecificationAdapter.ViewHolder viewHolder, int position) {

        switch (itemSpecificationModelList.get(position).getType()) {
            case ItemSpecificationModel.SPECIFICATION_TITLE:
                viewHolder.setTitle(itemSpecificationModelList.get(position).getTitle());
                break;
            case ItemSpecificationModel.SPECIFICATION_BODY:
                String featureTitle = itemSpecificationModelList.get(position).getFeatureName();
                String featureDetail = itemSpecificationModelList.get(position).getFeatureValue();
                viewHolder.setFeatures(featureTitle, featureDetail);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return itemSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView featureName;
        private TextView featureValue;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void setTitle(String titleText) {
            title = (TextView) itemView;
            title.setText(titleText);
        }

        private void setFeatures(String featureTitle, String featuredetail) {
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
            featureName.setText(featureTitle);
            featureValue.setText(featuredetail);
        }
    }

    private int setDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
