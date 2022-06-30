package ru.harlion.psy.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import ru.harlion.psy.R

class EditTextDialog(context: Context) {

    private var alertDialog: Dialog = Dialog(context)
    private val inset = context.resources.getDimensionPixelSize(R.dimen.dialog_inset_50dp_25_dp)
    init {
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCancelable(false)
        alertDialog.setContentView(R.layout.fragment_edit_text_dialog)
        alertDialog.window!!.apply {

            setBackgroundDrawable(
                InsetDrawable(
                AppCompatResources.getDrawable(context, R.drawable.bg_light_violet),
                inset,
                0,
                inset,
                0
            )
            )
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            decorView.apply {
                layoutParams =
                    ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                setPadding(inset, 0, inset, 0)
            }
        }
    }

    fun setAddPhotoButton(onClickListener: View.OnClickListener?) {
        alertDialog.findViewById<AppCompatButton>(R.id.photo_button).apply {
            visibility = View.VISIBLE
            setOnClickListener {
                onClickListener?.onClick(it)
                alertDialog.dismiss()
            }
        }
    }

    fun setTitle(msg: String) {
        alertDialog.findViewById<TextView>(R.id.title).apply {
            visibility = View.VISIBLE
            text = msg
        }
    }

    fun setText(msg: String) {
        alertDialog.findViewById<TextView>(R.id.text_normal).apply {
            visibility = View.VISIBLE
            text = msg
        }
    }

    fun setEditText(hintText: String): View {
        return alertDialog.findViewById<EditText>(R.id.input_text).apply {
            visibility = View.VISIBLE
            hint = hintText
        }
    }

    fun setPositiveButton(title: String, onClickListener: View.OnClickListener?) {
        alertDialog.findViewById<AppCompatButton>(R.id.positive_button).apply {
            text = title
            visibility = View.VISIBLE
            setOnClickListener {
                onClickListener?.onClick(it)
                alertDialog.dismiss()
            }
        }
    }

    fun setNegativeButton(title: String, onClickListener: View.OnClickListener?) {
        alertDialog.findViewById<AppCompatButton>(R.id.negative_button).apply {
            text = title
            visibility = View.VISIBLE
            setOnClickListener {
                onClickListener?.onClick(it)
                alertDialog.dismiss()
            }
        }
    }

    fun show() {
        alertDialog.show()
    }

    fun cancelable(isCancelable: Boolean) {
        alertDialog.setCancelable(isCancelable)
    }
}