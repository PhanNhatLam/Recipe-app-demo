package vn.edu.vhu.phannhatlam.recipecooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ItemDescriptionFragment extends Fragment {

    public ItemDescriptionFragment() {
        // Required empty public constructor
    }

    private TextView descriptionBody;
    public static String body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_description, container, false);
        descriptionBody = view.findViewById(R.id.tv_item_description);
        descriptionBody.setText(body);
        return view;
    }
}