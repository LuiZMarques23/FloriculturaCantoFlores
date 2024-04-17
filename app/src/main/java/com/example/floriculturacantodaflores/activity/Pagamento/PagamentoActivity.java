package com.example.floriculturacantodaflores.activity.Pagamento;

import android.graphics.Color;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.floriculturacantodaflores.R;
import com.example.floriculturacantodaflores.databinding.ActivityPagamentoBinding;
import com.example.floriculturacantodaflores.interfaceMercadoPago.ComunicacaoServidorMP;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PagamentoActivity extends AppCompatActivity {

    ActivityPagamentoBinding binding;
    private final String PUBLIC_KEY = "TEST-5376cba5-e853-448d-923a-25f064ebe3a3";
    private final String ACCESS_TOKEN = "TEST-8904395470066170-041711-e3af42add2abfbb191b9edb2a468e275-363385476";


    private String nome;
    private String preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        corStatusBar();

        nome = getIntent().getExtras().getString("nome");
        preco = getIntent().getExtras().getString("preco");

        binding.btFazerPagamento.setOnClickListener(v -> {
            String bairro = binding.edBairro.getText().toString();
            String rua_numero = binding.edRuaNumero.getText().toString();
            String cidade_estado = binding.edCidadeEstado.getText().toString();
            String celular = binding.edCelular.getText().toString();

            if (bairro.isEmpty() || rua_numero.isEmpty() || cidade_estado.isEmpty() || celular.isEmpty()){
                Snackbar snackbar = Snackbar.make(v,"Preencha todos os campos!",Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.RED);
                snackbar.setTextColor(Color.WHITE);
                snackbar.show();
            }else {
                criarJsonObject();

            }
        });


    }

    private void criarJsonObject(){
        JsonObject dados = new JsonObject();

        // Primeiro item
        JsonArray item_lista = new JsonArray();
        JsonObject item;

        // Segunto item
        JsonObject email = new JsonObject();

        item = new JsonObject();
        item.addProperty("title",nome);
        item.addProperty("quantity",1);
        item.addProperty("currency_id","BRL");
        item.addProperty("unit_price",preco);
        item_lista.add(item);

        dados.add("items",item_lista);



        String emailUsuario = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email.addProperty("email",emailUsuario);
        dados.add("payer",email);

        Log.d("j",dados.toString());

        criarPrefereciaPagamento(dados);

    }

    private void criarPrefereciaPagamento(JsonObject dados){
        String sit = "https://api.mercadopago.com";
        String url = "/v1/payments?access_token" + ACCESS_TOKEN;

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sit)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        ComunicacaoServidorMP conexao_pagamento = retrofit.create(ComunicacaoServidorMP.class);
        Call<JsonObject> request = conexao_pagamento.enviarPagamento(url,dados);
        request.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                String preferenceID = response.body().get("id").getAsString();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    private void corStatusBar(){
        getWindow().setStatusBarColor(Color.parseColor("#37F818"));
    }

}