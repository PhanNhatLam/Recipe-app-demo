package vn.edu.vhu.phannhatlam.recipecooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyFavoriteFragment extends Fragment {

    public MyFavoriteFragment() {
        // Required empty public constructor
    }

    private RecyclerView favoriteRecyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_favorite, container, false);

        favoriteRecyclerView = view.findViewById(R.id.my_favorite_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favoriteRecyclerView.setLayoutManager(linearLayoutManager);

        List<FavoriteModel> favoriteModelList = new ArrayList<>();
//        favoriteModelList.add(new FavoriteModel(R.drawable.banner, "123", "4.5", 15, "Me", "30"));

        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(favoriteModelList, true);
        favoriteRecyclerView.setAdapter(favoriteAdapter);
        favoriteAdapter.notifyDataSetChanged();

        return view;
    }

}