package com.shekhar.uobtest.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shekhar.uobtest.R
import kotlinx.android.synthetic.main.server_details_fragment.*

class ServerDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.server_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val url = arguments?.let { ServerDetailsFragmentArgs.fromBundle(it).obj }
        urlTxt.text=url?.baseurl;
        descTxt.text=url?.desc
    }

}