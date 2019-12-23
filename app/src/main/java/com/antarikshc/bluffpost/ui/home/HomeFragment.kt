package com.antarikshc.bluffpost.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.antarikshc.bluffpost.R

/**
 * A simple [Fragment] subclass with [OnBackPressedDispatcher]
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()

        private val TAG = HomeFragment::class.java.simpleName
    }

    private val navController by lazy { findNavController() }
    private val viewModel by lazy { provideHomeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) { onBackPressed() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.news.observe(this, Observer {
            Log.d(TAG, "Response: $it")
        })
    }

    /**
     * Intercepts Back Pressed to delegate responsibility to Fragment
     * Do onBackPressed action here
     */
    private fun onBackPressed() {
        requireActivity().finish()
    }

    private fun provideHomeViewModel() =
        ViewModelProviders.of(requireActivity()).get(HomeVM::class.java)
}
