package com.example.crud.template;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {

    private RecyclerView templatesRv;
    private ArrayList<Template> templates = new ArrayList<>();
    private ProgressBar progressBar;
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        log("onCreate");
        //Todo: first create title in the onCreate
        getSupportActionBar().setTitle("Templates");
        findIds();
        setupTemplatesAdapter();
        setupTemplatesRv();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.templates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchTemplates();
    }

    private void fetchTemplates() {
        showProgressBar();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideProgressBar();
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load data");
            }
        });
    }

    private void findIds() {
        progressBar = findViewById(R.id.templates_progressbar);
        templatesRv = findViewById(R.id.templates_rv);
    }

    private void setupTemplatesAdapter() {
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
                fetchTemplates();
            }

            @Override
            public void onEdit(Template template) {
                editTemplate(template);
            }
        });
    }

    private void setupTemplatesRv() {
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesRv.setAdapter(templatesAdapter);
    }

    private void deleteTemplate(String id) {
        showProgressBar();
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideProgressBar();
                showToast("successfully deleted the template");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideProgressBar();
                showToast("failed to delete template");
            }
        });
    }

    private void editTemplate(Template template) {
        Intent intent = new Intent(this, EditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, template);
        startActivity(intent);
    }
}