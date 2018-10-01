package com.syousa1982.todo4android.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Task(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "is_done") val isDone: Boolean
)