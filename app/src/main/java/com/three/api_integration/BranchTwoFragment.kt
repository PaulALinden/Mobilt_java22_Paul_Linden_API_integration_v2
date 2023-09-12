package com.three.api_integration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class BranchTwoFragment : Fragment() {

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
        val rootView = inflater.inflate(R.layout.fragment_branch_two, container, false)
        val progressBar = rootView.findViewById<ProgressBar>(R.id.loadingPanel)
        val button = rootView.findViewById<Button>(R.id.button4)
        val catButton = rootView.findViewById<Button>(R.id.catButton)
        val catImageView = rootView.findViewById<ImageView>(R.id.catImageView)

        catButton.setOnClickListener {
            catImageView.setImageResource(0)
            progressBar.visibility = View.VISIBLE

            getFoxImg { path ->
                activity?.runOnUiThread {
                    Picasso.get().load(path)
                        .into(catImageView, object : Callback {

                            override fun onSuccess() {
                                progressBar.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                progressBar.visibility = View.GONE
                                Log.e("Picasso", "Error loading image: ${e?.message}")
                            }
                        })
                }
            }
        }

        button.setOnClickListener {
            view?.findNavController()?.popBackStack(R.id.startFragment, false)
        }

        return rootView
    }

    private fun getFoxImg(callback: (String) -> Unit) {
        val apiCallManager = APICallManager()
        apiCallManager.foxApiCall { result ->
            if (result != null) {
                callback(result)
            } else {
                callback("Fel")
                Log.e("api_error", "API call failed or result is null")
            }
        }
    }
}