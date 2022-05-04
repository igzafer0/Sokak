package com.igzafer.sokak.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.igzafer.sokak.R
import com.igzafer.sokak.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var selectedPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val transaction = childFragmentManager.beginTransaction()
        Log.d("winter","çalıştı")
        transaction.replace(R.id.frame, NewsFragment())
        transaction.commit()
        binding.bugunTw.setOnClickListener {
            if (selectedPage == 1) {
                binding.haberBg.visibility = View.VISIBLE
                binding.kayitBg.visibility = View.GONE
                childFragmentManager.popBackStack()
                selectedPage = 0
            }

        }
        binding.kaydedilenlerTw.setOnClickListener {
            if (selectedPage == 0) {
                binding.kayitBg.visibility = View.VISIBLE
                binding.haberBg.visibility = View.GONE
                selectedPage = 1
                val transaction = childFragmentManager.beginTransaction()
                val fragmentPopped: Boolean = childFragmentManager.popBackStackImmediate(NewsFragment().javaClass.name, 0)
                if(!fragmentPopped){
                    transaction.replace(R.id.frame, SavedFragment()).addToBackStack(NewsFragment().javaClass.name).commit()

                }


            }

        }
        val c = Calendar.getInstance()


        val month = c.get(Calendar.MONTH)
        val gun_ay = c.get(Calendar.DAY_OF_MONTH)
        val gun_hafta = c.get(Calendar.DAY_OF_WEEK)
        binding.toolBarTarih.text="${gun[gun_hafta]}, ${ay[month]} $gun_ay"

    }
    val gun= arrayOf("Cumartesi","Pazar","Pazartesi","Salı","Çarşamba","Perşeme","Cuma","Cuma")
    val ay= arrayOf("Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık")
}