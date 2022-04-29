package ru.harlion.psy.ui.exercise.ex_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemExerciseTitleCountBinding
import ru.harlion.psy.models.Exercise

private typealias ItemHolder = BindingHolder<ItemExerciseTitleCountBinding>

class AdapterEx(private val clickEdit: (Long) -> Unit) : RecyclerView.Adapter<ItemHolder>() {

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

            containerItem.setOnClickListener {
                clickEdit.invoke(items[position].id)
            }

            date.text = "12 ноября"//items[position].date
            fieldOne.text = items[position].fieldOne

            when {
                items[position].fieldTwo.isNotEmpty() -> {
                    fieldTwo.text = items[position].fieldTwo
                    fieldTwo.visibility = View.VISIBLE
                }
                items[position].fieldThree.isNotEmpty() -> {
                    fieldThree.text = items[position].fieldThree
                    fieldThree.visibility = View.VISIBLE
                }
                items[position].listString.isNotEmpty() -> {
                    fieldTwo.text = "${items[position].listString.size}"
                    fieldTwo.visibility = View.VISIBLE
                }
                else -> {
                    fieldTwo.visibility = View.GONE
                    fieldThree.visibility = View.GONE
                }
            }

            if (items[position].fieldThree.isNotEmpty()) {
                fieldThree.text = items[position].fieldThree
                fieldThree.visibility = View.VISIBLE
            } else {
                fieldThree.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = items.size
}