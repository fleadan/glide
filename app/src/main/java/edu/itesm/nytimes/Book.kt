package edu.itesm.nytimes

import com.google.gson.annotations.SerializedName


data class Results(
        var results: Books,
)

data class Books(
        var books: MutableList<Book>
)


data class Book(val rank: Int,
                val title: String,
                val description: String,
                val book_image: String)

