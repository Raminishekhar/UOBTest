package com.shekhar.uobtest.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shekhar.uobtest.adapter.ServerListAdapter
import com.shekhar.uobtest.databinding.ServerListFragmentBinding
import com.shekhar.uobtest.viewmodel.ServerListViewModel
import kotlinx.android.synthetic.main.server_list_fragment.*

class ServerListFragment : Fragment() {

    private lateinit var viewDataBinding: ServerListFragmentBinding
    private lateinit var adapter: ServerListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = ServerListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ServerListFragment).get(ServerListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchServers(context!!)

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.serverListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateServer(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
           Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = ServerListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            server_list_rv.layoutManager = layoutManager
            server_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            server_list_rv.adapter = adapter
        }
    }
}
