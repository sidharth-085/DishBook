package com.app.dishbook.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConvertor {
    @TypeConverter
    fun anyToString(attribute: Any?): String {
        if (attribute == null) {
            return ""
        }

        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?): Any {
        if (attribute == null) {
            return ""
        }

        return attribute
    }
}