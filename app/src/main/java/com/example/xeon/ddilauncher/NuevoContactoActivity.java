package com.example.xeon.ddilauncher;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NuevoContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        final EditText nombre = (EditText)findViewById(R.id.editText);
        final EditText telefono = (EditText)findViewById(R.id.editText2);
        Button boton = (Button)findViewById(R.id.button);
        Button boton2 = (Button)findViewById(R.id.button2);

        boton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
                int rawContactID = ops.size();

                ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                        .build());
                if(nombre.getText().toString() != null){
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nombre.getText().toString())
                            .build());                }
                if(telefono.getText().toString() != null){
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, telefono.getText().toString())
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                            .build());
                }
                try{
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                    Toast.makeText(getBaseContext(), "El nuevo contacto ha sido añadido", Toast.LENGTH_SHORT).show();
                }catch (RemoteException e) {
                    e.printStackTrace();
                }catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent contacts = new Intent(Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI);
                startActivity(contacts);
            }
        });
    }
}
