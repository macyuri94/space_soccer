package aula5.example.spacesoccer

import org.json.JSONObject

class Torneios {
    var Nome: String? = null

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("Nome", Nome)
        return jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject): Torneios {
            val torneio = Torneios()

            torneio.Nome = jsonArticle.getString("Nome")

            return torneio
        }
    }
}