package com.example.testktra;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connectors.ProductConnector;
import com.example.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    TextView txtProductDetailID;
    EditText edtProductDetailName, edtProductDetailPrice, edtProductDetailImageUrl, edtProductDetailCategoryID;
    Button btnSave, btnDelete, btnCancel;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductConnector db = new ProductConnector(ProductDetailActivity.this);

                String name = edtProductDetailName.getText().toString();
                double price = Double.parseDouble(edtProductDetailPrice.getText().toString());
                String imageUrl = edtProductDetailImageUrl.getText().toString();
                int categoryID = Integer.parseInt(edtProductDetailCategoryID.getText().toString());

                String idText = txtProductDetailID.getText().toString();

                if (idText.equals("Auto ID")) {
                    // New
                    Product newProduct = new Product(0, name, price, imageUrl, categoryID);
                    db.addProduct(newProduct);
                    Toast.makeText(ProductDetailActivity.this, getString(R.string.title_save_successfull), Toast.LENGTH_SHORT).show();
                } else {
                    // Fix
                    int id = Integer.parseInt(idText);
                    Product updatedProduct = new Product(id, name, price, imageUrl, categoryID);
                    db.updateProduct(updatedProduct);
                    Toast.makeText(ProductDetailActivity.this, getString(R.string.title_save_successfull), Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ProductDetailActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProductDetailActivity.this)
                        .setTitle("Xác nhận xoá")
                        .setMessage("Bạn có chắc chắn muốn xoá sản phẩm này?")
                        .setPositiveButton("Xoá", (dialog, which) -> {
                            ProductConnector db = new ProductConnector(ProductDetailActivity.this);
                            db.deleteProduct(product.getProductID());

                            Toast.makeText(ProductDetailActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ProductDetailActivity.this, CategoryListActivity.class);
                            startActivity(intent);
                        })
                        .setNegativeButton("Huỷ", null)
                        .show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addViews() {
        txtProductDetailID = findViewById(R.id.txtProductDetailID);
        edtProductDetailName = findViewById(R.id.edtProductDetailName);
        edtProductDetailPrice = findViewById(R.id.edtProductDetailPrice);
        edtProductDetailImageUrl = findViewById(R.id.edtProductDetailImageUrl);
        edtProductDetailCategoryID = findViewById(R.id.edtProductDetailCategoryID);

        product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
        txtProductDetailID.setText(product.getProductID()+"");
        edtProductDetailName.setText(product.getProductName());
        edtProductDetailPrice.setText(product.getUnitPrice()+"");
        edtProductDetailImageUrl.setText(product.getImageLink());
        edtProductDetailCategoryID.setText(product.getCategoryID()+"");
        }

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

    }

}