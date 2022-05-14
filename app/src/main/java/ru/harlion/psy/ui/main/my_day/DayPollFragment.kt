package ru.harlion.psy.ui.main.my_day


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentDayPollBinding


class DayPollFragment : BindingFragment<FragmentDayPollBinding>(FragmentDayPollBinding::inflate) {

    private lateinit var adapterPollDay: AdapterPollDay
    private val viewModel: DayPollsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterPollDay = AdapterPollDay()
        binding.dayPollsRv.apply {
            adapter = adapterPollDay
            layoutManager = LinearLayoutManager(requireContext())
        }
        observe()
    }

    private fun observe() {
        viewModel.getListPolls()
        viewModel.polls.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapterPollDay.items = it
                binding.dayPollsRv.visibility = View.VISIBLE
                binding.emptyList.visibility = View.GONE
            } else {
                binding.dayPollsRv.visibility = View.GONE
                binding.emptyList.visibility = View.VISIBLE
            }
        })
    }

}