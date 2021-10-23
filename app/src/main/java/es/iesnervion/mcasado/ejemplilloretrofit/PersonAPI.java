package es.iesnervion.mcasado.ejemplilloretrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PersonAPI {

    @GET("person/{id}")
    Call<Person> getPerson (@Path("id") int id);

}
