package com.syousa1982.todo4android.model.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ItemsResponse<D>(
        @Json(name = "Items") val items: D
)