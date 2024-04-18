package com.karas.petproj.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.karas.petproj.db.data.SwipeAbleUser
import com.karas.petproj.navigation.NavigationItem
import com.karas.petproj.repository.YouTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val youTRepository: YouTRepository) : ViewModel() {
    var userLoggedState = mutableStateOf("")
    var userSwipedImage = mutableStateListOf(SwipeAbleUser(null, "John DOE","https://i.pinimg.com/236x/c2/fc/9d/c2fc9d585f744fdc86993f2d062848b1.jpg"),SwipeAbleUser(null, "Doe Boe","https://deepai.org/static/images/cyberpunkdolphin.png"), SwipeAbleUser(null, "Browser","https://pixlr.com/images/index/ai-image-generator-two.webp"))

    var tempRandomImages = mutableStateListOf("https://cdn.motor1.com/images/mgl/E6PLBN/s1/future-cars-alfa-romeo-giulia-quadrifoglio-ev.jpg",
        "https://cdn.motor1.com/images/mgl/zx9lN0/s1/future-cars-alfa-romeo-sports-car.jpg",
        "https://cdn.motor1.com/images/mgl/6ZzXge/s1/future-cars-aston-martin-valhalla.jpg",
        "https://cdn.motor1.com/images/mgl/vxKNX6/s1/future-cars-aston-martin-vanquish.jpg",
        "https://cdn.motor1.com/images/mgl/jl9NK7/s1/future-cars-audi-a6-e-tron.jpg",
        "https://cdn.motor1.com/images/mgl/ZnmOer/s1/future-cars-bentley-ev.jpg",
        "https://cdn.motor1.com/images/mgl/G3Bjk1/s1/future-cars-bmw-m2-cs.jpg",
        "https://cdn.motor1.com/images/mgl/Rq9Lor/s1/future-cars-bmw-neue-klasse-ev.jpg",
        "https://cdn.motor1.com/images/mgl/Vzn9J9/s1/future-cars-chevrolet-corvette-zr1-zora.jpg")

    suspend fun getStartDestination() {
        youTRepository.hasUserInSystem()
        userLoggedState.value = if(false) NavigationItem.Login.route else NavigationItem.Main.route
    }


    fun onSwipeLeft() {
        println("SwipeLeft Was called")

        userSwipedImage.removeLast()
        userSwipedImage.add(0, SwipeAbleUser(null, "BO ${System.currentTimeMillis()}", tempRandomImages.random()))
    }

    fun onSwipeRight() {
        println("SwipeRight Was called")

    //        userSwipedColor.add(userSwipedColor.size, Color.Magenta)
    }


    fun updateSwipeAbleItem() {
        val userSwiped = userSwipedImage.first()
//        userSwiped.swipedState = !userSwiped.swipedState
//        userSwipedImage[0] = userSwiped
    }
}