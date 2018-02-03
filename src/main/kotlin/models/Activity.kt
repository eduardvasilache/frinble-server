package models

// TODO: Rename - maybe something like Interval, Break, Opening or Opportunity

data class Activity(
    val id: Long,
    val startTime: Long,
    val endTime: Long
)