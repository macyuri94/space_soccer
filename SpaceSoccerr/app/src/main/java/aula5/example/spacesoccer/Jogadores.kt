package aula5.example.spacesoccer

import org.json.JSONObject

class Jogadores {
    var Nome: String? = null

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("Nome", Nome)
        return jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject): Jogadores {
            val jogador = Jogadores()

            jogador.Nome = jsonArticle.getString("Nome")

            return jogador
        }
    }
}