package com.example.tfisland.model.dto

data class MemoryResponse(
    val memoryId: Long,
    val createdAt: String,
    val content: String,
    val color: String,
    val title: String,
    val memoryDate: String?
)
