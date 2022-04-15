package ru.harlion.psy.ui.exercise

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemMenuExercizeBinding

private typealias ItemHolderMenuEx = BindingHolder<ItemMenuExercizeBinding>
class AdapterMenuExercizes :
 RecyclerView.Adapter<ItemHolderMenuEx>(){

    var items: List<MenuEx> = listOf()
    set(value) {
       field =  value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderMenuEx(ItemMenuExercizeBinding::inflate, parent).apply {}

    override fun onBindViewHolder(holder: ItemHolderMenuEx, position: Int) {
        holder.binding.apply {
            titleEx.text = items[position].title
            countEx.text = "+ ${items[position].count} к прогрессу"
            imageEx.setBackgroundResource(items[position].image)
        }
    }

    override fun getItemCount() = items.size
}

class MenuEx(
    val title: String,
    val image: Int,
    val count: Int
)