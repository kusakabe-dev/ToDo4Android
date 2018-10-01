package com.syousa1982.todo4android.model.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * 一覧取得用のレスポンス
 */
@JsonSerializable
data class ItemsResponse<D>(
        @Json(name = "Items") val items: D
)