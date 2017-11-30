package tonneltromb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee implements Comparable<Employee>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pos_id")
    private int positionId;

    @Column(name = "pass")
    private String pass;

    @Temporal(TemporalType.DATE)
    @Column(name = "works_from")
    private Date dateOfEmployment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_id", insertable = false, updatable = false)
    private Position position;

    public Employee() {
    }

    public Employee(String name, String lastName, int positionId, String pass,
                    String dateOfEmployment) throws ParseException {
        this.name = name;
        this.lastName = lastName;
        this.positionId = positionId;
        this.pass = pass;
        this.dateOfEmployment = new SimpleDateFormat("yyyy-mm-dd")
                .parse(dateOfEmployment);
    }

    public Employee(String name, String lastName, String pass,int positionId,
                    Date dateOfEmployment) {
        this.name = name;
        this.lastName = lastName;
        this.pass = pass;
        this.dateOfEmployment = dateOfEmployment;
        this.positionId = positionId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position.getValue();
    }

    public int getPositionId() {
        return positionId;
    }

    public String getPass() {
        return pass;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment.toString();
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        Employee employee = (Employee) that;

        if (id != employee.id) return false;
        if (!pass.equals(employee.pass)) return false;
        if (!dateOfEmployment.equals(employee.dateOfEmployment)) return false;
        if (!lastName.equalsIgnoreCase(employee.lastName)) return false;
        if (!name.equalsIgnoreCase(employee.name)) return false;

        return position.equals(employee.position);
    }

    @Override
    public int hashCode() {
        int result = id + position.hashCode() + name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + dateOfEmployment.hashCode();
        return result;
    }

    @Override
    public int compareTo(Employee that) {
        int result = lastName.compareTo(that.lastName);
        return result != 0 ? result : name.compareTo(that.name);
    }

    @Override
    public String toString() {
        return "Сотрудник: " + lastName + " " + name +
                ", номер пропуска: " + pass +
                ", занимаемая должность: " + position.toString() +
                ", дата трудоустройства(год - мес - день): " + dateOfEmployment;
    }

}

