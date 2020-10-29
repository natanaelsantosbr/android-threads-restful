package br.natanael.android.androidthreads.api;

import java.util.List;

import br.natanael.android.androidthreads.model.Foto;
import br.natanael.android.androidthreads.model.Postagem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IDataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();
}
