import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.goproapplication.data.profile.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileViewModel : ViewModel() {
    private val firestoreDb = FirebaseFirestore.getInstance()
    val userProfile: MutableState<UserProfile?> = mutableStateOf(null)

    init {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserUid != null) {
            val userRef = firestoreDb.collection("users").document(currentUserUid)
            userRef.addSnapshotListener { snapshot, exception ->
                if (exception != null || snapshot == null) {
                    Log.e(TAG, "Exception when querying user profile", exception)
                    return@addSnapshotListener
                }
                val userProfileData = snapshot.toObject(UserProfile::class.java)
                userProfile.value = userProfileData
            }
        } else {
            Log.e(TAG, "Current user is null")
        }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}