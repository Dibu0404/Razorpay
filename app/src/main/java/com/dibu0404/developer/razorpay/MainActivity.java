package com.dibu0404.developer.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    Button paybtn ;
    TextView payid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());

        paybtn  = (Button) findViewById(R.id.paybtn);
        payid = (TextView) findViewById(R.id.payid);

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });

    }

    private void makepayment() {

        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_Ao0DMBirmPWB6o");
         checkout.setImage(R.drawable.ic_icon);

        final Activity activity = this;

                try {
            JSONObject options = new JSONObject();

            options.put("name", "Divisha Jain");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "50000");//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9424499512");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        payid.setText("Successfull payment ID :"+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        payid.setText("Payment Failed  :"+s);
    }
}