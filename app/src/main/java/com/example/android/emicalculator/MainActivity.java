package com.example.android.emicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText loanAmt,annualInterest;//Declaring loan amount and AnnualInterest
    TextView years5,years10,years15,years20,years25,years30;// declaring years from 5 to 30
    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loanAmt= (EditText) findViewById(R.id.loanAmt);
        annualInterest= (EditText) findViewById(R.id.annualInterest);
        years5= (TextView) findViewById(R.id.years5);
        years10= (TextView) findViewById(R.id.years10);
        years15= (TextView) findViewById(R.id.years15);
        years20= (TextView) findViewById(R.id.years20);
        years25= (TextView) findViewById(R.id.years25);
        years30= (TextView) findViewById(R.id.years30);

        calculate= (Button) findViewById(R.id.calculate);//Initializing the button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    String amountText=loanAmt.getText().toString();//getting text from amount and interest
                    String interestText=annualInterest.getText().toString();

                    double amount=Double.parseDouble(amountText);//converting the string to double
                    double interest=Double.parseDouble(interestText);
                    double monthlyInterest=getMonthlyInterest(interest);
                    /**
                     * Calculating the monthly payment for years 5,10,15,20,25 and 30
                     */
                    double monthlyPayment5=getMonthlyPayment(amount,5,monthlyInterest);
                    double monthlyPayment10=getMonthlyPayment(amount,10,monthlyInterest);
                    double monthlyPayment15=getMonthlyPayment(amount,15,monthlyInterest);
                    double monthlyPayment20=getMonthlyPayment(amount,20,monthlyInterest);
                    double monthlyPayment25=getMonthlyPayment(amount,25,monthlyInterest);
                    double monthlyPayment30=getMonthlyPayment(amount,30,monthlyInterest);

                    years5.setText("$"+String.format("%.2f",monthlyPayment5));//updating the text fields
                    years10.setText("$"+String.format("%.2f",monthlyPayment10));
                    years15.setText("$"+String.format("%.2f",monthlyPayment15));
                    years20.setText("$"+String.format("%.2f",monthlyPayment20));
                    years25.setText("$"+String.format("%.2f",monthlyPayment25));
                    years30.setText("$"+String.format("%.2f",monthlyPayment30));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //method to find monthly payment
    static double getMonthlyPayment(double loanAmt, int numYears,
                                    double monthlyInterest) {

        int numMonths=numYears*12;
        double monthlyPaymentRate = loanAmt * monthlyInterest
                * (Math.pow(1 + monthlyInterest, numMonths))
                / (Math.pow(1 + monthlyInterest, numMonths) - 1);
        return monthlyPaymentRate;
    }

    /**
     a method to calculate monthly interest
     **/
    static double getMonthlyInterest(double interestRate){
        double monthlyInterest = ((interestRate / 100) / 12);
        return monthlyInterest;
    }
}
