package com.syousa1982.todo4android.data.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ItemResponse<T>(
        @Json(name = "Item") val item: T
)