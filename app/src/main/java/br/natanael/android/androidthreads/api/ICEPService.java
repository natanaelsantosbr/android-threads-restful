package br.natanael.android.androidthreads.api;

import br.natanael.android.androidthreads.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ICEPService {

    @GET("72304052/json/")
    Call<CEP> recuperarCep();
}
