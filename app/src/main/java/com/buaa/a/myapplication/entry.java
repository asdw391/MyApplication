package com.buaa.a.myapplication;

public class entry {
    private String _ID;
    private String DATE;
    private String MONEY;
    private String REMARKS;
    private String TYPE;

    public entry(String id,String data,String money,String remark,String tpye) {
        this._ID=id;
        this.DATE=data;
        this.MONEY=money;
        this.REMARKS=remark;
        this.TYPE=tpye;
    }

    public String get_content(){
        return "日期："+DATE+"\n"+"金额："+MONEY+"\n";
    }

    public String get_ID() {
        return _ID;
    }

    public String getDATE() {
        return DATE;
    }

    public String getMONEY() {
        return MONEY;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public void setMONEY(String MONEY) {
        this.MONEY = MONEY;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
