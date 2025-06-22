package com.example.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.models.Category;
import com.example.testktra.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    Activity context;
    int resource;
    public CategoryAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(resource, null);

        TextView txtCategoryID = item.findViewById(R.id.txtCategoryID);
        TextView txtCategoryName = item.findViewById(R.id.txtCategoryName);

        Category category = getItem(position);
        if (category != null) {
            txtCategoryID.setText(category.getCategoryID() + "");
            txtCategoryName.setText(category.getCategoryName());
        }

        return item;
    }
}
