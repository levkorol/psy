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
import ru.harlion.psy.data.Repository
import ru.harlion.psy.databinding.FragmentParentExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.ex_list.ExListFragment
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.utils.PhotoRequest
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.dialogs.EditTextDialog
import ru.harlion.psy.utils.dialogs.InfoDialog
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.setRoundImage
import java.io.File


class ParentExercizesFragment :
    BindingFragment<FragmentParentExercizesBinding>(FragmentParentExercizesBinding::inflate) {

    private lateinit var prefs: Prefs
    private lateinit var adapterMenu: AdapterMenuExercizes
    private val app
        get() = requireActivity().application as AppApplication

    lateinit var launcher: ActivityResultLauncher<Intent>
    private var photoRequest: PhotoRequest? = null
    private var repo = Repository.get()

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
                        app.user.value =
                            app.user.value?.copy(photoParent = photoRequest?.file?.path ?: "")
                    }
                }
            }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())

        initClick()

        app.user.observe(viewLifecycleOwner) {
            binding.name.text = if (it.nameParent.isNotBlank()) {
                it.nameParent
            } else {
                getString(R.string.name_parent)
            }
            binding.progress.progress = it.progressParent
            binding.countProgress.text = "${it.progressParent} %"
            val photoUri = Uri.fromFile(File(it.photoParent))
            try {
                binding.parentPhoto.setRoundImage(photoUri, R.drawable.pic_parent_cat)
            } catch (e: Exception) {
                binding.parentPhoto.setRoundImage(null, R.drawable.pic_parent_cat)
            }
        }

        adapterMenu = AdapterMenuExercizes {
            if (prefs.isPremiumBilling || it == 0 || it == 1) {
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
            } else {
                InfoDialog.newInstance(isPremium = true).show(parentFragmentManager, null)
            }
        }

        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }

        val exercises = listOf(
            MenuEx(
                getString(R.string.success_diary_ex),
                R.drawable.menu_trophy,
                exSize(TypeEx.SUCCESS_DIARY),
                false
            ),
            MenuEx(
                getString(R.string.work_with_belief_ex),
                R.drawable.menu_hands,
                exSize(TypeEx.WORK_WITH_BELIEFS),
                false
            ),
            MenuEx(
                getString(R.string.positive_belief_ex),
                R.drawable.menu_positive,
                exSize(TypeEx.POSITIVE_BELIEFS),
                !prefs.isPremiumBilling
            ),
            MenuEx(
                getString(R.string.life_rules_ex),
                R.drawable.menu_pr,
                exSize(TypeEx.LIFE_RULES),
                !prefs.isPremiumBilling
            ),
            MenuEx(
                getString(R.string.perfect_life_ex),
                R.drawable.menu_check,
                exSize(TypeEx.PERFECT_LIFE),
                !prefs.isPremiumBilling
            )
        )

        adapterMenu.items = exercises


    }

    private fun exSize(typeEx: TypeEx) = repo.getListEx(typeEx).size

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
                    app.user.value = app.user.value?.copy(nameParent = name.toString())
                }
                setAddPhotoButton {
                    setPhoto()
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }

        binding.parentPhoto.setOnClickListener {
            setPhoto()
        }

    }

    private fun setPhoto() {
        if (photoRequest == null) {
            photoRequest = PhotoRequest(this@ParentExercizesFragment)
        }
        photoRequest!!.openGallery(launcher)
    }
}