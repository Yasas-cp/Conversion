package com.example.conversions;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Level1 extends AppCompatActivity {

    private TextView valueNumber;
    private Button b1, b2, b3, b4;

    public Level1() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1);

        valueNumber = findViewById(R.id.textView10);

        Random random = new Random();
        int value = random.nextInt(100);

        valueNumber.setText(String.format("%d", value));

        b1 = (Button) findViewById(R.id.button6);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.randomnumberapi.com/api/v1.0/randomnumber/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomDecimalGenerator randomDecimalGenerator = retrofit.create(RandomDecimalGenerator.class);
        Call<List<Post>> call = randomDecimalGenerator.getRandom();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    b1.setText(response.code());
                    return;

                }
                List<Post> posts = response.body();
                for(Post post : posts){
                    String content="";
                    content = post.getRandom();
                    b1.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                b1.setText(t.getMessage());
            }
        });

    }
}
