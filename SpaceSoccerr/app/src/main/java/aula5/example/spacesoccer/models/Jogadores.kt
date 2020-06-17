package aula5.example.spacesoccer.models

import org.json.JSONObject
import java.lang.NullPointerException

class Jogadores {
    var IdJogador:              Int? = null
    var NumeroCartaoCidadao:    String? = null
    var Nome:                   String? = null
    var NumCamisola:            Int? = null
    var DataNascimento:         String? = null
    var Nacionalidade:          String? = null
    var Posicao:                String? = null
    var Altura:                 Int? = null
    var Peso:                   Int? = null
    var IdClube:                Int? = null

    companion object {
        fun parseJson(jsonArticle: JSONObject): Jogadores {
            val jogador = Jogadores()

            jogador.IdJogador           = jsonArticle.getInt("IdJogador")
            jogador.NumeroCartaoCidadao = jsonArticle.getString("NumeroCartaoCidadao")
            jogador.Nome                = jsonArticle.getString("Nome")
            jogador.NumCamisola         = jsonArticle.getInt("NumCamisola")
            jogador.DataNascimento      = jsonArticle.getString("DataNascimento")
            jogador.Nacionalidade       = jsonArticle.getString("Nacionalidade")
            jogador.Posicao             = jsonArticle.getString("Posicao")
            jogador.Altura              = jsonArticle.getInt("Altura")
            jogador.Peso                = jsonArticle.getInt("Peso")
            jogador.IdClube             = jsonArticle.getInt("IdClube")

            return jogador
        }
    }
}