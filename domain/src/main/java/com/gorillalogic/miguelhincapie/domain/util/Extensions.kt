package com.gorillalogic.miguelhincapie.domain.util

fun Int.toBoolean() = this == 1
fun Boolean.intValue() = if (this) 1 else 0