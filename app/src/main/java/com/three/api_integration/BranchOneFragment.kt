package com.three.api_integration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class BranchOneFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    view?.findNavController()?.popBackStack(R.id.secondFragment, false)
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_branch_one, container, false)
        val button = rootView.findViewById<Button>(R.id.button5)
        val chuckButton = rootView.findViewById<Button>(R.id.chuckButton)
        val chuckTextView = rootView.findViewById<TextView>(R.id.chuckTextView)

        chuckButton.setOnClickListener {
            getChuckQuote { quote ->
                activity?.runOnUiThread {
                    chuckTextView.text = quote
                }
            }
        }

        button.setOnClickListener {
            view?.findNavController()?.popBackStack(R.id.startFragment, false)
        }

        return rootView
    }

    private fun getChuckQuote(callback: (String) -> Unit) {
        val apiCallManager = APICallManager()
        apiCallManager.chuckNorrisApiCall { result ->
            if (result != null) {
                callback(result)
            } else {
                callback("Fel")
                Log.e("api_error", "API call failed or result is null")
            }
        }
    }
}