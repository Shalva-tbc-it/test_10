package com.example.testteen.presentation.screen.home.bottom_sheet.account_number

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testteen.databinding.FragmentAccountNumberBinding
import com.example.testteen.presentation.extension.paymentValidate
import com.example.testteen.presentation.extension.showSnackBar
import com.example.testteen.presentation.model.Cards
import com.example.testteen.presentation.screen.event.AccountNumberEvent
import com.example.testteen.presentation.screen.event.CardsEvent
import com.example.testteen.presentation.screen.state.CardsState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AccountNumberFragment : BottomSheetDialogFragment() {

    private val viewModel: AccountNumberViewModel by viewModels()

    private var _binding: FragmentAccountNumberBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountNumberBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        listener()
    }


    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentAccState.collect {
                    handleCards(it)
                }
            }
        }
    }

    private fun listener() {
        binding.btnSend.setOnClickListener {
            if (binding.edCardsNumber.text.toString().paymentValidate()) {
                viewModel.onEvent(AccountNumberEvent.GetCurrentAcc(binding.edCardsNumber.text.toString()))
            } else {
                binding.root.showSnackBar(message = "payment error")
            }
        }

    }


    private fun handleCards(state: CardsState<Cards>) {
//        binding.progressBar.visibility =
//            if (state.loading) View.VISIBLE else View.GONE

        state.currentData?.let {
            d("currentDate", "$it")
        }

        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            CardsEvent.ResetErrorMessage
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}