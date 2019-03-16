package com.juliensacre.findcar.internal

import androidx.room.TypeConverter

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun listToString(list: List<String>?) : String {

            var string = ""
            if(list!=null) {
                for (item in list)
                    string += "$item,"
            }
            return string
        }

        @TypeConverter
        @JvmStatic
        fun stringToList(string: String) : List<String>{
            val list = mutableListOf<String>()
            for (s in string.split(","))
                list.add(s)

            return list
        }
    }
}