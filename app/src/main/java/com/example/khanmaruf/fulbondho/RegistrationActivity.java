package com.example.khanmaruf.fulbondho;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner s1, s2;
    Spinner studentClass;
    String thana[] = null;

    private final String USER_PHONE="user_phone";
    private final String USER_PASSWORD="user_password";

    //shared preferacne for userphone & password
    SharedPreferences pref;

    Button submit_reg_btn;

    private static final String TAG = "RegistrationActivity";

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    TextView mDisplayDate;

    private EditText student_name, student_school, student_class, student_roll,
            student_district, student_thana, student_phone, student_password;

    int b = 0, point = 0;
    String reg_student_name, reg_student_school, reg_student_class, reg_student_roll,
            reg_student_district, reg_student_thana, reg_student_phone, reg_student_birthday,
            reg_student_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        studentClass = (Spinner) findViewById(R.id.stu_class);
        //------------District Spinner-------------------//
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);

        s1.setOnItemSelectedListener(this);


        student_name = (EditText) findViewById(R.id.student_name);
        student_school = (EditText) findViewById(R.id.student_school);
        // student_class = (EditText) findViewById(R.id.student_class);
        student_roll = (EditText) findViewById(R.id.student_roll);
        // student_district = (EditText) findViewById(R.id.student_district);
        // student_thana = (EditText) findViewById(R.id.student_thana);
        student_phone = (EditText) findViewById(R.id.student_phone);
        student_password = (EditText) findViewById(R.id.student_password);

        // student_birthday = (TextView) findViewById(R.id.student_birthday);


        mDisplayDate = (TextView) findViewById(R.id.tvDate);


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegistrationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG,"onDateSet: dd/mm/yyy: " + month + "/" + day + "/" + year);
                String date =  year +"-" + month  + "-" + day;
                mDisplayDate.setText(date);
            }
        };

        submit_reg_btn = (Button) findViewById(R.id.submit_reg_btn);
        submit_reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RegistrationActivity.this, All_Round.class);
//                startActivity(intent);


                submit_reg_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                       //------------------validation----------------//
