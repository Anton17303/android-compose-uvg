package com.zezzi.eventzezziapp.data.repository


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.zezzi.eventzezziapp.data.networking.response.Meal
import com.zezzi.eventzezziapp.data.networking.response.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
class MealsRepository() {
    val db = Firebase.firestore
    suspend fun getMealsBy(): MealsResponse {
        return withContext(Dispatchers.IO) {
            val documents = db.collection("meals")
                .get().await().documents
            MealsResponse(
                documents.map {
                    Meal(
                        it.id,
                        it.getString("name") ?: "",
                        it.getString("imageUrl") ?: "",
                        it.getString("description") ?: ""

                        )
                }
            )

        }
    }
}
