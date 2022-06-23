package ru.harlion.psy.ui.profile.widgets

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemWidgetBinding
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.setImageRes
import ru.harlion.psy.utils.setRoundImage

private typealias ItemHolder = BindingHolder<ItemWidgetBinding>

class WidgetsAdapter(val prefs: Prefs) : RecyclerView.Adapter<ItemHolder>() {

    var items = listOf<ItemWidget>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var checkedPosition: Int
        set(value) {
            val valueOld = checkedPosition
            if (value != valueOld) {
                prefs.widgetPosition = value
            }
            notifyItemChanged(value)
            notifyItemChanged(valueOld)
        }
        get() {
            return prefs.widgetPosition
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemWidgetBinding::inflate, parent).apply {}

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.apply {
            textTitle.text = items[position].text
            image.setImageRes(items[position].bg)
            if (items[position].isBlock) {
                check.visibility = View.GONE
                lock.visibility = View.VISIBLE
            } else {
                check.visibility = View.VISIBLE
                lock.visibility = View.GONE
            }
            check.isChecked = position == checkedPosition

            check.setOnClickListener {
                checkedPosition = position
            }
            textTitle.setTextColor(
                ContextCompat.getColor(
                    textTitle.context,
                    items[position].textColor
                )
            )

        }
    }

    override fun getItemCount() = items.size


}

class ItemWidget(
    val text: String,
    val bg: Int,
    var isBlock: Boolean = false,
    val textColor: Int,
)