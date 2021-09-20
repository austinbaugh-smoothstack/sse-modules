package lms.domain;

public class Publisher {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    
    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(final String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }
}
