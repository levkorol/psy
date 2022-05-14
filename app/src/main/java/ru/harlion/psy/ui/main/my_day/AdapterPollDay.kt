package ru.harlion.psy.ui.main.my_day

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemPollDayBinding
import ru.harlion.psy.models.Poll
import ru.harlion.psy.utils.dateAndTimeToString

private typealias ItemHolder = BindingHolder<ItemPollDayBinding>

class AdapterPollDay : RecyclerView.Adapter<ItemHolder>() {

    var items: List<Poll> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemPollDayBinding::inflate, parent).apply {

        }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.apply {
            time.text = dateAndTimeToString(items[position].dateCreate)
            one.text = items[position].question[0].assessment.toString()
            two.text = items[position].question[1].assessment.toString()
            three.text = items[position].question[2].assessment.toString()
            four.text = items[position].question[3].assessment.toString()
            fife.text = items[position].question[4].assessment.toString()
            six.text = items[position].question[5].assessment.toString()
            seven.text = items[position].question[6].assessment.toString()
            eight.text = items[position].question[7].assessment.toString()
        }
    }

    override fun getItemCount() = items.size
}