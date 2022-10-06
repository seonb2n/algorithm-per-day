import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RetrofitService {

    @GET("/get")
    Call<Object> get();

    @GET("/get/{id}")
    Call<Object> getWithPath(@Path("id") String id);

    @PUT("/put")
    Call<Object> put(@Query("value") String value);

    @POST("/post")
    Call<Object> post(@Body String str);
}
