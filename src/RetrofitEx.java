import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.Map;

public class RetrofitEx {

    public static void main(String[] args) {
        try {
            RetrofitClient retrofitClient = RetrofitClient.getInstance();
            RetrofitService retrofitService = retrofitClient.create(RetrofitService.class);

            Object getResponse = retrofitService.get().execute().body();
            Gson gson = new Gson();
            var resultJsonStr = gson.toJson(getResponse);
            Map<String, Object> map = gson.fromJson(resultJsonStr, Map.class);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

