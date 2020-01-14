package com.mystudies.imggallery.utils

class WSResult<T>(private var resType: Type, private var resValue: T?) {

    public val type: Type get() = resType

    public val value: T? get() = resValue

    enum class Type {
        SUCCESS, ERROR
    }
}
