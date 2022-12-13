package com.example.crud.template;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplateActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // id change
        if (item.getItemId() == R.id.check_movie) {
            String message = addTemplateTxt.getText().toString();
                setAddTemplate(message);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
// change methode name
    private void setAddTemplate(String message) {
        template = new Template();
        template.text = message;
        Call<Template> call = service.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("successfully added  the template");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                showToast("Failed to load template ");
            }
        });
    }
}
