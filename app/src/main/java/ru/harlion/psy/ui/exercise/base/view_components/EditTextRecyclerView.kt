package ru.harlion.psy.ui.exercise.base.view_components


import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R
import ru.harlion.psy.ui.exercise.base.view_components.adapter.AdapterEditText

class EditTextRecyclerView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        orientation = VERTICAL
        inflate(context, R.layout.component_edit_text_recycler_view, this)
    }

    private val adapterET: AdapterEditText

    private val editText = findViewById<TextView>(R.id.answer_c3)

    private val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_c3)

    private val sendBtn = findViewById<ImageButton>(R.id.btn_send)

    var items
        get() = adapterET.items
        set(value) {
            adapterET.items = value
        }

    var hint
        get() = editText.hint
        set(value) {
            editText.hint = value
        }

    init {
        val params = context.obtainStyledAttributes(
            attrs,
            intArrayOf(android.R.attr.hint)
        )
        if (params.hasValue(0)) {
            editText.hint = params.getText(0)
        }
        params.recycle()

        adapterET = AdapterEditText()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterET
        }

        sendBtn.setOnClickListener {
            adapterET.addItem(editText.text.toString())
            editText.text = ""
            recyclerView.smoothScrollToPosition(0)
        }
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        val bundle = Bundle()
        bundle.putStringArray("ITEMS", items.map {
            it.toString()
        }.toTypedArray())
        bundle.putParcelable("EDIT_TEXT", editText.onSaveInstanceState())
        container.put(id, bundle)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        val bundle = container.get(id) as Bundle
        items = bundle.getStringArray("ITEMS")!!.asList()
        editText.onRestoreInstanceState(bundle.getParcelable("EDIT_TEXT"))
    }
}