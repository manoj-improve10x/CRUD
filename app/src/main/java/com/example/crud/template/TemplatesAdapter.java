package com.example.crud.template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.TemplateItemBinding;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {

    private List<Template> templates;
    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    void setData(List<Template> templates) {
        this.templates = templates;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TemplateItemBinding binding = TemplateItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(binding);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.binding.setTemplate(template);
        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(template.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(template);
        });
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}
