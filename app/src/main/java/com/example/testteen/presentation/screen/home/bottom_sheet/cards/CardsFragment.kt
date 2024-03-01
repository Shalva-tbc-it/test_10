package com.example.testteen.presentation.screen.home.bottom_sheet.cards

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testteen.databinding.FragmentCardsBinding
import com.example.testteen.presentation.extension.showSnackBar
import com.example.testteen.presentation.model.Cards
import com.example.testteen.presentation.screen.event.CardsEvent
import com.example.testteen.presentation.screen.home.bottom_sheet.cards.adapter.CardsRecyclerAdapter
import com.example.testteen.presentation.screen.state.CardsState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private var placeSearchListener: PlaceSearchListener? = null
    private val viewModel: CardsViewModel by viewModels()
    private lateinit var adapter: CardsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
        bindObserve()
    }

    fun setSearchListener(listener: PlaceSearchListener) {
        placeSearchListener = listener
    }


    private fun bindObserve() {
        viewModel.onEvent(CardsEvent.GetCards)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cardsState.collect {
                    handleCards(it)
                }
            }
        }
    }

    private fun bindAdapter() = with(binding) {
        adapter = CardsRecyclerAdapter()
        cardsRecycler.layoutManager = LinearLayoutManager(requireContext())
        cardsRecycler.adapter = adapter

    }


    private fun handleCards(state: CardsState<Cards>) {
//        binding.progressBar.visibility =
//            if (state.loading) View.VISIBLE else View.GONE

        state.data?.let {
            adapter.submitList(it)
            d("getCards", "$it")
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

    interface PlaceSearchListener {
        fun cards(latLng: String)
    }

}