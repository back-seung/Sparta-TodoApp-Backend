package com.todo.todoapplication.domain.todo.dto

import com.todo.todoapplication.domain.todo.dto.request.TodoCreateRequest
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.web.bind.MethodArgumentNotValidException

class TodoCreateRequestTest : BehaviorSpec(
    {
        given("TodoCreationRequest가 생성되었을 때") {
            `when`("제목이 입력되지 않으면") {
                then("InvalidTodoRequestException이 발생한다") {
                    shouldThrowExactly<MethodArgumentNotValidException> {
                        TodoCreateRequest(
                            title = "",
                            description = "내용",
                            name = "홍길동"
                        )
                    }
                }
            }
            `when`("작성자가 입력되지 않으면") {
                then("InvalidTodoRequestException이 발생한다") {
                    shouldThrowExactly<MethodArgumentNotValidException> {
                        TodoCreateRequest(
                            title = "제목",
                            description = "내용",
                            name = ""
                        )
                    }
                }
            }
        }
    })
