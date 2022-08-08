package com.farhanrv.wisatasidoarjo.data

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DataExecutors constructor(private val diskIO: Executor) {
    constructor() : this(Executors.newSingleThreadExecutor())

    fun diskIO(): Executor = diskIO
}