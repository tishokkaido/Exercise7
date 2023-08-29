package com.example.exercise7.dto

data class QiitaDto(
    val title: String,
    val tags: List<Tag>
)

data class Tag(
    val name: String
)
