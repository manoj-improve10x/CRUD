package com.example.crud.templates;

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
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {

    private CrudService service;
    private RecyclerView templatesRv;
    private ArrayList<Template> templates = new ArrayList<>();
    private ProgressBar templatesProgressbar;
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        setupApiService();
        log("onCreate");
        getSupportActionBar().setTitle("Templates");
        setupTemplatesRv();

    }

    private void setupApiService() {
        CrudApi api = new CrudApi();
        service = api.createCrudService();
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
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       log("onResume");
        fetchTemplate();

    }

    private void fetchTemplate() {
        showProgressbar();
        Call<List<Template>> call = service.fetchTemplate();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
                hideProgressbar();
                showToast("Successfully loaded the data");
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                showToast("Failed to load data");
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
        Call<Void> call =service .deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("successfully deleted the template");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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