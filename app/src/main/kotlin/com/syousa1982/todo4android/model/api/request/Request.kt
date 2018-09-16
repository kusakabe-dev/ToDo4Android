package com.syousa1982.todo4android.model.api.request

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * リクエスト共通クラス
 *  <p>
 *      任意の型でリクエストを送信するためのクラス
 *  </p>
 *
 */
@JsonSerializable
data class Request<M>(
        @Json(name = "request") val request: M
)