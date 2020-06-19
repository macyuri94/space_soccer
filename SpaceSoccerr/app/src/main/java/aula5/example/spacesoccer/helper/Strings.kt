package aula5.example.spacesoccer.helper

import java.text.SimpleDateFormat
import java.util.*

class Strings {

    fun dateToString(date : Date) : String{
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm:ss", Locale.getDefault())
        return formatter.format(date)
    }

    fun stringToDate(dateStr: String) : Date {
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        val date = formatter.parse(dateStr)
        return date ?: Date()
    }
}