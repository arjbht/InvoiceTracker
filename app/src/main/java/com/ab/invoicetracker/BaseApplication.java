package com.ab.invoicetracker;

import android.app.Application;

import com.ab.invoicetracker.database.realm.RealmString;
import com.ab.invoicetracker.database.realm.RealmStringListTypeAdapter;
import com.ab.invoicetracker.network.HeaderInterceptor;
import com.ab.invoicetracker.network.IRetrofit;
import com.ab.invoicetracker.network.RequestHeader;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.onesignal.OneSignal;

import java.util.concurrent.TimeUnit;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseApplication extends Application {

    private static volatile IRetrofit IRETROFIT = null;
    private static volatile Realm REALM = null;

    public static synchronized Realm getRealm() {
        if (REALM != null) {
            return REALM;
        } else {
            RealmConfiguration realmConfig =
                    new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(realmConfig);
            REALM = Realm.getDefaultInstance();
            return REALM;
        }
    }

    public static synchronized IRetrofit getRetrofitAPI(boolean autohrised) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        gsonBuilder.registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
        }.getType(), RealmStringListTypeAdapter.INSTANCE);

        Gson gson = gsonBuilder.create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS);


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);
        if (autohrised) httpClientBuilder.addInterceptor(new HeaderInterceptor(getHeader()));

        IRETROFIT = new Retrofit.Builder().baseUrl(IRetrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build()
                .create(IRetrofit.class);


        return IRETROFIT;
    }

    public static IRetrofit getImageUrlApi() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        gsonBuilder.registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
        }.getType(), RealmStringListTypeAdapter.INSTANCE);

        Gson gson = gsonBuilder.create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        IRetrofit retrofit = new Retrofit.Builder().baseUrl(IRetrofit.IMAGE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build()
                .create(IRetrofit.class);

        return retrofit;
    }

    private static RequestHeader getHeader() {
        RequestHeader header = null;
//        RealmResults<LoginData> query =
//                BaseApplication.getRealm().where(LoginData.class).findAll();
//        if (query != null && query.size() > 0) {
//            header = new RequestHeader();
//            header.setHeaderName("Authorization");
//            assert query.get(0) != null;
//            assert query.get(0) != null;
//            header.setHeaderValue(query.get(0).getTokenType() + " " + query.get(0).getAccessToken());
//        }
        return header;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // initialise the realm database
        try {
            Realm.init(this);
            RealmConfiguration realmConfiguration =
                    new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // configuring the font for calligraphy
        try {
            ViewPump.init(ViewPump.builder()
                    .addInterceptor(new CalligraphyInterceptor(
                            new CalligraphyConfig.Builder()
                                    .setDefaultFontPath("fonts/font.ttf")
                                    .setFontAttrId(R.attr.fontPath)
                                    .build()))
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
