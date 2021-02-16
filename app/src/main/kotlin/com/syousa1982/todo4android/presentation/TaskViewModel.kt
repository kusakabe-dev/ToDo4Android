package com.syousa1982.todo4android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syousa1982.todo4android.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks = repository.tasks

    fun add(name: String) = viewModelScope.launch {
        repository.add(name)
    }
}