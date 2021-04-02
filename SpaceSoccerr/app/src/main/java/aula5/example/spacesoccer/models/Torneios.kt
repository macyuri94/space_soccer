package aula5.example.spacesoccer.models

import org.json.JSONObject

class Torneios {
    var IdTorneio:          Int? = null
    var Nome:               String? = null
    var dtInicio:           String? = null
    var dtFim:              String? = null
    var Campeao:            String? = null
    var NumEquipas:         Int? = null

    companion object {
        fun parseJson(jsonArticle: JSONObject): Torneios {
            val torneio = Torneios()

            torneio.IdTorneio       = jsonArticle.getInt("IdTorneio")
            torneio.Nome            = jsonArticle.getString("Nome")
            torneio.dtInicio        = jsonArticle.getString("dtInicio")
            torneio.dtFim           = jsonArticle.getString("dtFim")
            torneio.Campeao         = jsonArticle.getString("Campeao")
            torneio.NumEquipas      = jsonArticle.getInt("NumEquipas")

            return torneio
        }
    }
}