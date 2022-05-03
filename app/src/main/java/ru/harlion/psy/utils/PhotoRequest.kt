package ru.harlion.psy.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.harlion.psy.R
import java.io.File
import java.util.*


private const val PICK_IMAGE = 100
private const val CAMERA_INTENT = 12

fun createNewFile(fragment: Fragment): File {
    val photosDir = File(fragment.requireContext().filesDir, "photos")
    if (!photosDir.exists()) photosDir.mkdir()
    return File(photosDir, UUID.randomUUID().toString())
}

class PhotoRequest(val fragment: Fragment, val file: File) {

    constructor(fragment: Fragment) : this(fragment, createNewFile(fragment))

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {

            file.outputStream().use { outputStream ->
                fragment.requireContext().contentResolver.openInputStream(data!!.data!!)!!
                    .use { inputStream ->
                        inputStream.copyTo(outputStream)
                    }
            }
            return true
        }
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_INTENT) {
            return true
        }

        return false
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )

        try {
            fragment.startActivityForResult(intent, PICK_IMAGE)
        } catch (e: ActivityNotFoundException) {

        }
    }

    private fun openCamera() {
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
        fragment.startActivityForResult(intent, CAMERA_INTENT)
    }

    fun showAlterDialog() {
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
                0 -> openGallery()
                1 -> openCamera()
            }
        }
        builder.show()
    }
}