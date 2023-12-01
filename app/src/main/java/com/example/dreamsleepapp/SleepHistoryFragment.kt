package com.example.dreamsleepapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SleepHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepHistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sleepList : List<Sleep>

    private var param1: String? = null
    private var param2: String? = null

    private var sleepDate: String? = null
    private var hrsSlept: Int? = null
    private var dream: String? = null
    private var rating: Int? = null

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
        val view = inflater.inflate(R.layout.fragment_sleep_history, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(this.context)


        val floatingButton : FloatingActionButton = view.findViewById(R.id.floatingActionButton)

        sleepList = emptyList()

        val client = OkHttpClient()
        val request = Request.Builder().url("http://35.199.3.100/api/sleeps/").get().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread { Log.e(TAG, "failed", e)}
            }

            override fun onResponse(call: Call, response: Response) {
                val res = response.body?.string()
                Log.d("sleep fragment response", "got sleeps from list")
                res.let {
                    activity?.runOnUiThread {
                        val list : List<Sleep>? = parseList(it)

                        list?.let {
                            activity?.runOnUiThread {
                                sleepList = list
                                Log.d("sleep fragment response", sleepList.toString())
                                recyclerView.adapter = SleepAdapter(sleepList)
                                Index.index = SleepAdapter(sleepList).itemCount

                            }
                        }
                    }

                }

            }

        })



        floatingButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SleepLogFragment.newInstance(-1, "", -1, Index.index+1)).commit()
        }
        return view;
    }

    private fun parseList(listJson: String?) : List<Sleep>? {
        return try {
            val moshi : Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val sleepListType = Types.newParameterizedType(List::class.java, Sleep::class.java)
            val jsonAdapter : JsonAdapter<List<Sleep>> = moshi.adapter(sleepListType)
            jsonAdapter.fromJson(listJson)
        } catch (e: Exception) {
           Log.d("sleep fragment response", e.message.toString());
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
         * @return A new instance of fragment SleepHistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SleepHistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}