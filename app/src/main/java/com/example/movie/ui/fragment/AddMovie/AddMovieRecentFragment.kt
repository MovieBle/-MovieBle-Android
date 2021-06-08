package com.example.movie.ui.fragment.AddMovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.adapters.MovieListAdapter
import com.example.movie.databinding.FragmentAddRecentMovieBinding
import com.example.movie.models.Result
import com.example.movie.untils.Constants
import com.example.movie.untils.MovieCase

class AddMovieRecentFragment : Fragment() {

    private var _binding: FragmentAddRecentMovieBinding? = null
    private val binding get() = _binding!!

    private val listAdapter: MovieListAdapter by lazy {
        MovieListAdapter(
            movieList,  MovieCase.MOVIE_RECENT

        )
    }
     var page=1
    private var recent_recycler: RecyclerView? = null
    var movieList = emptyList<Result>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : View {

        _binding = FragmentAddRecentMovieBinding.inflate(inflater, container, false)

        recent_recycler = binding.addRecentRecycler
        setTopAdapter()

        recyclerViewAddPlus()
        return binding.root
    }


    private fun recyclerViewAddPlus() {
        recent_recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                //recyclerView 마지막일때
                if (!recent_recycler!!.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    //  mSearchAdapter.deleteLoading()
                    Log.d(Constants.TAG, "onScrolled Position: $lastVisibleItemPosition")
                    Log.d(Constants.TAG, "onScrolled : Page: $page")

                }
            }
        })
    }
    private fun setTopAdapter() {


        recent_recycler?.adapter = listAdapter
        recent_recycler?.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recent_recycler?.setHasFixedSize(true)


    }
}