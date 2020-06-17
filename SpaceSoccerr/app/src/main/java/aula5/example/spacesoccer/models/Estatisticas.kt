package aula5.example.spacesoccer.models

import org.json.JSONObject

class Estatisticas {

    var IdEstatistica:      Int? = null
    var IdJogo:             Int? = null
    var PosseBolaA:         Int? = null
    var PosseBolaB:         Int? = null
    var RematesBalizaA:     Int? = null
    var RematesTotaisA:     Int? = null
    var RematesBalizaB:     Int? = null
    var RematesTotaisB:     Int? = null
    var NumIncidenciasA:    Int? = null
    var NumIncidenciasB:    Int? = null
    var GolosA:             Int? = null
    var GolosB:             Int? = null
    var ForaDeJogoA:        Int? = null
    var ForaDeJogoB:        Int? = null

    companion object {
        fun parseJson(jsonArticle: JSONObject): Estatisticas {
            val estatisticas = Estatisticas()

            estatisticas.IdEstatistica      = jsonArticle.getInt("IdEstatistica")
            estatisticas.IdJogo             = jsonArticle.getInt("IdJogo")
            estatisticas.PosseBolaA         = jsonArticle.getInt("PosseBolaA")
            estatisticas.PosseBolaB         = jsonArticle.getInt("PosseBolaB")
            estatisticas.RematesBalizaA     = jsonArticle.getInt("RematesBalizaA")
            estatisticas.RematesTotaisA     = jsonArticle.getInt("RematesTotaisA")
            estatisticas.RematesBalizaB     = jsonArticle.getInt("RematesBalizaB")
            estatisticas.RematesTotaisB     = jsonArticle.getInt("RematesTotaisB")
            estatisticas.NumIncidenciasA    = jsonArticle.getInt("NumIncidenciasA")
            estatisticas.NumIncidenciasB    = jsonArticle.getInt("NumIncidenciasB")
            estatisticas.GolosA             = jsonArticle.getInt("GolosA")
            estatisticas.GolosB             = jsonArticle.getInt("GolosB")
            estatisticas.ForaDeJogoA        = jsonArticle.getInt("ForaDeJogoA")
            estatisticas.ForaDeJogoB        = jsonArticle.getInt("ForaDeJogoB")

            return estatisticas
        }
    }
}