package com.syousa1982.todo4android.manager

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.model.ApplicationJsonAdapterFactory
import com.syousa1982.todo4android.model.api.MockTaskApi
import com.syousa1982.todo4android.model.api.TaskApi
import com.syousa1982.todo4android.model.repository.ITaskRepository
import com.syousa1982.todo4android.model.repository.MockTaskRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.*
import java.util.concurrent.TimeUnit

class RepositoryManager(private val context: Context) : IRepositoryManager {

    /**
     * Moshiライブラリ Jsonコンバートインスタンス
     */
    private val converter by lazy {
        Moshi.Builder()
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
    }


    /**
     * OkHttpライブラリ 通信クライアントインスタンス
     */
    private val httpClient by lazy {
        val builder = OkHttpClient.Builder()
        // タイムアウトを設定
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)
        // デバッグビルドかつOkHttp有効時のみ通信ログを出力
        if (BuildConfig.DEBUG && BuildConfig.OK_HTTP_LOG_ENABLED) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(interceptor)
        }
        return@lazy builder.build()
    }

    /**
     * ネットワークの挙動
     * <p>
     * モックのみ使用
     * </p>
     */
    private val networkBehavior by lazy {
        NetworkBehavior.create()
    }

    // region リポジトリ
    override val task: ITaskRepository by lazy {
        val mockRetrofit = createRetrofit()
        val delegate = mockRetrofit.create(TaskApi::class.java)
        return@lazy MockTaskRepository(MockTaskApi(delegate))
    }
    // endregion

    // Private
    /**
     * MockRetrofitを生成
     *
     * @return MockRetrofit
     */
    private fun createRetrofit(): MockRetrofit {
        // Retrofitライブラリを使用してAPIを作成
        val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(converter))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(httpClient)
                .build()
        // Retrofit(Mock)ライブラリを使用してAPIを作成
        return MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build()
    }
    // endregion
}