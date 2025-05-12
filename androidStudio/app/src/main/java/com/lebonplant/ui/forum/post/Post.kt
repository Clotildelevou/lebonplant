package com.lebonplant.ui.forum.post

class Post (id: Int, poster : String, header: String, body: String){
    var embbed: String = ""

    fun embbedInit(content : String)
    {
        this.embbed = content;
    }
}