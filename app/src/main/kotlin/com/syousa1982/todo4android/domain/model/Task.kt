package com.syousa1982.todo4android.domain.model

import java.io.Serializable

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
) : Serializable {
    enum class Status(val value: String) {
        TODO("todo"),
        DONE("done");

        companion object {
            fun from(value: String): Status = Status.values().first { it.value == value }

            fun changeStatus(status: Status): Status = when (status) {
                TODO -> DONE
                DONE -> TODO
            }
        }
    }
}