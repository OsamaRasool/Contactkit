package org.wit.contactkit.models

import android.content.Context
import org.jetbrains.anko.AnkoLogger
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*
import org.wit.contactkit.helpers.*

val JSON_FILE = "contactkits.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ContactkitModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ContactkitJSONStore : ContactkitStore, AnkoLogger {

    val context: Context
    var contactkits = mutableListOf<ContactkitModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ContactkitModel> {
        return contactkits
    }

    override fun create(contactkit: ContactkitModel) {
        contactkit.id = generateRandomId()
        contactkits.add(contactkit)
        serialize()
    }


    override fun update(contactkit: ContactkitModel) {
        val contactkitsList = findAll() as ArrayList<ContactkitModel>
        var foundContactkit: ContactkitModel? = contactkitsList.find { p -> p.id == contactkit.id }
        if (foundContactkit != null) {
            foundContactkit.title = contactkit.title
            foundContactkit.number = contactkit.number
            foundContactkit.note = contactkit.note
            foundContactkit.image = contactkit.image

        }
        serialize()
    }



    private fun serialize() {
        val jsonString = gsonBuilder.toJson(contactkits, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        contactkits = Gson().fromJson(jsonString, listType)
    }

    override fun delete(contactkit: ContactkitModel) {
        contactkits.remove(contactkit)
        serialize()
    }
}