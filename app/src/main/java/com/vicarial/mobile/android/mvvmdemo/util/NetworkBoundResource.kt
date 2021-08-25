package com.vicarial.mobile.android.mvvmdemo.util

import kotlinx.coroutines.flow.*

/**
 * As metioned in the google architecture docs, this is a higher
 * order function that saves changed data to a store (in this app, Room),
 * and then emits the changes to observers
 * Uses a resource wrapper idea from Florian at codinginflow.com to
 * allow us to easily determine request/response state
 */

// inline the functions for perf
inline fun <ResultType, RequestType>networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow { // flow is executed whenever we call this function
    // do we need to fetch new data
    val data = query().first() // return one list
    // see if we need to fetch new data - this example is pretty simplistic,
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading())
        try{
            saveFetchResult(fetch()) // this can fail
            query().map { Resource.Success(it)} // emits data to flow
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it)}
        }
    } else { // current cache data
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}