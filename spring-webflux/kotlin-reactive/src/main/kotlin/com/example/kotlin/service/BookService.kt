package com.example.kotlin.service

import com.example.kotlin.domain.Book
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BookService {

    fun findById(id: Long): Mono<Book>
    fun findAll(): Flux<Book>
    fun create(book: Book): Mono<Book>
    fun update(book: Book): Mono<Book>
    fun deleteById(id: Long): Mono<Void>
    fun deleteAll(): Mono<Void>
}