package vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllExhibitJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllMaterialTypeJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllResultJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllStuffJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.AllTimeJsonResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ExhibitModel;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.ImageByIDResponse;
import vn.com.lacviet.lacviethpsmuseummanagementapp.WebAPI.Model.StuffModel;


public interface ApiService {

    @GET("stuff/getall")
    Call<AllStuffJsonResponse> getAllStuffs();

    @GET("Stuff/GetById")
    Call<StuffModel> getStuffById(@Query("id") String id);

    @GET("time/getall")
    Call<AllTimeJsonResponse> getAllTimes();

    @GET("materialtype/getall")
    Call<AllMaterialTypeJsonResponse> getAllMaterialType();

    @GET("Exhibit/SearchPaging")
    Call<AllExhibitJsonResponse> getExhibitByPage(@Query("pageindex") int index,
                                                  @Query("pagesize") int size);

    @GET("Exhibit/GetById")
    Call<ExhibitModel> getExhibitById(@Query("id") int id);

    @GET("Exhibit/GetImage")
    Call<String> getExhibitImageById(@Query("id") int id,
                                     @Query("thumbnail") boolean thumbnail);

    @GET("Exhibit/GetImages")
    Call<ImageByIDResponse> getAllExhibitImageById(@Query("id") int id,
                                                   @Query("thumbnail") boolean thumbnail);

    //Search
    @GET("Exhibit/SearchPaging")
    Call<AllResultJsonResponse> getResultByStuff(@Query("pageindex") int index,
                                                 @Query("pagesize") int size,
                                                 @Query("stuff") String stuff
    );

    @GET("Exhibit/SearchPaging")
    Call<AllResultJsonResponse> getResultSearchNormal(@Query("pageindex") int index,
                                                      @Query("pagesize") int size,
                                                      @Query("category") String category,
                                                      @Query("query") String query
    );

    @GET("Exhibit/SearchPaging")
    Call<AllResultJsonResponse> getResultSearchAdvance(@Query("pageindex") int index,
                                                       @Query("pagesize") int size,
                                                       @Query("category") String category,
                                                       @Query("query") String query,
                                                       @Query("stuff") String stuff,
                                                       @Query("materialtype") String materialtype,
                                                       @Query("time") String time);
    @GET("Page/GetById")
    Call<String> getPage(@Query("id") String id);


}
