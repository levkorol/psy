package ru.harlion.psy.ui.main.diary_emotions.adapter


import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionBinding
import ru.harlion.psy.models.emotions.Emotion

private typealias ItemHolderEmotion = BindingHolder<ItemEmotionBinding>

class AdapterEmotion(
    private val checkedItems: HashSet<Emotion>,
    private val changeItems: () -> Unit = {}
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
                    changeItems.invoke()
                }
            }
        }

    override fun onBindViewHolder(holder: ItemHolderEmotion, position: Int) {
        val colors = StateListDrawable()
        val dp = holder.binding.checkedTextView.resources.displayMetrics.density * 18
        colors.addState(intArrayOf(android.R.attr.state_checked), GradientDrawable().apply {
            setSize(dp.toInt(), dp.toInt())
            setColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    items[position].categoryEmotions.color
                )
            )
            shape = GradientDrawable.OVAL
        })
        colors.addState(
            intArrayOf(),
            GradientDrawable().apply {
                setSize(dp.toInt(), dp.toInt())
                setColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.gray_super_light
                    )
                )
                shape = GradientDrawable.OVAL
            })

        holder.binding.apply {
            checkedTextView.apply {
                isChecked = items[position] in checkedItems
                setCompoundDrawablesRelativeWithIntrinsicBounds(colors, null, null, null)
                text = items[position].getName(this.resources)
            }
        }
    }

    override fun getItemCount() = items.size

}


