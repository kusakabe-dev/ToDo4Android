package com.syousa1982.todo4android.domain.model

/**
 * タスクModel
 *
 * @param id
 * @param name
 * @param status
 */
data class Task(
        val id: Int,
        val name: String,
        val status: Status
) {
    enum class Status(val value: String) {
        TODO("todo"),
        DONE("done")
    }
}