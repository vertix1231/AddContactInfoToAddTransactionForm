package com.test.kerja.addcontactinfotoaddtransactionform

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var name :String,
    var number:String,
//    var id:Int,
) : Parcelable
