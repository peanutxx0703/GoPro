package com.example.goproapplication.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class TaskViewModel: ViewModel() {
    private val firestoreDb = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    val tasks: MutableState<List<Task>> = mutableStateOf(emptyList())

    init {
        val todotaskCollection = firestoreDb.collection("todotask")
            .orderBy("creation_time", Query.Direction.DESCENDING)

        todotaskCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                Log.e("TaskViewModel", "Exception when querying tasks", exception)
                return@addSnapshotListener
            }
            val taskList = snapshot.toObjects(Task::class.java)
            tasks.value = taskList
        }
    }

    fun deleteTask(id: String) {
        firestoreDb.collection("todotask").document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("TaskViewModel", "DocumentSnapshot successfully deleted!")
                tasks.value = tasks.value.filter { it.id != id }
            }
            .addOnFailureListener { e ->
                Log.w("AnnouncementViewModel", "Error deleting document", e)
            }
    }
}