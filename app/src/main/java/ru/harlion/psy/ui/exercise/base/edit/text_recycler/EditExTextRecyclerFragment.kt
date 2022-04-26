package ru.harlion.psy.ui.exercise.base.edit.text_recycler


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.databinding.FragmentEditExTextRecyclerBinding
import ru.harlion.psy.models.TypeEx


class EditExTextRecyclerFragment :
    BindingFragment<FragmentEditExTextRecyclerBinding>(FragmentEditExTextRecyclerBinding::inflate) {

    private val viewModel: EditTextRecyclerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (title, hint, listHint) = requireArguments().getIntArray("TEXT_IDS")!!
        val typeEx = requireArguments().getSerializable("TYPE_EX")

        binding.questionOne.title = resources.getText(title)
        binding.questionOne.hint = resources.getText(hint)
        binding.answers.hint = resources.getText(listHint)

        binding.save.setOnClickListener {
            viewModel.add(
                binding.questionOne.text.toString(),
                binding.answers.items, //todo Создавать видимый список до сохранения
                when (typeEx) {
                    TypeEx.LIFE_RULES -> TypeEx.LIFE_RULES
                    TypeEx.SELF_ESTEEM -> TypeEx.SELF_ESTEEM
                    TypeEx.POSITIVE_BELIEFS -> TypeEx.POSITIVE_BELIEFS
                    else -> TypeEx.IDEAS_DIARY
                }
            )
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(title: Int, hint: Int, listHint: Int, typeEx: TypeEx) =
            EditExTextRecyclerFragment().apply {
                arguments = Bundle().apply {
                    putIntArray("TEXT_IDS", intArrayOf(title, hint, listHint))
                    putSerializable("TYPE_EX", typeEx)
                }
            }
    }
}