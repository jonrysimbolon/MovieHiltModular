package com.jonrysimbolon.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.jonrysimbolon.core.dialog.CustomDialog
import com.jonrysimbolon.core.dialog.CustomDialogReload
import com.jonrysimbolon.core.extension.setupSnackbar
import com.jonrysimbolon.core.navigation.NavigationCommand
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding, VM : BaseViewModel>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected abstract val baseViewModel: VM

    @Inject
    lateinit var loadingDialog: CustomDialog

    @Inject
    lateinit var failureDialog: CustomDialogReload

    /*protected val loadingDialog: CustomDialog by lazy {
        Loading(requireContext())
    }

    protected val failureDialog: CustomDialogReload by lazy {
        Failure(requireContext())
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun setUpUi() = Unit
    protected open fun setUpVm() = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation(baseViewModel)
        setupSnackbar(this, baseViewModel.snackBarError, Snackbar.LENGTH_LONG)
        setUpUi()
        setUpVm()
    }

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(
                        command.directions,
                        getExtras()
                    )

                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}