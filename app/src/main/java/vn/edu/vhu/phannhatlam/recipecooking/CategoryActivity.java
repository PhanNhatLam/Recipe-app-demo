package vn.edu.vhu.phannhatlam.recipecooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.lists;
import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.loadFragmentData;
import static vn.edu.vhu.phannhatlam.recipecooking.DBqueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private HomePageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        int listPosition = 0;
        for (int x = 0; x < loadedCategoriesNames.size(); x++) {
            if (loadedCategoriesNames.get(x).equals(title.toUpperCase(Locale.ENGLISH))) {
                listPosition = x;
            }
        }
        if (listPosition == 0) {
            loadedCategoriesNames.add(title.toUpperCase(Locale.ENGLISH));
            lists.add(new ArrayList<HomePageModel>());
            adapter = new HomePageAdapter(lists.get(loadedCategoriesNames.size() - 1));
            loadFragmentData(adapter, this, loadedCategoriesNames.size() - 1, title);
        } else {
            adapter = new HomePageAdapter(lists.get(listPosition));
        }

        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}