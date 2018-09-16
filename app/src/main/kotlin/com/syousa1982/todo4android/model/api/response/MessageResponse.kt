package com.syousa1982.todo4android.model.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * メッセージ 共通レスポンス
 * <p>
 * APIの簡易的な応答に使用するためのクラス
 * </p>
 *
 * @param success
 * @param message
 *
 */
@JsonSerializable
data class MessageResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String
)