package com.lebonplant.ui.forum.post

class Post (val id: Int, val poster : String, val header: String, val body: String){
    var embbed: String = ""

    fun embbedInit(content : String)
    {
        this.embbed = content;
    }
}