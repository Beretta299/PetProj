package com.karas.petproj.network

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.karas.petproj.network.model.UserNetworkData
import com.karas.petproj.network.utils.toData
import com.karas.petproj.network.utils.toModel
import kotlinx.coroutines.tasks.await

interface FireStoreRepository {

    suspend fun readData() :List<UserNetworkData>

    suspend fun addUser(userNetworkData: UserNetworkData)
}


class FireStoreImplementation: FireStoreRepository {
    var db: FirebaseFirestore = Firebase.firestore

    private val CollectionName = "users"

    override suspend fun readData() : List<UserNetworkData> {
        val users = db.collection(CollectionName)
            .get()
            .await()
            .map {
                it.data.toModel()
            }

        return users
    }

    override suspend fun addUser(userNetworkData: UserNetworkData) {
        val data = userNetworkData.toData()

        try {
            db.collection(CollectionName)
                .add(data)
                .await()
        } catch (e:Exception) {
            println("Rythm $e")
        }
    }
}