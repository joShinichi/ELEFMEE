package com.elfmecorporation.nuel.elefmee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by jo_shinichi1 on 4/7/2016.
 */
public class BackgroundWorker extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    Context context;
    LoginActivity loginActivity = new LoginActivity();
    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        String login_url = "http://192.168.43.139/elfme_login1.php";
        String register_url = "http://192.168.43.139/elfme_register1.php";
        if(type.equals("register")){
            try {
                String nama = params[1];
                String username = params[2];
                String password = params[3];
                String email = params[4];
                String noPonsel = params[5];
                String alamat = params[6];
                URL url = new URL(register_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"
                +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                +URLEncoder.encode("ponsel","UTF-8")+"="+URLEncoder.encode(noPonsel,"UTF-8")+"&"
                +URLEncoder.encode("alamat","UTF-8")+"="+URLEncoder.encode(alamat,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("login")){
            try {
                String userName = params[1];
                String Password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Register Status");
    }

    @Override
    protected void onPostExecute(String result) {
            String s = result.trim();
//        alertDialog.setMessage(result);
//        alertDialog.show();

            if(s.equals("login success")){
                Toast.makeText(context, "Login Sukses !!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,akun.class);
                context.startActivity(intent);
            }else if(s.equals("login not success")){
                Toast.makeText(context, "username atau password salah", Toast.LENGTH_SHORT).show();
            } else if(s.equals("Register sukses")){
                alertDialog.setMessage(result);
                alertDialog.show();
            context.startActivity(new Intent(context,LoginActivity.class));
        }else if(s.equals("some_error")){
            Toast.makeText(context, "ada sesuatu yang error", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

