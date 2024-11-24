package com.example.productexploreapp.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.example.productexploreapp.data.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null


        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                try {

                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "product_database"
                    )
                        .build()

                    INSTANCE = instance
                    instance

                } catch (e: Exception) {
                    Log.e("Database", "Error setting up database: ${e.message}")
                    throw e
                }
            }
        }
    }
}

