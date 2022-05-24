package ru.harlion.psy.ui.exercise.child


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.app
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentChildExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.ex_list.ExListFragment
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.utils.PhotoRequest
import ru.harlion.psy.utils.dialogs.EditTextDialog
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.setRoundImage
import java.io.File


class ChildExercizesFragment : BindingFragment<FragmentChildExercizesBinding>(
    FragmentChildExercizesBinding::inflate
) {
    private lateinit var adapterMenu: AdapterMenuExercizes
    lateinit var launcher: ActivityResultLauncher<Intent>
    private var photoRequest: PhotoRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (photoRequest?.onActivityResult(it.data) == true) {
                        binding.childPhoto.setRoundImage(Uri.fromFile(photoRequest!!.file), R.drawable.pic_child_cat)
                        app.user.value = app.user.value?.copy(photoChild = photoRequest?.file?.path ?: "")
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()

        app.user.observe(viewLifecycleOwner, {
            binding.name.text = if(it.nameChild.isNotBlank()) {
                it.nameChild
            } else {
                getString(R.string.name_child)
            }
            binding.progress.progress = it.progressChild
            binding.countProgress.text = "${it.progressChild} %"

            val photoUri = Uri.fromFile(File(it.photoChild))
            try {
                binding.childPhoto.setRoundImage(photoUri,R.drawable.pic_child_cat)
            } catch (e: Exception) {
                binding.childPhoto.setRoundImage(null,R.drawable.pic_child_cat)
            }
        })

        val exercises = listOf(
            MenuEx(getString(R.string.thanks_diary), R.drawable.menu_heart, 2),
            MenuEx(getString(R.string.wish_diary_ex), R.drawable.menu_star, 2),
            MenuEx(getString(R.string.free_writing_ex), R.drawable.menu_freewriting, 4),
            MenuEx(getString(R.string.ideas_diary_ex), R.drawable.menu_idea, 2),
            //        MenuEx(getString(R.string.album_ex), R.drawable.menu_moments, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when (it) {
                0 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.thanks_diary,
                        TypeEx.GRATITUDE_DIARY
                    ), true
                )
                1 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.wish_diary_ex,
                        TypeEx.WISH_DIARY,
                        R.string.active,
                        R.string.done,
                    ), true
                )
                2 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.free_writing_ex,
                        TypeEx.FREE_WRITING
                    ), true
                )
                else -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.ideas_diary_ex,
                        TypeEx.IDEAS_DIARY
                    ), true
                )
//                else -> replaceFragment(
//                    ExListFragment.newInstance(
//                        R.string.album_ex,
//                        TypeEx.HIGHLIGHTS_ALBUM
//                    ), true
//                )
            }
        }
        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
        adapterMenu.items = exercises
    }

    private fun initClick() {

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.info.setOnClickListener {
            replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.child_info,
                    toolbar = R.string.informations,
                    isEx = false
                ), true
            )
        }

        binding.edit.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                val text =  setEditText(getString(R.string.name_child))
                setTitle(getString(R.string.name_child_title))
                setPositiveButton(getString(R.string.save)) {
                    val name = text.findViewById<TextView>(R.id.input_text).text
                    app.user.value = app.user.value?.copy(nameChild = name.toString())
                    binding.name.text = name.toString()
                }
                setAddPhotoButton {
                    if (photoRequest == null) {
                        photoRequest = PhotoRequest(this@ChildExercizesFragment)
                    }
                    photoRequest!!.openGallery(launcher)
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }
    }
}