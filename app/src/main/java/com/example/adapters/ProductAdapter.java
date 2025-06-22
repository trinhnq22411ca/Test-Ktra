package com.example.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.models.Product;
import com.example.testktra.R;
import java.text.NumberFormat;
import java.util.Locale;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;

    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(resource, null);

        TextView txtProductID = item.findViewById(R.id.txtProductID);
        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtUnitPrice = item.findViewById(R.id.txtUnitPrice);
        ImageView imgProduct = item.findViewById(R.id.imgProduct);

        Product p = getItem(position);
        if (p != null) {
            txtProductID.setText(p.getProductID()+"");
            txtProductName.setText(p.getProductName());
            NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
            txtUnitPrice.setText(formatter.format(p.getUnitPrice()) + " VNĐ");
            Glide.with(context).load(p.getImageLink()).into(imgProduct);
        }

        return item;
    }
}
