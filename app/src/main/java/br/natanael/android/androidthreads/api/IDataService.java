package br.natanael.android.androidthreads.api;

import java.util.List;

import br.natanael.android.androidthreads.model.Foto;
import br.natanael.android.androidthreads.model.Postagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IDataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();

    /*@POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);*/

    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(@Field("userId") String userId,
                                  @Field("title") String title,
                                  @Field("body") String body);
}
