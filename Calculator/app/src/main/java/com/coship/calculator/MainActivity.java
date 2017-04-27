package com.coship.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String str = "";
    int method;
    boolean flag;
    double result;
    double twoNum;
    double oneNum;
    View vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button[] num = new Button[10];
        num[0] = (Button)findViewById(R.id.num_zero);//数字0
        num[1] = (Button)findViewById(R.id.num_one);//数字1
        num[2] = (Button)findViewById(R.id.num_two);//数字2
        num[3] = (Button)findViewById(R.id.num_three);//数字3
        num[4] = (Button)findViewById(R.id.num_four);//数字4
        num[5] = (Button)findViewById(R.id.num_five);//数字5
        num[6] = (Button)findViewById(R.id.num_six);//数字6
        num[7] = (Button)findViewById(R.id.num_seven);//数字7
        num[8] = (Button)findViewById(R.id.num_eight);//数字8
        num[9] = (Button)findViewById(R.id.num_nine);//数字9

        Button function_c =(Button)findViewById(R.id.function_c);//清零c
        Button function_square =(Button)findViewById(R.id.function_square);//平方square
        Button function_radicals =(Button)findViewById(R.id.function_radicals);//开根radicals
        final Button function_divide =(Button)findViewById(R.id.function_divide);//除法divide
        final Button function_multiply =(Button)findViewById(R.id.function_multiply);//乘法multiply
        final Button function_minus =(Button)findViewById(R.id.function_minus);//减法minus
        final Button function_add =(Button)findViewById(R.id.function_add);//加法add
        Button function_decimalpoint =(Button)findViewById(R.id.function_decimalpoint);//小数点
        Button function_equal =(Button)findViewById(R.id.function_equal);//等于equal

        editText = (EditText)findViewById(R.id.show);


        //清零C事件处理
        function_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoNum = 0.0;
                oneNum = 0.0;
                method = 0;
                str  = "";
                editText.setText("0");
            }
        });

        //平方事件处理
        function_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != "") {
                    double t = Double.parseDouble(str);
                    str = "" + t*t;
                    editText.setText(str);
                }
            }
        });

        //开根事件处理
        function_radicals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != "") {
                    double t = Double.parseDouble(str);
                    str = "" + Math.sqrt(t);
                    editText.setText(str);
                }
            }
        });

        //加法事件处理
        function_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != "") {
                    if (vi == function_add || vi == function_minus
                            ||vi == function_multiply ||vi == function_divide) {
                        method = 1;
                    }else {
                        twoNum = Double.parseDouble(str);
                        calculate();
                        str = "" + result;
                        editText.setText(str);
                        method = 1;
                        flag = true;
                        vi = v;
                    }
                }
            }
        });

        //减法事件处理
        function_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != "") {
                    if (vi == function_add || vi == function_minus
                            ||vi == function_multiply ||vi == function_divide) {
                        method = 2;
                    }else {
                        twoNum = Double.parseDouble(str);
                        calculate();
                        str = "" + result;
                        editText.setText(str);
                        method = 2;
                        flag = true;
                        vi = v;
                    }
                }
            }
        });

        //乘法事件处理
        function_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != "") {
                    if (vi == function_add || vi == function_minus
                            ||vi == function_multiply ||vi == function_divide) {
                        method = 3;
                    }else {
                        twoNum = Double.parseDouble(str);
                        calculate();
                        str = "" + result;
                        editText.setText(str);
                        method = 3;
                        flag = true;
                        vi = v;
                    }
                }
            }
        });

        //除法事件处理∞
        function_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str != "") {
                    if (vi == function_add || vi == function_minus
                            ||vi == function_multiply ||vi == function_divide)
                    {//点击除号键之前是已经点击了+-*/等运算键，点击除号之后操作符变为除号
                        method = 4;
                    }else {//点击除号之前不是加减乘除运算键
                        twoNum = Double.parseDouble(str);
                        calculate();
                        str = "" + result;
                        editText.setText(str);
                        method = 4;
                        flag = true;
                        vi = v;
                    }
                }
            }
        });

        //小数点事件处理
        function_decimalpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str == "") {
                    str = "0.";
                    editText.setText(str);
                }else {
                    //需要判断前面数字是否有小数点
                    char ch[];
                    int i = 0;
                    ch = str.toCharArray();
                    for (int j = 0; j < ch.length; j++) {
                        if (ch[j] == '.') {
                            i++;
                        }
                    }
                    if (i == 0) {//前面没有小数点，在string后面加上小数点
                        str = str + ".";
                        editText.setText(str);
                    }
                }

            }
        });

        //等于事件处理
        function_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!("".equals(str)) && vi != function_add && vi != function_minus
                        &&vi != function_multiply &&vi != function_divide) {
                    //string不为空，view不为加减乘除等运算键
                    twoNum = Double.parseDouble(str);
                    calculate();
                    str = "" + result;
                    editText.setText(str);
                    flag = true;
                    vi = v;
                }

            }
        });

        //数字0
        num[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    str = "";
                    str += 0;
                    editText.setText(str);
                    flag = false;
                }else {
                    char ch1[];
                    ch1 = str.toCharArray();
                    if (!(ch1.length == 1 && ch1[0] == '0')) {
                        str += "0";
                        editText.setText(str);
                    }
                }
                vi = v;
            }
        });

        final int[] t = new int[]{1,2,3,4,5,6,7,8,9};
        for (final int i :t)
        {
            num[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag == true){
                        str = "";
                        str += i;
                        editText.setText(str);
                        flag = false;
                    }else {
                        str += i;
                        editText.setText(str);
                    }
                    vi = v;
                }
            });
        }
    }

    //计算方式
    public double calculate()
    {
        switch (method) {
            case 0:
                result = twoNum;
                break;
            case 1:
                result = oneNum + twoNum;
                break;
            case 2:
                result = oneNum - twoNum;
                break;
            case 3:
                result = oneNum * twoNum;
                break;
            case 4:
                result = oneNum / twoNum;
                break;
            default:
                break;
        }
        oneNum = result;
        method = 0;
        return result;
    }
}
