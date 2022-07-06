package ru.harlion.psy.ui.exercise.adult


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.R
import ru.harlion.psy.app
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.data.Repository
import ru.harlion.psy.databinding.FragmentAdultExercizesBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.AdapterMenuExercizes
import ru.harlion.psy.ui.exercise.base.MenuEx
import ru.harlion.psy.ui.exercise.base.ex_list.ExListFragment
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.utils.PhotoRequest
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.dialogs.EditTextDialog
import ru.harlion.psy.utils.dialogs.InfoDialog
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.setRoundImage
import java.io.File


class AdultExercizesFragment :
    BindingFragment<FragmentAdultExercizesBinding>(FragmentAdultExercizesBinding::inflate) {

    private lateinit var adapterMenu: AdapterMenuExercizes
    private lateinit var prefs: Prefs
    lateinit var launcher: ActivityResultLauncher<Intent>
    private var photoRequest: PhotoRequest? = null
    private val repo = Repository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (photoRequest?.onActivityResult(it.data) == true) {
                        binding.adultPhoto.setRoundImage(
                            Uri.fromFile(photoRequest!!.file),
                            R.drawable.pic_adulte_cat
                        )
                        app.user.value =
                            app.user.value?.copy(photoAdult = photoRequest?.file?.path ?: "")
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())

        initClick()

        app.user.observe(viewLifecycleOwner) {
            binding.name.text = if (it.nameAdult.isNotBlank()) {
                it.nameAdult
            } else {
                getString(R.string.name_adult)
            }
            binding.progress.progress = it.progressAdult
            binding.countProcent.text = "${it.progressAdult} %"
            val photoUri = Uri.fromFile(File(it.photoAdult))
            try {
                binding.adultPhoto.setRoundImage(photoUri, R.drawable.pic_adulte_cat)
            } catch (e: Exception) {
                binding.adultPhoto.setRoundImage(null, R.drawable.pic_adulte_cat)
            }
        }

        val exercises = listOf(
            MenuEx(
                getString(R.string.self_ex),
                R.drawable.menu_like,
                exSize(TypeEx.SELF_ESTEEM),
                false
            ),
            MenuEx(
                getString(R.string.fail_diary_ex),
                R.drawable.menu_sad,
                count = exSize(TypeEx.FAIL_DIARY),
                false
            ),
            MenuEx(
                getString(R.string.do_love_self_ex),
                R.drawable.menu_self_love,
                count =exSize(TypeEx.ACTS_SELF_LOVE),
                !prefs.isPremiumBilling
            ),
            MenuEx(
                getString(R.string.my_emergency_ex),
                R.drawable.menu_life_preserver,
                exSize(TypeEx.MY_AMBULANCE),
                !prefs.isPremiumBilling
            ),
            MenuEx(
                getString(R.string.ex_mindfullness),
                R.drawable.menu_sun,
                 null,
                isBlock = true,
                about_ex = "Упражнение в разработке"
            )
        )

        adapterMenu = AdapterMenuExercizes {
            if (prefs.isPremiumBilling || it == 0 || it == 1 || it == 4) {
                when (it) {
                    0 -> replaceFragment(
                        ExListFragment.newInstance(
                            R.string.self_ex,
                            TypeEx.SELF_ESTEEM,
                        ), true
                    )
                    1 -> replaceFragment(
                        ExListFragment.newInstance(
                            R.string.fail_diary_ex,
                            TypeEx.FAIL_DIARY
                        ), true
                    )
                    2 -> replaceFragment(
                        ExListFragment.newInstance(
                            R.string.do_love_self_ex,
                            TypeEx.ACTS_SELF_LOVE
                        ), true
                    )
                    3 -> replaceFragment(
                        ExListFragment.newInstance(
                            R.string.my_emergency_ex,
                            TypeEx.MY_AMBULANCE
                        ), true
                    )
                    else -> Snackbar.make(
                        binding.root,
                        "Будет доступна в следующих версиях",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                InfoDialog.newInstance(isPremium = true).show(parentFragmentManager, null)
            }
        }

        binding.menuRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterMenu
        }
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
                    oneTitle = R.string.adult_info,
                    toolbar = R.string.informations,
                    isEx = false
                ), true
            )
        }

        binding.edit.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                val text = setEditText(getString(R.string.name_adult))
                setTitle(getString(R.string.name_adult_title))
                setPositiveButton(getString(R.string.save)) {
                    val name = text.findViewById<TextView>(R.id.input_text).text
                    app.user.value = app.user.value?.copy(nameAdult = name.toString())
                }
                setAddPhotoButton {
                    if (photoRequest == null) {
                        photoRequest = PhotoRequest(this@AdultExercizesFragment)
                    }
                    photoRequest!!.openGallery(launcher)
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }
    }
}