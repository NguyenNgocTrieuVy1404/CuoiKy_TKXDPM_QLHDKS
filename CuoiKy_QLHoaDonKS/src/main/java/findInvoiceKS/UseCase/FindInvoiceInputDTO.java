package findInvoiceKS.UseCase;

public class FindInvoiceInputDTO {
    private String luaChon;
    private String giaTri;

    public FindInvoiceInputDTO(String luaChon, String giaTri) {
        this.luaChon = luaChon;
        this.giaTri = giaTri;
    }

    public String getLuaChon() {
        return luaChon;
    }

    public String getGiaTri() {
        return giaTri;
    }

}
