package com.example.uncledrew.animationtext;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTextActivity extends AppCompatActivity implements View.OnClickListener{

    List<String> contactsName = new ArrayList<>();
    List<String> contactsNumber = new ArrayList<>();
    List<String> contacts = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    ArrayAdapter<String> adapter;
    private ListView showList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_text);
        Button moveContact = findViewById(R.id.moveContact);
        Button show = findViewById(R.id.show);
        showList = findViewById(R.id.showList);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        showList.setAdapter(adapter);
        moveContact.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.moveContact:
                moveContact();
                Toast.makeText(SQLiteTextActivity.this,"完成！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.show:
                show();
                break;
            default:
        }
    }

    private void show(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("contact",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                contacts.add(name + "\n" + number);
            }while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void moveContact(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{
            readContacts();
            writeContact();
        }

    }

    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsName.add(name);
                    contactsNumber.add(number);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }

    private void writeContact(){
        dbHelper = new MyDatabaseHelper(this,"Contacts.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int i=0;i<contactsNumber.size();i++){
            values.put("name",contactsName.get(i));
            values.put("number",contactsNumber.get(i));
            db.insert("contact",null,values);
            values.clear();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                    writeContact();
                }else {

                }
                break;
            default:
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.anim_out);
    }
}
