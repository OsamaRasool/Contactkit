package org.wit.contactkit.models

interface ContactkitStore {
    fun findAll(): List<ContactkitModel>
    fun create(contactkit: ContactkitModel)
    fun update(contactkit: ContactkitModel)
    fun delete(contactkit: ContactkitModel)
}