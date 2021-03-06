package com.test.kerja.addcontactinfotoaddtransactionform

//import android.R
import android.Manifest
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.test.kerja.addcontactinfotoaddtransactionform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()
    private var dataContact :ArrayList<Cursor> =  ArrayList()
    private var dataContactModelClassList :ArrayList<Contact> =  ArrayList()
    private lateinit var adapterContact :SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){ Manifest.permission.READ_CONTACTS},120)

        }else{
            readContact()
        }
    }
//    private fun dbContact():ArrayList<Contact>{
//        for (i in data.indices){
//            val contact = Contact(
//
//            )
//
//        }
//    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==120&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readContact()
        }
    }
    private fun readContact() {
        val fromm = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER).toTypedArray()

        val too = intArrayOf(android.R.id.text1,android.R.id.text2)

        val resultlistContact = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val resultCursorToString = resultlistContact?.getColumnName(0)?.get(3).toString()
//        if(resultlistContact?.moveToNext()!!){
//            Toast.makeText(applicationContext,resultlistContact.getString(2), Toast.LENGTH_LONG).show()
//        }
        adapterContact = SimpleCursorAdapter(this,
            android.R.layout.simple_expandable_list_item_2,resultlistContact,fromm,too,0)

        binding.listviewOne.adapter = adapterContact
        adapterContact.notifyDataSetChanged()
//        binding.listviewOne.onItemClickListener

        dataContactModelClassList.clear()
        resultlistContact?.moveToPosition(-1)
        while (resultlistContact!=null&&resultlistContact.moveToNext()){
//            dataContact.add(resultlistContact)
            val contact = Contact(
                name = resultlistContact.getString(0),
                number = resultlistContact.getString(1),
            )


            dataContactModelClassList.add(contact)
            Log.d("i-tag","panjang array biasa adalah : ${dataContactModelClassList.size}")
            println(dataContactModelClassList)
            println("------------------------------------------------------------------------------------------1")
//            for (i in dataContact){
//
//                val contact = Contact(
//                    dataContact.get(i).getString(0),
//                    dataContact.get(i).getString(1),
//                    0
//                )
//            }
        }
        Log.d("i-tag","tag size biasa: ${dataContactModelClassList.size}")
        binding.listviewOne.setOnItemClickListener { adapterView, view, i, l ->
            val cursorChoose = dataContactModelClassList.get(i)
//            Toast.makeText(applicationContext,resultCursorToString, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext,cursorChoose.name+"\n"+cursorChoose.number, Toast.LENGTH_LONG).show()
            val detailContact = Intent(this,DetailContactActivity::class.java).apply {
                putExtra(DetailContactActivity.IN_USERNAME,cursorChoose.name)
                putExtra(DetailContactActivity.IN_NUMBER,cursorChoose .number)
            }
            startActivity(detailContact)
            Toast.makeText(applicationContext,"nilai i adalah $i dan nilai l adalah $l", Toast.LENGTH_LONG).show()
            Log.d("i-tag","nilai i adalah : $i")
            Log.d("i-tag","nilai l adalah : $l")
            Log.d("i-tag","panjang array biasa adalah : ${dataContactModelClassList.size}")
            println(dataContactModelClassList)
            println("------------------------------------------------------------------------------------------2")
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {

                    var resultlistContact = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,cols,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE ?",Array(1){"%$p0%"},ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    println("------------------------------------------------------------------------------------------------3 cursor query")
                    println(resultlistContact?.count)
                    println(resultlistContact?.count)
                    adapterContact.changeCursor(resultlistContact)
                    adapterContact.notifyDataSetChanged()
                    dataContactModelClassList.clear()
                    resultlistContact?.moveToPosition(-1)
                    while (resultlistContact!=null&&resultlistContact.moveToNext()){
                        val contact = Contact(
                            name = resultlistContact.getString(0),
                            number = resultlistContact.getString(1)
                        )

                        dataContactModelClassList.add(contact)
//                        val contact = Contact(resultlistContact.getColumnName())
//                    dataContact.add(resultlistContact)

                        Log.d("i-tag","panjang array while query adalah: ${dataContactModelClassList.size}")
                        println(dataContactModelClassList)
                        println("------------------------------------------------------------------------------------------3")
                    }

                }
                return true
            }

        })

        return true

    }


}