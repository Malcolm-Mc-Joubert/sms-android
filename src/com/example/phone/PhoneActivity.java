package com.example.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// --------------------
public class PhoneActivity extends Activity implements OnClickListener {

	private EditText editTextTelephone;
	private EditText editTextMessage;
	private Button buttonEnvoyer;
	private TextView textViewErreur;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);

		editTextTelephone = (EditText)findViewById(R.id.editTextTelephone);
		editTextMessage = (EditText)findViewById(R.id.editTextMessage);
		buttonEnvoyer = (Button)findViewById(R.id.buttonEnvoyer);
		textViewErreur = (TextView) findViewById(R.id.textViewErreur);

		buttonEnvoyer.setOnClickListener(this);
	} /// onCreate

	@Override
	public void onClick(View v) {
		if(v == buttonEnvoyer) {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(editTextTelephone.getText().toString(), null, editTextMessage.getText().toString(), null, null);
			
			try {
				/*
				 * Envoi SMS en passant par une intention
				 * Cela va ouvrir une activite ou l'on peut changer
				 * le texte, ajouter une image ...
				 */
				Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"
				+ editTextTelephone.getText().toString()));

				sms.putExtra("sms_body", editTextMessage.getText().toString());

				startActivity(sms);

				// --- Petit message de verification !!!
				textViewErreur.setText("SMS envoyé !!!");
			} catch (Exception e) {
				textViewErreur.setText(e.getMessage());
			}
		}
		
	} /// onClick

} /// SMSEnvoyer