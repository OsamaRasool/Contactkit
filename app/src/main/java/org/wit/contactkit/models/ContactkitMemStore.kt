package org.wit.contactkit.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ContactkitMemStore : ContactkitStore, AnkoLogger {

    val contactkits = ArrayList<ContactkitModel>()

    override fun findAll(): List<ContactkitModel> {
        return contactkits
    }

    override fun create(contactkit: ContactkitModel) {
        contactkit.id = getId()
        contactkits.add(contactkit)
        logAll()
    }

    override fun delete(contactkit: ContactkitModel) {
        contactkits.remove(contactkit)
    }

    override  fun update(contactkit: ContactkitModel) {
        var foundContactkit: ContactkitModel? = contactkits.find { p -> p.id == contactkit.id }
        if (foundContactkit != null) {
            foundContactkit.title = contactkit.title
            foundContactkit.number = contactkit.number
            foundContactkit.note= contactkit.note
            foundContactkit.image = contactkit.image
            logAll()
        }
    }

    fun logAll() {
        contactkits.forEach { info("${it}") }
    }
}