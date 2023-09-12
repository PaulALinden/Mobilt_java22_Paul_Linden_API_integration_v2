package com.three.api_integration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController

class SecondFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
        val button2 = rootView.findViewById<Button>(R.id.button2)
        val button3 = rootView.findViewById<Button>(R.id.button3)

        button2.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_secondFragment_to_branchOneFragment)
        }

        button3.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_secondFragment_to_branchTwoFragment)
        }

        view?.findNavController()?.popBackStack(R.id.branchOneFragment,true)

        return rootView
    }
}