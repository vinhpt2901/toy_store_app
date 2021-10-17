package com.toy_store_app;

import static com.jdbc.RoomConnection.getInstance;
import static com.util.Helper.formatNumber;
import static com.util.Helper.loadLocale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dao.BrandDao;
import com.dao.ImageDao;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jdbc.RoomConnection;
import com.model.Brand;
import com.model.Card;
import com.model.Image;
import com.model.Product;
import com.toast.CustomToast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    private ImageView imageMainProduct;
    private ImageButton imageButtonFirst;
    private ImageButton imageButtonSecond;
    private ImageButton imageButtonThird;

    private TextView productName;
    private TextView productPrice;
    private TextView productDescription;

    private MaterialTextView colorProduct;
    private MaterialTextView brandProduct;
    private MaterialTextView quantityProduct;
    private MaterialTextView mauTemplate;
    private MaterialButton btnAddToCart;
    private MaterialButton btnBoyNow;

    private RoomConnection roomConnection;
    private Brand brand;
    private BrandDao brandDao;
    private ImageDao imageDao;
    private List<Image> listImage;
    private String imageMain;
    private Product product;
    private List<Card> carts;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(getBaseContext(), "Language", "My_Lang");
        setContentView(R.layout.activity_detail_product);
        getInstanceView();
        roomConnection = getInstance(getApplicationContext());
        brandDao = roomConnection.brandDao();
        imageDao = roomConnection.imageDao();
        getIncomingIntent();
        EventAddToCart();
    }

    private void getIncomingIntent() {
        product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            setProduct(product);
        }
    }

    private void EventAddToCart() {
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBoyNow = findViewById(R.id.btnBuyNow);
        carts = loadCart();
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carts = addCart(carts, product);
                if (!carts.isEmpty()) {
                    CustomToast.makeText(getApplicationContext(), "Add to cart success!", Toast.LENGTH_SHORT).show();
                }
                saveCart(carts);

            }
        });
        btnBoyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent to cart
                Intent intent = new Intent(DetailProductActivity.this, CartActivity.class);
                carts = addCart(carts, product);
                saveCart(carts);
                startActivity(intent);
            }
        });
    }

    private void saveCart(List<Card> cardList) {
        SharedPreferences preferences = getSharedPreferences("Carts", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cardList);
        editor.putString("listCart", json);
        editor.commit();
    }

    private List<Card> loadCart() {
        SharedPreferences preferences = getSharedPreferences("Carts", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("listCart", null);
        Type type = new TypeToken<ArrayList<Card>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private List<Card> addCart(List<Card> cards, Product p) {
        boolean exist = false;
        if (cards != null) {
            for (int i = 0; i < cards.size(); i++) {
                Card c = cards.get(i);
                if (c.getProductId() == p.getId()) {
                    int quantity = c.getQuantity();
                    int productQuantity = c.getProductQuantity();
                    if (quantity < productQuantity) {
                        cards.get(i).setQuantity(c.getQuantity() + 1);
                    } else {
                        Toast.makeText(getApplicationContext(), "Sản phẩm đã hết hàng",
                                Toast.LENGTH_SHORT).show();
                    }
                    exist = true;
                }
            }
            if (!exist) {
                Card cart = new Card(p.getId(), p.getProductName(), imageMain, p.getSellPrice(),
                        p.getOriginPrice(), p.getColor(), p.getQuantity(),
                        1, 1 * p.getSellPrice());
                cards.add(cart);
            }
        } else {
            cards = new ArrayList<>();
            Card cart = new Card(p.getId(), p.getProductName(), imageMain, p.getSellPrice(),
                    p.getOriginPrice(), p.getColor(), p.getQuantity(),
                    1, 1 * p.getSellPrice());
            cards.add(cart);
        }
        return cards;
    }


    private void setProduct(Product product) {
        brand = brandDao.getOneByProduct(product.getId());
        productName.setText(product.getProductName());
        productPrice.setText(formatNumber((int) product.getSellPrice()));
        productDescription.setText(product.getDescription());
        if (product.getColor() == null) {
            mauTemplate.setText("");
            colorProduct.setText("");
        } else {
            colorProduct.setText(product.getColor());
        }
        brandProduct.setText(brand.getBrandName());
        quantityProduct.setText(product.getQuantity() + "");

        // set Image
        listImage = imageDao.getImageByProductCover(product.getId());
        imageMain = imageDao.getImageByProductCoverTrue(product.getId());

        // set for main
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open(imageMain);
            Drawable d = Drawable.createFromStream(ims, null);
            imageMainProduct.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }

        // set for button
        getImageForImageButton(listImage.get(0).getImageLink(), imageButtonFirst);
        getImageForImageButton(listImage.get(1).getImageLink(), imageButtonSecond);
        getImageForImageButton(listImage.get(2).getImageLink(), imageButtonThird);
    }

    private void getInstanceView() {
        imageMainProduct = findViewById(R.id.imageproductDetailMain);
        imageButtonFirst = findViewById(R.id.imageProductFirst);
        imageButtonSecond = findViewById(R.id.imageProductSecond);
        imageButtonThird = findViewById(R.id.imageProductThird);
        productName = findViewById(R.id.detailProductName);
        productPrice = findViewById(R.id.productDetailPrice);
        productDescription = findViewById(R.id.productDetailDescription);
        colorProduct = findViewById(R.id.colorProduct);
        brandProduct = findViewById(R.id.brandProduct);
        mauTemplate = findViewById(R.id.mauTemplate);
        quantityProduct = findViewById(R.id.quantityProduct);
    }

    public void selectFirst(View view) {
        getImage(listImage.get(0).getImageLink(), imageMainProduct);
    }

    public void selectSecond(View view) {
        getImage(listImage.get(1).getImageLink(), imageMainProduct);
    }

    public void selectThird(View view) {
        getImage(listImage.get(2).getImageLink(), imageMainProduct);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocale(getBaseContext(), "Language", "My_Lang");
        getIncomingIntent();
    }

    private void getImage(String image, ImageView imageButton) {
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open(image);
            Drawable d = Drawable.createFromStream(ims, null);
            imageButton.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
    }

    private void getImageForImageButton(String image, ImageButton imageButton) {
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open(image);
            Drawable d = Drawable.createFromStream(ims, null);
            imageButton.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
    }
}