package com.example.dreamsleepapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val HRS_SLEPT = "hrsSlept"
private const val DREAM = "dream"
private const val RATING = "rating"
private const val ID = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [SleepLogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepLogFragment : Fragment() {
    private var hrsSlept: Int? = null
    private var dream: String? = null
    private var rating: Int? = null
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hrsSlept = it.getInt(HRS_SLEPT)
            dream = it.getString(DREAM)
            rating = it.getInt(RATING)
            id = it.getInt(ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sleep_log, container, false)
        val hrsTextView : EditText = view.findViewById(R.id.editTextNumber)
        val dreamText : EditText = view.findViewById(R.id.editDreamText)

        val rating1 : RadioButton = view.findViewById(R.id.rating1Btn)
        val rating2 : RadioButton = view.findViewById(R.id.rating2Btn)
        val rating3 : RadioButton = view.findViewById(R.id.rating3Btn)
        val rating4 : RadioButton = view.findViewById(R.id.rating4Btn)
        val rating5 : RadioButton = view.findViewById(R.id.rating5Btn)

        rating1.setOnClickListener { rating = 1 }
        rating2.setOnClickListener { rating = 2 }
        rating3.setOnClickListener { rating = 3 }
        rating4.setOnClickListener { rating = 4 }
        rating5.setOnClickListener { rating = 5 }

        val saveBtn : Button = view.findViewById(R.id.saveBtn)

        if(hrsSlept != null && hrsSlept != -1) {
            hrsTextView.setText(hrsSlept.toString())
        }
        if(dream != null && dream != "") {
            dreamText.setText(dream)
        }


        saveBtn.setOnClickListener {
            val client = OkHttpClient()

            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val date: String = sdf.format(Date())

            val jsonObject = JSONObject().apply {
                put("hours_slept", hrsTextView.text)
                put("sleep_quality", rating)
                put("date", date)
            }

            val hasDream = dreamText.text.toString().equals("")

            val sleepObject = JSONObject().apply {
                put("description", dreamText.text.toString())
                put("has_description", hasDream)
            }

            val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val sleepRequestBody = sleepObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val putRequest = Request.Builder().url("http://35.199.3.100/api/sleeps/").post(requestBody).build()
            val sleepPutRequest = Request.Builder().url("http://35.199.3.100/api/dreams/$id").post(sleepRequestBody).build()


            client.newCall(putRequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("sleep fragment response", "sent sleep put request")
                }

            })

            client.newCall(sleepPutRequest).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("sleep fragment response", "sent dream put request")
                }

            })
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SleepHistoryFragment.newInstance("", "")).commit()
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SleepLogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(hrsSlept: Int, dream: String, rating: Int, id: Int) =
            SleepLogFragment().apply {
                arguments = Bundle().apply {
                    putInt(HRS_SLEPT, hrsSlept)
                    putString(DREAM, dream)
                    putInt(RATING, rating)
                    putInt(ID, id)
                }
            }
    }
}