package com.xsl.lerist.llibrarys.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.socks.library.KLog;
import com.xsl.lerist.llibrarys.model.Contacts;

import java.util.ArrayList;

/**
 * Created by Lerist on 2016/4/13, 0013.
 */
public class ContactsUtils {
    public static ArrayList<Contacts> getContacts(Context context, Uri uri) {
        KLog.i(uri.toString());
        ArrayList<Contacts> contactses = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
//        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Contacts contacts = new Contacts();
            //联系人名字
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            KLog.i(name);
            String contactsId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phones = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactsId,
                    null,
                    null);
            contacts.setName(name);
//            phones.moveToFirst();
            while (phones.moveToNext()) {
                String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phone = phone.replaceAll("-", "").replaceAll(" ", "");
                contacts.addPhone(phone);
                KLog.i(phone);
            }

            contactses.add(contacts);
        }
        return contactses;
    }
}
