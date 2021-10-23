package es.iesnervion.mcasado.ejemplilloretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGetPerson;
    TextView nameTxV;
    MainVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = new ViewModelProvider(this).get(MainVM.class);
        btnGetPerson = findViewById(R.id.btnGetPerson);
        nameTxV = findViewById(R.id.name);
        btnGetPerson.setOnClickListener(this);

        vm.getNameLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nameTxV.setText(s);
            }
        });


    }

    @Override
    public void onClick(View view) {
        Random rand = new Random();
        int randomNum = rand.nextInt(50) + 1;
        vm.updateIdLiveData(randomNum);
    }
}