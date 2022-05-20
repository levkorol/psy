package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion

private typealias ItemHolderEmotion = BindingHolder<ItemEmotionBinding>

class AdapterEmotion(
    var emotions: (Set<String>) -> Unit
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
            textEmotion.apply {

                if (checkbox.isChecked) {
                    emotions.invoke(setOf(items.emotions[position]))
                }

                text = items.emotions[position]
                checkbox.buttonTintList = colors

                //checkedTextView
                checkedTextView.apply {
                    setOnClickListener {
                        isChecked = checkedTextView.isChecked
                    }
                    checkMarkTintList = colors
                    text = items.emotions[position]
                }
            }
        }
    }

    override fun getItemCount() = items.emotions.size

}


