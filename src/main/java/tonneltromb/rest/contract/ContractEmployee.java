package tonneltromb.rest.contract;

import java.util.Date;

public class ContractEmployee {

    private Integer id;
    private String name;
    private String lastName;
    private Integer positionId;
    private String pass;
    private Date dateOfEmployment;

    public ContractEmployee() {}

    public ContractEmployee(String name,
                            String lastName,
                            int positionId,
                            String pass,
                            long dateOfEmployment) {
        this.name = name;
        this.lastName = lastName;
        this.positionId = positionId;
        this.pass = pass;
        this.dateOfEmployment = new Date(dateOfEmployment);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(long dateOfEmployment) {
        this.dateOfEmployment = new Date(dateOfEmployment);
    }

    @Override
    public String toString() {
        return "ContractEmployee{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", positionId=" + positionId +
                ", pass='" + pass + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                '}';
    }
}
