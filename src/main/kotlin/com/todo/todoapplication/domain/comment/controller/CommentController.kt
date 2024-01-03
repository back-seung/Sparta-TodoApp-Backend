package com.todo.todoapplication.domain.comment.controller

import com.todo.todoapplication.domain.comment.dto.request.CommentCreateRequest
import com.todo.todoapplication.domain.comment.dto.request.UpdateCommentRequest
import com.todo.todoapplication.domain.comment.dto.response.CommentResponse
import com.todo.todoapplication.domain.comment.service.CommentService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1")
class CommentController(private val commentService: CommentService) {

    // C
    @PostMapping("/todos/{todoId}/comments")
    fun createComment(
        @PathVariable todoId: Long,
        @Valid @RequestBody request: CommentCreateRequest
    ): ResponseEntity<Unit> {
        val id = commentService.createComment(todoId, request)
        return ResponseEntity.created(URI.create(String.format("/api/v1/todos/%d/comments/%d", todoId, id))).build()
    }

    // R
    @GetMapping("todos/{todoId}/comments")
    fun findAll(@PathVariable todoId: Long): ResponseEntity<List<CommentResponse>> {
        val comments = commentService.findAll(todoId)
        return ResponseEntity.ok(comments)
    }

    // U
    @PutMapping("/comments/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @Valid @RequestBody request: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        val comment = commentService.updateComment(commentId, request)
        return ResponseEntity.ok(comment)
    }

    // D
    @DeleteMapping("/comments/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long
    ): ResponseEntity<Unit> {
        commentService.deleteComment(commentId)

        return ResponseEntity.noContent().build()
    }
}