package aula5.example.spacesoccer

import org.json.JSONObject

class Equipas {
    var NomeClube: String? = null

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("NomeClube", NomeClube)
        return jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject): Equipas {
            val equipa = Equipas()

            equipa.NomeClube = jsonArticle.getString("NomeClube")

            return equipa
        }
    }
}