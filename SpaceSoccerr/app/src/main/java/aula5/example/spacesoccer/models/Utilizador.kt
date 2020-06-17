package aula5.example.spacesoccer.models

import org.json.JSONObject

class Utilizador {
    var IdUtilizador:           Int? = null
    var NumeroCartaoCidadao:    Int? = null
    var PrimeiroNome:           String? = null
    var UltimoNome:             String? = null
    var DataNascimento:         String? = null
    var Email:                  String? = null
    var Password:               String? = null

    companion object {
        fun parseJson(jsonArticle: JSONObject): Utilizador {
            val utilizador = Utilizador()

            utilizador.IdUtilizador         = jsonArticle.getInt("IdUtilizador")
            utilizador.NumeroCartaoCidadao  = jsonArticle.getInt("NumeroCartaoCidadao")
            utilizador.PrimeiroNome         = jsonArticle.getString("PrimeiroNome")
            utilizador.UltimoNome           = jsonArticle.getString("UltimoNome")
            utilizador.DataNascimento       = jsonArticle.getString("DataNascimento")
            utilizador.Email                = jsonArticle.getString("Email")
            utilizador.Password             = jsonArticle.getString("Password")

            return utilizador
        }
    }
}