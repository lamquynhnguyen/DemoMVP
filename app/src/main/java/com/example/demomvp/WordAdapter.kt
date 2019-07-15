package com.example.demomvp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.each_item_layout.view.*

class WordAdapter(val context: Context, private val words: ArrayList<Word>) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(context)
				.inflate(R.layout.each_item_layout, viewGroup, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return words.size
	}

	override fun onBindViewHolder(viewGroup: ViewHolder, position: Int) {
		val item = words.get(position)
		viewGroup.bindView(item)
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val nameItem = itemView.txtName
		fun bindView(word: Word) {
			nameItem.text = word.name
		}
	}
}
