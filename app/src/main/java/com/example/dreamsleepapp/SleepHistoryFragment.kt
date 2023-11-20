package com.example.dreamsleepapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
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
        val view = inflater.inflate(R.layout.fragment_sleep_history, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val tempDataSet = mutableListOf<Sleep>()

        tempDataSet.add(0, Sleep(id = 4, hrs = 5, dream = "", rating = 3, date = "11/14/2023"))
        tempDataSet.add(0, Sleep(id = 0, hrs = 6, dream = "", rating = 2, date = "11/15/2023"))
        tempDataSet.add(0, Sleep(id = 6, hrs = 10, dream = "", rating = 5, date = "11/16/2023"))
        tempDataSet.add(0, Sleep(id = 1, hrs = 2, dream = "", rating = 1, date = "11/17/2023"))
        tempDataSet.add(0, Sleep(id = 2, hrs = 4, dream = "", rating = 2, date = "11/18/2023"))
        tempDataSet.add(0, Sleep(id = 3, hrs = 8, dream = "", rating = 4, date = "11/19/2023"))

        recyclerView.adapter = SleepAdapter(tempDataSet)
        return view;
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