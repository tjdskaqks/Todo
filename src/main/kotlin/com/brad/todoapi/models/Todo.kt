package com.brad.todoapi.models

import javax.persistence.*
import javax.validation.constraints.NotBlank
import java.time.LocalDateTime

@Entity
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    @field:NotBlank(message = "title is required")
    var title: String,
    @Column
    var content: String? = null,
    @Enumerated(EnumType.STRING)
    var status: TodoStatus = TodoStatus.INCOMPLETE,
    @Column(name = "complete_at")
    var completeAt: LocalDateTime? = null,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // 설정한 관계
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 설정
    val user: Users, // Users 클래스의 인스턴스를 참조
)

enum class TodoStatus {
    INCOMPLETE,
    COMPLETE
}
