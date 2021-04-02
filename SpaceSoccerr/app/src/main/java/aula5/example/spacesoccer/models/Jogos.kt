package aula5.example.spacesoccer.models

import org.json.JSONObject

class Jogos {

    var IdJogo:             Int? = null
    var EquipaCasa:         String? = null
    var EquipaConvidada:    String? = null
    var Arbitro:            String? = null
    var DataJogo:           String? = null
    var IdTorneio:          Int? = null

    companion object {
        fun parseJson(jsonArticle: JSONObject): Jogos {
            val jogos = Jogos()

            jogos.IdJogo            = jsonArticle.getInt("IdJogo")
            jogos.EquipaCasa        = jsonArticle.getString("EquipaCasa")
            jogos.EquipaConvidada   = jsonArticle.getString("EquipaConvidada")
            jogos.Arbitro           = jsonArticle.getString("Arbitro")
            jogos.DataJogo          = jsonArticle.getString("DataJogo")
            jogos.IdTorneio         = jsonArticle.getInt("Torneio")

            return jogos
        }
    }
}