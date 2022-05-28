package hello.hellospring.controller;

public class MemberForm {
    //Form의 name이랑 이 변수 이름이랑 매칭 아님. getter setter이름이랑 매칭임
    private String form_name1234;


    public String getForm_name1234() {
        return form_name1234;
    }

    public void setForm_name1234(String name) {
        this.form_name1234 = name;
    }
}
