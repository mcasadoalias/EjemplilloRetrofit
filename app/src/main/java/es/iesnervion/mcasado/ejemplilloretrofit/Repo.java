package es.iesnervion.mcasado.ejemplilloretrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repo {

    static final String BASE_URL = "https://615974b0601e6f0017e5a1cd.mockapi.io/";
    private PersonAPI personAPI;

    public Repo() {
        //TODO We should use dependency injection

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

         this.personAPI = retrofit.create(PersonAPI.class);

    }

    public LiveData<String> getPersonName(int id) {

        MutableLiveData<String> nameLiveData = new MutableLiveData<>();
        if (id <0){
            nameLiveData.postValue("Name_Placeholder");
        }
        else {
            personAPI.getPerson(id).enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                    Person p = response.body();
                    nameLiveData.postValue(p.getName());
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {

                }
            });
        }

        return nameLiveData;
    }
}