//                            String validPhone = "^[2-9]{2}[0-9]{8}$";
//                          String  phone = student_phone.getText().toString();
//                           Matcher matcher = Pattern.compile(validPhone).matcher(phone);
//                        if (matcher.matches()){
//                            Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Enter valid Phone", Toast.LENGTH_LONG).show();
//                        }
//                        student_password.setError("Enter password");
////                        //----------------------------------------------------//



                        if(point == 0) {
                            handleSSLHandshake();
                            point = 1;


                            reg_student_name = student_name.getText().toString();
                            reg_student_school = student_school.getText().toString();
                            reg_student_class = studentClass.getSelectedItem().toString();

                            // reg_student_class = student_class.getText().toString();
                            reg_student_roll = student_roll.getText().toString();

                            reg_student_district = s1.getSelectedItem().toString();
                            reg_student_thana = s2.getSelectedItem().toString();

                            // reg_student_district = student_district.getText().toString();
                            // reg_student_thana = student_thana.getText().toString();
                            reg_student_phone = student_phone.getText().toString();
                            reg_student_birthday = mDisplayDate.getText().toString();
                            reg_student_password = student_password.getText().toString();



//                            onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//                                @Override
//                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                                    month = month+1;
//                                    Log.d(TAG,"onDateSet: dd/mm/yyy: " + month + "/" + day + "/" + year);
//                                    String date = month + "/" + day + "/" + year;
//                                    mDisplayDate.setText(date);
//                                }
//                            };


                            String myURL = "https://sererl.com/api/v1/registration.php";
                            //String myURL = "http://fulbondhu.net/sheiblu/api/v1/registration.php";
                            //String myURL = "https://realistic-camps.000webhostapp.com/v1/registration.php";
                            // String myURL = "https://unheard-of-lumber.000webhostapp.com/fulbondhu/api/v1/registration.php";
                            // String myURL = "https://unheard-of-lumber.000webhostapp.com/fulbondhu/api/v1/registration.php";
                            // String myURL = "https://173.212.240.186/api/v1/registration";
                            //String myURL = "https://undrooping-till.000webhostapp.com/equipement.php";


                            Log.d("sd", "Run");

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, myURL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    Log.d("res",response.toString());
                                    point = 0;

                                    b = response.length();
                                    // Toast.makeText(getContext(), "Done it",Toast.LENGTH_SHORT).show();
                                    if (b > 37) {
                                        updatePlayerScore();
                                        //saving authentication details in prefs
                                        pref = getApplicationContext().getSharedPreferences("auth", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString(USER_PHONE,student_phone.getText().toString());
                                        editor.putString(USER_PASSWORD,student_password.getText().toString());

                                        editor.putString("phone",student_phone.getText().toString());

                                        editor.apply();

                                        // cleanInputField();

                                        Log.d("sd", response + "  " + response.toString());
                                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        intent.putExtra("round", 1);
                                        startActivity(intent);
   //                            finish();
                                    } else if (b > 25) {
                                        Toast.makeText(getApplicationContext(), "Phone number duplicate ", Toast.LENGTH_SHORT).show();
                                        Log.d("sd", response + "  " + response.toString());
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration Failed, fill the all filed", Toast.LENGTH_SHORT).show();
                                        Log.d("sd", response + "  " + response.toString());
                                    }
                                    b = 0;

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    point = 0;
                                    Toast.makeText(getApplication(),"Error data  for"+error.toString(),Toast.LENGTH_SHORT).show();
                                    Log.d("sd",error.toString()+ "  ");
                                }
                            }) {
                                @Override
                                protected Map<String,String> getParams() throws AuthFailureError {
                                    Map<String ,String> parr = new HashMap<String,String>();

                                    parr.put("student_name", reg_student_name);
                                    parr.put("student_school", reg_student_school);
                                    parr.put("student_class", reg_student_class);
                                    parr.put("student_roll", reg_student_roll);
                                    parr.put("student_district", reg_student_district);
                                    parr.put("student_thana", reg_student_thana);
                                    parr.put("student_phone", reg_student_phone);
                                    parr.put("student_birthday", reg_student_birthday);
                                    parr.put("student_password", reg_student_password);
                                    point = 0;

                                    return parr;
                                }

                            };

                            AppController.getInstance().addToRequestQueue(stringRequest);

                        }


                    }


                });
            }
        });
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }

    }

