package com.example.bluetoothadapteruse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public BluetoothManager bluetoothManager;//蓝牙管理器
    public BluetoothAdapter bluetoothAdapter;
    private Switch s0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        s0 = findViewById(R.id.s0);
        s0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if (bluetoothAdapter == null) {
                        Toast.makeText(MainActivity.this, "没有蓝牙", Toast.LENGTH_SHORT).show();
                    }
                    if (bluetoothAdapter.isEnabled()) {
                        Toast.makeText(MainActivity.this, "蓝牙已经连接", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "蓝牙没有连接", Toast.LENGTH_SHORT).show();
                        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT)
                                != PackageManager.PERMISSION_GRANTED ) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                                    Manifest.permission.BLUETOOTH_CONNECT
                            }, 1);
                        }
                        else{
                            bluetoothAdapter.enable();
                        }

                    }
                }
            }
        });

        }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                  bluetoothAdapter.enable();
                    Log.d("TAG", "进来了");
                }

        else{
            Log.d("TAG","没进来");
            Toast.makeText(this,"不知道",Toast.LENGTH_SHORT).show();
                    }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Toast.makeText(this,"已打开",Toast.LENGTH_SHORT).show();
        }
    }
}