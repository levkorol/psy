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
import ru.harlion.psy.models.emotions.Emotion

private typealias ItemHolderEmotion = BindingHolder<ItemEmotionBinding>

class AdapterEmotion(
    private val checkedItems: HashSet<Emotion>
) : RecyclerView.Adapter<ItemHolderEmotion>() {

    var items: List<Emotion> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderEmotion(ItemEmotionBinding::inflate, parent).apply {
            binding.checkedTextView.setOnClickListener {
                if (adapterPosition > -1) {
                    val element = items[adapterPosition]
                    checkedItems.add(element) || checkedItems.remove(element)
                    notifyItemChanged(adapterPosition)
                }
            }
        }

    override fun onBindViewHolder(holder: ItemHolderEmotion, position: Int) {
        val colors = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ), intArrayOf(
                ContextCompat.getColor(holder.itemView.context, items[position].categoryEmotions.color),
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray_super_light
                )
            )
        )

        holder.binding.apply {
            checkedTextView.apply {
                isChecked = items[position] in checkedItems
                TextViewCompat.setCompoundDrawableTintList(this, colors)
                text = items[position].getName(this.resources)
            }
        }
    }

    override fun getItemCount() = items.size

}


