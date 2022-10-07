import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final Retrofit retrofit;

    private RetrofitClient() {
        this.retrofit = new Retrofit.Builder().baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static final class RetrofitClientLazyHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return RetrofitClientLazyHolder.INSTANCE;
    }

    public <T> T create(Class<T> service) {
        return this.retrofit.create(service);
    }
}
