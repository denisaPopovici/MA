package com.example.macruduinative.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macruduinative.R

class DrugListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<DrugListAdapter.DrugViewHolder>() {

    var onItemClick: ((Drug) -> Unit)? = null
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var drugs = emptyList<Drug>() // Cached copy of words

    inner class DrugViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val drugItemView: TextView = itemView.findViewById(R.id.textView)
        init {
            itemView.setOnClickListener {
                onItemClick?. invoke(drugs[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return DrugViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        val current = drugs[position]
        holder.drugItemView.text = current.name
    }

    internal fun setDrugs(drugs: List<Drug>) {
        this.drugs = drugs
        notifyDataSetChanged()
    }

    override fun getItemCount() = drugs.size
}