package ru.harlion.psy.ui.main.diary_emotions.table_emotions.adapter

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.astrocode.flm.FlowLayoutManager
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEmotionsTableBinding
import ru.harlion.psy.models.emotions.CategoryEmotions
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.ui.main.diary_emotions.adapter.AdapterEmotion

private typealias ItemHolderTableEmotions = BindingHolder<ItemEmotionsTableBinding>

class AdapterTableEmotions(
    private var adapterEmotion: AdapterEmotion? = null,
    val checkedItems: HashSet<Emotion>,
//    val countChecked: (Int) -> Unit
) :
    RecyclerView.Adapter<ItemHolderTableEmotions>() {

    var items = listOf<CategoryEmotions>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolderTableEmotions(ItemEmotionsTableBinding::inflate, parent).apply {
            binding.countEmo.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width,
                        view.height,
                        10 * view.resources.displayMetrics.density
                    )
                }
            }
            binding.countEmo.clipToOutline = true
        }

    override fun onBindViewHolder(holder: ItemHolderTableEmotions, position: Int) {
        holder.binding.apply {
            nameEmoCategory.text = items[position].getName(nameEmoCategory.resources)
            countEmo.setBackgroundColor(
                ContextCompat.getColor(
                    countEmo.context,
                    items[position].color
                )
            )

            emotionsRv.adapter
            initRecyclerViewEmotion(emotionsRv, items[position], checkedItems)
        }
    }

    override fun getItemCount() = items.size


    private fun initRecyclerViewEmotion(
        recyclerView: RecyclerView,
        categoryEmotions: CategoryEmotions,
        checkedItems: HashSet<Emotion>
    ) {
        adapterEmotion = AdapterEmotion(checkedItems)
        val llm = FlowLayoutManager(FlowLayoutManager.VERTICAL)
        llm.spacingBetweenItems((8 * recyclerView.context.resources.displayMetrics.density).toInt())
        llm.spacingBetweenLines((8 * recyclerView.context.resources.displayMetrics.density).toInt())
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapterEmotion

        adapterEmotion?.items = categoryEmotions.getEmotions(recyclerView.resources)
    }

}