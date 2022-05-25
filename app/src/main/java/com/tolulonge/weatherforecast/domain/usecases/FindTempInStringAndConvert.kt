package com.tolulonge.weatherforecast.domain.usecases

class FindTempInStringAndConvert(
    private val convertTemperatureValueToWords: ConvertTemperatureValueToWords
) {

    operator fun invoke(text: String?): String? {
       val temperatureMatcher1 = "temperature"
        val temperatureMatcher2 = "°C"
         if (text.isNullOrEmpty()) return null

        val indexOfTempMatcher1 = text.indexOf(temperatureMatcher1)


        var resultingString: String

        resultingString = if (indexOfTempMatcher1 != -1) {
            val tempRangeStart = indexOfTempMatcher1 + temperatureMatcher1.length + 1
            determineTempRangeInWordsForTempMatcher1(text, tempRangeStart)
        }else{
            text
        }

        val indexOfTempMatcher2 = resultingString.indexOf(temperatureMatcher2)

        if (indexOfTempMatcher2 != -1){
            val tempRangeEnd = indexOfTempMatcher2 - 1
         resultingString =  determineTempRangeInWordsForTempMatcher2(resultingString,tempRangeEnd)
        }
        return resultingString
    }

    private fun determineTempRangeInWordsForTempMatcher1(text: String, tempRangeStart: Int): String{
        var i = tempRangeStart
        var minRange = ""
        while (i < text.length && text[i].isDigit()){
            minRange += text[i]
            i++
        }

        while (i < text.length && !text[i].isDigit()){
            i++
        }
        var maxRange = ""
        while (i < text.length && text[i].isDigit()){
            maxRange += text[i]
            i++
        }

        return if (text[i] == '°'){
            text.substring(0, tempRangeStart)+ convertTempRangeToWords(minRange, maxRange) + text.substring(i + 2)
        }else{
            text.substring(0, tempRangeStart)+ convertTempRangeToWords(minRange, maxRange) + text.substring(i)
        }
    }

    private fun determineTempRangeInWordsForTempMatcher2(text: String, tempRangeEnd: Int): String{
        var j = tempRangeEnd
        val maxRange = StringBuilder("")
        while (j > 0 && text[j].isDigit()){
            maxRange.insert(0,text[j])
            j--
        }

        var timeInLoop = 0
        while (j > 0 && !text[j].isDigit()){
            j--
            timeInLoop++
            if (timeInLoop > 2){
                return text.substring(0, j + 4)+ "${convertTemperatureValueToWords(String(maxRange).toInt())} degrees" + text.substring(tempRangeEnd + 3)
            }
        }
        val minRange = StringBuilder("")
        while (j > 0 && text[j].isDigit()){
            minRange.insert(0,text[j])
            j--
        }

        return text.substring(0, j + 1)+ convertTempRangeToWords(String(minRange),String(maxRange)) + text.substring(tempRangeEnd + 3)
    }

    private fun convertTempRangeToWords(tempRangeStart: String, tempRangeEnd: String): String {

        val minTempRangeInWords = convertTemperatureValueToWords(tempRangeStart.toInt())
        val maxTempRangeInWords = convertTemperatureValueToWords(tempRangeEnd.toInt())

        return "$minTempRangeInWords to $maxTempRangeInWords degrees"

    }
}