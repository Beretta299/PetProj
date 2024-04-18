package com.karas.petproj.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.karas.petproj.db.data.UserEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class DBTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var scheduler: TestCoroutineScheduler
    lateinit var testDispatcher: TestDispatcher
    lateinit var testDb: AppDatabase

    @Before
    fun init() {
        hiltRule.inject()
        scheduler =TestCoroutineScheduler()
        testDispatcher = StandardTestDispatcher(scheduler)

        testDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()

        //Room has it's own dispatcher,
        // so thus we will not be able to check everything properly without setting custom dispatchers
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun userAdded() = runTest(testDispatcher) {

        val list = listOf(UserEntity(125, "Askold", "", ""), UserEntity(126, "Vitold", "", ""))
        val b = async(testDispatcher) {
            testDb.userDao().registerUser(UserEntity(125, "Askold", "", ""))
        }
        val a = launch(testDispatcher) {
            testDb.userDao().registerUser(UserEntity(126, "Vitold", "", ""))
        }

        b.await()

        scheduler.advanceUntilIdle()

        assertEquals(list, testDb.userDao().getUserList())
    }
}