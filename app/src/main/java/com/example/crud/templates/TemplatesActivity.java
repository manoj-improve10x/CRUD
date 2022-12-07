package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.AddEditSeriesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {

    private RecyclerView templatesRv;
    private ArrayList<Template> templates = new ArrayList<>();
    private ProgressBar templatesProgressbar;
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setupTemplatesRv();

    }
    private void showProgressbar() {
        templatesProgressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        templatesProgressbar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.templates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.template_add) {
            Intent intent = new Intent(this, AddEditTemplateActivity.class);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplate();

    }

    private void fetchTemplate() {
        showProgressbar();
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.creteTemplatesService();
        Call<List<Template>> call = templatesService.fetchTemplate();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
                hideProgressbar();

            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {

            }
        });
    }

    private void setupTemplatesRv() {
        templatesProgressbar = findViewById( R.id.templates_progressbar);
        templatesRv = findViewById(R.id.templates_rv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
                fetchTemplate();
            }

            @Override
            public void onEdit(Template template) {
                editTemplate(template);
            }
        });
        templatesRv.setAdapter(templatesAdapter);
    }

    private void deleteTemplate(String id) {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.creteTemplatesService();
        Call<Void> call = templatesService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    private void editTemplate(Template template) {
        Intent intent = new Intent(this,AddEditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, template);
        startActivity(intent);
    }
}