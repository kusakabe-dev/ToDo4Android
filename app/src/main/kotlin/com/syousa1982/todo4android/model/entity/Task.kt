package com.syousa1982.todo4android.model.entity

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * タスクModel
 *
 * @param id     ID
 * @param name   タスク名
 * @param isDone 完了フラグ
 */
@JsonSerializable
data class Task(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "is_done") val isDone: Boolean
)