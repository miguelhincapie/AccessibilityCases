package com.gorillalogic.miguelhincapie.accessibilitycases.domain.util

fun Int.toBoolean() = this == 1
fun Boolean.intValue() = if (this) 1 else 0