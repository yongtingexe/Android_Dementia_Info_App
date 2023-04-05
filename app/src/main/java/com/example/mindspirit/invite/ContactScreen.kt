package com.example.mindspirit.invite

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun ContactScreen() {
    val context = LocalContext.current

    val contactIntent = Intent(Intent.ACTION_PICK).apply {
        type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
    }

    val launchContactForResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
            val contactUri: Uri? = result?.data?.data

            val projection: Array<String> = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )

            contactUri?.let {
                context.contentResolver.query(it, projection, null, null, null).use { cursor ->
                    if (cursor!!.moveToFirst()) {
                        val numberIndex =
                            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val number = cursor.getString(numberIndex)

                        val nameIndex =
                            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                        val name = cursor.getString(nameIndex)

                        Toast.makeText(
                            context,
                            "Number is $number & Name is $name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    val launchContactPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launchContactForResult.launch(contactIntent)
        } else {
            Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT)
                .show()
        }

    }

    Column(modifier = Modifier
            .fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            content = { Text("IMPORT FROM CONTACT") },
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_CONTACTS
                    ) -> {
                        launchContactForResult.launch(contactIntent)
                    }
                    else -> {
                        launchContactPermission.launch(Manifest.permission.READ_CONTACTS)
                    }
                }
            }
        )
    }
}