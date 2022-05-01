package ru.harlion.psy.ui.exercise.base.view_components.adapter


import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.ItemEditTextVioletBinding


private typealias ItemHolder = BindingHolder<ItemEditTextVioletBinding>

class AdapterEditText :
    RecyclerView.Adapter<ItemHolder>() {

    var items : List<CharSequence>
        get() = _items
        set(value) {
            _items = value.mapTo(arrayListOf()) {
                SpannableStringBuilder(it)
            }
            notifyDataSetChanged()
        }

    private var _items = mutableListOf<CharSequence>()

    fun addItem(item : CharSequence) {
        _items.add(0, SpannableStringBuilder(item))
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemEditTextVioletBinding::inflate,parent).apply {
            binding.answers.setEditableFactory(object : Editable.Factory() {
                override fun newEditable(source: CharSequence?): Editable {
                    return source as Editable
                }
            })
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       holder.binding.apply {
           val answer =  answers
           val charSequence = items[position]
           answer.setText(charSequence)
       }
    }

    override fun getItemCount() = items.size

}