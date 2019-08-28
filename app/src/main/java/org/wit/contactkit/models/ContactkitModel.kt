package org.wit.contactkit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactkitModel (var id: Long = 0,
                            var title: String = "",
                            var number: String = "",
                            var note : String ="",
                            var image: String = ""): Parcelable