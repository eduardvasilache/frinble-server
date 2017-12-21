package utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

fun ZonedDateTime.toIsoString(): String = format(isoFormatter)