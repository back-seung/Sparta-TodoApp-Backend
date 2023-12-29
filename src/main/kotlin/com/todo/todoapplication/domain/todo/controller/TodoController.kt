package com.todo.todoapplication.domain.todo.controller

import com.todo.todoapplication.domain.todo.dto.request.TodoCreateRequest
import com.todo.todoapplication.domain.todo.dto.request.TodoUpdateRequest
import com.todo.todoapplication.domain.todo.dto.response.TodoResponse
import com.todo.todoapplication.domain.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/todos")
class TodoController(private val todoService: TodoService) {

    // C
    @PostMapping
    fun createTodo(@RequestBody request: TodoCreateRequest): ResponseEntity<Unit> {
        val id = todoService.createTodo(request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/todos/%d", id))).build()
    }

    // R
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        val response = todoService.getTodo(todoId)
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        val response = todoService.getTodoList()
        return ResponseEntity.ok(response)
    }

    // U
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody request: TodoUpdateRequest
    ): ResponseEntity<Unit> {
        todoService.updateTodo(todoId, request)
        return ResponseEntity.noContent().build()
    }

    // D
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.noContent().build()
    }
}