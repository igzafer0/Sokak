package com.igzafer.sokak.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.igzafer.sokak.R
import com.igzafer.sokak.data.util.Resource
import com.igzafer.sokak.databinding.FragmentHomeBinding
import com.igzafer.sokak.databinding.FragmentNewsBinding
import com.igzafer.sokak.presentation.adapter.NewsAdapter
import com.igzafer.sokak.presentation.viewmodel.NewsViewModel


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        Log.d("winter","ssadfasdfafasd")
        viewModel = (activity as HomeActivity).viewModel
        newsAdapter = (activity as HomeActivity).adapter
        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("selected_article",it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment,bundle)
        }
        initRecy()

        if(newsAdapter.article.size==0){
            viewNewsList()
        }else{
            binding.rvNews.scrollToPosition(viewModel.recyPos)
        }

    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines("tr", page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.setList(it.articles!!)

                        if (it.totalResults!! % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecy() {
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(activity)
        binding.rvNews.addOnScrollListener(this@NewsFragment.onScrollListener)
        binding.rvNews.setHasFixedSize(true)
        newsAdapter.registerAdapterDataObserver( object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(
                positionStart: Int,
                itemCount: Int
            ) {
                // This will scroll to the top when new data was inserted
                binding.rvNews.scrollToPosition(viewModel.recyPos)
            }
        })
    }

    private fun showProgressBar() {
        isLoading = true
    }

    private fun hideProgressBar() {
        isLoading = false

    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            viewModel.recyPos=topPosition
            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd
            if (shouldPaginate) {
                page++
                viewModel.getNewsHeadlines("tr", page)
                isScrolling = false
            }

        }
    }

}