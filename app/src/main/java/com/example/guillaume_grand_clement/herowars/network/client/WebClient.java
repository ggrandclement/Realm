package com.example.guillaume_grand_clement.herowars.network.client;

import android.content.Context;

import com.example.guillaume_grand_clement.herowars.BuildConfig;
import com.example.guillaume_grand_clement.herowars.network.IWebService;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class WebClient {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    public static WebClient sInstance;
    private IWebService mService;
    private Context mContext;

    //endregion

    //region Override Methods **********************************************************************

    //endregion

    //region Public Methods ************************************************************************

    public static IWebService getService(final Context context) {
        return getClient(context).mService;
    }
    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    private static WebClient getClient(final Context context) {
        if (sInstance == null) {
            sInstance = new WebClient(context);
        }
        return sInstance;
    }

    private WebClient(Context context) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        final Gson gson = getGson();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(builder.build())
                .build();
        mService = retrofit.create(IWebService.class);
        mContext = context;
    }

    private Gson getGson() {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
