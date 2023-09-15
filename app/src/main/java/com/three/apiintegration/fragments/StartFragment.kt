package com.three.apiintegration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.three.apiintegration.R

class StartFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //For app to stay in start so that app doesn't shut down on backpress. Only for testing.
                view?.findNavController()?.popBackStack(R.id.startFragment, false)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_start, container, false)
        val button = rootView.findViewById<Button>(R.id.startButton)

        button.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_startFragment_to_secondFragment)
        }

        return rootView
    }
}