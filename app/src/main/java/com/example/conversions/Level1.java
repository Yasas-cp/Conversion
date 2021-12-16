package com.example.conversions;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Level1 extends AppCompatActivity {

    private int m;
    private int n;
    private int o;
    private int p;
    private final int countPair=0;


    int[] randomArray;
    int pos[] = {m,n,o,p};
    int currentpos = -1;
    Random random = new Random();
    int value = random.nextInt(100);

    public Level1() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1);

        TextView valueNumber = findViewById(R.id.textView10);



        valueNumber.setText(String.format("%d", value));

        GridView gridView = (GridView)findViewById(R.id.GridView);

        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, randomArray(pos));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer values=Integer.parseInt(gridView.getItemAtPosition(position).toString());
                BreakIterator y = null;
                y.setText(String.valueOf(value));
            }

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.randomnumberapi.com/api/v1.0/randomnumber/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RandomDecimalGenerator randomDecimalGenerator = retrofit.create(RandomDecimalGenerator.class);
//        Call<List<Post>> call = randomDecimalGenerator.getRandom();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if(!response.isSuccessful()){
//                    m.setText(response.code());
//                    return;
//
//                }
//                List<Post> posts = response.body();
//                for(Post post : posts){
//                    String content="";
//                    content = post.getRandom();
//                    m.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                m.setText(t.getMessage());
//            }
//        });

    });
    }

    private List<Integer> randomArray(int[] pos) {

        List<Integer> list=new ArrayList<>();
        int x = (value/2);
        if (pos = m){

        }
        if (pos = n){
            list.add(x);
        }
        if (pos = o){
            list.add(x);
        }
        list.add(4);
        return list;
    }

}
