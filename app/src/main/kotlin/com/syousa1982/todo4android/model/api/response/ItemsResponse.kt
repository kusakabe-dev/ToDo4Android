package com.syousa1982.todo4android.model.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * 一覧取得用のレスポンスクラス
 *
 * @param items 表示アイテム(複数)
 */
@JsonSerializable
data class ItemsResponse<D>(
        @Json(name = "Items") val items: D
)

/**
 * 単体取得用のレスポンスクラス
 * <p>
 *     データを1件受け取るときはWebAPI側の仕様でキー名が変わるため、
 *     単体取得用のレスポンスクラスを定義
 * </p>
 *
 * @param item 表示アイテム
 */
@JsonSerializable
data class ItemResponse<D>(
        @Json(name = "Item") val item: D
)