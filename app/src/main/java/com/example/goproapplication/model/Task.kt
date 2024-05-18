package com.example.goproapplication.model

import com.google.firebase.firestore.PropertyName

data class Task(
    var id: String ="",
    var title: String = "",
    var content: String = "",
    @get:PropertyName("creation_time") @set:PropertyName("creation_time")var creationTime: Long = 0,
    var user: User = User()
)