package com.syousa1982.todo4android.util.extention


/**
 * クラス名を取得
 *
 * @return String
 */
fun Any.className(): String = javaClass.simpleName

/**
 * クラス名を取得
 *
 * @param any Any
 * @return String
 */
fun Any.className(any: Any?): String {
    if (any == null) {
        return "Null"
    }
    return any.javaClass.simpleName
}