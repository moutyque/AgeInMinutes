package small.app.ageinminutes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ViewModel : ViewModel() {


    val selectedDate = MutableLiveData<LocalDate>()

    val minutes = MutableLiveData<Long>()
    val days = MutableLiveData<Long>()

    init {
        selectedDate.value = LocalDate.MIN
        minutes.value = 0
        days.value = 0
    }

    fun newDate(newDate: LocalDate?) {
        selectedDate.value = newDate
        days.value = ChronoUnit.DAYS.between(newDate, LocalDate.now())
        minutes.value = ChronoUnit.DAYS.between(newDate, LocalDate.now()) * 24 * 60
    }

}