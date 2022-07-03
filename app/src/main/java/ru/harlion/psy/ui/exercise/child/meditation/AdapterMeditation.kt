package ru.harlion.psy.ui.exercise.child.meditation

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemMeditationMenuBinding

private typealias ItemHolder = BindingHolder<ItemMeditationMenuBinding>

class AdapterMeditation(val click: (Int) -> Unit) : RecyclerView.Adapter<ItemHolder>() {

    var items = listOf<ItemMeditation>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemMeditationMenuBinding::inflate, parent).apply {}

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.apply {
            titleMeditation.text = items[position].name
            // containerLl.setBackgroundResource(items[position].bg)
            containerLl.setBackgroundColor(
                ContextCompat.getColor(
                    containerLl.context,
                    items[position].bg
                )
            )

            containerLl.setOnClickListener {
                click.invoke(position)
            }
        }
    }

    override fun getItemCount() = items.size
}

class ItemMeditation(
    val name: String,
    val bg: Int
)