package com.igzafer.sokak.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.igzafer.sokak.R
import com.igzafer.sokak.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    private lateinit var binding: FragmentInfoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        val args:InfoFragmentArgs by navArgs()
        binding.wvInfo.apply {
            webViewClient = WebViewClient()
            if(args.selectedArticle.url!=null){
                loadUrl(args.selectedArticle.url!!)
            }
        }

    }
}