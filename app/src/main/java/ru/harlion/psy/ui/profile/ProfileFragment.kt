package ru.harlion.psy.ui.profile


import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.fragment_edit_album.*
import ru.harlion.psy.AppApplication
import ru.harlion.psy.R
import ru.harlion.psy.app
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentProfileBinding
import ru.harlion.psy.ui.exercise.base.instructions.ExInstructionsFragment
import ru.harlion.psy.ui.main.diary_emotions.DiaryEmotionFragment
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment
import ru.harlion.psy.ui.profile.on_boarding.TeamPsyFragment
import ru.harlion.psy.ui.profile.pincode.PinCodeFragment
import ru.harlion.psy.ui.profile.pincode.SetPinCodeFragment
import ru.harlion.psy.ui.profile.premium.PremiumFragment
import ru.harlion.psy.ui.profile.test.TestFragment
import ru.harlion.psy.ui.profile.widgets.SetWidgetFragment
import ru.harlion.psy.utils.PhotoRequest
import ru.harlion.psy.utils.dialogs.EditTextDialog
import ru.harlion.psy.utils.replaceFragment
import ru.harlion.psy.utils.setRoundImage
import java.io.File


class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    lateinit var launcher: ActivityResultLauncher<Intent>
    private var photoRequest: PhotoRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (photoRequest?.onActivityResult(it.data) == true) {
                        binding.photoProfile.setRoundImage(Uri.fromFile(photoRequest!!.file), R.drawable.ic_profile)
                        app.user.value = app.user.value?.copy(photoMain = photoRequest?.file?.path ?: "")
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()

        app.user.observe(viewLifecycleOwner) {
            binding.nameUser.text =
                it.name.ifEmpty { getString(R.string.your_name) }
            val photoUri = Uri.fromFile(File(it.photoMain))
            try {
                binding.photoProfile.setRoundImage(photoUri, R.drawable.ic_profile)
            } catch (e: Exception) {
                binding.photoProfile.setRoundImage(null, R.drawable.ic_profile)
            }
        }
    }

    private fun initClicks() {
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }

        binding.photoProfile.setOnClickListener {
            showAlterDialog()
        }

        binding.psyProject.setOnClickListener {
            replaceFragment(TeamPsyFragment(), true)
        }

        binding.nameUser.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                val text = setEditText("")
                setTitle(getString(R.string.your_nam))
                setPositiveButton(getString(R.string.save)) {
                    val name = text.findViewById<TextView>(R.id.input_text).text
                    app.user.value = app.user.value?.copy(name = name.toString())
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }

        binding.diaryEmotions.setOnClickListener {
            replaceFragment(DiaryEmotionFragment(), true)
        }
        binding.premium.setOnClickListener {
            replaceFragment(PremiumFragment(), true)
        }
        binding.pinCode.setOnClickListener {
            replaceFragment(SetPinCodeFragment(), true)
        }
        binding.mail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:lev.dev.apps@gmail.com")
            }
            startActivity(Intent.createChooser(emailIntent, getString(R.string.mail_to)))
        }
        binding.mark.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=ru.harlion.pomodorolist")
            )
            startActivity(browserIntent)
        }

        binding.psyTelegram.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://t.me/my_psy_app")
            )
            startActivity(browserIntent)
        }

        binding.infoUser.setOnClickListener {
            replaceFragment(
                ExInstructionsFragment.newInstance(
                    oneTitle = R.string.about_app,
                    toolbar = R.string.for_user,
                    isEx = false
                ), true
            )
        }
        binding.widgets.setOnClickListener {
            replaceFragment(SetWidgetFragment(), true)
        }
    }

    private fun showAlterDialog() {
        if (photoRequest == null) {
            photoRequest = PhotoRequest(this)
        }
        // photoRequest!!.showAlterDialog(launcher)
        photoRequest!!.openGallery(launcher)
    }
}