package com.example.dreamsleepapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DreamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DreamFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_dream, container, false)
        val maxHrs : TextView = view.findViewById(R.id.mostWordsText)

        val client = OkHttpClient()
        val request = Request.Builder().url("http://35.199.3.100/api/sleeps/best-hours-slept/").get().build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("sleep fragment response", "couldnt get optimal hrs")
            }

            override fun onResponse(call: Call, response: Response) {
                val res = response.body?.string()
                Log.d("sleep fragment response", "got max hrs")
                res?.let {
                    val hrs: Int? = parseHrs(it)
                    hrs?.let {
                        activity?.runOnUiThread {
                            maxHrs.text = hrs.toString()
                        }
                    }
                }
            }

        })
        return view
    }

    private fun parseHrs (json: String?): Int? {
        return try {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val jsonAdapter : JsonAdapter<Int> = moshi.adapter(Int::class.java)
            jsonAdapter.fromJson(json)
        } catch (e: Exception) {
            Log.d("sleep fragment response", e.message.toString())
            null
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DreamFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DreamFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}