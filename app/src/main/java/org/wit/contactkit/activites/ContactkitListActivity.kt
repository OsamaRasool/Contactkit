package org.wit.contactkit.activites


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import org.wit.contactkit.R
import kotlinx.android.synthetic.main.activity_contactkit_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.contactkit.main.MainApp
import org.wit.contactkit.models.ContactkitModel



class ContactkitListActivity:AppCompatActivity(),ContactkitListener{

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactkit_list)
        app = application as MainApp

//layout and populate for display
    val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ContactkitAdapter(app.contactkits.findAll(), this)
        loadContactkits()

        //enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
}


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<ContactkitActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContactkitClick(contactkit: ContactkitModel) {
        startActivityForResult(intentFor<ContactkitActivity>().putExtra("contactkit_edit",contactkit), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_contactkit_list.xml
      loadContactkits()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadContactkits() {
        showContactkits(app.contactkits.findAll())
    }


    fun showContactkits (contactkits: List<ContactkitModel>) {
        recyclerView.adapter = ContactkitAdapter(contactkits, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


}



