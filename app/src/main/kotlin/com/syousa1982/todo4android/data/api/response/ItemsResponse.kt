package com.syousa1982.todo4android.data.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ItemsResponse<T>(
        @Json(name = "Items") val items: List<T>
)