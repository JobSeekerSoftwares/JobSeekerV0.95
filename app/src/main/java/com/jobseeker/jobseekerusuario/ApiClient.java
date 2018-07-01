package com.jobseeker.jobseekerusuario;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jobseeker.jobseekerusuario.Model.Empregador;
import com.jobseeker.jobseekerusuario.Model.Trabalhador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public class ApiClient {
    private static JobInterface JobService;

    public static JobInterface getJobClient() {
        if (JobService == null) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl("http://jonatas.pythonanywhere.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            JobService = restAdapter.create(JobInterface.class);
        }

        return JobService;
    }

    public interface JobInterface {
        @GET("snipp/")
        Call<List<Empregador>> getAllJobsDisponiveis();

        @GET("procurando/")
        Call<List<Empregador>> getJobsDisponiveis2(@Query("curriculo") String spec);

        @GET("pessoa/")
        Call<List<Trabalhador>> getPessoaByGmail(@Query("idGmail") String idGmail);

        @PUT("singup/trabalhador/")
        Call<Trabalhador> changeDataPessoal(@Body Trabalhador user);

        @PUT("singup/trabalhador/")
        Call<Trabalhador> changeDataProfissional(@Body Trabalhador user);

        @PUT("singup/empregador/")
        Call<Empregador> changeDataTrabalho(@Body Empregador user, @Query("oldCargo") String oldCargo);

        @POST("singup/trabalhador/")
        Call<Trabalhador> uploadDadosTrabalhador(@Body Trabalhador user);

        @POST("singup/empregador/")
        Call<Empregador> uploadDadosEmpregador(@Body Empregador user);

        @GET("meusEmpregos/")
        Call<List<Empregador>> getMeusEmpregos(@Query("idGmail") String idGmail);
    }
}