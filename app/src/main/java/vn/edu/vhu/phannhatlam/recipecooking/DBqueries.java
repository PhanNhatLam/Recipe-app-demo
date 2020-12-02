package vn.edu.vhu.phannhatlam.recipecooking;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBqueries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();

    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames = new ArrayList<>();

    public static void loadCategories(final CategoryAdapter categoryAdapter, final Context context) {

        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getLocalizedMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public static void loadFragmentData(final HomePageAdapter adapter, final Context context, final int index, String categoryName) {
        firebaseFirestore.collection("CATEGORIES")
                .document(categoryName.toUpperCase(Locale.ENGLISH))
                .collection("HOT_NEWS").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if ((long) documentSnapshot.get("view_type") == 0) {

                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long number_of_banners = (long) documentSnapshot.get("number_of_banners");
                                    for (long x = 1; x < number_of_banners + 1; x++) {
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_" + x).toString()
                                                , documentSnapshot.get("banner_" + x + "_background").toString()));
                                    }
                                    lists.get(index).add(new HomePageModel(0, sliderModelList));
                                } else if ((long) documentSnapshot.get("view_type") == 1) {

                                    List<HorizontalItemScrollModel> horizontalItemScrollModelList = new ArrayList<>();
                                    long number_of_recipes = (long) documentSnapshot.get("number_of_recipes");
                                    for (long x = 1; x < number_of_recipes + 1; x++) {
                                        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(documentSnapshot.get("recipe_ID_" + x).toString()
                                                ,documentSnapshot.get("recipe_image_" + x).toString()
                                                ,documentSnapshot.get("recipe_title_" + x).toString()
                                                ,documentSnapshot.get("recipe_subtitle_" + x).toString()
                                                ,documentSnapshot.get("recipe_time_" + x).toString()));

                                    }
                                    lists.get(index).add(new HomePageModel(1, documentSnapshot.get("layout_title").toString(), horizontalItemScrollModelList));

                                } else if ((long) documentSnapshot.get("view_type") == 2) {

                                    List<HorizontalItemScrollModel> gridLayoutModelList = new ArrayList<>();
                                    long number_of_recipes = (long) documentSnapshot.get("number_of_recipes");
                                    for (long x = 1; x < number_of_recipes + 1; x++) {
                                        gridLayoutModelList.add(new HorizontalItemScrollModel(documentSnapshot.get("recipe_ID_" + x).toString()
                                                ,documentSnapshot.get("recipe_image_" + x).toString()
                                                ,documentSnapshot.get("recipe_title_" + x).toString()
                                                ,documentSnapshot.get("recipe_subtitle_" + x).toString()
                                                ,documentSnapshot.get("recipe_time_" + x).toString()));
                                    }
                                    lists.get(index).add(new HomePageModel(2, documentSnapshot.get("layout_title").toString(), gridLayoutModelList));

                                } else if ((long) documentSnapshot.get("view_type") == 3) {

//                                    List<HorizontalItemScrollModel> horizontalItemScrollModelList = new ArrayList<>();
//                                    List<FavoriteModel> favoriteModelList = new ArrayList<>();
//                                    long number_of_recipes = (long) documentSnapshot.get("number_of_recipes");
//                                    for (long x = 1; x < number_of_recipes + 1; x++) {
//                                        horizontalItemScrollModelList.add(new HorizontalItemScrollModel(documentSnapshot.get("recipe_ID_" + x).toString()
//                                                ,documentSnapshot.get("recipe_image_" + x).toString()
//                                                ,documentSnapshot.get("recipe_title_" + x).toString()
//                                                ,documentSnapshot.get("recipe_subtitle_" + x).toString()
//                                                ,documentSnapshot.get("recipe_time_" + x).toString()));
//
//                                        favoriteModelList.add(new FavoriteModel(documentSnapshot.get("recipe_image_" + x).toString()
//                                                ,documentSnapshot.get("recipe_title_" +x).toString()
//                                                ,documentSnapshot.get("average_rating_" +x).toString()
//                                                ,(int) documentSnapshot.get("total_ratings_" +x)
//                                                ,documentSnapshot.get("recipe_subtitle_" +x).toString()
//                                                ,documentSnapshot.get("recipe_time_" +x).toString()));
//
//                                    }
//                                    lists.get(index).add(new HomePageModel(3, documentSnapshot.get("layout_title").toString(), horizontalItemScrollModelList, favoriteModelList));
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getLocalizedMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
