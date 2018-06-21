package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.SOAnswersResponse;


public interface SOService {

    @GET("stuff/getall")
    Call<SOAnswersResponse> getAnswers();



}
