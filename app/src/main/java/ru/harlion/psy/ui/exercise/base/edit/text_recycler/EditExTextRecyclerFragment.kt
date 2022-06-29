package ru.harlion.psy.ui.exercise.base.edit.text_recycler


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditExTextRecyclerBinding
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.utils.Prefs
import ru.harlion.psy.utils.dialogs.EditTextDialog


class EditExTextRecyclerFragment :
    BindingFragment<FragmentEditExTextRecyclerBinding>(FragmentEditExTextRecyclerBinding::inflate) {

    private val viewModel: EditTextRecyclerViewModel by viewModels()
    private lateinit var prefs: Prefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())
        val id = requireArguments().getLong("ID")
        val (title, hint, listHint) = requireArguments().getIntArray("TEXT_IDS")!!
        val typeEx = requireArguments().getSerializable("TYPE_EX")

        viewModel.getExById(id)
        observe()

        if (id > 0 && typeEx == TypeEx.POSITIVE_BELIEFS) {
            binding.widget.visibility = View.VISIBLE
            if (id == prefs.exIdForWidget.value) {
                binding.widget.text = getString(R.string.remove_ex_widget)
            } else {
                binding.widget.text = getString(R.string.set_ex_widget)
            }
        }

        binding.answers.countItems = {
            binding.listCount.text = "${String(Character.toChars(0x1F4DC))} $it"
        }

        binding.questionOne.title = resources.getText(title)
        binding.questionOne.hint = resources.getText(hint)
        binding.answers.hint = resources.getText(listHint)

        binding.widget.setOnClickListener {
            checkClickIdWidget(id)
        }
        binding.back.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.save.setOnClickListener {
            if (id > 0) {
                viewModel.update(
                    binding.questionOne.text.toString(),
                    binding.answers.items.map {
                        it.toString()
                    }
                )
                Snackbar.make(
                    binding.root,
                    getString(R.string.update_completed),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                viewModel.add(
                    binding.questionOne.text.toString(),
                    binding.answers.items.map {
                        it.toString()
                    },
                    when (typeEx) {
                        TypeEx.LIFE_RULES -> TypeEx.LIFE_RULES
                        TypeEx.SELF_ESTEEM -> TypeEx.SELF_ESTEEM
                        TypeEx.POSITIVE_BELIEFS -> TypeEx.POSITIVE_BELIEFS
                        else -> TypeEx.IDEAS_DIARY
                    }
                )
            }
            parentFragmentManager.popBackStack()
        }

        binding.delete.setOnClickListener {
            EditTextDialog(requireContext()).apply {
                setTitle(getString(R.string.delete_ex_dialog))
                setPositiveButton(getString(R.string.yes)) {
                    viewModel.delete(id)
                    prefs.exIdForWidget.value = 0
                    parentFragmentManager.popBackStack()
                }
                setNegativeButton(getString(R.string.cancel)) {}
            }.show()
        }
    }

    private fun checkClickIdWidget(id: Long) {
        if (id != prefs.exIdForWidget.value) {
            prefs.exIdForWidget.value = id
            binding.widget.text = getString(R.string.remove_ex_widget)
        } else {
            prefs.exIdForWidget.value = -1
            binding.widget.text = getString(R.string.set_ex_widget)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.update(
            binding.questionOne.text.toString(),
            binding.answers.items.map {
                it.toString()
            }
        )
    }

    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.delete.visibility = View.VISIBLE
                binding.questionOne.setText(it.fieldOne)
                binding.answers.items = it.listString
                binding.listCount.text =
                    "${String(Character.toChars(0x1F4DC))} ${it.listString.size}"
            }
        }
    }

    companion object {
        fun newInstance(id: Long = 0, title: Int, hint: Int, listHint: Int, typeEx: TypeEx) =
            EditExTextRecyclerFragment().apply {
                arguments = Bundle().apply {
                    putIntArray("TEXT_IDS", intArrayOf(title, hint, listHint))
                    putSerializable("TYPE_EX", typeEx)
                    putLong("ID", id)
                }
            }
    }
}