package com.yolda.domain

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.yolda.common.EMPTY_STRING
import com.yolda.data.remote.SignInResult
import com.yolda.data.remote.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.yolda.R
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PhoneNumberAuthUiClient @Inject constructor(
    private val activity: Activity
) {

    private val auth = FirebaseAuth.getInstance()

    private lateinit var phoneNumber: String
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var signInResultLauncher: ActivityResultLauncher<Intent>

    fun initialize(resultLauncher: ActivityResultLauncher<Intent>) {
        signInResultLauncher = resultLauncher
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This method will be invoked if the verification is automatically completed by the system.
            // You can use credential to sign in the user (similar to signInWithCredential()).
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            // This method will be invoked if the verification fails.
        }

        override fun onCodeSent(
            vId: String,
            resendToken: PhoneAuthProvider.ForceResendingToken
        ) {
            verificationId = vId
            this@PhoneNumberAuthUiClient.resendToken = resendToken
        }
    }

    suspend fun signInWithPhoneNumber(phoneNumber: String): IntentSender? {
        this.phoneNumber = phoneNumber
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        return try {
            PhoneAuthProvider.verifyPhoneNumber(options)
            null
        } catch (e: FirebaseException) {
            Log.e("TAG", "PhoneAuth signInWithPhoneNumber error: ${e.message}")
            null
        }
    }

    suspend fun signInWithCode(code: String): SignInResult {
        val credential = PhoneAuthProvider.getCredential(requireNotNull(verificationId), code)
        return try {
            val user = auth.signInWithCredential(credential).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        id = uid,
                        email = email ?: EMPTY_STRING,
                        name = displayName ?: EMPTY_STRING,
                        photoUrl = photoUrl?.toString() ?: EMPTY_STRING
                    )
                },
                errorMessage = null
            )
        } catch (e: FirebaseAuthInvalidUserException) {
            Log.e("TAG", "PhoneAuth signInWithCode error: Invalid user")
            SignInResult(
                data = null,
                errorMessage = "Invalid user"
            )
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Log.e("TAG", "PhoneAuth signInWithCode error: Invalid verification code")
            SignInResult(
                data = null,
                errorMessage = "Invalid verification code"
            )
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            Log.e("TAG", "PhoneAuth signInWithCode error: Recent login required")
            SignInResult(
                data = null,
                errorMessage = "Recent login required"
            )
        } catch (e: FirebaseAuthMultiFactorException) {
            Log.e("TAG", "PhoneAuth signInWithCode error: Multi-factor authentication required")
            SignInResult(
                data = null,
                errorMessage = "Multi-factor authentication required"
            )
        } catch (e: Exception) {
            Log.e("TAG", "PhoneAuth signInWithCode error: ${e.message}")
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun resendVerificationCode(): IntentSender? {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setForceResendingToken(resendToken)
            .setCallbacks(callbacks)
            .build()
        return try {
            PhoneAuthProvider.verifyPhoneNumber(options)
            null
        } catch (e: FirebaseException) {
            Log.e("TAG", "PhoneAuth resendVerificationCode error: ${e.message}")
            null
        }
    }

    suspend fun signOut() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            Log.e("TAG", "PhoneAuth signOut error: ${e.message}")
            if (e is CancellationException) throw e
        }
    }

    fun getVerificationId(): String {
        return verificationId
    }

    fun getResendToken(): PhoneAuthProvider.ForceResendingToken {
        return resendToken
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            id = uid,
            email = email ?: EMPTY_STRING,
            name = displayName ?: EMPTY_STRING,
            photoUrl = photoUrl?.toString() ?: EMPTY_STRING
        )
    }
}

class GoogleAuthUiClient @Inject constructor(
    private val context: Context,
    private val oneTapClient: SignInClient
) {

    private val auth = FirebaseAuth.getInstance()

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            Log.e("TAG", "${e.printStackTrace()}")
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredential).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        id = uid,
                        email = email ?: EMPTY_STRING,
                        name = displayName ?: EMPTY_STRING,
                        photoUrl = photoUrl?.toString() ?: EMPTY_STRING
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            Log.e("TAG", "SignInWithIntent error: ${e.message}")
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            Log.e("TAG", "SignOut error: ${e.message}")
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            id = uid,
            email = email ?: EMPTY_STRING,
            name = displayName ?: EMPTY_STRING,
            photoUrl = photoUrl?.toString() ?: EMPTY_STRING
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest =
        BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
}