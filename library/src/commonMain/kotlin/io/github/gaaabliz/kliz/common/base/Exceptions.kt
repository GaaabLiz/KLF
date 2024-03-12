package io.github.gaaabliz.kliz.common.base

class OperationException(
    val msg:String,
    val state:Boolean? = null,
    val title:String? = null,
) : Exception(msg)


/*

class OperationException(val title:String, val msg:String) : Exception(msg) {

    constructor(title:String, msg:String, logger: Logger? = null) : this(title, msg) {
        logger?.error(msg)
    }

}

 */