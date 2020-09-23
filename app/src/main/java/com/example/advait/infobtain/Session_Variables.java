package com.example.advait.infobtain;

public class Session_Variables {
    static String s_email;
    static String s_pass;
    static String s_name;
    static String s_phone;
    static String s_web;
    static String s_comp;
    static String s_desi;
    public void empty_Session_Variables()
    {
        s_email="";
        s_pass="";
        s_name="";
        s_phone="";
        s_web="";
        s_comp="";
        s_desi="";
    }
    public boolean check_session()
    {
        if((!s_email.equals(""))&&(!s_pass.equals("")))
            return true;
        else
            return false;
    }
}