//    public void cleanInputField() {
//        student_name.setText("");
//        student_school.setText("");
//        student_class.setText("");
//        student_roll.setText("");
//        student_district.setText("");
//        student_thana.setText("");
//        student_phone.setText("");
//        mDisplayDate.setText("");
//        student_password.setText("");
//
//    }

    //-----------------------For District Spinner-------------------------------//

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (i == 0)
        {
            thana = new String[]{"Upozela"};
        }
        if (i == 1)
        {
            thana = new String[]{"BAGERHAT SADAR","CHITALMARI","FAKIRHAT","KACHUA","MOLLAHAT","MONGLA","MORRELGANJ","RAMPAL","SARANKHOLA"};
        }
        if (i == 2)
        {
            thana = new String[]{"ALIKADAM","BANDARBAN SADAR","LAMA","NAIKHONGCHHARI","ROWANGCHHARI","RUMA","THANCHI"};
        }
        if (i == 3)
        {
            thana = new String[]{"AMTALI","BAMNA","BARGUNA SADAR","BETAGI","PATHARGHATA"};
        }
        if (i == 4)
        {
            thana = new String[]{"AGAILJHARA","BABUGANJ","BAKERGANJ","BANARI PARA","GAURNADI","HIZLA","BARISAL SADAR","MHENDIGANJ","MULADI","WAZIRPUR"};
        }
        if (i == 5)
        {
            thana = new String[]{"BHOLA SADAR","BURHANUDDIN","CHAR FASSON","DAULAT KHAN","LALMOHAN","MANPURA","TAZUMUDDIN"};
        }
        if (i == 6)
        {
            thana = new String[]{"ADAMDIGHI","BOGRA SADAR","DHUNAT","DHUPCHANCHIA","GABTALI","KAHALOO","NANDIGRAM","SARIAKANDI","SHAJAHANPUR","SHERPUR","SHIBGANJ","SONATOLA"};
        }
        if (i == 7)
        {
            thana = new String[]{"AKHAURA","BANCHHARAMPUR","BRAHMANBARIA SADAR","ASHUGANJ","KASBA","NABINAGAR","NASIRNAGAR","SARAIL"};
        }
        if (i == 8)
        {
            thana = new String[]{"CHANDPUR SADAR", "FARIDGANJ","HAIM CHAR","HAJIGANJ","KACHUA","MATLAB DAKSHIN","MATLAB UTTAR","SHAHRASTI"};
        }
        if (i == 9)
        {
            thana = new String[]{"ANOWARA","BAYEJID BOSTAMI","BANSHKHALI","BAKALIA","BOALKHALI","CHANDANAISH","CHANDGAON","CHITTAGONG PORT","DOUBLE MOORING","FATIKCHHARI","HALISHAHAR","HATHAZARI","KARNAFULI (POLICE STATION)","KOTWALI","KHULSHI","LOHAGARA","MIRSHARAI","PAHARTALI","PANCHLAISH","PATIYA","PATENGA","RANGUNIA","RAOZAN","SANDWIP","SATKANIA","SITAKUNDA"};
        }
        if (i == 10)
        {
            thana = new String[]{"BARURA","BRAHMAN PARA","BURICHANG","CHANDINA","CHAUDDAGRAM","COMILLA SADAR DAKSHIN","DAUDKANDI","DEBIDWAR","HOMNA","COMILLA ADARSHA SADAR","LAKSAM","MANOHARGANJ","MEGHNA","MURADNAGAR","NANGALKOT","TITAS"};
        }

        //11

        if (i == 11)
        {
            thana = new String[]{"ALAMDANGA","CHUADANGA SADAR","DAMURHUDA","JIBAN NAGAR"};
        }
        if (i == 12)
        {
            thana = new String[]{"CHAKARIA","COXS BAZAR SADAR","KUTUBDIA","MAHESHKHALI","PEKUA","RAMU","TEKNAF","UKHIA"};
        }
        if (i == 13)
        {
            thana = new String[]{"ADABOR","BADDA","BIMAN BANDAR","CANTONMENT","DAKSHINKHAN","DEMRA","DHAMRAI","DHANMONDI","DOHAR","GULSHAN","HAZARIBAGH","JATRABARI","KAFRUL","KADAMTALI","KAMRANGIR CHAR","KHILGAON","KHILKHET","KERANIGANJ","KOTWALI","LALBAGH","MIRPUR","MOHAMMADPUR","MOTIJHEEL","NAWABGANJ","NEW MARKET","PALLABI","PALTAN","RAMNA","SABUJBAGH","SAVAR","SHAH ALI","SHAHBAGH","SHYAMPUR","SUTRAPUR","TEJGAON","TEJGAON IND. AREA","TURAG","UTTARA","UTTAR KHAN"};
        }
        if (i == 14)
        {
            thana = new String[]{"BIRAMPUR","BIRGANJ","BIRAL","BOCHAGANJ","CHIRIRBANDAR","FULBARI","GHORAGHAT","HAKIMPUR","KAHAROLE","KHANSAMA","DINAJPUR SADAR","NAWABGANJ","PARBATIPUR"};
        }
        if (i == 15)
        {
            thana = new String[]{"ALFADANGA","BHANGA","BOALMARI","CHAR BHADRASAN","FARIDPUR SADAR","MADHUKHALI","NAGARKANDA","SADARPUR","SALTHA"};
        }
        if (i == 16)
        {
            thana = new String[]{"CHHAGALNAIYA","DAGANBHUIYAN","FENI SADAR","FULGAZI","PARSHURAM","SONAGAZI"};
        }
        if (i == 17)
        {
            thana = new String[]{"FULCHHARI","GAIBANDHA SADAR","GOBINDAGANJ","PALASHBARI","SADULLAPUR","SAGHATA","SUNDARGANJ"};
        }
        if (i == 18)
        {
            thana = new String[]{"GAZIPUR SADAR","KALIAKAIR","KALIGANJ","KAPASIA","SREEPUR"};
        }
        if (i == 19)
        {
            thana = new String[]{"GOPALGANJ SADAR","KASHIANI","KOTALIPARA","MUKSUDPUR","TUNGIPARA"};
        }
        if (i == 20)
        {
            thana = new String[]{"AJMIRIGANJ","BAHUBAL","BANIACHONG","CHUNARUGHAT","HABIGANJ SADAR","LAKHAI","MADHABPUR","NABIGANJ"};
        }

        //21

        if (i == 21)
        {
            thana = new String[]{"AKKELPUR","JOYPURHAT SADAR","KALAI","KHETLAL","PANCHBIBI"};
        }
        if (i == 22)
        {
            thana = new String[]{"BAKSHIGANJ","DEWANGANJ","ISLAMPUR","JAMALPUR SADAR","MADARGANJ","MELANDAHA","SARISHABARI"};
        }
        if (i == 23)
        {
            thana = new String[]{"ABHAYNAGAR","BAGHER PARA","CHAUGACHHA","JHIKARGACHHA","KESHABPUR","JESSOR SADAR","MANIRAMPUR","SHARSHA"};
        }
        if (i == 24)
        {
            thana = new String[]{"JHALOKATI SADAR","KANTHALIA","NALCHITY","RAJAPUR","JHENAIDAH","HARINAKUNDA","JHENAIDAH SADAR","KALIGANJ","KOTCHANDPUR","MAHESHPUR","SHAILKUPA"};
        }
        if (i == 25)
        {
            thana = new String[]{"HARINAKUNDA","JHENAIDAH SADAR","KALIGANJ","KOTCHANDPUR","MAHESHPUR","SHAILKUPA"};
        }
        if (i == 26)
        {
            thana = new String[]{"DIGHINALA","KHAGRACHHARI SADAR","LAKSHMICHHARI","MAHALCHHARI","MANIKCHHARI","MATIRANGA","PANCHHARI","RAMGARH"};
        }
        if (i == 27)
        {
            thana = new String[]{"BATIAGHATA","DACOPE","DAULATPUR","DUMURIA","DIGHALIA","KHALISHPUR","KHAN JAHAN ALI","KHULNA SADAR","KOYRA","PAIKGACHHA","PHULTALA","RUPSA","SONADANGA","TEROKHADA"};
        }
        if (i == 28)
        {
            thana = new String[]{"AUSTAGRAM","BAJITPUR","BHAIRAB","HOSSAINPUR","ITNA","KARIMGANJ","KATIADI","KISHOREGANJ SADAR","KULIAR CHAR","MITHAMAIN","NIKLI","PAKUNDIA","TARAIL"};
        }
        if (i == 29)
        {
            thana = new String[]{"BHURUNGAMARI","CHAR RAJIBPUR","CHILMARI","PHULBARI","KURIGRAM SADAR","NAGESHWARI","RAJARHAT","RAUMARI","ULIPUR"};
        }
        if (i == 30)
        {
            thana = new String[]{"BHERAMARA","DAULATPUR","KHOKSA","KUMARKHALI","KUSHTIA SADAR","MIRPUR"};
        }

        //31

        if (i == 31)
        {
            thana = new String[]{"KAMALNAGAR","LAKSHMIPUR SADAR","ROYPUR","RAMGANJ","RAMGATI"};
        }
        if (i == 32)
        {
            thana = new String[]{"ADITMARI","HATIBANDHA","KALIGANJ","LALMONIRHAT SADAR","PATGRAM"};
        }
        if (i == 33)
        {
            thana = new String[]{"KALKINI","MADARIPUR SADAR","RAJOIR","SHIB CHAR"};
        }
        if (i == 34)
        {
            thana = new String[]{"MAGURA SADAR","MOHAMMADPUR","SHALIKHA","SREEPUR"};
        }
        if (i == 35)
        {
            thana = new String[]{"DAULATPUR","GHIOR","HARIRAMPUR","MANIKGANJ SADAR","SATURIA","SHIBALAYA","SINGAIR"};
        }
        if (i == 36)
        {
            thana = new String[]{"GANGNI","MUJIB NAGAR","MEHERPUR SADAR"};
        }
        if (i == 37)
        {
            thana = new String[]{"BARLEKHA","JURI","KAMALGANJ","KULAURA","MAULVIBAZAR SADAR","RAJNAGAR","SREEMANGAL"};
        }
        if (i == 38)
        {
            thana = new String[]{"GAZARIA","LOHAJANG","MUNSHIGANJ SADAR","SERAJDIKHAN","SREENAGAR","TONGIBARI"};
        }
        if (i == 39)
        {
            thana = new String[]{"BHALUKA","DHOBAURA","FULBARIA","GAFFARGAON","GAURIPUR","HALUAGHAT","ISHWARGANJ","MYMENSINGH SADAR","MUKTAGACHHA","NANDAIL","PHULPUR","TRISHAL"};
        }
        if (i == 40)
        {
            thana = new String[]{"ATRAI","BADALGACHHI","DHAMOIRHAT","MANDA","MAHADEBPUR","NAOGAON SADAR","NIAMATPUR","PATNITALA","PORSHA","RANINAGAR","SAPAHAR"};
        }

        //41
        if (i == 41)
        {
            thana = new String[]{"KALIA","LOHAGARA","NARAIL SADAR"};
        }
        if (i == 42)
        {
            thana = new String[]{"ARAIHAZAR","SONARGAON","BANDAR","NARAYANGANJ SADAR","RUPGANJ"};
        }
        if (i == 43)
        {
            thana = new String[]{"BELABO","MANOHARDI","NARSINGDI SADAR","PALASH","ROYPURA","SHIBPUR"};
        }
        if (i == 44)
        {
            thana = new String[]{"BAGATIPARA","BARAIGRAM","GURUDASPUR","LALPUR","NATORE SADAR","SINGRA"};
        }
        if (i == 45)
        {
            thana = new String[]{"BHOLAHAT","GOMASTAPUR","NACHOLE","CHAPAI NABABGANJ SADAR","SHIBGANJ"};
        }
        if (i == 46)
        {
            thana = new String[]{"ATPARA","BARHATTA","DURGAPUR","KHALIAJURI","KALMAKANDA","KENDUA","MADAN","MOHANGANJ","NETROKONA SADAR","PURBADHALA"};
        }
        if (i == 47)
        {
            thana = new String[]{"DIMLA","DOMAR","JALDHAKA","KISHOREGANJ","NILPHAMARI SADAR","SAIDPUR"};
        }
        if (i == 48)
        {
            thana = new String[]{"BEGUMGANJ","CHATKHIL","COMPANIGANJ","HATIYA","KABIRHAT","SENBAGH","SONAIMURI","SUBARNACHAR","NOAKHALI SADAR"};
        }
        if (i == 49)
        {
            thana = new String[]{"ATGHARIA","BERA","BHANGURA","CHATMOHAR","FARIDPUR","ISHWARDI","PABNA SADAR","SANTHIA","SUJANAGAR"};
        }
        if (i == 50)
        {
            thana = new String[]{"ATWARI","BODA","DEBIGANJ","PANCHAGARH SADAR","TENTULIA"};
        }

        //51

        if (i == 51)
        {
            thana = new String[]{"BAUPHAL","DASHMINA","DUMKI","GALACHIPA","KALA PARA","MIRZAGANJ","PATUAKHALI SADAR"};
        }
        if (i == 52)
        {
            thana = new String[]{"BHANDARIA","KAWKHALI","MATHBARIA","NAZIRPUR UPAZILA","PIROJPUR SADAR","NESARABAD (SWARUPKATI)","ZIANAGAR"};
        }
        if (i == 53)
        {
            thana = new String[]{"BAGHA","BAGHMARA","BOALIA","CHARGHAT","DURGAPUR","GODAGARI","MATIHAR","MOHANPUR","PABA","PUTHIA","RAJPARA","SHAH MAKHDUM","TANORE"};
        }
        if (i == 54)
        {
            thana = new String[]{"BALIAKANDI","GOALANDA","PANGSHA","RAJBARI SADAR"};
        }
        if (i == 55)
        {
            thana = new String[]{"BAGHAICHHARI","BARKAL","KAWKHALI","BELAI CHHARI","KAPTAI","JURAI CHHARI","LANGADU","NANIARCHAR","RAJASTHALI","RANGAMATI SADAR"};
        }
        if (i == 56)
        {
            thana = new String[]{"BADARGANJ","GANGACHARA","KAUNIA","RANGPUR SADAR","MITHA PUKUR","PIRGACHHA","PIRGANJ","TARAGANJ"};
        }
        if (i == 57)
        {
            thana = new String[]{"BHEDARGANJ","DAMUDYA","GOSAIRHAT","NARIA","SHARIATPUR SADAR","ZANJIRA"};
        }
        if (i == 58)
        {
            thana = new String[]{"ASSASUNI","DEBHATA","KALAROA","KALIGANJ","SATKHIRA SADAR","SHYAMNAGAR","TALA"};
        }
        if (i == 59)
        {
            thana = new String[]{"BELKUCHI","CHAUHALI","KAMARKHANDA","KAZIPUR","ROYGANJ","SHAHJADPUR","SIRAJGANJ SADAR","TARASH","ULLAH PARA"};
        }
        if (i == 60)
        {
            thana = new String[]{"JHENAIGATI","NAKLA","NALITABARI","SHERPUR SADAR","SREEBARDI"};
        }

        //61

        if (i == 61)
        {
            thana = new String[]{"BISHWAMBARPUR","CHHATAK","DAKSHIN SUNAMGANJ","DERAI","DHARAMPASHA","DOWARABAZAR","JAGANNATHPUR","JAMALGANJ","SULLA","SUNAMGANJ SADAR","TAHIRPUR"};
        }
        if (i == 62)
        {
            thana = new String[]{"BALAGANJ","BEANI BAZAR","BISHWANATH","COMPANIGANJ","DAKSHIN SURMA","FENCHUGANJ","GOLAPGANJ","GOWAINGHAT","JAINTIAPUR","KANAIGHAT","SYLHET SADAR","ZAKIGANJ"};
        }
        if (i == 63)
        {
            thana = new String[]{"BASAIL","BHUAPUR","DELDUAR","DHANBARI","GHATAIL","GOPALPUR","KALIHATI","MADHUPUR","MIRZAPUR","NAGARPUR","SAKHIPUR","TANGAIL SADAR"};
        }
        if (i == 64)
        {
            thana = new String[]{"BALIADANGI","HARIPUR","PIRGANJ","RANISANKAIL","THAKURGAON SADAR"};
        }





        ArrayAdapter<String> adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thana);
        s2.setAdapter(adt );
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void  updatePlayerScore() {
        //Start Copy
        Log.d("myLog", "*********** Database Start *************");
        dbCopy mydb = new dbCopy(this);
        String dbPath = mydb.DB_PATH + "/fulbondhu.db";
        try {
            mydb.createDataBase();
            Log.d("myLog", "Database Create");
        } catch (IOException e) {
            Log.d("myLog", "Database Not Create");
            e.printStackTrace();
        }



        SQLiteDatabase myDatabase = null;

        Log.d("DB PAth", dbPath + "\n" + mydb.DB_PATH);

        try {
            myDatabase = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Log.d("myLog", "Database Open Problem menu");
        }


        try {

            for(int i = 1; i< 8 ; i++){
                if (i != 1){
                    Log.d("myLog", "Update...............");

                    myDatabase.execSQL("Update android_score_table set currect_answer = -1 where round = "+ i +" ;");
                }else{
                    assert myDatabase != null;
                    myDatabase.execSQL("Update android_score_table set currect_answer = 0 where round = "+ i +" ;");
                }


                //    myDatabase.execSQL("Update android_score_table set currect_answer = 0 where round = "+ i +" ;");
                myDatabase.execSQL("Update android_score_table set time = 0 where round = "+ i +" ;");
                myDatabase.execSQL("Update android_score_table set number_of_play = 0 where round = "+ i +" ;");
                // String a = "Update android_score_table set currect_answer = " + currectAnswer[i-1] + " where round = "+ i +" ;" ;
            }



        } catch (Exception e) {
            Log.d("myLog", "raw query error in menu");
            e.printStackTrace();
        }

        myDatabase.close();
    }
}
