package aula5.example.spacesoccer.models

import org.json.JSONObject

class Equipas {
    var IdClube:        Int? = null
    var IdTorneio:      Int? = null
    var NomeClube:      String? = null
    var Presidente:     String? = null
    var Treinador:      String? = null
    var AnoFundacao:    String? = null
    var CidadeFundacao: String? = null

    companion object {
        fun parseJson(jsonArticle: JSONObject): Equipas {
            val equipa = Equipas()

            equipa.IdClube          = jsonArticle.getInt("IdClube")
            equipa.IdTorneio        = jsonArticle.getInt("IdTorneio")
            equipa.NomeClube        = jsonArticle.getString("NomeClube")
            equipa.Presidente       = jsonArticle.getString("Presidente")
            equipa.Treinador        = jsonArticle.getString("Treinador")
            equipa.AnoFundacao      = jsonArticle.getString("AnoFundacao")
            equipa.CidadeFundacao   = jsonArticle.getString("CidadeFundacao")

            return equipa
        }
    }
}