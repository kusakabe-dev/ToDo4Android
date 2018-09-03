package com.syousa1982.todo4android.manager

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.model.ApplicationJsonAdapterFactory
import com.syousa1982.todo4android.model.api.TaskApi
import com.syousa1982.todo4android.model.repository.ITaskRepository
import com.syousa1982.todo4android.model.repository.TaskRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
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

    // region リポジトリ
    override val task: ITaskRepository by lazy {
        val retrofit = createRetrofit()
        return@lazy TaskRepository(retrofit.create(TaskApi::class.java))
    }
    // endregion

    // Private

    /**
     * Retrofitを生成
     *
     * @return Retrofit
     */
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(converter))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(httpClient)
                .build()
    }

    // endregion
}