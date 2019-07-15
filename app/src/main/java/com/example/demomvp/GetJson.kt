package com.example.demomvp

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetJson {
	private var line: String? = null

	fun getJsonStreamFromUrl(urlString: String): String {
		var response: String? = null
		val url = URL(urlString)
		val httpConnection = url.openConnection() as HttpURLConnection
		httpConnection.requestMethod = "GET"
		val inputStream = httpConnection.inputStream
		response = convertStreamToString(inputStream)
		return response
	}

	private fun convertStreamToString(inputStream: InputStream): String {
		val bufferReader = BufferedReader(InputStreamReader(inputStream))
		val stringBuilder = StringBuilder()
		line = bufferReader.readLine()
		while(line != null){
			stringBuilder.append(line).append("\n")
			line = bufferReader.readLine()
		}
		inputStream.close()
		return stringBuilder.toString()
	}
}
