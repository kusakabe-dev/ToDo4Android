package com.syousa1982.todo4android.data.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class TaskResponse(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "is_done") val isDone: String
)