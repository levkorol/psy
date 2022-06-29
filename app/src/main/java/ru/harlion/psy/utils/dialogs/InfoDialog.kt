package ru.harlion.psy.utils.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.harlion.psy.R
import ru.harlion.psy.databinding.FragmentDialogInfoBinding
import ru.harlion.psy.ui.profile.premium.PremiumFragment
import ru.harlion.psy.utils.replaceFragment
import java.time.ZoneId

class InfoDialog : BottomSheetDialogFragment() {

    private lateinit var bind: FragmentDialogInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDialogInfoBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.close.setOnClickListener {
            dismiss()
        }

        if (requireArguments().getBoolean("PREMIUM")) {
            bind.infoPremium.visibility = View.VISIBLE
            bind.btnPositive.apply {
                text = getString(R.string.pro)
                setOnClickListener {
                    setFragmentResult("info_premium", Bundle().apply {
                        putBoolean("premium", true)
                    })
                    dismiss()
                }
            }
        }
        if (requireArguments().getBoolean("TEST")) {
            bind.infoProgress.visibility = View.VISIBLE
            bind.btnPositive.apply {
                text = getString(R.string.test_begin)
                setOnClickListener {
                    setFragmentResult("info_test", Bundle().apply {
                        putBoolean("test", true)
                    })
                    dismiss()
                }
            }
        }
    }

    companion object {
        fun newInstance(isTest: Boolean = false, isPremium: Boolean = false) = InfoDialog().apply {
            arguments = Bundle().apply {
                putBoolean("PREMIUM", isPremium)
                putBoolean("TEST", isTest)
            }
        }
    }
}