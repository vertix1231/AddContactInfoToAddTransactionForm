package com.test.kerja.addcontactinfotoaddtransactionform

import android.content.ContentResolver
import android.provider.ContactsContract

class MyContactsDataSource(private val contentResolver: ContentResolver) {
    fun fetchContacts(): List<Contact> {
        var cols = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        val result: MutableList<Contact> = mutableListOf()

        val resultlistContact = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        resultlistContact?.let {
            resultlistContact.moveToFirst()
            while (!resultlistContact.isAfterLast) {
                result.add(
                    Contact(
                        resultlistContact.getString(0),
                        resultlistContact.getString(1)
                    )
                ) //add the item
                resultlistContact.moveToNext()
            }
            resultlistContact.close()
        }

        return result.toList()
    }
}