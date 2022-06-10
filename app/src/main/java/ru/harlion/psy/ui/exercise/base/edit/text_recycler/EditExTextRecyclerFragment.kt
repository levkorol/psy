package ru.harlion.psy.ui.exercise.base.edit.text_recycler


import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditExTextRecyclerBinding
import ru.harlion.psy.models.TypeEx


class EditExTextRecyclerFragment :
    BindingFragment<FragmentEditExTextRecyclerBinding>(FragmentEditExTextRecyclerBinding::inflate) {

    private val viewModel: EditTextRecyclerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getLong("ID")
        val (title, hint, listHint) = requireArguments().getIntArray("TEXT_IDS")!!
        val typeEx = requireArguments().getSerializable("TYPE_EX")

        viewModel.getExById(id)
        observe()

        binding.questionOne.title = resources.getText(title)
        binding.questionOne.hint = resources.getText(hint)
        binding.answers.hint = resources.getText(listHint)

        binding.save.setOnClickListener {
            if(id > 0) {
                viewModel.update(
                    binding.questionOne.text.toString(),
                    binding.answers.items.map {
                        it.toString()
                    }
                )
                Snackbar.make(binding.root, getString(R.string.update_completed), Snackbar.LENGTH_SHORT).show()
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
    }

    private fun observe() {
        viewModel.exercise.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.questionOne.setText(it.fieldOne)
                binding.answers.items = it.listString
            }
        })
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