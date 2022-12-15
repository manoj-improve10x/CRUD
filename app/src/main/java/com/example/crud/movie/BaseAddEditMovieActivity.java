package com.example.crud.movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {
    //Todo: remove CrudService in all activities
    protected CrudService crudService;
    //ToDo:  change class name CustomSeriesItemsAdapter
    protected CustomSeriesAdapter customSeriesAdapter;
    //ToDo: change object to seriesItems
    protected ArrayList<Series> seriesList = new ArrayList<>();
    protected Spinner seriesSp;
    protected Movie movie;
    protected EditText movieNameTxt;
    protected EditText movieIdTxt;
    protected EditText imageUrlTxt;
    protected EditText movieDescriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        log("on create");
        setupApiService();
        findIds();
        setupSeriesListSp();
        fetchSeriesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    //ToDo: remove setupApiService method in all classes
    private void setupApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    protected void showData() {
        movieNameTxt.setText(movie.movieName);
        movieIdTxt.setText(movie.movieId);
        imageUrlTxt.setText(movie.movieImageUrl);
        movieDescriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesAdapter.getCount(); i++) {
            Series series = customSeriesAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                seriesSp.setSelection(i);
            }
        }
    }

    //Todo: change method name setupSeriesItemsSp
    private void setupSeriesListSp() {
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesAdapter);
    }

    private void findIds() {
        seriesSp = findViewById(R.id.movie_series_sp);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        movieIdTxt = findViewById(R.id.movie_id_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        movieDescriptionTxt = findViewById(R.id.movie_description_txt);
    }

    //ToDo: change method name fetchSeriesItems
    private void fetchSeriesList() {
        Call<List<Series>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                customSeriesAdapter.addAll(series);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
            }
        });
    }
}