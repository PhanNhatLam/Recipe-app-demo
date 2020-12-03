package vn.edu.vhu.phannhatlam.recipecooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ItemSpecificationFragment extends Fragment {

    public ItemSpecificationFragment() {
        // Required empty public constructor
    }

    private RecyclerView itemSpecificationRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_specification, container, false);

        itemSpecificationRecyclerView = view.findViewById(R.id.item_specification_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        itemSpecificationRecyclerView.setLayoutManager(linearLayoutManager);

        List<ItemSpecificationModel> itemSpecificationModelList = new ArrayList<>();
        itemSpecificationModelList.add(new ItemSpecificationModel(0, "Bước thực hiện"));
        itemSpecificationModelList.add(new ItemSpecificationModel(1, "Bước 1", "A"));
        itemSpecificationModelList.add(new ItemSpecificationModel(1, "Bước 2", "B"));
        itemSpecificationModelList.add(new ItemSpecificationModel(1, "Bước 3", "C"));
        itemSpecificationModelList.add(new ItemSpecificationModel(1, "Bước 4", "D"));


        ItemSpecificationAdapter itemSpecificationAdapter = new ItemSpecificationAdapter(itemSpecificationModelList);
        itemSpecificationRecyclerView.setAdapter(itemSpecificationAdapter);
        itemSpecificationAdapter.notifyDataSetChanged();

        return view;
    }
}