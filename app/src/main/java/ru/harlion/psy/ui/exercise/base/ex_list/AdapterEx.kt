package ru.harlion.psy.ui.exercise.base.ex_list

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_edit_album.*
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemExerciseTitleCountBinding
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.utils.dateAndTimeToString
import ru.harlion.psy.utils.dateToString

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

            if( items[position].date > 0) {
                date.text = "${String(Character.toChars(0x1F4AB))} ${dateToString(items[position].date)}"
                date.visibility = View.VISIBLE
            } else {
                date.visibility = View.GONE
            }

            dateCreate.text = dateAndTimeToString(items[position].dateCreate)
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
                    fieldTwo.text = "${String(Character.toChars(0x1F4DC))} ${items[position].listString.size}"
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

            if(items[position].image.isNotEmpty()) {
                image.visibility = View.VISIBLE
                val photoUri = Uri.parse(items[position].image)
                try {
                    image.setImageURI(photoUri)
                } catch (e: Exception) {
                    image.setImageURI(null)
                }
            } else {
                image.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = items.size
}