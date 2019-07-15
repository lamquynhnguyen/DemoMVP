package com.example.demomvp

interface SearchContract {
	interface View {
		fun searchFound()
		fun searchAlreadyExist()
		fun searchNotFound()
		fun searchNoInput()
	}

	interface Presenter {
		fun handleSearch(nameFound: String)
	}
}
