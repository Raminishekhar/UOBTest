package com.shekhar.uobtest.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shekhar.uobtest.databinding.ServerDetailsFragmentBinding
import com.shekhar.uobtest.viewmodel.ServerDetailViewModel
import kotlinx.android.synthetic.main.server_details_fragment.*

class ServerDetailsFragment : Fragment() {

private lateinit var viewDataBinding: ServerDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = ServerDetailsFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ServerDetailsFragment).get(ServerDetailViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val server = arguments?.let { ServerDetailsFragmentArgs.fromBundle(it).obj }
        viewDataBinding.viewmodel?.setServer(server!!)
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.serverLive?.observe(viewLifecycleOwner, Observer {
            urlTxt.text=it.baseurl
            descTxt.text=it.desc
        })


    }

}