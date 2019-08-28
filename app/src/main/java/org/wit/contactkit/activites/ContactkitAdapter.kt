package org.wit.contactkit.activites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_contactkit.view.*
import org.wit.contactkit.R
import org.wit.contactkit.helpers.readImageFromPath
import org.wit.contactkit.models.ContactkitModel


interface ContactkitListener {
    fun onContactkitClick(contactkit: ContactkitModel)
}
class ContactkitAdapter constructor(private var contactkits: List<ContactkitModel>,
                                    private val listener: ContactkitListener) : RecyclerView.Adapter<ContactkitAdapter.MainHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_contactkit, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val contactkit = contactkits[holder.adapterPosition]
        holder.bind(contactkit,listener)
    }

    override fun getItemCount(): Int = contactkits.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contactkit: ContactkitModel,listener:ContactkitListener) {
            itemView.contactkitTitle.text = contactkit.title
            itemView.contactkitNumber.text = contactkit.number
            itemView.contactkitNote.text =contactkit.note
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, contactkit.image))
            itemView.setOnClickListener { listener.onContactkitClick(contactkit) }
        }
    }
}