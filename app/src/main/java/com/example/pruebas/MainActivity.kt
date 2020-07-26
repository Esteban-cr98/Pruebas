package com.example.pruebas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

lateinit var appExecutors: AppExecutors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appExecutors = AppExecutors()

        bt_enviar.setOnClickListener{
            val code = (100000..999999).random()
            sendEmail(code.toString())
        }
    }


    private fun sendEmail(code: String) {

        appExecutors.diskIO().execute{
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")

            val session =  Session.getInstance(props,
                object : javax.mail.Authenticator(){
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(Credentials.EMAIL, Credentials.PASSWORD)
                }
        })
            try {
                //Creating MimeMessage object
                val mm = MimeMessage(session)
                val emailId = et_correo.text.toString()
                //Setting sender address
                mm.setFrom(InternetAddress(Credentials.EMAIL))
                //Adding receiver
                mm.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(emailId))
                //Adding subject
                mm.subject = "Confirmación de correo electrónico"
                //Adding message
                mm.setText("Su código de verificación es $code ")

                //Sending email
                Transport.send(mm)

                appExecutors.mainThread().execute {
                    //Something that should be executed on main thread.
                }
            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }

    }
}

object Credentials {
    const val EMAIL = "kingsgameapp16@gmail.com"
    const val PASSWORD = "kingspass"
}
