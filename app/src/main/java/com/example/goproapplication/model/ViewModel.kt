package com.example.goproapplication.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AnnouncementViewModel : ViewModel() {
    private val firestoreDb = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    val announcements: MutableState<List<Announcement>> = mutableStateOf(emptyList())
    val currentUser: String? = auth.currentUser?.displayName

    init {
        val announcementRef = firestoreDb.collection("announcement")
            .orderBy("creation_time", Query.Direction.DESCENDING)

        announcementRef.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                Log.e("AnnouncementViewModel", "Exception when querying announcements", exception)
                return@addSnapshotListener
            }
            val announcementList = snapshot.toObjects(Announcement::class.java)
            announcements.value = announcementList
        }
    }

    fun deleteAnnouncement(id: String) {
        firestoreDb.collection("announcement").document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("AnnouncementViewModel", "DocumentSnapshot successfully deleted!")
                announcements.value = announcements.value.filter { it.id != id }
            }
            .addOnFailureListener { e ->
                Log.w("AnnouncementViewModel", "Error deleting document", e)
            }
    }
}