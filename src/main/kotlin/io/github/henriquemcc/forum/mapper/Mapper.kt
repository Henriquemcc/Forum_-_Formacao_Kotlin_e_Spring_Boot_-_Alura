package io.github.henriquemcc.forum.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
