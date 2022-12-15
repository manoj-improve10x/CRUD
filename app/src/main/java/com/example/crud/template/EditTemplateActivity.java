package com.example.crud.template;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddEditTemplateActivity {

    private Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            template = (Template) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            getSupportActionBar().setTitle("Edit Template");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save) {
            String message = TemplateTxt.getText().toString();
            updateTemplate(this.template.id, message);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        TemplateTxt.setText(template.messageText);
    }

    private void updateTemplate(String id, String message) {
        template = new Template();
        template.messageText = message;

        Call<Void> call = crudService.editTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
                showToast("Successfully updated the template");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update template");
            }
        });
    }
}
