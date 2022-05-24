package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionBinding
import ru.harlion.psy.models.emotions.CategoryEmotions

private typealias ItemHolderEmotion = BindingHolder<ItemEmotionBinding>

class AdapterEmotion(
    private val checkedItems: HashSet<String>
) : RecyclerView.Adapter<ItemHolderEmotion>() {

    var colors: ColorStateList? = null

    var items: CategoryEmotions = CategoryEmotions.emptyCategory
        set(value) {
            field = value
            colors = null
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderEmotion(ItemEmotionBinding::inflate, parent).apply {
            binding.checkedTextView.setOnClickListener {
                if (adapterPosition > -1) {
                    val element = items.emotions[adapterPosition]
                    checkedItems.add(element) || checkedItems.remove(element)
                    notifyItemChanged(adapterPosition)
                }
            }
        }

    override fun onBindViewHolder(holder: ItemHolderEmotion, position: Int) {
        val colors = colors ?: ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ), intArrayOf(
                ContextCompat.getColor(holder.itemView.context, items.color),
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray_super_light
                )
            )
        ).also { colors = it }

        holder.binding.apply {
            checkedTextView.apply {
                isChecked = items.emotions[position] in checkedItems
                TextViewCompat.setCompoundDrawableTintList(this, colors)
                text = items.emotions[position]
            }
        }
    }

    override fun getItemCount() = items.emotions.size

}


