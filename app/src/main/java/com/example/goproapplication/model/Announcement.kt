package com.example.goproapplication.model

import com.google.firebase.database.PropertyName

data class Announcement(
    var id: String = "",
    var content:String = "",
    @get:PropertyName("creation_time") @set:PropertyName("creation_time") var creationTime: Long = 0,
    var image: String = "",
    var title: String = "",
    var user: User = User()
)