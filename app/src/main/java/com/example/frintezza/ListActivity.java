package com.example.frintezza;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private WebSocketClient webSocketClient;
    String token=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = findViewById(R.id.rvWords);
        recyclerView.setLayoutManager(new LinearLayoutManager( this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new RouterAdapter(getTestData()));
        webSocketClient = new WebSocketClient(ListActivity.this,token,"getdata");
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_router){
            Intent intent = new Intent(this, AddRouterActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void onLongClick(Routers router) {
        Toast.makeText(this, router.getName()+"   "+router.getId(), Toast.LENGTH_SHORT).show();
    }

    private List<Routers> getTestData(){
        List<Routers> data = new ArrayList<>();
        data.add(
                new Routers(1,"test1122","10.0.0.1",true)
        );
        data.add(
                new Routers(2,"test2233","10.0.0.2",false)
        );
        return data;
    }
}
