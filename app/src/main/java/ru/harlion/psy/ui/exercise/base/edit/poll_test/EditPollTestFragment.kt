package ru.harlion.psy.ui.exercise.base.edit.poll_test


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ru.harlion.psy.R
import ru.harlion.psy.base.BindingFragment
import ru.harlion.psy.base.BindingHolder
import ru.harlion.psy.databinding.FragmentPollTestEditBinding
import ru.harlion.psy.databinding.ItemSliderBinding
import ru.harlion.psy.models.Answer
import ru.harlion.psy.ui.profile.test.TestFragment
import ru.harlion.psy.utils.replaceFragment
import kotlin.math.roundToInt


class EditPollTestFragment :
    BindingFragment<FragmentPollTestEditBinding>(FragmentPollTestEditBinding::inflate) {

    private val viewModel : EditPollTestViewModel by viewModels()

    private lateinit var answers: ArrayList<Answer>
    private lateinit var questions: List<String>
    private var isTesting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isTesting = requireArguments().getBoolean("TESTING")
        questions = if (isTesting) {
            resources.getStringArray(R.array.questions_test)
        } else {
            resources.getStringArray(R.array.questions_poll)
        }.asList()
        answers = savedInstanceState?.getParcelableArrayList("A") ?: questions.indices.mapTo(
            ArrayList()
        ) {
            Answer(it, 0, "")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("A", answers)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isTesting) {
            binding.titleToolbar.setText(R.string.go_tests)
        } else {
            binding.titleToolbar.setText(R.string.poll_day_ex)
        }

        binding.viewPager.adapter = AdapterPollTest(questions, answers, isTesting)
        binding.nextQuestion.setOnClickListener {
            if (questions.lastIndex == binding.viewPager.currentItem) {
                if (questions.lastIndex == binding.viewPager.currentItem) {
                    if (isTesting) {
                        val reduce = answers.map {
                            it.assessment
                        }.chunked(3).reduce { acc, elements ->
                            acc.mapIndexed { index, element ->
                                element + elements[index]
                            }
                        }
                        val sum = reduce.sum()
                        val result = reduce.map {
                            it * 100F / sum
                        }
                        replaceFragment(TestFragment.newInstance(true, result.toFloatArray()), true)
                    } else {
                        viewModel.add(answers)
                        parentFragmentManager.popBackStack()
                    }
                }
            } else {
                binding.viewPager.currentItem++
            }
        }

        binding.countQuestion.text = "${questions.size} / ${answers.size}"
    }

    companion object {
        fun newInstance(isTesting: Boolean) = EditPollTestFragment().apply {
            arguments = Bundle().apply {
                putBoolean("TESTING", isTesting)
            }
        }
    }
}

private typealias ItemHolder = BindingHolder<ItemSliderBinding>

class AdapterPollTest(
    val questions: List<String>,
    val items: List<Answer>,
    val isTesting: Boolean
) :
    RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(ItemSliderBinding::inflate, parent).apply {
            binding.question.addSliderListener { _, value, _ ->
                if (adapterPosition > -1) {
                    items[adapterPosition].assessment = value.roundToInt()
                }
            }
            binding.comment.title = parent.context.getText(R.string.poll_question_comment)
            binding.comment.hint = parent.context.getText(R.string.comment)
            binding.comment.addListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    if (adapterPosition > -1) {
                        items[adapterPosition].comment = s.toString()
                    }
                }
            })
        }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.apply {
            if (isTesting) {
                text.text = "Оцените насколько убедительной является для вас эта мысль"
                comment.visibility = View.GONE
            } else {
                comment.visibility = View.VISIBLE
                text.text = ""
            }
            question.text = questions[position]
            comment.text = items[position].comment
            question.countSlider = items[position].assessment.toFloat()
        }
    }

    override fun getItemCount() = items.size

}