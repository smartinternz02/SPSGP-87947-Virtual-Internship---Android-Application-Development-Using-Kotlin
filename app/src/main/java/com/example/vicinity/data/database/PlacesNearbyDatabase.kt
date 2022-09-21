package com.example.vicinity.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vicinity.data.database.converter.ConverterListString
import com.example.vicinity.data.database.dao.FavoritePlacesNearbyDao
import com.example.vicinity.models.Places

@Database(entities = [Places::class], version = 1, exportSchema = false)
@TypeConverters(ConverterListString::class)
abstract class PlacesNearbyDatabase: RoomDatabase() {

    abstract fun favoritePlacesNearbyDao(): FavoritePlacesNearbyDao

}