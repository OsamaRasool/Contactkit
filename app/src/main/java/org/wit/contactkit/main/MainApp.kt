package org.wit.contactkit.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.contactkit.models.ContactkitJSONStore
import org.wit.contactkit.models.ContactkitMemStore
import org.wit.contactkit.models.ContactkitModel
import org.wit.contactkit.models.ContactkitStore

class MainApp : Application(),AnkoLogger{

  lateinit var contactkits :ContactkitStore

    override fun onCreate() {
        super.onCreate()
         contactkits = ContactkitJSONStore(applicationContext)
        info ( "Contactkit started")

    }
}