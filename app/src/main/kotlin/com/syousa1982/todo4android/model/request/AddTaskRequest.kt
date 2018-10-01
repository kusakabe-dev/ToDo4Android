package com.syousa1982.todo4android.model.request

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * タスク追加リクエストクラス
 */
@JsonSerializable
data class AddTaskRequest(
        @Json(name = "name") val name: String
)