package ru.harlion.psy.utils.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.harlion.psy.R
import ru.harlion.psy.databinding.FragmentDialogCalendarBinding
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId

class DialogCalendar : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDialogCalendarBinding.bind(view)

        binding.close.setOnClickListener {
            dismiss()
        }

        var lDate = LocalDate.now()

        binding.calendarView.apply {
            val millis = lDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000
            date = millis
            maxDate = millis + 31536000000

            setOnDateChangeListener { _, year, month, dayOfMonth ->
                lDate = LocalDate.of(year, Month.values()[month], dayOfMonth)
            }
        }
        binding.save.setOnClickListener {
            setFragmentResult("calendarDate", Bundle().apply {
                putLong("epochMillis", lDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000)
            })
            dismiss()
        }
    }
}