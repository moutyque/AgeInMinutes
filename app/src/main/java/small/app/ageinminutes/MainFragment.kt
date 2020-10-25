package small.app.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import small.app.ageinminutes.databinding.FragmentMainBinding
import java.time.LocalDate
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentMainBinding
    lateinit var model: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        val button = binding.button


        model = ViewModel()
        binding.fragmentModel = model

        button.setOnClickListener {
            clickDataPicker()
        }

        model.selectedDate.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { newSelectedDate ->
                binding.textViewSelectedDate.text = newSelectedDate.toString()
            })


        model.minutes.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { newMinutes ->
                binding.textViewAgeInMinutes.text = newMinutes.toString()
            })
        model.days.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { newDays ->
                binding.textViewAgeInDays.text = newDays.toString()
            })



        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun clickDataPicker() {
        val myCalendar = Calendar.getInstance()

        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            activity!!,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                model.newDate(LocalDate.of(selectedYear, selectedMonth + 1, selectedDay))

            },
            year,
            month,
            day
        ).show()


    }


}