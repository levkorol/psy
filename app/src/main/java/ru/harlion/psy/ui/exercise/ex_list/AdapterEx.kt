package ru.harlion.psy.ui.exercise.ex_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemExerciseTitleCountBinding
import ru.harlion.psy.models.Exercise

private typealias ItemHolder = BindingHolder<ItemExerciseTitleCountBinding>
class AdapterEx : RecyclerView.Adapter<ItemHolder>() {

    var items = listOf<Exercise>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemExerciseTitleCountBinding::inflate, parent).apply {
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
         holder.binding.apply {
             date.text = "12 ноября"//items[position].date
             fieldOne.text = items[position].fieldOne

             if (items[position].fieldTwo.isNotEmpty()) {
                 fieldTwo.text = items[position].fieldTwo
                 fieldTwo.visibility = View.VISIBLE
             } else {
                 fieldTwo.visibility = View.GONE
             }

             if (items[position].fieldTwo.isNotEmpty()) {
                 fieldThree.text = items[position].fieldTwo
                 fieldThree.visibility = View.VISIBLE
             } else {
                 fieldThree.visibility = View.GONE
             }
         }
    }

    override fun getItemCount() = items.size
}