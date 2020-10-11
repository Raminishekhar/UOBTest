package com.shekhar.uobtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shekhar.uobtest.R
import com.shekhar.uobtest.databinding.ItemServerBinding
import com.shekhar.uobtest.model.Server
import com.shekhar.uobtest.viewmodel.ServerListViewModel
import org.jetbrains.anko.bundleOf


class ServerListAdapter (var viewModel: ServerListViewModel) :
    RecyclerView.Adapter<ServerListAdapter.CountryViewHolder>() {
    var serverList: List<Server> = emptyList()

    fun updateServer(serverList: List<Server>) {
        this.serverList = serverList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.getContext())
        val itemBinding: ItemServerBinding =
            ItemServerBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int {
        return serverList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val server = serverList.get(position)

        holder.bind(server)
        holder.itemView.setOnClickListener { v ->
            val bundle = bundleOf("obj" to server)
            holder.itemView.findNavController().navigate(R.id.action_serverListFragment_to_serverDetailFragment, bundle)

        }
    }
    class CountryViewHolder(val binding: ItemServerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Server) {
            binding.server = item
            binding.executePendingBindings()
        }

    }
}