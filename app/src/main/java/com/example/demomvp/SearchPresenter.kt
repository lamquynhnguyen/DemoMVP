package com.example.demomvp

import android.os.AsyncTask
import org.json.JSONArray

const val URL = "https://api.github.com/users/google/repos"

class SearchPresenter(private val view: SearchContract.View) : SearchContract.Presenter {
	private var isEqual = false
	private var isExist = false
	val words: List<Word> = ArrayList()
	override fun handleSearch(nameFound: String) {
		if (checkEmpty(nameFound)) view.searchNoInput()
		else {
			isEqual = CheckNameAsyncTask().execute(nameFound).get()
			if (isEqual) {
				if (checkExist(nameFound)) {
					view.searchAlreadyExist()
				} else {
					(words as ArrayList).add(Word(nameFound))
					view.searchFound()
				}
			} else view.searchNotFound()
		}
	}

	private fun checkEmpty(nameFound: String): Boolean = nameFound.isEmpty()

	private fun checkExist(nameFound: String): Boolean {
		for (i in 0 until words.size) {
			if (nameFound == words[i].name) {
				isExist = true
				break
			}
		}
		return isExist
	}

	class CheckNameAsyncTask : AsyncTask<String, Void, Boolean>() {
		override fun doInBackground(vararg params: String?): Boolean {
			val jsonString = GetJson().getJsonStreamFromUrl(URL)
			val jsonArrayRoot = JSONArray(jsonString)
			val length = jsonArrayRoot.length()
			for (i in 0 until length) {
				val jsonObject = jsonArrayRoot.getJSONObject(i)
				val name = jsonObject.getString("name")
				if (name == params[0]) {
					return true
				}
			}
			return false
		}
	}
}
