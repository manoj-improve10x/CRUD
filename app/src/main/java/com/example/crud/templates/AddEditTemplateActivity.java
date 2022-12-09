package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {

    private EditText addTemplateTxt;
    private Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        Log.i("AddEditTemplateActivity", "onCreate called");
        addTemplateTxt = findViewById(R.id.add_template_txt);
        if (getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            template = (Template)getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            getSupportActionBar().setTitle("Edit Template");
        }else {
            getSupportActionBar().setTitle("Add Template");
        }
    }

    private void showData() {
        addTemplateTxt.setText(template.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.check_movie) {
            String message = addTemplateTxt.getText().toString();
            if (this.template == null) {
                setAddTemplate(message);
            } else {
                setEditTemplate(this.template.id, message);
            }
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setAddTemplate(String message) {
        template = new Template();
        template.text = message;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.creteTemplatesService();
        Call<Template> call = templatesService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {

            }
        });
    }

     private void setEditTemplate(String id, String message) {
        template = new Template();
        template.text = message;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService =templatesApi.creteTemplatesService();
        Call<Void> call = templatesService.editTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("AddEditTemplateActivity", "successfully called editTemplate");
                finish();
                Toast.makeText(AddEditTemplateActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}