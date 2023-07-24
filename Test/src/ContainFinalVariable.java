/*
    @author: Dinh Quang Anh
    Date   : 7/20/2023
    Project: Test
*/
public class ContainFinalVariable {

    final String value;

    private String nonFinal;

    static final int test = 10;

    public ContainFinalVariable(String value) {
        this.value = value;
    }

    public ContainFinalVariable(String value, String nonFinal) {
        this.value = value;
        this.nonFinal = nonFinal;
    }

    public String getValue() {
        return value;
    }

    public String getNonFinal() {
        return nonFinal;
    }

    public void setNonFinal(String nonFinal) {
        this.nonFinal = nonFinal;
    }
}
