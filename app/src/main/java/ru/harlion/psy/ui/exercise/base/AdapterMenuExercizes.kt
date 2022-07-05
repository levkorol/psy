package ru.harlion.psy.ui.exercise.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemMenuExercizeBinding

private typealias ItemHolderMenuEx = BindingHolder<ItemMenuExercizeBinding>
class AdapterMenuExercizes(private val click : (Int) -> Unit) :
 RecyclerView.Adapter<ItemHolderMenuEx>(){

    var items: List<MenuEx> = listOf()
    set(value) {
       field =  value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderMenuEx(ItemMenuExercizeBinding::inflate, parent).apply {
        }

    override fun onBindViewHolder(holder: ItemHolderMenuEx, position: Int) {
        holder.binding.apply {
            titleEx.text = items[position].title
            countEx.text =  if (items[position].count != null) {
                "Записей : ${ items[position].count } "
            } else {
                items[position].about_ex
            }

            imageEx.setBackgroundResource(items[position].image)
            itemCl.setOnClickListener {
                click.invoke(position)
            }
           if (items[position].isBlock) {
               lock.visibility = View.VISIBLE
           } else {
               lock.visibility = View.GONE
           }
        }
    }

    override fun getItemCount() = items.size
}

class MenuEx(
    val title: String,
    val image: Int,
    val count: Int?,
    val isBlock: Boolean,
    val about_ex: String = ""
)