package com.example.demomvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SearchContract.View {
	private lateinit var searchPresenter: SearchPresenter
	private lateinit var wordAdapter: WordAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		searchPresenter = SearchPresenter(this)
		wordAdapter = WordAdapter(applicationContext,searchPresenter.words as ArrayList<Word>)
		recyclerListName.adapter = wordAdapter

		btnSearch.setOnClickListener {
			search()
		}
	}

	private fun search() {
		val nameFound = edtInpName.text.toString()
		searchPresenter.handleSearch(nameFound)
	}

	override fun searchFound() {
		Toast.makeText(this, getString(R.string.msg_found), Toast.LENGTH_SHORT).show()
		wordAdapter.notifyDataSetChanged()
	}

	override fun searchAlreadyExist() {
		Toast.makeText(this, getString(R.string.msg_exist), Toast.LENGTH_SHORT).show()
	}

	override fun searchNotFound() {
		Toast.makeText(this, getString(R.string.msg_not_found), Toast.LENGTH_SHORT).show()
	}

	override fun searchNoInput() {
		Toast.makeText(this, getString(R.string.msg_no_input), Toast.LENGTH_SHORT).show()
	}
}
