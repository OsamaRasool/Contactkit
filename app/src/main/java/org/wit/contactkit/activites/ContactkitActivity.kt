package org.wit.contactkit.activites

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_contactkit.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.contactkit.R
import org.wit.contactkit.helpers.readImage
import org.wit.contactkit.helpers.readImageFromPath
import org.wit.contactkit.helpers.showImagePicker
import org.wit.contactkit.main.MainApp
import org.wit.contactkit.models.ContactkitModel

class ContactkitActivity : AppCompatActivity() ,AnkoLogger{

    var contactkit = ContactkitModel()
    lateinit var app: MainApp
    var edit = false
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactkit)
         app=application as  MainApp

        if (intent.hasExtra("contactkit_edit")) {
            edit=true
            contactkit = intent.extras.getParcelable<ContactkitModel>("contactkit_edit")
            contactkitTitle.setText(contactkit.title)
            contactkitNumber.setText(contactkit.number)
            contactkitNote.setText(contactkit.note)
            contactkitImage.setImageBitmap(readImageFromPath(this, contactkit.image))
            btnAdd.setText(R.string.button_saveContactkit)
            chooseImage.setText(R.string.button_changeImage)
        }

        btnAdd.setOnClickListener() {
             contactkit.title = contactkitTitle.text.toString()
             contactkit.number = contactkitNumber.text. toString()
             contactkit.note =contactkitNote.text.toString()
            if (contactkit.title.isNotEmpty()) {
                if (edit){
                    app.contactkits.update(contactkit.copy())
                } else {
                    app.contactkits.create(contactkit.copy())
                }
                info("add Button Pressed: ${contactkit.title}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else{
                toast(R.string.message_enter_title)
            }
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
        
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_contactkit, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.contactkits.delete(contactkit)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    contactkit.image = data.getData().toString()
                    contactkitImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }
}