package com.aldemir.unittest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aldemir.unittest.data.LoginDataSource
import com.aldemir.unittest.data.LoginRepository
import com.aldemir.unittest.ui.login.LoginResult
import com.aldemir.unittest.ui.login.LoginViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoginViewModelUnitTest {

    @Mock
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<LoginResult>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(Mockito.mock(LoginRepository::class.java))

        loginViewModel._loginResult.observeForever(observer)
    }

    @Test
    fun isUserNameValidTrueTest() {
       loginViewModel = LoginViewModel(Mockito.mock(LoginRepository::class.java))
        Assert.assertTrue(loginViewModel.isUserNameValid("aldemir@gmail.com"))
    }

    @Test
    fun isPasswordValidTrueTest() {
        loginViewModel = LoginViewModel(Mockito.mock(LoginRepository::class.java))
        Assert.assertTrue(loginViewModel.isPasswordValid("123456"))
    }

    @Test
    fun handleClickCallHandleLogin() {
        loginViewModel = LoginViewModel(Mockito.mock(LoginRepository::class.java))
        val spy = Mockito.spy(loginViewModel)

        spy.onLogin()

        Mockito.verify(spy, Mockito.times(1))
            .login("aldemir@gomes.com", "123456")
    }
}