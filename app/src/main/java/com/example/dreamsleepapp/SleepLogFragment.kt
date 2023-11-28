package com.example.dreamsleepapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val HRS_SLEPT = "hrsSlept"
private const val DREAM = "dream"
private const val RATING = "rating"

/**
 * A simple [Fragment] subclass.
 * Use the [SleepLogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepLogFragment : Fragment() {
    private var hrsSlept: Int? = null
    private var dream: String? = null
    private var rating: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hrsSlept = it.getInt(HRS_SLEPT)
            dream = it.getString(DREAM)
            rating = it.getInt(RATING)
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
        //TODO: implement radio button stuff
        val saveBtn : Button = view.findViewById(R.id.saveBtn)

        if(hrsSlept != null && hrsSlept != -1) {
            hrsTextView.setText(hrsSlept.toString())
        }
        if(dream != null && dream != "") {
            dreamText.setText(dream)
        }


        saveBtn.setOnClickListener {
            //TODO: connect to backend & send api 'put' request
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
        fun newInstance(hrsSlept: Int, dream: String, rating: Int) =
            SleepLogFragment().apply {
                arguments = Bundle().apply {
                    putInt(HRS_SLEPT, hrsSlept)
                    putString(DREAM, dream)
                    putInt(RATING, rating)
                }
            }
    }
}