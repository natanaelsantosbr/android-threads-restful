package br.natanael.android.androidthreads.api;

import br.natanael.android.androidthreads.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICEPService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCep(@Path("cep")String cep);
}
