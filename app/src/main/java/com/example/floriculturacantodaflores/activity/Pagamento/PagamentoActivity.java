package com.example.floriculturacantodaflores.activity.Pagamento;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.floriculturacantodaflores.R;
import com.example.floriculturacantodaflores.databinding.ActivityPagamentoBinding;

public class PagamentoActivity extends AppCompatActivity {

    ActivityPagamentoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        corStatusBar();


    }

    private void corStatusBar(){
        getWindow().setStatusBarColor(Color.parseColor("#37F818"));
    }

}