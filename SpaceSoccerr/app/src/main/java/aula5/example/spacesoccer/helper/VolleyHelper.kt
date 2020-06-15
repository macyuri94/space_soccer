package aula5.example.spacesoccer.helper

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

class VolleyHelper {

    private var queue : RequestQueue? = null

    fun userLogin (context: Context, username : String, password: String, loginEvent : ((Boolean)->Unit) ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)

            val jsonObjectRequest = JsonObjectRequest( POST,
                BASE_API + USER_LOGIN, jsonObject,
                Response.Listener {

                    Log.d("VolleyHelper", it.toString()) // login bem sucedido
                    if (it.getBoolean("auth")){
                        token = it.getString("token")
                        loginEvent.invoke(true)
                    }else {
                        // login mal sucedido
                        loginEvent.invoke(false)
                    }
                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    // login mal sucedido
                    loginEvent.invoke(false)
                }
            )
            queue!!.add(jsonObjectRequest)
        }
    }


    fun userRegister (context: Context, username : String, password: String, registerEvent : ((Boolean)->Unit) ) {
        doAsync {
            this@VolleyHelper.queue = Volley.newRequestQueue(context)


            val jsonObject = JSONObject()
            jsonObject.put("username", username)
            jsonObject.put("password", password)


            val jsonObjectRequest = JsonObjectRequest( POST,
                BASE_API + USER_REGISTER,
                jsonObject,
                Response.Listener{
                    // login bem sucedido
                    Log.d("VolleyHelper", it.toString())

                    registerEvent.invoke(true)
                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    // login mal sucedido
                    registerEvent.invoke(false)
                }
            )
            queue!!.add(jsonObjectRequest)
        }
    }


    fun getAllPlayers(context: Context, playersEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest( GET,
                BASE_API + PLAYERS,
                Response.Listener<String>{
                    playersEvent.invoke(JSONArray(it))
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("x-access-token", token)
                    return map
                }
            }

            queue!!.add(stringRequest)
        }
    }


    fun getPlayersbyId(context: Context, id : Long, playersEvent : ((JSONArray?)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                GET,
                BASE_API + PLAYERS + "/" + id,
                Response.Listener<String>{
                    playersEvent.invoke(JSONArray(it))
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    playersEvent.invoke(null)
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("x-access-token", token)
                    return map
                }
            }

            queue!!.add(stringRequest)
        }
    }


    fun updatePlayersById(context: Context, id : Long, jsonObject: JSONObject, playersEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest( PUT,
                BASE_API + PLAYERS + "/" + id,
                jsonObject,
                Response.Listener {
                    //medicosEvent.invoke(JSONArray(it))
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("x-access-token", token)
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }


    fun addPlayers(context: Context, jsonObject: JSONObject, playersEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest( POST,
                BASE_API + PLAYERS ,
                jsonObject,
                Response.Listener {
                    //medicosEvent.invoke(JSONArray(it))
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("x-access-token", token)
                    return map
                }
            }

            queue!!.add(jsonObjectRequest)
        }
    }

    fun deletePlayersById(context: Context, id: Long, playersEvent : ((Boolean)->Unit)){
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest( DELETE,
                BASE_API + PLAYERS + "/" + id,

                Response.Listener<String> {
                    //playersEvent.invoke(JSONArray(it))
                    Log.d("VolleyHelper", it.toString())
                },Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf<String, String>()
                    map.put("x-access-token", token)
                    return map
                }
            }

            queue!!.add(stringRequest)
        }
    }

    companion object {

        const val  BASE_API = "http://192.168.1.99:3000"
        const val  USER_LOGIN = "/authentication/login"
        const val USER_REGISTER = "/authentication/register"
        const val  PLAYERS = "/api/tournament"

        var token = ""

        private var mInstance : VolleyHelper? = VolleyHelper()

        val instance : VolleyHelper
            @Synchronized get() {
                if(mInstance == null) {
                    mInstance = VolleyHelper()
                }
                return mInstance!!
            }
    }
}