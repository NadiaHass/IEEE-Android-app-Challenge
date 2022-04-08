package com.hamza.ieeechallenge.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hamza.ieeechallenge.databinding.ActivityFoodDetailBinding;
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart;
import com.hamza.ieeechallenge.ui.cart.CartViewModel;

public class FoodDetailActivity extends AppCompatActivity {
    private ActivityFoodDetailBinding binding;
    private CartViewModel cartViewModel;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        getDataFromIntent();

        binding.btnAddToCart.setOnClickListener(view -> {
            assert user != null;
            insertDataToCartDatabase(user.getUid());
        });

        binding.btnMinus.setOnClickListener(view -> {
            decreaseQuantity();
        });
        binding.btnPlus.setOnClickListener(view -> {
            increaseQuantity();
        });
    }



    private void getDataFromIntent() {
        binding.tvTitle.setText(getIntent().getStringExtra("title"));
        binding.tvRestaurantName.setText(getIntent().getStringExtra("restaurantName"));
        binding.tvPrice.setText(getIntent().getStringExtra("price"));
        binding.tvRating.setText(String.valueOf(getIntent().getDoubleExtra("rating",0)));
        binding.tvDescription.setText(getIntent().getStringExtra("description"));
        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .into(binding.ivFood);
        binding.ratingBar.setRating((float) getIntent().getDoubleExtra("rating",0));
    }

    private void insertDataToCartDatabase(String userId) {
        String title = binding.tvTitle.getText().toString();
        String restaurantName = binding.tvRestaurantName.getText().toString();
        double price = Double.parseDouble(binding.tvPrice.getText().toString().replace("$","").replace(" " , ""));
        int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        String image = getIntent().getStringExtra("image");

        Cart cart = new Cart(0 , userId,"Progress", title , image , restaurantName  , quantity , price);
        cartViewModel.addToCart(cart);
        Toast.makeText(this, "data inserted to db", Toast.LENGTH_SHORT).show();
    }

    private void decreaseQuantity() {
        int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        if (quantity>=1){
            quantity--;
            binding.tvQuantity.setText(String.valueOf(quantity));
        }
    }

    private void increaseQuantity() {
        int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        quantity++;
        binding.tvQuantity.setText(String.valueOf(quantity));
    }

}