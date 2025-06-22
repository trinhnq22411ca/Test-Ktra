package com.example.testktra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.CategoryAdapter;
import com.example.connectors.CategoryConnector;
import com.example.models.Category;

import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    ListView lvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }
    private void addViews() {
        lvCategory=findViewById(R.id.lvCategory);

        CategoryAdapter adapter = new CategoryAdapter(
                this,
                R.layout.item_category
        );
        lvCategory.setAdapter(adapter);

        CategoryConnector connector = new CategoryConnector(this);
        List<Category> categories = connector.getAllCategories();

        adapter.addAll(categories);
    }
    private void addEvents() {
        lvCategory.setOnItemClickListener((parent, view, position, id) -> {
            Category selectedCategory = (Category) parent.getItemAtPosition(position);

            Intent intent = new Intent(this, ProductListActivity.class);
            intent.putExtra("CategoryID", selectedCategory.getCategoryID());
            intent.putExtra("CategoryName", selectedCategory.getCategoryName());
            startActivity(intent);
        });
    }
}