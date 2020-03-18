package com.app.ecom.store.enums;

public enum ErrorCode {
	ERR000001("ERR000001"),
	ERR000002("ERR000002"),
	ERR000003("ERR000003"),
	ERR000004("ERR000004"),
	ERR000005("ERR000005"),
	ERR000006("ERR000006"),
	ERR000007("ERR000007"),
	ERR000008("ERR000008"),
	ERR000009("ERR000009"),
	ERR000010("ERR000010"),
	ERR000011("ERR000011"),
	ERR000012("ERR000012"),
	ERR000013("ERR000013"),
	ERR000014("ERR000014"),
	ERR000015("ERR000015"),
	ERR000016("ERR000016"),
	ERR000017("ERR000017"),
	ERR000018("ERR000018"), 
	ERR000019("ERR000019"),
	ERR000020("ERR000020"),
	ERR000021("ERR000021"),
	ERR000022("ERR000022"),
	ERR000023("ERR000023"),
	ERR000024("ERR000024"),
	ERR000025("ERR000025"),
	ERR000026("ERR000026"),
	ERR000027("ERR000027"),
	ERR000028("ERR000028"),
	ERR000029("ERR000029"),
	ERR000030("ERR000030");
	
    String code;

    private ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
