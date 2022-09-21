package com.example.vicinity.models

import com.google.gson.annotations.Expose
import java.io.Serializable

class Routes(

    @Expose
    var legs: List<Legs>? = ArrayList()

): Serializable