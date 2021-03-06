package ru.harlion.psy.ui.exercise.child.edit.highlights_album


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_edit_album.*
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditAlbumBinding
import ru.harlion.psy.utils.PhotoRequest
import ru.harlion.psy.utils.dateToString
import ru.harlion.psy.utils.dialogs.DialogCalendar
import java.io.File


class EditAlbumFragment :
    BindingFragment<FragmentEditAlbumBinding>(FragmentEditAlbumBinding::inflate) {

    private val viewModel: EditAlbumViewModel by viewModels()
    private var id = 0L
    private var photoRequest: PhotoRequest? = null
    private var date = 0L
    lateinit var launcher: ActivityResultLauncher<Intent>
    private var isPhotoChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("calendarDate") { _, bundle ->
            date = bundle.getLong("epochMillis")
            binding.date.text = dateToString(date)
        }

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (photoRequest?.onActivityResult(it.data) == true) {
                        binding.photoView.setImageURI(Uri.fromFile(photoRequest!!.file))
                        isPhotoChanged = true
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = requireArguments().getLong("ID")
        viewModel.getExById(id)

        binding.questionOne.title = getString(R.string.event)
        binding.questionTwo.title = getString(R.string.ex_album_title_two)

        observe()
        initClicks()
    }

    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner, {
            if (id > 0) {
                binding.questionOne.setText(it.fieldOne)
                binding.questionTwo.setText(it.fieldOne)
                binding.date.text = dateToString(it.date)
                val photoUri = Uri.parse(it.image)
                try {
                    photoView.setImageURI(photoUri)
                } catch (e: Exception) {
                    photoView.setImageURI(null)
                }
            }
        })
    }

    private fun showAlterDialog() {
        if (photoRequest == null) {
            photoRequest = PhotoRequest(this)
        }
        photoRequest!!.showAlterDialog(launcher)
    }

    private fun initClicks() {
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.photo.setOnClickListener { showAlterDialog() }
        binding.date.setOnClickListener { DialogCalendar().show(parentFragmentManager, null) }
        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.update(
                    binding.questionOne.text.toString(),
                    binding.questionTwo.text.toString(),
                    if(isPhotoChanged) photoRequest?.file?.path ?: "" else null,
                    date,
                )
            } else {
                viewModel.add(
                    binding.questionOne.text.toString(),
                    binding.questionTwo.text.toString(),
                    photoRequest?.file?.path ?: "",
                    date,
                )
            }
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(id: Long) = EditAlbumFragment().apply {
            arguments = Bundle().apply {
                putLong("ID", id)
            }
        }
    }
}