package ru.harlion.psy.ui.profile.widgets

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemWidgetBinding

private typealias ItemHolder = BindingHolder<ItemWidgetBinding>

class WidgetsAdapter : RecyclerView.Adapter<ItemHolder>() {

    var items = listOf<ItemWidget>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemWidgetBinding::inflate, parent).apply {}

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
         holder.binding.apply {
             textTitle.text = items[position].text
             cardContainer.setBackgroundResource(items[position].bg)
             if (items[position].isBlock) {
                 check.visibility = View.GONE
                 lock.visibility = View.VISIBLE
             } else {
                 check.visibility = View.VISIBLE
                 lock.visibility = View.GONE
             }
         }
    }

    override fun getItemCount() = items.size
}

class ItemWidget(
    val text: String,
    val bg: Int,
    val isBlock: Boolean = false
)