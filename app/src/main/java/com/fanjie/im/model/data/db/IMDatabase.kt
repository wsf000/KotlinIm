package com.itheima.im.data.db

import com.fanjie.im.ui.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class IMDatabase{

    companion object {
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact: Contact){
        databaseHelper.use {
            //SQLiteDatabase的扩展方法
            insert(ContactTable.NAME, *contact.map.toVarargArray())
        }
    }

    fun getAllContacts(): List<Contact> = databaseHelper.use {
            select(ContactTable.NAME).parseList(object : MapRowParser<Contact>{
                override fun parseRow(columns: Map<String, Any?>): Contact = Contact(columns.toMutableMap())
            })
        }

    fun deleteAllContacts(){
        databaseHelper.use {
            delete(ContactTable.NAME, null, null)
        }
    }


}