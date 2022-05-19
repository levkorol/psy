package ru.harlion.psy.ui.exercise.parent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.AppApplication
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentParentExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.ex_list.ExListFragment
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.utils.PhotoRequest
import ru.harlion.psy.utils.dialogs.EditTextDialog
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.setRoundImage


class ParentExercizesFragment :
    BindingFragment<FragmentParentExercizesBinding>(FragmentParentExercizesBinding::inflate) {

    private lateinit var adapterMenu: AdapterMenuExercizes
    private val app
        get() = requireActivity().application as AppApplication

    lateinit var launcher: ActivityResultLauncher<Intent>
    private var photoRequest: PhotoRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (photoRequest?.onActivityResult(it.data) == true) {
                        binding.parentPhoto.setRoundImage(
                            Uri.fromFile(photoRequest!!.file),
                            R.drawable.pic_parent_cat
                        )
                        //app.user.value?.photoParent = photoRequest?.file?.path ?: ""
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()

        app.user.observe(viewLifecycleOwner, {
            binding.name.text = it.nameParent
        })

        val exercises = listOf(
            MenuEx(getString(R.string.success_diary_ex), R.drawable.menu_trophy, 0),
            MenuEx(getString(R.string.work_with_belief_ex), R.drawable.menu_hands, 2),
            MenuEx(getString(R.string.positive_belief_ex), R.drawable.menu_positive, 0),
            MenuEx(getString(R.string.life_rules_ex), R.drawable.menu_pr, 0),
            MenuEx(getString(R.string.perfect_life_ex), R.drawable.menu_check, 0)
        )

        adapterMenu = AdapterMenuExercizes {
            when (it) {
                0 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.success_diary_ex,
                        TypeEx.SUCCESS_DIARY
                    ), true
                )
                1 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.work_with_belief_ex,
                        TypeEx.WORK_WITH_BELIEFS
                    ), true
                )
                2 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.positive_belief_ex,
                        TypeEx.POSITIVE_BELIEFS
                    ), true
                )
                3 -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.life_rules_ex,
                        TypeEx.LIFE_RULES
                    ), true
                )
                else -> replaceFragment(
                    ExListFragment.newInstance(
                        R.string.perfect_life_ex,
                        TypeEx.PERFECT_LIFE
                    ), true
                )
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
                    oneTitle = R.string.parent_info,
                    toolbar = R.string.informations,
                    isEx = false
                ), true
            )
        }

        binding.edit.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                val text = setEditText(getString(R.string.name_parent))
                setTitle(getString(R.string.name_parent_title))
                setPositiveButton(getString(R.string.save)) {
                    val name = text.findViewById<TextView>(R.id.input_text).text
                    //  app.user.value?.name = name.toString()
                    binding.name.text = name.toString()
                }
                setAddPhotoButton {
                    if (photoRequest == null) {
                        photoRequest = PhotoRequest(this@ParentExercizesFragment)
                    }
                    photoRequest!!.openGallery(launcher)
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }
    }
}