package aula5.example.spacesoccer.helper

// << ----------------------------------------------------------------------------------------->> //

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class VolleyHelper {

    private var queue: RequestQueue? = null

    fun userLogin(
        context: Context,
        username: String,
        password: String,
        loginEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)

            val jsonObjectRequest = JsonObjectRequest(POST,
                BASE_API + USER_LOGIN, jsonObject,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString()) // login bem sucedido
                    if (it.getBoolean("auth")) {
                        token = it.getString("token")
                        loginEvent.invoke(true)
                    } else {
                        loginEvent.invoke(false) // login mal sucedido
                    }
                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    loginEvent.invoke(false) // login mal sucedido
                }
            )
            queue!!.add(jsonObjectRequest)
        }
    }

    // << --------------------------------- Inicio Utilizador ------------------------------- >> //

    fun userRegister(
        context: Context,
        numerocartaocidadao: String,
        firstName: String,
        lastName: String,
        birthDate: String,
        username: String,
        password: String,
        registerEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            this@VolleyHelper.queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("numerocartaocidadao", numerocartaocidadao)
            jsonObject.put("firstName", firstName)
            jsonObject.put("lastName", lastName)
            jsonObject.put("birthDate", birthDate)
            jsonObject.put("username", username)
            jsonObject.put("password", password)

            val jsonObjectRequest = JsonObjectRequest(POST,
                BASE_API + USER_REGISTER,
                jsonObject,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString()) // login bem sucedido
                    registerEvent.invoke(true)
                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    registerEvent.invoke(false) // login mal sucedido
                }
            )
            queue!!.add(jsonObjectRequest)
        }
    }

    fun getAllUsers(context: Context, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + USERS,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

// << --------------------------------- Fim Utilizador -------------------------------------- >> //


// << -------------------------------- Inicio Jogadores ------------------------------------- >> //

    fun getAllPlayers(context: Context, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + PLAYERS,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    // << ------------------------------------------------------------------------------------ >> //

    fun getPlayersById(context: Context, id: Int, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + PLAYERS_CLUBE + "/" + id,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    // << ------------------------------------------------------------------------------------ >> //

    fun updatePlayersById(
        context: Context,
        id: Long,
        jsonObject: JSONObject,
        playersEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(PUT,
                BASE_API + PLAYERS + "/" + id,
                jsonObject,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    // << ------------------------------------------------------------------------------------ >> //

    fun addPlayers(
        context: Context,
        NumeroCartaoCidadao: String,
        name: String,
        number: String,
        birthDate: String,
        nationality: String,
        position: String,
        height: String,
        weight: String,
        idclube: String,
        playersEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("numerocartaocidadao", NumeroCartaoCidadao)
            jsonObject.put("name", name)
            jsonObject.put("number", number)
            jsonObject.put("birthDate", birthDate)
            jsonObject.put("nationality", nationality)
            jsonObject.put("position", position)
            jsonObject.put("height", height)
            jsonObject.put("weight", weight)
            jsonObject.put("idclube", idclube)

            val jsonObjectRequest = object : JsonObjectRequest(POST,
                BASE_API + PLAYERS,
                jsonObject,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    // << ------------------------------------------------------------------------------------ >> //

    fun deletePlayersById(context: Context, id: Long, playersEvent: ((Boolean) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(DELETE,
                BASE_API + PLAYERS + "/" + id,

                Response.Listener<String> {
                    Log.d("VolleyHelper", it.toString()) //playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

// << ---------------------------------- Fim Jogadores --------------------------------------- >> //


// << --------------------------------- Inicio Torneios -------------------------------------- >> //

    fun addTournament(
        context: Context,
        name: String,
        dtstart: String,
        dtend: String,
        numberteams: String,
        playersEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            //jsonObject.put("id", id)
            jsonObject.put("name", name)
            jsonObject.put("dtstart", dtstart)
            jsonObject.put("dtend", dtend)
            jsonObject.put("numberteams", numberteams)

            val jsonObjectRequest = object : JsonObjectRequest(POST,
                BASE_API + TORNEIOS,
                jsonObject,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    fun getAllTournaments(context: Context, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + TORNEIOS,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun getTournamentsClubById(context: Context, id: Int, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + TORNEIOS_CLUBE + "/" + id,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

// << ----------------------------------- Fim Torneio ---------------------------------------- >> //


// << --------------------------------- Inicio Equipas --------------------------------------- >> //

    fun addTeams(
        context: Context,
        idtorneio: String,
        name: String,
        president: String,
        coach: String,
        yearfundation: String,
        cityfundation: String,
        playersEvent: (Boolean) -> Unit
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("idtorneio", idtorneio)
            jsonObject.put("name", name)
            jsonObject.put("president", president)
            jsonObject.put("coach", coach)
            jsonObject.put("yearfundation", yearfundation)
            jsonObject.put("cityfundation", cityfundation)

            val jsonObjectRequest = object : JsonObjectRequest(POST,
                BASE_API + TEAMS,
                jsonObject,
                Response.Listener {
                    Log.d("VolleyHelper", it.toString())
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    fun getAllTeams(context: Context, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + TEAMS,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun deleteTeamsById(context: Context, id: Int, playersEvent: ((Boolean) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(DELETE,
                BASE_API + TEAMS + "/" + id,

                Response.Listener<String> {
                    Log.d("VolleyHelper", it.toString()) //playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

// << ----------------------------------- Fim Equipas ---------------------------------------- >> //


// << ----------------------------------- Inicio Jogos --------------------------------------- >> //

    fun getAllGames(context: Context, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + GAMES,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun getTournamentsGamesById(context: Context, id: Int, playersEvent: ((JSONArray?) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(GET,
                BASE_API + TORNEIOS_GAMES + "/" + id,
                Response.Listener<String> {
                    playersEvent.invoke(JSONArray(it))
                }, Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put(tokenName, token)
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

// << ------------------------------------- Fim Jogos ---------------------------------------- >> //


    companion object {

        const val BASE_API = "http://192.168.1.72:3000"
        const val USER_LOGIN = "/authentication/login"
        const val USER_REGISTER = "/authentication/register"
        const val PLAYERS = "/api/players"
        const val TORNEIOS = "/api/torneio"
        const val TEAMS = "/api/clube"
        const val TORNEIOS_CLUBE = "/api/torneioclube"
        const val USERS = "/authentication/list"
        const val PLAYERS_CLUBE = "/api/playersclube"
        const val GAMES = "/api/jogo"
        const val TORNEIOS_GAMES = "/api/torneiojogo"

        var token = ""
        const val tokenName = "x-access-token"

        private var mInstance: VolleyHelper? = VolleyHelper()

        val instance: VolleyHelper
            @Synchronized get() {
                if (mInstance == null) {
                    mInstance = VolleyHelper()
                }
                return mInstance!!
            }
    }
}