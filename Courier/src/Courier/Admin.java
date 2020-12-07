package Courier;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.ParseException;

public class Admin {
    private SimpleIntegerProperty id_office;
    private SimpleStringProperty location_office;
    private SimpleObjectProperty<Timestamp> open_office;
    private SimpleObjectProperty<Timestamp> close_office;
    private SimpleIntegerProperty id_courier;
    private SimpleStringProperty egn_courier;
    private SimpleStringProperty name_courier;
    private SimpleStringProperty phone_courier;
    public Admin()
    {
            this.id_office=new SimpleIntegerProperty();
            this.location_office=new SimpleStringProperty();
            this.open_office = new SimpleObjectProperty<>();
            this.close_office = new SimpleObjectProperty<>();
            this.id_courier = new SimpleIntegerProperty();
            this.egn_courier=new SimpleStringProperty();
            this.name_courier=new SimpleStringProperty();
            this.phone_courier=new SimpleStringProperty();
    }
    public String getNameCourier()
    {

        return name_courier.get();
    }
    public void setNameCourier(String nameCourier)
    {
        this.name_courier.set(nameCourier);
    }
    public StringProperty NameCourierProperty()
    {
        return name_courier;
    }
    public String getLocationOffice()
    {

        return location_office.get();
    }
    public void setLocationOffice(String locationOffice)
    {
        this.location_office.set(locationOffice);
    }
    public StringProperty LocationOfficeProperty()
    {
        return location_office;
    }
    public int getOfficeId()
    {

        return id_office.get();
    }
    public void setOfficeId(int officeid)
    {
        this.id_office.set(officeid);
    }
    public IntegerProperty OfficeIdProperty()
    {
        return id_office;
    }
    public int getCourierId()
    {

        return id_courier.get();
    }
    public void setCourierId(int courierId)
    {
        this.id_courier.set(courierId);
    }
    public IntegerProperty CourierIdProperty()
    {
        return id_courier;
    }
    public String getPhoneCourier()
    {

        return phone_courier.get();
    }
    public void setPhoneCourier(String phoneCourier)
    {
        this.phone_courier.set(phoneCourier);
    }
    public StringProperty PhoneCourierProperty()
    {
        return phone_courier;
    }
    public String getEgnCourier()
    {

        return egn_courier.get();
    }
    public void setEgnCourier(String egncourier)
    {
        this.egn_courier.set(egncourier);
    }
    public StringProperty EgnCourierProperty()
    {
        return egn_courier;
    }
    public Object getOpenTime(){
        return open_office.get();
    }

    public void setOpenOffice(Timestamp openoffice) throws ParseException {
         /*SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
         String open=dateFormat.format(openoffice);
        Date dateopen=null;
        dateopen=  dateFormat.parse(open);
        Timestamp ts=new Timestamp(dateopen.getTime());*/
        this.open_office.setValue(openoffice);
    }

    public SimpleObjectProperty<Timestamp> OpenOfficeProperty(){
        return open_office;
    }
    public Object getCloseTime(){
        return close_office.get();
    }

    public void setCloseOffice(Timestamp closeoffice) throws ParseException {
     /*   SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
        String open=dateFormat.format(closeoffice);
        Date dateclose=null;
        dateclose=  dateFormat.parse(open);
        Timestamp ts=new Timestamp(dateopen.getTime());*/
        this.close_office.setValue(closeoffice);
    }

    public SimpleObjectProperty<Timestamp> CloseOfficeProperty(){
        return close_office;
    }
}
