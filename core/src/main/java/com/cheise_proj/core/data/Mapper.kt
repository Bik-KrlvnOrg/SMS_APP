package com.cheise_proj.core.data

interface Mapper<T, E> {
    fun mapTo(t: T): E
    fun mapFrom(e: E): T
}

interface MapperList<T, E> : Mapper<T, E> {
    fun mapToList(tList: List<T>): List<E>
    fun mapFromList(eList: List<E>): List<T>
}