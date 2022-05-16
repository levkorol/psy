package ru.harlion.psy.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.harlion.psy.R
import java.io.File
import java.util.*


fun createNewFile(fragment: Fragment): File {
    val photosDir = File(fragment.requireContext().filesDir, "photos")
    if (!photosDir.exists()) photosDir.mkdir()
    return File(photosDir, UUID.randomUUID().toString())
}

class PhotoRequest(val fragment: Fragment, val file: File) {

    constructor(fragment: Fragment) : this(fragment, createNewFile(fragment))

    fun onActivityResult( data: Intent?): Boolean {

            file.outputStream().use { outputStream ->
                fragment.requireContext().contentResolver.openInputStream(data!!.data!!)!!
                    .use { inputStream ->
                        inputStream.copyTo(outputStream)
                    }
            }
            return true

    }

    fun openGallery(launcher : ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )

        try {
            launcher.launch(intent)
        } catch (e: ActivityNotFoundException) {

        }
    }

    private fun openCamera(launcher : ActivityResultLauncher<Intent>) {
        val photoUri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Uri.fromFile(file)
        } else {
            FileProvider.getUriForFile(
                fragment.requireContext(),
                fragment.requireContext().packageName + ".provider",
                file
            )
        }

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        launcher.launch(intent)
    }

    fun showAlterDialog(launcher : ActivityResultLauncher<Intent>) {
        val builder = MaterialAlertDialogBuilder(fragment.requireContext())
        builder.setTitle(fragment.requireContext().getString(R.string.question_photo_load))
        val pictureDialogItems = arrayOf(
            fragment.requireContext().getString(R.string.gallery),
         fragment.requireContext().getString(
                R.string.camera
            )
        )
        builder.setItems(
            pictureDialogItems
        ) { _, which ->
            when (which) {
                0 -> openGallery(launcher)
                1 -> openCamera(launcher)
            }
        }
        builder.show()
    }
}