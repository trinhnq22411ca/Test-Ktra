package com.example.testktra;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.ProductAdapter;
import com.example.connectors.ProductConnector;
import com.example.models.Product;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    ListView lvProduct;
    TextView txtProductListCategoryID, txtProductListCategoryName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_add_product)
        {
            Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        lvProduct.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = (Product) parent.getItemAtPosition(position);

            Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
            intent.putExtra("product", selectedProduct);
            startActivity(intent);
        });
    }

    private void addViews() {
        String categoryName = getIntent().getStringExtra("CategoryName");
        int categoryId = getIntent().getIntExtra("CategoryID", -1);

        txtProductListCategoryID = findViewById(R.id.txtProductListCategoryID);
        txtProductListCategoryName = findViewById(R.id.txtProductListCategoryName);
        txtProductListCategoryID.setText("Product List Category ID: " + categoryId);
        txtProductListCategoryName.setText("Product List Category Name:\n" + categoryName);

        lvProduct = findViewById(R.id.lvProduct);

        if (categoryId != -1) {
            ProductConnector connector = new ProductConnector(this);
            List<Product> products = connector.getProductsByCategory(categoryId);

            ProductAdapter adapter = new ProductAdapter(this, R.layout.item_product);
            adapter.addAll(products);
            lvProduct.setAdapter(adapter);
        }
    }
}