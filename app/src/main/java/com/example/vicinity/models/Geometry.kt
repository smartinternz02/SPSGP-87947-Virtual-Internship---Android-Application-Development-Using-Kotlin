package com.example.vicinity.models

import androidx.room.TypeConverters
import com.example.vicinity.data.database.converter.ConverterLocation
import java.io.Serializable

@TypeConverters(ConverterLocation::class)
class Geometry(

    var location: Location?

): Serializable